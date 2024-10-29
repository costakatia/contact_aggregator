package org.aggregator.controller.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.model.Contact;
import org.model.ContactResponse;
import org.service.ContactService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ContactServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contactService = new ContactService(restTemplate, objectMapper, "http://mock-api.com/contacts", "mock-token");
    }

    @Test
    void getAllContacts_shouldReturnListOfContacts() throws IOException {
        // Mock the JSON response as a string
        String jsonResponse = """
            {
                "contacts": [
                    {"id": 1, "name": "jmadsen", "email": "jmadsen@kenect.com", "created_at": "2020-06-25T02:29:23.755Z", "updated_at": "2020-06-25T02:29:23.755Z"},
                    {"id": 2, "name": "Jalisa Quigley", "email": "jalisa@example.com", "created_at": "2020-06-25T02:31:51.233Z", "updated_at": "2020-06-25T02:31:51.233Z"}
                ]
            }
        """;

        // Mock the deserialization process
        ContactResponse mockResponse = new ContactResponse();
        mockResponse.setContacts(List.of(
                new Contact(1, "jmadsen", "jmadsen@kenect.com", LocalDateTime.now(),  LocalDateTime.now()),
                new Contact(2, "Jalisa Quigley", "jalisa@example.com",  LocalDateTime.now(),  LocalDateTime.now())
        ));

        // Configure o mock ObjectMapper para retornar o mockResponse
        when(objectMapper.readValue(jsonResponse, ContactResponse.class)).thenReturn(mockResponse);

        // Mock the response entity
        ResponseEntity<String> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(jsonResponse);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", "1");
        when(responseEntity.getHeaders()).thenReturn(headers);

        // Mock RestTemplate exchange method
        when(restTemplate.exchange(
                any(String.class),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class))
        ).thenReturn(responseEntity);

        // Execute the service method
        List<Contact> contacts = contactService.getAllContacts();

        // Validate the result
        assertEquals(2, contacts.size());
        assertEquals("jmadsen", contacts.get(0).getName());
        assertEquals("Jalisa Quigley", contacts.get(1).getName());
    }
}

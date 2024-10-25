package org.aggregator.controller.service;


import org.dto.ExternalContactResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.model.Contact;
import org.service.ContactService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private RestTemplate restTemplate;



    private static final String API_URL = "https://k-messages-api.herokuapp.com/api/v1/contacts";
    private static final String AUTH_TOKEN = "J7ybt6jv6pdJ4gyQP9gNonsY";


    @Test
    public void testGetAllContacts() {
        // Mock the response from the external API
        ExternalContactResponse mockResponse = new ExternalContactResponse();
        List<Contact> mockContacts = List.of (new Contact(2, "name", "teste@teste.com", "source", LocalDateTime.now(), LocalDateTime.now()));
        new Contact(2, "Name", "teste@teste.com", "source", LocalDateTime.now(), LocalDateTime.now());
        mockResponse.setContacts(mockContacts);

        ResponseEntity<ExternalContactResponse> responseEntity = ResponseEntity.ok(mockResponse);

        // Mock the RestTemplate exchange method
        when(restTemplate.exchange(
                eq(API_URL),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(ExternalContactResponse.class)
        )).thenReturn(responseEntity);

        // Call the method to test
        List<Contact> contacts = contactService.getAllContacts();

        // Verify the result
        assertEquals(1, contacts.size());
        assertEquals("name", contacts.get(0).getName());
        assertEquals("teste@teste.com", contacts.get(0).getEmail());
    }

    @Test
    public void testGetAllContactsEmptyResponse() {
        // Mock the response from the external API
        ExternalContactResponse mockResponse = new ExternalContactResponse();
        mockResponse.setContacts(List.of());

        ResponseEntity<ExternalContactResponse> responseEntity = ResponseEntity.ok(mockResponse);

        // Mock the RestTemplate exchange method
        when(restTemplate.exchange(
                eq(API_URL),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(ExternalContactResponse.class)
        )).thenReturn(responseEntity);

        // Call the method to test
        List<Contact> contacts = contactService.getAllContacts();

        // Verify the result
        assertEquals(0, contacts.size());
    }

    @Test
    public void testGetAllContactsNullResponse() {
        // Mock the response from the external API
        ResponseEntity<ExternalContactResponse> responseEntity = ResponseEntity.ok(null);

        // Mock the RestTemplate exchange method
        when(restTemplate.exchange(
                eq(API_URL),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(ExternalContactResponse.class)
        )).thenReturn(responseEntity);

        // Call the method to test
        List<Contact> contacts = contactService.getAllContacts();

        // Verify the result
        assertEquals(0, contacts.size());
    }
}
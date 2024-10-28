package org.aggregator.controller.service;

import org.dto.ContactDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.model.Contact;
import org.service.ContactService;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ContactService contactService;

    private Contact[] contactsPage1;
    private Contact[] contactsPage2;

    @BeforeEach
    public void setUp() {
        // Initialize mock data
        contactsPage1 = new Contact[]{
                new Contact(1, "John Doe", "john.doe@example.com", "2023-01-01T00:00:00Z", LocalDateTime.now(),LocalDateTime.now()),
                new Contact(2, "Jane Smith", "jane.smith@example.com", "2023-01-01T00:00:00Z", LocalDateTime.now(),LocalDateTime.now())
        };

        contactsPage2 = new Contact[]{
                new Contact(3, "Alice Johnson", "alice.johnson@example.com", "2023-01-01T00:00:00Z", LocalDateTime.now(),LocalDateTime.now())
        };
    }

    @Test
    public void testGetAllContacts() {
        // Mock the RestTemplate behavior
        when(restTemplate.getForObject(anyString(), eq(Contact[].class)))
                .thenReturn(contactsPage1)
                .thenReturn(contactsPage2)
                .thenReturn(new Contact[0]); // Simulate no more pages

        // Call the method under test
        List<ContactDTO> result = contactService.getAllContacts();

        // Verify the results
        assertEquals(3, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());
        assertEquals("Alice Johnson", result.get(2).getName());

        // Verify that the RestTemplate was called the expected number of times
        verify(restTemplate, times(3)).getForObject(anyString(), eq(Contact[].class));
    }

    @Test
    public void testGetAllContactsEmpty() {
        // Mock the RestTemplate behavior to return no contacts
        when(restTemplate.getForObject(anyString(), eq(Contact[].class)))
                .thenReturn(new Contact[0]);

        // Call the method under test
        List<ContactDTO> result = contactService.getAllContacts();

        // Verify the results
        assertEquals(0, result.size());

        // Verify that the RestTemplate was called once
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Contact[].class));
    }
}

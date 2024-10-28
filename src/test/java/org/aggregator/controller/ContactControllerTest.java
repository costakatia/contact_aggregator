package org.aggregator.controller;


import org.controller.ContactController;
import org.dto.ContactDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.service.ContactService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @MockBean
    private ContactService contactService;

    @InjectMocks
    private ContactController contactController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    @Test
    public void testGetAllContacts() throws Exception {
        // Arrange
        ContactDTO contact1 = new ContactDTO();
        contact1.setId(1);
        contact1.setName("John Doe");
        contact1.setEmail("john.doe@example.com");

        ContactDTO contact2 = new ContactDTO();
        contact2.setId(2);
        contact2.setName("Jane Doe");
        contact2.setEmail("jane.doe@example.com");

        List<ContactDTO> contacts = Arrays.asList(contact1, contact2);

        when(contactService.getAllContacts()).thenReturn(contacts);


    }
}

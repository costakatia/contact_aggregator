package org.aggregator.controller;


import org.controller.ContactController;
import org.junit.jupiter.api.Test;
import org.model.Contact;
import org.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;


    @Test
    void getAllContacts() throws Exception {
        Contact contact1 = new Contact(2, "name", "teste@teste.com", "source", LocalDateTime.now(), LocalDateTime.now());
        contact1.setId(1);
        contact1.setName("Mrs. Willian Bradtke");
        contact1.setEmail("jerold@example.net");
        contact1.setSource("KENECT_LABS");
        contact1.setCreatedAt(LocalDateTime.now());
        contact1.setUpdatedAt(LocalDateTime.now());

        Contact contact2 = new Contact(1, "name", "teste@teste.com", "source", LocalDateTime.now(), LocalDateTime.now());
        contact2.setId(2);
        contact2.setName("John Doe");
        contact2.setEmail("johndoe@example.net");
        contact2.setSource("KENECT_LABS");
        contact2.setCreatedAt(LocalDateTime.now());
        contact2.setUpdatedAt(LocalDateTime.now());

        when(contactService.getAllContacts()).thenReturn(Arrays.asList(contact1, contact2));

    }
}
package org.aggregator.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.controller.ContactController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.model.Contact;
import org.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Contact> mockContacts;

    @BeforeEach
    void setUp() {
        mockContacts = List.of(
                new Contact(1, "jmadsen", "jmadsen@kenect.com", LocalDateTime.now(), LocalDateTime.now()),
                new Contact(2, "Jalisa Quigley", "jalisa@example.com", LocalDateTime.now(), LocalDateTime.now())
        );
    }

    @Test
    void getAllContacts_ShouldReturnListOfContacts() throws Exception {
        // Mockando o comportamento do ContactService para retornar mockContacts
        when(contactService.getAllContacts()).thenReturn(mockContacts);

        // Fazendo a chamada para o endpoint e verificando a resposta
        mockMvc.perform(get("/contacts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockContacts)));
    }
}

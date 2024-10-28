package org.controller;


import org.dto.ContactDTO;
import org.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<ContactDTO> getAllContacts() {
        return  contactService.getAllContacts();

    }
}

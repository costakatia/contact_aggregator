package org.service;


import org.dto.ContactDTO;

import org.model.Contact;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    private final RestTemplate restTemplate;

    public ContactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ContactDTO> getAllContacts() {
        List<ContactDTO> contactDTOs = new ArrayList<>();
        int page = 1;
        boolean hasMorePages = true;

        while (hasMorePages) {
            // Call the external API
            String url = String.format("https://k-messages-api.herokuapp.com/api/v1/contacts?page=%d", page);
            Contact[] contacts = restTemplate.getForObject(url, Contact[].class);

            // Convert to DTOs and collect
            assert contacts != null;
            for (Contact contact : contacts) {
                ContactDTO dto = new ContactDTO(
                        contact.getId(),
                        contact.getName(),
                        contact.getEmail(),
                        "KENECT_LABS",
                        contact.getCreatedAt(),
                        contact.getUpdatedAt()
                );

                contactDTOs.add(dto);
            }

            // Check if there are more pages
            // (You would normally get this from the headers of the response)
            // Simulating with a static condition for this example
            hasMorePages = (contacts.length > 0);
            page++;
        }

        return contactDTOs;
    }
}
package org.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.model.Contact;
import org.model.ContactResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper; // Adicionamos o ObjectMapper
    private final String apiUrl;
    private final String apiToken;


    public ContactService(RestTemplate restTemplate,
                          ObjectMapper objectMapper, // Adicionado no construtor
                          @Value("${external.api.url}") String apiUrl,
                          @Value("${external.api.token}") String apiToken) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper; // Inicializamos o ObjectMapper
        this.apiUrl = apiUrl;
        this.apiToken = apiToken;
    }

    // Método para deserializar o JSON
    public ContactResponse deserializeJson(String json) throws IOException {
        return objectMapper.readValue(json, ContactResponse.class);
    }

    public List<Contact> getAllContacts() throws IOException {
        List<Contact> allContacts = new ArrayList<>();
        int currentPage = 1;
        int totalPages;

        do {
            // Build URL with page parameter
            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(apiUrl)
                    .queryParam("page", currentPage);

            // Set headers for authentication
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Make the API call and retrieve the data
            var responseEntity = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            // Deserialize JSON response
            String jsonResponse = responseEntity.getBody();
            ContactResponse contactResponse = deserializeJson(jsonResponse);

            // Get contacts from the response
            List<Contact> contactsFromPage = contactResponse.getContacts();
            if (contactsFromPage != null) {
                allContacts.addAll(contactsFromPage);
            }

            // Get pagination info from headers
            HttpHeaders responseHeaders = responseEntity.getHeaders();
            totalPages = Integer.parseInt(responseHeaders.get("Total-Pages").get(0));
            currentPage++;

        } while (currentPage <= totalPages);

        return allContacts;
    }
}

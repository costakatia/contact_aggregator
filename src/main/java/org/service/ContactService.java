package org.service;


import org.dto.ExternalContactResponse;
import org.model.Contact;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class ContactService {

   /* RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "https://k-messages-api.herokuapp.com/api/v1/contacts";
    private static final String AUTH_TOKEN = "J7ybt6jv6pdJ4gyQP9gNonsY";

    public List<Contact> getAllContacts() {


        // Criar headers com o token de autenticação
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);

        // Construir a requisição HTTP
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Fazer a chamada GET com headers
        ResponseEntity<ExternalContactResponse> response = restTemplate.exchange(
                API_URL, HttpMethod.GET, entity, ExternalContactResponse.class);

        // Retornar os contatos da resposta
        return response.getBody() != null ? response.getBody().getContacts() : List.of();
    }
}*/

    public List<Contact> getAllContacts() {

        RestTemplate restTemplate = new RestTemplate();
        final String API_URL = "https://k-messages-api.herokuapp.com/api/v1/contacts";
        final String AUTH_TOKEN = "J7ybt6jv6pdJ4gyQP9gNonsY";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(AUTH_TOKEN);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<ExternalContactResponse> response = restTemplate.exchange(
                    API_URL, HttpMethod.GET, entity, ExternalContactResponse.class
            );

            return response.getBody() != null ? response.getBody().getContacts() : List.of();
        } catch (Exception e) {
            e.printStackTrace(); // Log do erro para inspeção
            return List.of(); // Retorna uma lista vazia em caso de erro
        }
    }
}
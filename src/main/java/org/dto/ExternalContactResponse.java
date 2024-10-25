package org.dto;

import lombok.Getter;
import lombok.Setter;
import org.model.Contact;

import java.util.List;

@Setter
@Getter
public class ExternalContactResponse {

    // Getters e setters
    private List<Contact> contacts;
    private Pagination pagination;

    @Setter
    @Getter
    // Classe interna para lidar com a paginação
    public static class Pagination {
        private int currentPage;
        private int totalPages;


    }
}
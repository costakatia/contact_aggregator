package org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private int id;
    private String name;
    private String email;
    private String source;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
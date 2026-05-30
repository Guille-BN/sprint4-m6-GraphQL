package com.davinchicoder.springgraphql.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/** Entity: representa el modelo interno/persistente (BD) con identidad y estado;
 * DTO: objeto ligero para transferir datos entre capas sin exponer la entidad ni su lógica. */
@Data
@Builder
public class Post {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private String commonName;
    private String scientificName;
    private String gender;
    private String diet;
    private Boolean extant;
    private String genus;
    private String type;

}

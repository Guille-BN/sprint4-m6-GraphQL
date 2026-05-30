package com.davinchicoder.springgraphql.dto;

import lombok.Data;

/** DTO representing combined filter criteria for posts. */
@Data
public class PostFilter {
    private String type;
    private String gender;
    private String diet;
    private Boolean extant;
}

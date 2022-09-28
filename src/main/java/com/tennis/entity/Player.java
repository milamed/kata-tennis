package com.tennis.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@Document
public class Player {

    @Id
    private String id;

    @NotBlank(message = "firstName field is required")
    private String firstName;

    @NotBlank(message = "lastName field is required")
    private String lastName;

    @NotBlank(message = "nationality field is required")
    private String nationality;

    @NotBlank(message = "age field is required")
    private int age;
}

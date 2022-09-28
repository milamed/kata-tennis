package com.tennis.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@Document
public class Match {

    @Id
    private String id;

    @NotBlank(message = "name field is required")
    private String name;

    private LocalDateTime matchDate;

    @DBRef
    @NotBlank(message = "referee field is required")
    private Referee referee;

    @DBRef
    @NotBlank(message = "player1 field is required")
    private Player player1;

    @DBRef
    @NotBlank(message = "player2 field is required")
    private Player player2;
}

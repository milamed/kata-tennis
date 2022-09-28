package com.tennis.entity;

import com.tennis.utils.GameStatus;
import com.tennis.utils.SetScore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@Document
public class Set {

    @Id
    private String id;

    @DBRef
    @NotBlank(message = "match field is required")
    private Match match;

    private SetScore player1SetScore;
    private SetScore player2SetScore;
    private GameStatus gameStatus;
}

package com.tennis.entity;

import com.tennis.utils.GameScore;
import com.tennis.utils.GameStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@Document
public class Game {
    @Id
    private String id;

    @DBRef
    @NotBlank(message = "set field is required")
    private Set set;

    private GameScore player1Score;

    private GameScore player2Score;

    private GameStatus gameStatus;
}

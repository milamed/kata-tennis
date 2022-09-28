package com.tennis.controller;


import com.tennis.entity.Game;
import com.tennis.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/{idSet}")
    public ResponseEntity<Object> startGame(@PathVariable String idSet) {
        try {
            Game game = gameService.startGame(idSet);
            return new ResponseEntity<>(game, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{idGame}/add-point-to-player-1")
    public ResponseEntity<Object> addPointToPlayer1(@PathVariable String idGame) {
        try {
            Game game = gameService.addPointPlayer1(idGame);
            return new ResponseEntity<>(game, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{idGame}/add-point-to-player-2")
    public ResponseEntity<Object> addPointToPlayer2(@PathVariable String idGame) {
        try {
            Game game = gameService.addPointPlayer2(idGame);
            return new ResponseEntity<>(game, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

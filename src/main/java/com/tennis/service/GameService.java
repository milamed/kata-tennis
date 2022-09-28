package com.tennis.service;

import com.tennis.entity.Game;

public interface GameService {

    Game startGame(String idSet);
    Game addPointPlayer1(String gameId);
    Game addPointPlayer2(String gameId) ;
}

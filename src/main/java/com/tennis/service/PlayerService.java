package com.tennis.service;

import com.tennis.entity.Player;

import java.util.List;

public interface PlayerService {

    Player createPlayer(Player player);
    List<Player> getAllPlayers();
}

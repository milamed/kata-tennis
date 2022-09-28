package com.tennis.service;

import com.tennis.entity.Game;
import com.tennis.entity.Set;

import java.util.Optional;

public interface SetService {

    Set startSet(String matchId);
    void addSetPoint(Game game);
    boolean isSetInProgress(Set set);
    Optional<Set> getSet(String setId);
}

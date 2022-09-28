package com.tennis.service;

import com.tennis.entity.Match;

import java.util.List;
import java.util.Optional;

public interface MatchService {
    Match createMatch(Match match);
    List<Match> getAllMatches();
    Optional<Match> getMatch(String idMatch);
}

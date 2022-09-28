package com.tennis.service.impl;

import com.tennis.entity.Match;
import com.tennis.repository.MatchRepository;
import com.tennis.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Override
    public Match createMatch(Match match) {
        match.setMatchDate(LocalDateTime.now());
        return matchRepository.save(match);
    }

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Optional<Match> getMatch(String idMatch) {
        return matchRepository.findById(idMatch);
    }
}

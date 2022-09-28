package com.tennis.service.impl;

import com.tennis.entity.Game;
import com.tennis.entity.Match;
import com.tennis.entity.Set;
import com.tennis.repository.SetRepository;
import com.tennis.service.MatchService;
import com.tennis.service.SetService;
import com.tennis.utils.GameStatus;
import com.tennis.utils.SetScore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SetServiceImpl implements SetService {

    private final SetRepository setRepository;
    private final MatchService matchService;

    @Override
    public Set startSet(String matchId) {
        Optional<Match> match = matchService.getMatch(matchId);
        // Check if match exist
        if(match.isPresent()) {
            Set set = Set.builder()
                    .match(match.get())
                    .player1SetScore(SetScore.ZERO)
                    .player2SetScore(SetScore.ZERO)
                    .gameStatus(GameStatus.IN_PROGRESS)
                    .build();
            return setRepository.save(set);
        }
        throw new IllegalArgumentException("This match does not exist");
    }

    @Override
    public void addSetPoint(Game game) {
        Set set = game.getSet();
        if(GameStatus.PLAYER_1_WON.equals(game.getGameStatus())) {
            checkPlayer1SetPoint(set);
        }
        if(GameStatus.PLAYER_2_WON.equals(game.getGameStatus())) {
            checkPlayer2SetPoint(set);
        }
        setRepository.save(set);
    }

    @Override
    public Optional<Set> getSet(String setId) {
        return setRepository.findById(setId);
    }

    private void checkPlayer1SetPoint(Set set) {
        if (isSetPoint(set.getPlayer1SetScore(), set.getPlayer2SetScore())) {
            set.setGameStatus(GameStatus.PLAYER_1_WON);
        }
        SetScore actualScore = incrementSetPoint(set.getPlayer1SetScore());
        set.setPlayer1SetScore(actualScore);
    }

    private void checkPlayer2SetPoint(Set set) {
            if(isSetPoint(set.getPlayer2SetScore(), set.getPlayer1SetScore())) {
                set.setGameStatus(GameStatus.PLAYER_2_WON);
            }
            SetScore actualScore = incrementSetPoint(set.getPlayer2SetScore());
            set.setPlayer2SetScore(actualScore);
    }

    private SetScore incrementSetPoint(SetScore setScore) {
        return SetScore.values()[setScore.ordinal() + 1];
    }

    private boolean isSetPoint(SetScore playerScore, SetScore opponent) {
        return (SetScore.FIVE.equals(playerScore) && !SetScore.FIVE.equals(opponent))
                || (SetScore.SIX.equals(playerScore)
        );
    }

    public boolean isSetInProgress(Set set) {
        return GameStatus.IN_PROGRESS.equals(set.getGameStatus());
    }
}

package com.tennis.service.impl;

import com.tennis.entity.Game;
import com.tennis.entity.Set;
import com.tennis.repository.GameRepository;
import com.tennis.service.GameService;
import com.tennis.service.SetService;
import com.tennis.utils.GameScore;
import com.tennis.utils.GameStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private static final String PLAYER_1 = "P1";
    private static final String PLAYER_2 = "P2";

    private final SetService setService;
    private final GameRepository gameRepository;

    @Override
    public Game startGame(String idSet) {
        Optional<Set> set = setService.getSet(idSet);

        // Check if set exist
        if(set.isPresent()) {
            // Check if the given set is not finished
            if(setService.isSetInProgress(set.get())) {
                // TODO check id we have another game in progress
                Game game = buildGame(set.get());
                return gameRepository.save(game);
            }
            throw new IllegalArgumentException("This Set does not exist");
        }
       throw new IllegalArgumentException("This Set does not exist");
    }

    @Override
    public Game addPointPlayer1(String gameId) {
        return addPointToPlayer(gameId, PLAYER_1);
    }

    @Override
    public Game addPointPlayer2(String gameId) {
        return addPointToPlayer(gameId, PLAYER_2);
    }

    private Game addPointToPlayer(String gameId, String player)  {
        Optional<Game> game = gameRepository.findById(gameId);
        if(game.isPresent()) {
            if(isGameInProgress(game.get())) {
                Game updatedScoreGame = addPoint(game.get(), player);
                Game savedGame = gameRepository.save(updatedScoreGame);
                setService.addSetPoint(savedGame);
                return savedGame;
            }
            throw new IllegalArgumentException("Game is finished. You should start new game");
        }
        throw new IllegalArgumentException("This game does not exist");
    }

    private boolean isGameInProgress(Game game) {
        return !GameStatus.PLAYER_1_WON.equals(game.getGameStatus())
                && !GameStatus.PLAYER_2_WON.equals(game.getGameStatus());
    }

    private boolean isAdvantage(Game game) {
        return GameScore.ADVANTAGE.equals(game.getPlayer1Score())
                || GameScore.ADVANTAGE.equals(game.getPlayer2Score());
    }

    private boolean isGamePoint(GameScore playerScore, GameScore opponentScore) {
        return GameScore.ADVANTAGE.equals(playerScore)
                || (GameScore.FORTY.equals(playerScore)
                    && ( !GameScore.FORTY.equals(opponentScore)
                            && !GameScore.ADVANTAGE.equals(opponentScore)
                    ));
    }

    private Game addPoint(Game game, String player) {

        if(PLAYER_1.equals(player)) {
            if(isGamePoint(game.getPlayer1Score(), game.getPlayer2Score())) {
                game.setGameStatus(GameStatus.PLAYER_1_WON);
            } else if(isAdvantage(game)) {
                Pair<GameScore, GameScore> scorePair = incrementAdvantage(Pair.of(game.getPlayer1Score(), game.getPlayer2Score()));
                setPlayersScore(scorePair.getFirst(), scorePair.getSecond(), game);
            } else {
                game.setPlayer1Score(incrementScore(game.getPlayer1Score()));
            }
        } if(PLAYER_2.equals(player)) {
            if(isGamePoint(game.getPlayer2Score(), game.getPlayer1Score())) {
                game.setGameStatus(GameStatus.PLAYER_2_WON);
            } else if(isAdvantage(game)) {
                Pair<GameScore, GameScore> scorePair = incrementAdvantage(Pair.of(game.getPlayer2Score(), game.getPlayer1Score()));
                setPlayersScore(scorePair.getSecond(), scorePair.getFirst(), game);
            } else {
                game.setPlayer2Score(incrementScore(game.getPlayer2Score()));
            }
        }
        return game;
    }

    private void setPlayersScore(GameScore scorePlayer1, GameScore scorePlayer2, Game game) {
        game.setPlayer1Score(scorePlayer1);
        game.setPlayer2Score(scorePlayer2);
    }
    private GameScore incrementScore(GameScore point) {
        return GameScore.values()[point.ordinal() + 1];
    }

    private Pair<GameScore, GameScore> incrementAdvantage(Pair<GameScore, GameScore> score) {
        if(GameScore.ADVANTAGE.equals(score.getSecond())) {
            return Pair.of(score.getFirst(), GameScore.FORTY);
        }
        return score;
    }

    private Game buildGame(Set set) {
        return Game.builder()
        .set(set)
        .player1Score(GameScore.ZERO)
        .player2Score(GameScore.ZERO)
        .gameStatus(GameStatus.IN_PROGRESS)
        .build();
    }
}

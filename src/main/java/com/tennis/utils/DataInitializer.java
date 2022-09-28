package com.tennis.utils;

import com.tennis.entity.Player;
import com.tennis.entity.Referee;
import com.tennis.service.PlayerService;
import com.tennis.service.RefereeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements ApplicationRunner {

    private final PlayerService playerService;
    private final RefereeService refereeService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initiatePlayerDB();
        initiateRefereeDB();
    }

    public void initiatePlayerDB() {
        if (isEmptyPlayerDB()) {
            log.info("initiate player database");
            Player player1 = buildPlayer("Ons", "Jabeur", "Tunisia", 28);
            Player player2 = buildPlayer("Iga", "Swiatek", "Poland", 21);
            playerService.createPlayer(player1);
            playerService.createPlayer(player2);
        }
    }

    public void initiateRefereeDB() {
        if (isEmptyRefereeDB()) {
            log.info("initiate referee database");
            Referee referee = buildReferee("Marijana", "Veljovic");
            refereeService.createReferee(referee);
        }
    }

    private Player buildPlayer(String firstName, String lastName, String nationality, int age) {
        return Player.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .nationality(nationality)
                .build();
    }

    private Referee buildReferee(String firstName, String lastName) {
        return Referee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    private boolean isEmptyPlayerDB() {
        return playerService.getAllPlayers().size() == 0;
    }

    private boolean isEmptyRefereeDB() {
        return refereeService.getAllReferees().size() == 0;
    }

}

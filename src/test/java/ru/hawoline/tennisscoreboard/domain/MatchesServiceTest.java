package ru.hawoline.tennisscoreboard.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hawoline.tennisscoreboard.domain.model.Match;
import ru.hawoline.tennisscoreboard.domain.model.GameStage;
import ru.hawoline.tennisscoreboard.domain.model.Player;

import static org.junit.jupiter.api.Assertions.*;

class MatchesServiceTest {
    private CurrentMatchService currentMatchService = new CurrentMatchService();

    @BeforeEach
    public void init() {
        currentMatchService.newMatch("123", new Match(
                new Player("Belikto"),
                new Player("Bayarto")
        ));
    }
    @Test
    public void test() throws PlayerNotFoundException {


        currentMatchService.addPoint("123", "Belikto");
        Match match = currentMatchService.getCurrentMatches().get("123");
        assertEquals(1, match.getFirstPlayer().getScore());
    }

    @Test
    public void zeroGameWhenScore40_40() throws PlayerNotFoundException {

        currentMatchService.addPoint("123", "Belikto");
        currentMatchService.addPoint("123", "Belikto");
        currentMatchService.addPoint("123", "Belikto");
        currentMatchService.addPoint("123", "Bayarto");
        currentMatchService.addPoint("123", "Bayarto");
        currentMatchService.addPoint("123", "Bayarto");
        currentMatchService.addPoint("123", "Belikto");

        assertEquals(0, currentMatchService.getCurrentMatches().get("123").getFirstPlayer().getGames());
    }

    @Test
    public void tieBreak() throws PlayerNotFoundException {

        for (int i = 0; i < 20; i++) {
            currentMatchService.addPoint("123", "Belikto");
        }
        for (int i = 0; i < 24; i++) {
            currentMatchService.addPoint("123", "Bayarto");
        }
        currentMatchService.addPoint("123", "Belikto");
        currentMatchService.addPoint("123", "Belikto");
        currentMatchService.addPoint("123", "Belikto");
        currentMatchService.addPoint("123", "Belikto");
        assertEquals(GameStage.TIE_BREAK, currentMatchService.getCurrentMatches().get("123").getGameStage());
        currentMatchService.addPoint("123", "Belikto");
        currentMatchService.addPoint("123", "Belikto");
        currentMatchService.addPoint("123", "Belikto");
        currentMatchService.addPoint("123", "Belikto");
        currentMatchService.addPoint("123", "Belikto");
        currentMatchService.addPoint("123", "Belikto");
        assertEquals(GameStage.TIE_BREAK, currentMatchService.getCurrentMatches().get("123").getGameStage());
        currentMatchService.addPoint("123", "Belikto");
        assertEquals(GameStage.GAME, currentMatchService.getCurrentMatches().get("123").getGameStage());

    }
}
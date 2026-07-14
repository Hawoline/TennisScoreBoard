package ru.hawoline.tennisscoreboard.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hawoline.tennisscoreboard.domain.model.Game;
import ru.hawoline.tennisscoreboard.domain.model.GameStage;
import ru.hawoline.tennisscoreboard.domain.model.Player;

import static org.junit.jupiter.api.Assertions.*;

class ScoreCalculatorTest {
    private final ScoreCalculator scoreCalculator = new ScoreCalculator();
    @Test
    public void zeroGameWhenScore40_40() {
        Player player0 = new Player();
        Player player1 = new Player();

        scoreCalculator.win(player0, player1);
        scoreCalculator.win(player0, player1);
        scoreCalculator.win(player0, player1);
        scoreCalculator.win(player1, player0);
        scoreCalculator.win(player1, player0);
        scoreCalculator.win(player1, player0);
        scoreCalculator.win(player0, player1);

        assertEquals(0, player0.getGameScore());
    }

    @Test
    public void tieBreak() {
        scoreCalculator.setGameStage(GameStage.GAME);
        Player player0 = new Player();
        Player player1 = new Player();

        for (int i = 0; i < 20; i++) {
            scoreCalculator.win(player0, player1);
        }
        for (int i = 0; i < 24; i++) {
            scoreCalculator.win(player1, player0);
        }
        scoreCalculator.win(player0, player1);
        scoreCalculator.win(player0, player1);
        scoreCalculator.win(player0, player1);
        scoreCalculator.win(player0, player1);
        assertEquals(GameStage.TIE_BREAK, scoreCalculator.getGameStage());
        scoreCalculator.win(player0, player1);
        scoreCalculator.win(player0, player1);
        scoreCalculator.win(player0, player1);
        scoreCalculator.win(player0, player1);
        scoreCalculator.win(player0, player1);
        scoreCalculator.win(player0, player1);
        assertEquals(GameStage.TIE_BREAK, scoreCalculator.getGameStage());
        scoreCalculator.win(player0, player1);
        assertEquals(GameStage.GAME, scoreCalculator.getGameStage());

    }
}
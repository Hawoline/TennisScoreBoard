package ru.hawoline.tennisscoreboard.domain.service;

import org.junit.jupiter.api.Test;
import ru.hawoline.tennisscoreboard.domain.model.Game;
import ru.hawoline.tennisscoreboard.domain.model.GameSet;
import ru.hawoline.tennisscoreboard.domain.model.Match;
import ru.hawoline.tennisscoreboard.domain.model.Player;

import java.util.List;

class GameServiceTest {

    @Test
    public void test() {
        GameService gameService = new GameService();
        gameService.winFirst();
        gameService.winSecond();
        gameService.winFirst();
        gameService.winFirst();
        Player firstPlayer = new Player();
        Player secondPlayer = new Player();
        List<Match> matches = gameService.getMatches();
        List<GameSet> gameSets = matches.getFirst().getGameSets();
        List<Game> games = gameSets.getFirst().getGames();
    }
}
package ru.hawoline.tennisscoreboard.domain;

import ru.hawoline.tennisscoreboard.domain.model.Match;
import ru.hawoline.tennisscoreboard.domain.model.GameStage;
import ru.hawoline.tennisscoreboard.domain.model.Player;

public class ScoreCalculator {

    public void win(Match match, int winPlayerId) {
        Player winPlayer;
        Player losePlayer;
        if (winPlayerId == 0) {
            winPlayer = match.getFirstPlayer();
            losePlayer = match.getSecondPlayer();
        } else {
            winPlayer = match.getSecondPlayer();
            losePlayer = match.getFirstPlayer();
        }
        if (match.getGameStage() == GameStage.END) {
            return;
        }
        winPlayer.winScore();
        if (match.getGameStage() == GameStage.TIE_BREAK && winPlayer.getScore() > 6 && hasSuperiority(winPlayer, losePlayer)) {
            match.setGameStage(GameStage.GAME);
            winSet(winPlayer, losePlayer, match);
        } else if (match.getGameStage() == GameStage.GAME && winPlayer.getScore() > 3 && hasSuperiority(winPlayer, losePlayer)) {
            winPlayer.winGame();
            winPlayer.setScore(0);
            losePlayer.setScore(0);
            if (winPlayer.getGames() > 5) {
                if (losePlayer.getGames() == 6) {
                    match.setGameStage(GameStage.TIE_BREAK);
                    winPlayer.setScore(0);
                    losePlayer.setScore(0);
                } else if (winPlayer.getGames() > losePlayer.getGames() + 1 || winPlayer.getGames() == 7) {
                    winSet(winPlayer, losePlayer, match);
                }
            }
        }
    }

    private static boolean hasSuperiority(Player winPlayer, Player losePlayer) {
        return winPlayer.getScore() > losePlayer.getScore() + 1;
    }

    private void winSet(Player winPlayer, Player losePlayer, Match match) {
        winPlayer.winSet();
        winPlayer.setScore(0);
        winPlayer.setGames(0);
        losePlayer.setScore(0);
        losePlayer.setGames(0);
        if (winPlayer.getSets() > 1) {
            match.setGameStage(GameStage.END);
            match.setWinner(winPlayer);
        }
    }
}

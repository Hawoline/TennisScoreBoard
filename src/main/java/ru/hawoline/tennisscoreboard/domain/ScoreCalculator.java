package ru.hawoline.tennisscoreboard.domain;

import ru.hawoline.tennisscoreboard.domain.model.CurrentMatch;
import ru.hawoline.tennisscoreboard.domain.model.GameStage;
import ru.hawoline.tennisscoreboard.domain.model.Player;

public class ScoreCalculator {
    private Player winner;

    public void win(CurrentMatch currentMatch, int winPlayerId) {
        Player winPlayer;
        Player losePlayer;
        if (winPlayerId == 0) {
            winPlayer = currentMatch.getFirstPlayer();
            losePlayer = currentMatch.getSecondPlayer();
        } else {
            winPlayer = currentMatch.getSecondPlayer();
            losePlayer = currentMatch.getFirstPlayer();
        }
        if (currentMatch.getGameStage() == GameStage.END) {
            return;
        }
        winPlayer.winScore();
        if (currentMatch.getGameStage() == GameStage.TIE_BREAK && winPlayer.getScore() > 6 && hasSuperiority(winPlayer, losePlayer)) {
            currentMatch.setGameStage(GameStage.GAME);
            winSet(winPlayer, losePlayer, currentMatch);
        } else if (currentMatch.getGameStage() == GameStage.GAME && winPlayer.getScore() > 3 && hasSuperiority(winPlayer, losePlayer)) {
            winPlayer.winGame();
            winPlayer.setScore(0);
            losePlayer.setScore(0);
            if (winPlayer.getGames() > 5) {
                if (losePlayer.getGames() == 6) {
                    currentMatch.setGameStage(GameStage.TIE_BREAK);
                    winPlayer.setScore(0);
                    losePlayer.setScore(0);
                } else if (winPlayer.getGames() > losePlayer.getGames() + 1 || winPlayer.getGames() == 7) {
                    winSet(winPlayer, losePlayer, currentMatch);
                }
            }
        }
    }

    private static boolean hasSuperiority(Player winPlayer, Player losePlayer) {
        return winPlayer.getScore() > losePlayer.getScore() + 1;
    }

    private void winSet(Player winPlayer, Player losePlayer, CurrentMatch currentMatch) {
        winPlayer.winSet();
        winPlayer.setScore(0);
        winPlayer.setGames(0);
        losePlayer.setScore(0);
        losePlayer.setGames(0);
        if (winPlayer.getSets() > 1) {
            currentMatch.setGameStage(GameStage.END);
            winner = winPlayer;
        }
    }

    public Player getWinner() {
        return winner;
    }
}

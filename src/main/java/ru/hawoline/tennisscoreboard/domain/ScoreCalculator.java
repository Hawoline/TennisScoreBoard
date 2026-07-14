package ru.hawoline.tennisscoreboard.domain;

import ru.hawoline.tennisscoreboard.domain.model.CurrentScore;
import ru.hawoline.tennisscoreboard.domain.model.GameStage;
import ru.hawoline.tennisscoreboard.domain.model.Player;

public class ScoreCalculator {
    private GameStage gameStage = GameStage.GAME;
    private Player winner;

    public void win(Player winPlayer, Player losePlayer) {
        if (gameStage == GameStage.END) {
            return;
        }
        winPlayer.winScore();
        if (gameStage == GameStage.TIE_BREAK && winPlayer.getScore() > 6 && hasSuperiority(winPlayer, losePlayer)) {
            gameStage = GameStage.GAME;
            winSet(winPlayer, losePlayer);
        } else if (gameStage == GameStage.GAME && winPlayer.getScore() > 3 && hasSuperiority(winPlayer, losePlayer)) {
            winPlayer.winGame();
            winPlayer.setScore(0);
            losePlayer.setScore(0);
            if (winPlayer.getGameScore() > 5) {
                if (losePlayer.getGameScore() == 6) {
                    gameStage = GameStage.TIE_BREAK;
                    winPlayer.setScore(0);
                    losePlayer.setScore(0);
                } else if (winPlayer.getGameScore() > losePlayer.getGameScore() + 1 || winPlayer.getGameScore() == 7) {
                    winSet(winPlayer, losePlayer);
                }
            }
        }
    }

    private static boolean hasSuperiority(Player winPlayer, Player losePlayer) {
        return winPlayer.getScore() > losePlayer.getScore() + 1;
    }

    private void winSet(Player winPlayer, Player losePlayer) {
        winPlayer.winSet();
        winPlayer.setScore(0);
        winPlayer.setGameScore(0);
        losePlayer.setScore(0);
        losePlayer.setGameScore(0);
        if (winPlayer.getWinSets() > 1) {
            gameStage = GameStage.END;
            winner = winPlayer;
        }
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    public Player getWinner() {
        return winner;
    }
}

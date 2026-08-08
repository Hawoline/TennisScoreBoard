package ru.hawoline.tennisscoreboard.domain.model;

import ru.hawoline.tennisscoreboard.domain.exception.PlayerNotFoundException;

public class Match {
    private Player firstPlayer;
    private Player secondPlayer;
    private GameStage gameStage = GameStage.GAME;
    private Player winner;
    private Player winScorePlayer;
    private Player loseScorePlayer;

    public Match(Player firstPlayer, Player secondPlayer, Player winner) {
        this(firstPlayer, secondPlayer);
        this.winner = winner;
    }

    public Match(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }
    
    public void winScore(String playerName) throws PlayerNotFoundException {
        selectPlayers(playerName);
        if (gameStage == GameStage.END) {
            return;
        }
        winScorePlayer.winScore();
        if (gameStage == GameStage.TIE_BREAK && winScorePlayer.getScore() > 6 && hasSuperiority()) {
            gameStage = GameStage.GAME;
            winSet(winScorePlayer, loseScorePlayer);
        } else if (gameStage == GameStage.GAME && winScorePlayer.getScore() > 3 && hasSuperiority()) {
            winScorePlayer.winGame();
            loseScorePlayer.setScore(0);
            if (winScorePlayer.getGames() > 5) {
                if (loseScorePlayer.getGames() == 6) {
                    gameStage = GameStage.TIE_BREAK;
                    winScorePlayer.setScore(0);
                    loseScorePlayer.setScore(0);
                } else if (winScorePlayer.getGames() > loseScorePlayer.getGames() + 1 || winScorePlayer.getGames() == 7) {
                    winSet(winScorePlayer, loseScorePlayer);
                }
            }
        }
    }

    private void selectPlayers(String playerName) throws PlayerNotFoundException {
        if (firstPlayer.getName().equals(playerName)) {
            winScorePlayer = firstPlayer;
            loseScorePlayer = secondPlayer;
        } else if (secondPlayer.getName().equals(playerName)){
            winScorePlayer = secondPlayer;
            loseScorePlayer = firstPlayer;
        } else {
            throw new PlayerNotFoundException();
        }
    }

    private boolean hasSuperiority() {
        return winScorePlayer.getScore() > loseScorePlayer.getScore() + 1;
    }

    private void winSet(Player winPlayer, Player losePlayer) {
        winPlayer.winSet();
        losePlayer.newSet();
        if (winPlayer.getSets() > 1) {
            gameStage = GameStage.END;
            winner = winPlayer;
        }
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public Player getWinner() {
        return winner;
    }
}

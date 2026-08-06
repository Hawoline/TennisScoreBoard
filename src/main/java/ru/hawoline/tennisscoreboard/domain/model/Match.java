package ru.hawoline.tennisscoreboard.domain.model;

import ru.hawoline.tennisscoreboard.domain.exception.PlayerNotFoundException;

public class Match {
    private Player firstPlayer;
    private Player secondPlayer;
    private GameStage gameStage = GameStage.GAME;
    private Player winner;
    private Player winPlayer;
    private Player losePlayer;

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
        winPlayer.winScore();
        if (gameStage == GameStage.TIE_BREAK && winPlayer.getScore() > 6 && hasSuperiority()) {
            gameStage = GameStage.GAME;
            winSet(winPlayer, losePlayer);
        } else if (gameStage == GameStage.GAME && winPlayer.getScore() > 3 && hasSuperiority()) {
            winPlayer.winGame();
            losePlayer.setScore(0);
            if (winPlayer.getGames() > 5) {
                if (losePlayer.getGames() == 6) {
                    gameStage = GameStage.TIE_BREAK;
                    winPlayer.setScore(0);
                    losePlayer.setScore(0);
                } else if (winPlayer.getGames() > losePlayer.getGames() + 1 || winPlayer.getGames() == 7) {
                    winSet(winPlayer, losePlayer);
                }
            }
        }
    }

    private void selectPlayers(String playerName) throws PlayerNotFoundException {
        if (firstPlayer.getName().equals(playerName)) {
            winPlayer = firstPlayer;
            losePlayer = secondPlayer;
        } else if (secondPlayer.getName().equals(playerName)){
            winPlayer = secondPlayer;
            losePlayer = firstPlayer;
        } else {
            throw new PlayerNotFoundException();
        }
    }

    private boolean hasSuperiority() {
        return winPlayer.getScore() > losePlayer.getScore() + 1;
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

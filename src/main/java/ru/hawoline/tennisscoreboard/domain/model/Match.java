package ru.hawoline.tennisscoreboard.domain.model;

import java.util.Objects;

public class Match {
    private Player firstPlayer;
    private Player secondPlayer;
    private GameStage gameStage = GameStage.GAME;
    private Player winner;

    public Match(Player firstPlayer, Player secondPlayer, Player winner) {
        this(firstPlayer, secondPlayer);
        this.winner = winner;
    }

    public Match(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
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

    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}

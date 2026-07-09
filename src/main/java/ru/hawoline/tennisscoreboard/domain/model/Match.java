package ru.hawoline.tennisscoreboard.domain.model;

public class Match {
    private String firstPlayerName;
    private String secondPlayerName;

    public Match(String firstPlayerName, String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }

    public Match() {
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }
}

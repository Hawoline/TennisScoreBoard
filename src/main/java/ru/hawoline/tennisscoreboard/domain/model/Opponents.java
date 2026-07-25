package ru.hawoline.tennisscoreboard.domain.model;

public class Opponents {
    private String firstPlayerName;
    private String secondPlayerName;

    public Opponents(String firstPlayerName, String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }

    public Opponents() {
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }
}

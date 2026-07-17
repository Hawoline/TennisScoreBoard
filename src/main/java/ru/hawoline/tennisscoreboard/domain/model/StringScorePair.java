package ru.hawoline.tennisscoreboard.domain.model;

public class StringScorePair {
    private String firstPlayerScore;
    private String secondPlayerScore;

    public StringScorePair(String firstPlayerScore, String secondPlayerScore) {
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
    }

    public String getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public String getSecondPlayerScore() {
        return secondPlayerScore;
    }
}

package ru.hawoline.tennisscoreboard.domain.model;

public class ScorePair {
    private String firstPlayerScore;
    private String secondPlayerScore;

    public ScorePair(String firstPlayerScore, String secondPlayerScore) {
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

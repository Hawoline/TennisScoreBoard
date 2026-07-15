package ru.hawoline.tennisscoreboard.domain.model;

public class ScorePairResponse {
    private String firstPlayerScore;
    private String secondPlayerScore;

    public ScorePairResponse(String firstPlayerScore, String secondPlayerScore) {
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

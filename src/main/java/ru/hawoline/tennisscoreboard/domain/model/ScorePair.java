package ru.hawoline.tennisscoreboard.domain.model;

public class ScorePair {
    private int firstPlayerScore;
    private int secondPlayerScore;

    public ScorePair(int firstPlayerScore, int secondPlayerScore) {
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
    }

    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }
}

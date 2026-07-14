package ru.hawoline.tennisscoreboard.domain.model;

public class Player {
    private int score;
    private int gameScore;
    private int winSets;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    public int getWinSets() {
        return winSets;
    }

    public void setWinSets(int winSets) {
        this.winSets = winSets;
    }

    public void winScore() {
        score++;
    }

    public void winGame() {
        gameScore++;
    }

    public void winSet() {
        winSets++;
    }
}

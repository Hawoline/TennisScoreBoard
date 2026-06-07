package ru.hawoline.tennisscoreboard.domain;

public class GameScore {
    private int points;
    private int winPoints = 15;

    public void win() {
        points += winPoints;
        if (points >= 30) {
            winPoints = 10;
        }
    }

    public int getPoints() {
        return points;
    }
}

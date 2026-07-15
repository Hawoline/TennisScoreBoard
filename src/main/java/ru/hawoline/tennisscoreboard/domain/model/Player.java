package ru.hawoline.tennisscoreboard.domain.model;

public class Player {
    private String name;
    private int score;
    private int games;
    private int sets;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int score, int games, int sets) {
        this.name = name;
        this.score = score;
        this.games = games;
        this.sets = sets;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void winScore() {
        score++;
    }

    public void winGame() {
        games++;
    }

    public void winSet() {
        sets++;
    }
}

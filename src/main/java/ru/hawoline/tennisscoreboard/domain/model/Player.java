package ru.hawoline.tennisscoreboard.domain.model;

import java.util.Objects;

public class Player {
    private int id;
    private String name;
    private int score;
    private int games;
    private int sets;

    public Player(String name, int score, int games, int sets) {
        this.name = name;
        this.score = score;
        this.games = games;
        this.sets = sets;
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Player(String name) {
        this.name = name;
    }

    public Player() {
    }

    public void winScore() {
        score++;
    }

    public void winGame() {
        games++;
        score = 0;
    }

    public void winSet() {
        sets++;
        newSet();
    }

    public void newSet() {
        score = 0;
        games = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSets() {
        return sets;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

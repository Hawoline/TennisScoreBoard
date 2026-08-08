package ru.hawoline.tennisscoreboard.domain.model;

public class PlayerResponse {
    private String name;
    private String points;
    private int games;
    private int sets;
    private Integer tieBreakPoints;

    public PlayerResponse(String name, String points, int games, int sets, Integer tieBreakPoints) {
        this.name = name;
        this.points = points;
        this.games = games;
        this.sets = sets;
        this.tieBreakPoints = tieBreakPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
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

    public Integer getTieBreakPoints() {
        return tieBreakPoints;
    }

    public void setTieBreakPoints(Integer tieBreakPoints) {
        this.tieBreakPoints = tieBreakPoints;
    }
}

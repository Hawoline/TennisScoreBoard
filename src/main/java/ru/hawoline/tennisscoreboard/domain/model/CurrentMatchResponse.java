package ru.hawoline.tennisscoreboard.domain.model;

public class CurrentMatchResponse {
    private String firstPlayerName;
    private String secondPlayerName;
    private String firstPlayerPoints;
    private String secondPlayerPoints;
    private int firstPlayerGames;
    private int secondPlayerGames;
    private int firstPlayerSets;
    private int secondPlayerSets;
    private Integer firstPlayerTieBreakPoints;
    private Integer secondPlayerTieBreakPoints;
    private String winnerName;

    public CurrentMatchResponse(String firstPlayerName, String secondPlayerName, String firstPlayerPoints, String secondPlayerPoints, int firstPlayerGames, int secondPlayerGames, int firstPlayerSets, int secondPlayerSets, Integer firstPlayerTieBreakPoints, Integer secondPlayerTieBreakPoints, String winnerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        this.firstPlayerPoints = firstPlayerPoints;
        this.secondPlayerPoints = secondPlayerPoints;
        this.firstPlayerGames = firstPlayerGames;
        this.secondPlayerGames = secondPlayerGames;
        this.firstPlayerSets = firstPlayerSets;
        this.secondPlayerSets = secondPlayerSets;
        this.firstPlayerTieBreakPoints = firstPlayerTieBreakPoints;
        this.secondPlayerTieBreakPoints = secondPlayerTieBreakPoints;
        this.winnerName = winnerName;
    }

    public CurrentMatchResponse() {
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public void setFirstPlayerName(String firstPlayerName) {
        this.firstPlayerName = firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public void setSecondPlayerName(String secondPlayerName) {
        this.secondPlayerName = secondPlayerName;
    }

    public String getFirstPlayerPoints() {
        return firstPlayerPoints;
    }

    public void setFirstPlayerPoints(String firstPlayerPoints) {
        this.firstPlayerPoints = firstPlayerPoints;
    }

    public String getSecondPlayerPoints() {
        return secondPlayerPoints;
    }

    public void setSecondPlayerPoints(String secondPlayerPoints) {
        this.secondPlayerPoints = secondPlayerPoints;
    }

    public int getFirstPlayerGames() {
        return firstPlayerGames;
    }

    public void setFirstPlayerGames(int firstPlayerGames) {
        this.firstPlayerGames = firstPlayerGames;
    }

    public int getSecondPlayerGames() {
        return secondPlayerGames;
    }

    public void setSecondPlayerGames(int secondPlayerGames) {
        this.secondPlayerGames = secondPlayerGames;
    }

    public int getFirstPlayerSets() {
        return firstPlayerSets;
    }

    public void setFirstPlayerSets(int firstPlayerSets) {
        this.firstPlayerSets = firstPlayerSets;
    }

    public int getSecondPlayerSets() {
        return secondPlayerSets;
    }

    public void setSecondPlayerSets(int secondPlayerSets) {
        this.secondPlayerSets = secondPlayerSets;
    }

    public Integer getFirstPlayerTieBreakPoints() {
        return firstPlayerTieBreakPoints;
    }

    public void setFirstPlayerTieBreakPoints(Integer firstPlayerTieBreakPoints) {
        this.firstPlayerTieBreakPoints = firstPlayerTieBreakPoints;
    }

    public Integer getSecondPlayerTieBreakPoints() {
        return secondPlayerTieBreakPoints;
    }

    public void setSecondPlayerTieBreakPoints(Integer secondPlayerTieBreakPoints) {
        this.secondPlayerTieBreakPoints = secondPlayerTieBreakPoints;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }
}

package ru.hawoline.tennisscoreboard.domain.model;

public class CurrentMatchResponse {
    private PlayerResponse firstPlayer;
    private PlayerResponse secondPlayer;
    private String winnerName;

    public CurrentMatchResponse(PlayerResponse firstPlayer, PlayerResponse secondPlayer, String winnerName) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.winnerName = winnerName;
    }

    public PlayerResponse getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(PlayerResponse firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public PlayerResponse getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(PlayerResponse secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }
}

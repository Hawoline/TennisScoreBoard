package ru.hawoline.tennisscoreboard.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompletedMatch {
    private int id;
    private int player1;
    private int player2;
    private int winner;

    public CompletedMatch(int id, int player1, int player2, int winner) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public int getId() {
        return id;
    }

    public int getPlayer1() {
        return player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public int getWinner() {
        return winner;
    }

    public List<GameSet> getGameSets() {
        return new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CompletedMatch completedMatch = (CompletedMatch) o;
        return id == completedMatch.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

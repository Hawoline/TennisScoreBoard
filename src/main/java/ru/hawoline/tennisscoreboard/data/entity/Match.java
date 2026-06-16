package ru.hawoline.tennisscoreboard.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int player1;
    private int player2;
    private int winner;

    public Match(int id, int player1, int player2, int winner) {
        this(player1, player2, winner);
        this.id = id;
    }

    public Match(int player1, int player2, int winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public Match() {
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
}

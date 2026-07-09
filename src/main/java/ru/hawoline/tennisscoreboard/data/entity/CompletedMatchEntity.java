package ru.hawoline.tennisscoreboard.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Matches")
public class CompletedMatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private PlayerEntity playerEntity1;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private PlayerEntity playerEntity2;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private PlayerEntity winner;

    public CompletedMatchEntity(PlayerEntity playerEntity1, PlayerEntity playerEntity2, PlayerEntity winner) {
        this.playerEntity1 = playerEntity1;
        this.playerEntity2 = playerEntity2;
        this.winner = winner;
    }

    public CompletedMatchEntity(int id, PlayerEntity playerEntity1, PlayerEntity playerEntity2, PlayerEntity winner) {
        this(playerEntity1, playerEntity2, winner);
        this.id = id;
    }

    public CompletedMatchEntity() {
    }

    public int getId() {
        return id;
    }

    public PlayerEntity getPlayer1() {
        return playerEntity1;
    }

    public PlayerEntity getPlayer2() {
        return playerEntity2;
    }

    public PlayerEntity getWinner() {
        return winner;
    }
}

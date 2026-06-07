package ru.hawoline.tennisscoreboard.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Player(String name) {
        this.name = name;
    }

    public Player() {
    }
}

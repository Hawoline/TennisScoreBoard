package ru.hawoline.tennisscoreboard.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;

    public PlayerEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PlayerEntity(String name) {
        this.name = name;
    }

    public PlayerEntity() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

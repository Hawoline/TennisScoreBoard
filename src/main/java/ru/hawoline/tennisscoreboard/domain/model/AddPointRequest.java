package ru.hawoline.tennisscoreboard.domain.model;

public class AddPointRequest {
    private String name;

    public AddPointRequest(String name) {
        this.name = name;
    }

    public AddPointRequest() {
    }

    public String getName() {
        return name;
    }
}

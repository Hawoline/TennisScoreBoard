package ru.hawoline.tennisscoreboard.domain;

import java.util.List;

public interface Repository<Entity, Id> {
    void save(Entity entity);

    Entity getBy(Id id);

    List<Entity> getAll();
}

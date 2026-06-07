package ru.hawoline.tennisscoreboard.domain;

public interface Repository<Entity, Id> {
    void save(Entity entity);

    Entity loadBy(Id id);
}

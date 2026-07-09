package ru.hawoline.tennisscoreboard.data.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import ru.hawoline.tennisscoreboard.data.entity.CompletedMatchEntity;
import ru.hawoline.tennisscoreboard.domain.Repository;

import java.util.List;

@Component
public class MatchRepository implements Repository<CompletedMatchEntity, Integer> {
    private final EntityManager entityManager;

    public MatchRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(CompletedMatchEntity completedMatchEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(completedMatchEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public CompletedMatchEntity getBy(Integer key) {
        return entityManager.find(CompletedMatchEntity.class, key);
    }

    @Override
    public List<CompletedMatchEntity> getAll() {
        return List.of();
    }
}

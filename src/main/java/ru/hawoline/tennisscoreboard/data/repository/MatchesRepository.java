package ru.hawoline.tennisscoreboard.data.repository;

import jakarta.persistence.EntityManager;
import ru.hawoline.tennisscoreboard.data.entity.Match;
import ru.hawoline.tennisscoreboard.domain.Repository;

public class MatchesRepository implements Repository<Match, Integer> {
    private final EntityManager entityManager;

    public MatchesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Match match) {
        entityManager.getTransaction().begin();
        entityManager.persist(match);
        entityManager.getTransaction().commit();
    }

    @Override
    public Match loadBy(Integer key) {
        return entityManager.find(Match.class, key);
    }
}

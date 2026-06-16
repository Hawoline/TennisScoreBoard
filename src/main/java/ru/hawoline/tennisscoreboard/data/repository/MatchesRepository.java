package ru.hawoline.tennisscoreboard.data.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import ru.hawoline.tennisscoreboard.data.entity.Match;
import ru.hawoline.tennisscoreboard.domain.Repository;

import java.util.List;

@Component
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
    public Match getBy(Integer key) {
        return entityManager.find(Match.class, key);
    }

    @Override
    public List<Match> getAll() {
        return List.of();
    }
}

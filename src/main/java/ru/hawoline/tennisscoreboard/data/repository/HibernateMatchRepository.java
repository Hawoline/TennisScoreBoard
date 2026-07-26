package ru.hawoline.tennisscoreboard.data.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import ru.hawoline.tennisscoreboard.data.entity.CompletedMatchEntity;
import ru.hawoline.tennisscoreboard.data.entity.PlayerEntity;
import ru.hawoline.tennisscoreboard.domain.MatchRepository;
import ru.hawoline.tennisscoreboard.domain.Repository;
import ru.hawoline.tennisscoreboard.domain.model.Match;
import ru.hawoline.tennisscoreboard.domain.model.Player;

import java.util.List;

@Component
public class HibernateMatchRepository implements MatchRepository {
    private final EntityManager entityManager;

    public HibernateMatchRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Match match) {
        Player first = match.getFirstPlayer();
        Player second = match.getSecondPlayer();
        Player winner = match.getWinner();
        CompletedMatchEntity completedMatchEntity = new CompletedMatchEntity(new PlayerEntity(first.getId(), first.getName()),
                new PlayerEntity(second.getId(), second.getName()),
                new PlayerEntity(winner.getId(), winner.getName())
        );
        entityManager.getTransaction().begin();
        entityManager.persist(completedMatchEntity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Match getBy(Integer key) {
        CompletedMatchEntity completedMatchEntity = entityManager.find(CompletedMatchEntity.class, key);
        PlayerEntity firstEntity = completedMatchEntity.getPlayer1();
        PlayerEntity secondEntity = completedMatchEntity.getPlayer2();
        PlayerEntity winnerEntity = completedMatchEntity.getWinner();
        Player first = new Player(firstEntity.getId(), firstEntity.getName());
        Player second = new Player(secondEntity.getId(), secondEntity.getName());
        Player winner = new Player(winnerEntity.getId(), winnerEntity.getName());
        return new Match(first, second, winner);
    }

    @Override
    public List<Match> getAll() {
        return List.of();
    }
}

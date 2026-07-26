package ru.hawoline.tennisscoreboard.data.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import ru.hawoline.tennisscoreboard.data.entity.CompletedMatchEntity;
import ru.hawoline.tennisscoreboard.data.entity.MatchMapper;
import ru.hawoline.tennisscoreboard.data.entity.PlayerEntity;
import ru.hawoline.tennisscoreboard.domain.MatchRepository;
import ru.hawoline.tennisscoreboard.domain.model.Match;
import ru.hawoline.tennisscoreboard.domain.model.Player;

import java.util.ArrayList;
import java.util.List;

@Component
public class HibernateMatchRepository implements MatchRepository {
    private final EntityManager entityManager;
    private final MatchMapper matchMapper = new MatchMapper();

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
        return matchMapper.fromCompletedMatchEntity(completedMatchEntity);
    }

    @Override
    public List<Match> getAll() {
        return List.of();
    }

    @Override
    public List<Match> getBy(String playerName) {
        List<Match> result = new ArrayList<>();
        TypedQuery<CompletedMatchEntity> tq = entityManager.createQuery("from CompletedMatchEntity WHERE playerEntity1.name=:name", CompletedMatchEntity.class);
        List<CompletedMatchEntity> resultList = tq.setParameter("name", playerName).getResultList();
        for (CompletedMatchEntity completedMatchEntity : resultList) {
            result.add(matchMapper.fromCompletedMatchEntity(completedMatchEntity));
        }

        return result;
    }
}

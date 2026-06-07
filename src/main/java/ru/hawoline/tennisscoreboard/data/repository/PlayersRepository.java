package ru.hawoline.tennisscoreboard.data.repository;

import jakarta.persistence.EntityManager;
import ru.hawoline.tennisscoreboard.data.entity.Player;
import ru.hawoline.tennisscoreboard.domain.Repository;

public class PlayersRepository implements Repository<Player, Integer> {
    private final EntityManager entityManager;

    public PlayersRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Player player) {
        entityManager.getTransaction().begin();
        entityManager.persist(player);
        entityManager.getTransaction().commit();
    }

    @Override
    public Player loadBy(Integer key) {
        return entityManager.find(Player.class, key);
    }
}

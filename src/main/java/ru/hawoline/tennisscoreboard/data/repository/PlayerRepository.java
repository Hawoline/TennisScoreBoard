package ru.hawoline.tennisscoreboard.data.repository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.RollbackException;
import org.springframework.stereotype.Component;
import ru.hawoline.tennisscoreboard.data.entity.PlayerEntity;
import ru.hawoline.tennisscoreboard.domain.Repository;

import java.util.List;

@Component
public class PlayerRepository implements Repository<PlayerEntity, Integer> {
    private final EntityManager entityManager;

    public PlayerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(PlayerEntity playerEntity) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(playerEntity);
        } catch (PersistenceException e) {

        }
        entityManager.getTransaction().commit();
    }

    @Override
    public PlayerEntity getBy(Integer key) {
        return entityManager.find(PlayerEntity.class, key);
    }

    @Override
    public List<PlayerEntity> getAll() {
        return List.of();
    }
}

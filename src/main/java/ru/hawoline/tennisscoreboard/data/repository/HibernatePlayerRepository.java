package ru.hawoline.tennisscoreboard.data.repository;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import ru.hawoline.tennisscoreboard.data.entity.PlayerEntity;
import ru.hawoline.tennisscoreboard.domain.PlayerRepository;
import ru.hawoline.tennisscoreboard.domain.model.Player;

import java.util.List;

@Component
public class HibernatePlayerRepository implements PlayerRepository {
    private final EntityManager entityManager;

    public HibernatePlayerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Player player) {
        PlayerEntity playerEntity = new PlayerEntity(player.getName());
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(playerEntity);
        } catch (PersistenceException e) {

        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Player getBy(String key) {
        try {
            TypedQuery<PlayerEntity> tq = entityManager.createQuery("from PlayerEntity WHERE name=:name", PlayerEntity.class);
            PlayerEntity result = tq.setParameter("name", key).getSingleResult();

            return new Player(result.getId(), result.getName());
        } catch (NoResultException noresult) {
        } catch (NonUniqueResultException notUnique) {
        }

        return new Player(-1, "Null");
    }

    @Override
    public List<Player> getAll() {
        return List.of();
    }
}

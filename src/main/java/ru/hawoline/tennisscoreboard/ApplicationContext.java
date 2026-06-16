package ru.hawoline.tennisscoreboard;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.hawoline.tennisscoreboard.data.controller.NewMatchController;
import ru.hawoline.tennisscoreboard.data.repository.MatchesRepository;
import ru.hawoline.tennisscoreboard.data.repository.PlayersRepository;
import ru.hawoline.tennisscoreboard.domain.service.NewMatchService;

import java.util.List;

@Configuration
public class ApplicationContext {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("H2InMemoryPU");
    }
    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean
    public MatchesRepository matchesRepository() {
        return new MatchesRepository(entityManager());
    }

    @Bean
    public PlayersRepository playersRepository() {
        return new PlayersRepository(entityManager());
    }

    @Bean
    public NewMatchController newMatchController() {
        return new NewMatchController(newMatchService());
    }

    @Bean
    public NewMatchService newMatchService() {
        return new NewMatchService();
    }
}

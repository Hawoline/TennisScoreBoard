package ru.hawoline.tennisscoreboard;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hawoline.tennisscoreboard.data.controller.MatchController;
import ru.hawoline.tennisscoreboard.data.repository.MatchRepository;
import ru.hawoline.tennisscoreboard.data.repository.PlayerRepository;
import ru.hawoline.tennisscoreboard.data.service.MatchService;
import ru.hawoline.tennisscoreboard.domain.CurrentMatchService;

@Configuration
public class ApplicationContext {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("Postgres");
    }
    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean
    public MatchRepository matchesRepository() {
        return new MatchRepository(entityManager());
    }

    @Bean
    public PlayerRepository playerRepository() {
        return new PlayerRepository(entityManager());
    }

    @Bean
    public MatchController matchController() {
        return new MatchController(matchService());
    }

    @Bean
    public CurrentMatchService currentMatchService() {
        return new CurrentMatchService();
    }
    @Bean
    public MatchService matchService() {
        return new MatchService(playerRepository(), currentMatchService());
    }
}

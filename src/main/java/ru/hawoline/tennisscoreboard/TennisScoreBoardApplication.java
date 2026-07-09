package ru.hawoline.tennisscoreboard;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.hawoline.tennisscoreboard.data.entity.CompletedMatchEntity;
import ru.hawoline.tennisscoreboard.data.entity.PlayerEntity;
import ru.hawoline.tennisscoreboard.data.repository.MatchRepository;
import ru.hawoline.tennisscoreboard.data.repository.PlayerRepository;

public class TennisScoreBoardApplication {

	public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("Postgres");
             EntityManager em = emf.createEntityManager();) {
            PlayerRepository playerRepository = new PlayerRepository(em);
            PlayerEntity belikto = new PlayerEntity("Belikto");
            playerRepository.save(belikto);
            PlayerEntity bayarto = new PlayerEntity("Bayarto");
            PlayerEntity haw = new PlayerEntity("Haw");
            playerRepository.save(bayarto);

            PlayerEntity i = playerRepository.getBy(1);
            System.out.println(i);

            MatchRepository matchRepository = new MatchRepository(em);
            matchRepository.save(new CompletedMatchEntity(belikto, bayarto, bayarto));
            CompletedMatchEntity completedMatchEntity = matchRepository.getBy(1);
            System.out.println(completedMatchEntity.getWinner().getName());
        }
	}

}

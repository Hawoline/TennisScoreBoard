package ru.hawoline.tennisscoreboard;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.hawoline.tennisscoreboard.data.entity.Match;
import ru.hawoline.tennisscoreboard.data.entity.Player;
import ru.hawoline.tennisscoreboard.data.repository.MatchesRepository;
import ru.hawoline.tennisscoreboard.data.repository.PlayersRepository;

public class TennisScoreBoardApplication {

	public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("H2InMemoryPU");
             EntityManager em = emf.createEntityManager();) {
            PlayersRepository playersRepository = new PlayersRepository(em);
            playersRepository.save(new Player("Belikto"));
            playersRepository.save(new Player( "Bayarto"));

            Player i = playersRepository.getBy(1);
            System.out.println(i);

            MatchesRepository matchesRepository = new MatchesRepository(em);
            matchesRepository.save(new Match(1, 3, 2));
            Match match = matchesRepository.getBy(1);
            System.out.println(match.getWinner());
        }
	}

}

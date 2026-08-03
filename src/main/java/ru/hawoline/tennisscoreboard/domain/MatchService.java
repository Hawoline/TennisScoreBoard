package ru.hawoline.tennisscoreboard.domain;

import ru.hawoline.tennisscoreboard.domain.exception.DuplicateMatchException;
import ru.hawoline.tennisscoreboard.domain.exception.MatchNotFoundException;
import ru.hawoline.tennisscoreboard.domain.exception.PlayerNotFoundException;
import ru.hawoline.tennisscoreboard.domain.model.Match;
import ru.hawoline.tennisscoreboard.domain.model.Opponents;
import ru.hawoline.tennisscoreboard.domain.model.Player;

import java.util.List;
import java.util.UUID;

public class MatchService {
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final CurrentMatchService currentMatchService;

    public MatchService(CurrentMatchService currentMatchService, MatchRepository matchRepository, PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.currentMatchService = currentMatchService;
        this.matchRepository = matchRepository;
    }

    public String newMatch(Opponents opponents) throws DuplicateMatchException {
        String firstPlayerName = opponents.getFirstPlayerName();
        playerRepository.save(new Player(firstPlayerName));

        String secondPlayerName = opponents.getSecondPlayerName();
        playerRepository.save(new Player(secondPlayerName));

        String uuid = UUID.randomUUID().toString();
        currentMatchService.newMatch(uuid, new Match(new Player(firstPlayerName), new Player(secondPlayerName)));
        return uuid;
    }

    public Match addPoint(String uuid, String playerName) throws PlayerNotFoundException, MatchNotFoundException {
        Match match = currentMatchService.addPoint(uuid, playerName);

        if (match.getWinner() != null) {
            Player first = playerRepository.getBy(match.getFirstPlayer().getName());
            Player second = playerRepository.getBy(match.getSecondPlayer().getName());
            Player winner = playerRepository.getBy(match.getWinner().getName());
            match.getFirstPlayer().setId(first.getId());
            match.getSecondPlayer().setId(second.getId());
            match.getWinner().setId(winner.getId());
            matchRepository.save(match);
        }
        return match;
    }

    public Match getMatchBy(String uuid) {
        return currentMatchService.getMatchBy(uuid);
    }

    public List<Match> findByPlayerName(String playerName) {
        return matchRepository.getBy(playerName);
    }

    public List<Match> getCompletedMatches() {
        return matchRepository.getAll();
    }
}

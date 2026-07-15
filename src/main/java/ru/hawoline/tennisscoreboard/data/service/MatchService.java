package ru.hawoline.tennisscoreboard.data.service;

import ru.hawoline.tennisscoreboard.data.entity.PlayerEntity;
import ru.hawoline.tennisscoreboard.data.repository.PlayerRepository;
import ru.hawoline.tennisscoreboard.domain.CurrentMatchService;
import ru.hawoline.tennisscoreboard.domain.PlayerNotFoundException;
import ru.hawoline.tennisscoreboard.domain.ScoreCalculator;
import ru.hawoline.tennisscoreboard.domain.model.CurrentMatch;
import ru.hawoline.tennisscoreboard.domain.model.Match;
import ru.hawoline.tennisscoreboard.domain.model.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MatchService {
    private PlayerRepository playerRepository;
    private CurrentMatchService currentMatchService = new CurrentMatchService();

    public MatchService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public String newMatch(Match match) {
        playerRepository.save(new PlayerEntity(match.getFirstPlayerName()));
        playerRepository.save(new PlayerEntity(match.getSecondPlayerName()));
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        currentMatchService.newMatch(uuidString, new CurrentMatch(new Player(match.getFirstPlayerName()), new Player(match.getSecondPlayerName())));
        return uuidString;
    }
}

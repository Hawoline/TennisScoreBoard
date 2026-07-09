package ru.hawoline.tennisscoreboard.data.service;

import ru.hawoline.tennisscoreboard.data.entity.PlayerEntity;
import ru.hawoline.tennisscoreboard.data.repository.PlayerRepository;
import ru.hawoline.tennisscoreboard.domain.model.Match;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MatchService {
    private ConcurrentHashMap<String, Match> matches = new ConcurrentHashMap<>();
    private PlayerRepository playerRepository;

    public MatchService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public String newMatch(Match match) {
        playerRepository.save(new PlayerEntity(match.getFirstPlayerName()));
        playerRepository.save(new PlayerEntity(match.getSecondPlayerName()));
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        matches.put(uuidString, match);
        return uuidString;
    }
}

package ru.hawoline.tennisscoreboard.domain.service;

import ru.hawoline.tennisscoreboard.domain.Repository;
import ru.hawoline.tennisscoreboard.domain.model.Match;
import ru.hawoline.tennisscoreboard.domain.model.Player;

import java.util.List;

public class NewMatchService {
    private Repository<Match, Integer> matchRepository;
    private Repository<Player, Integer> playersRepository;

    public void newMatch(Player player0, Player player1) {
        List<Match> allMatches = matchRepository.getAll();


    }
}

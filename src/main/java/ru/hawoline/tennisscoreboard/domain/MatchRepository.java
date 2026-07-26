package ru.hawoline.tennisscoreboard.domain;

import ru.hawoline.tennisscoreboard.domain.model.Match;

import java.util.List;

public interface MatchRepository extends Repository<Match, Integer> {
    List<Match> getBy(String playerName);
}

package ru.hawoline.tennisscoreboard.domain;

import ru.hawoline.tennisscoreboard.domain.model.Match;

import java.util.concurrent.ConcurrentHashMap;

public class CurrentMatchService {
    private ConcurrentHashMap<String, Match> currentMatches = new ConcurrentHashMap<>();
    private ScoreCalculator scoreCalculator = new ScoreCalculator();

    public void newMatch(String uuid, Match match) {
        currentMatches.put(uuid, match);
    }

    public Match addPoint(String matchUuid, String playerName) throws PlayerNotFoundException, MatchNotFoundException {
        Match match = currentMatches.get(matchUuid);
        if(match == null) {
            throw new MatchNotFoundException();
        }
        String firstPlayerName = match.getFirstPlayer().getName();
        String secondPlayerName = match.getSecondPlayer().getName();
        if (firstPlayerName.equals(playerName)) {
            scoreCalculator.win(match, 0);
        } else if (secondPlayerName.equals(playerName)) {
            scoreCalculator.win(match, 1);
        } else {
            throw new PlayerNotFoundException();
        }

        if (match.getWinner() != null) {
            currentMatches.remove(matchUuid);
        }

        return match;
    }

    public ConcurrentHashMap<String, Match> getCurrentMatches() {
        return currentMatches;
    }
}

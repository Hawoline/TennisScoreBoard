package ru.hawoline.tennisscoreboard.domain;

import ru.hawoline.tennisscoreboard.domain.model.CurrentMatch;

import java.util.concurrent.ConcurrentHashMap;

public class CurrentMatchService {
    private ConcurrentHashMap<String, CurrentMatch> currentMatches = new ConcurrentHashMap<>();
    private ScoreCalculator scoreCalculator = new ScoreCalculator();

    public void newMatch(String uuid, CurrentMatch currentMatch) {
        currentMatches.put(uuid, currentMatch);
    }

    public CurrentMatch addPoint(String matchUuid, String playerName) throws PlayerNotFoundException {
        CurrentMatch currentMatch = currentMatches.get(matchUuid);
        String firstPlayerName = currentMatch.getFirstPlayer().getName();
        String secondPlayerName = currentMatch.getSecondPlayer().getName();
        if (firstPlayerName.equals(playerName)) {
            scoreCalculator.win(currentMatch, 0);
        } else if (secondPlayerName.equals(playerName)) {
            scoreCalculator.win(currentMatch, 1);
        } else {
            throw new PlayerNotFoundException();
        }

        return currentMatch;
    }

    public ConcurrentHashMap<String, CurrentMatch> getCurrentMatches() {
        return currentMatches;
    }
}

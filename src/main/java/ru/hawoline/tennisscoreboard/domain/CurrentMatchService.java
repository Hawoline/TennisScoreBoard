package ru.hawoline.tennisscoreboard.domain;

import ru.hawoline.tennisscoreboard.domain.exception.DuplicateMatchException;
import ru.hawoline.tennisscoreboard.domain.exception.MatchNotFoundException;
import ru.hawoline.tennisscoreboard.domain.exception.PlayerNotFoundException;
import ru.hawoline.tennisscoreboard.domain.model.Match;
import ru.hawoline.tennisscoreboard.domain.model.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentMatchService {
    private final ConcurrentHashMap<String, Match> currentMatches = new ConcurrentHashMap<>();
    private final ScoreCalculator scoreCalculator = new ScoreCalculator();

    public void newMatch(String uuid, Match match) throws DuplicateMatchException {
        Player firstPlayer = match.getFirstPlayer();
        Player secondPlayer = match.getSecondPlayer();
        for (Map.Entry<String, Match> matchEntry : currentMatches.entrySet()) {
            Match value = matchEntry.getValue();
            Player existingFirstPlayer = value.getFirstPlayer();
            Player existingSecondPlayer = value.getSecondPlayer();
            if (firstPlayer.equals(existingFirstPlayer) ||
                    firstPlayer.equals(existingSecondPlayer) ||
                    secondPlayer.equals(existingFirstPlayer) ||
                    secondPlayer.equals(existingSecondPlayer)
            ) {
                throw new DuplicateMatchException();
            }
        }

        currentMatches.put(uuid, match);
    }

    public Match addPoint(String matchUuid, String playerName) throws PlayerNotFoundException, MatchNotFoundException {
        Match match = currentMatches.get(matchUuid);
        if (match == null) {
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

package ru.hawoline.tennisscoreboard.domain;

import ru.hawoline.tennisscoreboard.domain.exception.DuplicateMatchException;
import ru.hawoline.tennisscoreboard.domain.exception.MatchNotFoundException;
import ru.hawoline.tennisscoreboard.domain.exception.PlayerNotFoundException;
import ru.hawoline.tennisscoreboard.domain.model.*;

import java.util.ArrayList;
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

        Player matchWinner = match.getWinner();
        System.out.println(match.getFirstPlayer().getId());
        if (matchWinner != null) {
            Player firstPlayer = match.getFirstPlayer();
            Player first = playerRepository.getBy(firstPlayer.getName());
            Player secondPlayer = match.getSecondPlayer();
            Player second = playerRepository.getBy(secondPlayer.getName());
            Player winner = playerRepository.getBy(matchWinner.getName());
            firstPlayer.setId(first.getId());
            secondPlayer.setId(second.getId());
            matchWinner.setId(winner.getId());
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

    public CompletedMatchesResponse getFromPage(List<CompletedMatchResponse> completedMatchResponses, int page) {
        int countInPage = 10;
        int size = completedMatchResponses.size();
        if (size <= countInPage) {
            return new CompletedMatchesResponse(completedMatchResponses, 0, 1);
        }
        List<CompletedMatchResponse> result = new ArrayList<>();
        int begin = countInPage * page;
        if (begin + countInPage > size) {
            for (int i = 0; i < size - begin; i++) {
                result.add(completedMatchResponses.get(size - i - 1));
            }
        } else {
            for (int i = 0; i < countInPage; i++) {
                result.add(completedMatchResponses.get(begin + i));
            }
        }

        return new CompletedMatchesResponse(result, page, size / countInPage);
    }


    public List<Match> getCompletedMatches() {
        return matchRepository.getAll();
    }
}

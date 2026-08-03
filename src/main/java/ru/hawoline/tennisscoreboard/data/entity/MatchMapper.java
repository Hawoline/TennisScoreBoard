package ru.hawoline.tennisscoreboard.data.entity;

import ru.hawoline.tennisscoreboard.domain.ScoreMapper;
import ru.hawoline.tennisscoreboard.domain.model.*;

import java.util.ArrayList;
import java.util.List;

public class MatchMapper {
    public CurrentMatchResponse toResponse(Match match) {
        Player first = match.getFirstPlayer();
        Player second = match.getSecondPlayer();
        ScoreMapper scoreMapper = new ScoreMapper();
        StringScorePair scores = scoreMapper.toString(first.getScore(), second.getScore());
        Player winner = match.getWinner();
        return new CurrentMatchResponse(
                first.getName(),
                second.getName(),
                match.getGameStage() == GameStage.GAME ? scores.getFirstPlayerScore() : null,
                match.getGameStage() == GameStage.GAME ? scores.getSecondPlayerScore() : null,
                first.getGames(),
                second.getGames(),
                first.getSets(),
                second.getSets(),
                match.getGameStage() == GameStage.TIE_BREAK ? first.getScore() : null,
                match.getGameStage() == GameStage.TIE_BREAK ? second.getScore() : null,
                winner == null ? null : winner.getName()
        );
    }

    public Match fromCompletedMatchEntity(CompletedMatchEntity completedMatchEntity) {
        PlayerEntity firstEntity = completedMatchEntity.getPlayer1();
        PlayerEntity secondEntity = completedMatchEntity.getPlayer2();
        PlayerEntity winnerEntity = completedMatchEntity.getWinner();
        Player first = new Player(firstEntity.getId(), firstEntity.getName());
        Player second = new Player(secondEntity.getId(), secondEntity.getName());
        Player winner = new Player(winnerEntity.getId(), winnerEntity.getName());
        return new Match(first, second, winner);
    }

    public List<CompletedMatchResponse> toCompletedMatchResponses(List<Match> completed) {
        List<CompletedMatchResponse> result = new ArrayList<>();
        for (Match match: completed) {
            result.add(toCompletedMatchResponse(match));
        }

        return result;
    }

    private CompletedMatchResponse toCompletedMatchResponse(Match match) {
        return new CompletedMatchResponse(
                match.getFirstPlayer().getName(),
                match.getSecondPlayer().getName(),
                match.getWinner().getName()
        );
    }
}

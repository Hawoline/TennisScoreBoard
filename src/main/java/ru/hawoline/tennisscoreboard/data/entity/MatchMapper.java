package ru.hawoline.tennisscoreboard.data.entity;

import ru.hawoline.tennisscoreboard.domain.ScoreMapper;
import ru.hawoline.tennisscoreboard.domain.model.*;

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
}

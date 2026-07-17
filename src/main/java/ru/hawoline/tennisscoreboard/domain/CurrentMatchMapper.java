package ru.hawoline.tennisscoreboard.domain;

import ru.hawoline.tennisscoreboard.domain.model.*;

public class CurrentMatchMapper {
    public CurrentMatchResponse toResponse(CurrentMatch currentMatch) {
        Player first = currentMatch.getFirstPlayer();
        Player second = currentMatch.getSecondPlayer();
        ScoreMapper scoreMapper = new ScoreMapper();
        StringScorePair scores = scoreMapper.toString(first.getScore(), second.getScore());
        Player winner = currentMatch.getWinner();
        return new CurrentMatchResponse(
                first.getName(),
                second.getName(),
                currentMatch.getGameStage() == GameStage.GAME ? scores.getFirstPlayerScore() : null,
                currentMatch.getGameStage() == GameStage.GAME ? scores.getSecondPlayerScore() : null,
                first.getGames(),
                second.getGames(),
                first.getSets(),
                second.getSets(),
                currentMatch.getGameStage() == GameStage.TIE_BREAK ? first.getScore() : null,
                currentMatch.getGameStage() == GameStage.TIE_BREAK ? second.getScore() : null,
                winner == null ? null : winner.getName()
        );
    }
}

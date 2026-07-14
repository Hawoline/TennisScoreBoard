package ru.hawoline.tennisscoreboard.domain;

import ru.hawoline.tennisscoreboard.domain.model.ScorePair;

public class ScoreConverter {
    public ScorePair convert(int firstPlayerScore, int secondPlayerScore) {
        String firstPlayerScoreString = "0";
        String secondPlayerScoreString;
        switch (firstPlayerScore) {
            case 0:
                firstPlayerScoreString = "0";
                break;
            case 1:
                firstPlayerScoreString = "15";
                break;
            case 2:
                firstPlayerScoreString = "30";
                break;
            case 3:
                firstPlayerScoreString = "40";
                break;
        }
        switch (secondPlayerScore) {
            case 0:
                secondPlayerScoreString = "0";
                break;
            case 1:
                secondPlayerScoreString = "15";
                break;
            case 2:
                secondPlayerScoreString = "30";
                break;
            case 3:
                secondPlayerScoreString = "40";
                break;
            default:
                if (firstPlayerScore < secondPlayerScore) {
                    return new ScorePair("40", "AD");
                } else if (firstPlayerScore > secondPlayerScore){
                    return new ScorePair("AD", "40");
                } else {
                    return new ScorePair("40", "40");
                }
        }
        return new ScorePair(firstPlayerScoreString, secondPlayerScoreString);
    }
}

package ru.hawoline.tennisscoreboard.domain;

import ru.hawoline.tennisscoreboard.domain.model.StringScorePair;

public class ScoreMapper {
    public StringScorePair toString(int firstPlayerScore, int secondPlayerScore) {
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
                    return new StringScorePair("40", "AD");
                } else if (firstPlayerScore > secondPlayerScore){
                    return new StringScorePair("AD", "40");
                } else {
                    return new StringScorePair("40", "40");
                }
        }
        return new StringScorePair(firstPlayerScoreString, secondPlayerScoreString);
    }

    public int toScorePair(String score) {
        switch (score) {
            case "15":
                return 1;
            case "30":
                return 2;
            case "40":
                return 4;
            case "AD":
                return 5;
        }

        return 0;
    }
}

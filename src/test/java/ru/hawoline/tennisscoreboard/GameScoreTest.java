package ru.hawoline.tennisscoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hawoline.tennisscoreboard.domain.model.GameScore;

import java.util.ArrayList;
import java.util.List;

class GameScoreTest {
    private List<Integer> scores = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        scores.add(0);
        scores.add(15);
        int score = 30;
        for (int i = 0; i < 10; i++) {
            scores.add(score + i * 10);
        }
    }
    @Test
    public void test() {
        GameScore score = new GameScore();
//        assertEquals();
    }
}
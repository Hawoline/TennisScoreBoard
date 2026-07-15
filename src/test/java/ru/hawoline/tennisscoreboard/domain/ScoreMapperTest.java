package ru.hawoline.tennisscoreboard.domain;

import org.junit.jupiter.api.Test;
import ru.hawoline.tennisscoreboard.domain.model.ScorePairResponse;

import static org.junit.jupiter.api.Assertions.*;

class ScoreMapperTest {

    @Test
    public void test() {
        ScoreMapper scoreMapper = new ScoreMapper();
        ScorePairResponse convert = scoreMapper.toResponse(0, 0);
        assertEquals("0", convert.getFirstPlayerScore());
        assertEquals("0", convert.getSecondPlayerScore());

        ScorePairResponse convert1 = scoreMapper.toResponse(1, 1);
        assertEquals("15", convert1.getSecondPlayerScore());
        ScorePairResponse convert2 = scoreMapper.toResponse(4, 5);
        assertEquals("AD", convert2.getSecondPlayerScore());
        assertEquals("40", convert2.getFirstPlayerScore());
        ScorePairResponse convert3 = scoreMapper.toResponse(6, 6);
        assertEquals("40", convert3.getFirstPlayerScore());
        assertEquals("40", convert3.getSecondPlayerScore());
    }
}
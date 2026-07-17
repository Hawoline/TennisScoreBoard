package ru.hawoline.tennisscoreboard.domain;

import org.junit.jupiter.api.Test;
import ru.hawoline.tennisscoreboard.domain.model.StringScorePair;

import static org.junit.jupiter.api.Assertions.*;

class ScoreMapperTest {

    @Test
    public void test() {
        ScoreMapper scoreMapper = new ScoreMapper();
        StringScorePair convert = scoreMapper.toString(0, 0);
        assertEquals("0", convert.getFirstPlayerScore());
        assertEquals("0", convert.getSecondPlayerScore());

        StringScorePair convert1 = scoreMapper.toString(1, 1);
        assertEquals("15", convert1.getSecondPlayerScore());
        StringScorePair convert2 = scoreMapper.toString(4, 5);
        assertEquals("AD", convert2.getSecondPlayerScore());
        assertEquals("40", convert2.getFirstPlayerScore());
        StringScorePair convert3 = scoreMapper.toString(6, 6);
        assertEquals("40", convert3.getFirstPlayerScore());
        assertEquals("40", convert3.getSecondPlayerScore());
    }
}
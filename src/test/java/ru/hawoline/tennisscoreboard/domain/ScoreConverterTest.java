package ru.hawoline.tennisscoreboard.domain;

import org.junit.jupiter.api.Test;
import ru.hawoline.tennisscoreboard.domain.model.ScorePair;

import static org.junit.jupiter.api.Assertions.*;

class ScoreConverterTest {

    @Test
    public void test() {
        ScoreConverter scoreConverter = new ScoreConverter();
        ScorePair convert = scoreConverter.convert(0, 0);
        assertEquals("0", convert.getFirstPlayerScore());
        assertEquals("0", convert.getSecondPlayerScore());

        ScorePair convert1 = scoreConverter.convert(1, 1);
        assertEquals("15", convert1.getSecondPlayerScore());
        ScorePair convert2 = scoreConverter.convert(4, 5);
        assertEquals("AD", convert2.getSecondPlayerScore());
        assertEquals("40", convert2.getFirstPlayerScore());
        ScorePair convert3 = scoreConverter.convert(6, 6);
        assertEquals("40", convert3.getFirstPlayerScore());
        assertEquals("40", convert3.getSecondPlayerScore());
    }
}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PipeTest {

    @Test
    void refillFillLevel() {
        FillColor[] fc = new FillColor[4];
        for (int i = 0; i < fc.length; i++) {
            fc[i] = new FillColor((char) ('a' + i), FillColor.getDefaultColors()[i]);
        }
        FillLevel[] fl = new FillLevel[4];
        for (int i = 0; i < fl.length; i++) {
            fl[i] = new FillLevel(fc[i], 50);
        }

        Pipe p = new Pipe('t', fl);
        p.refillFillLevel(fc[1]);
        assertEquals(100, p.fillLevels[1].getFillLevel());
    }

    @Test
    void isEmpty() {
        FillColor[] fc = new FillColor[4];
        for (int i = 0; i < fc.length; i++) {
            fc[i] = new FillColor((char) ('a' + i), FillColor.getDefaultColors()[i]);
        }
        FillLevel[] fl = new FillLevel[4];
        for (int i = 0; i < fl.length; i++) {
            fl[i] = new FillLevel(fc[i], 50);
        }

        Pipe p = new Pipe('t', fl);
        assertFalse(p.isEmpty());
        p.fillLevels[3].reduceFillLevel(45);
        assertFalse(p.isEmpty());
        p.fillLevels[3].reduceFillLevel(10);
        assertTrue(p.isEmpty());
    }
}
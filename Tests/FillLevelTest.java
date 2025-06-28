import codedraw.Palette;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FillLevelTest {

    @Test
    void refill() {
        FillLevel f1 = new FillLevel();
        assertNotNull(f1);
        assertEquals(0, f1.getFillLevel());
        f1.refill();
        assertEquals(100, f1.getFillLevel());
    }

    @Test
    void reduceFillLevel() {
        FillLevel f1 = new FillLevel();
        assertNotNull(f1);
        assertEquals(0, f1.getFillLevel());
        f1.refill();
        assertEquals(100, f1.getFillLevel());
        f1.reduceFillLevel(20);
        assertEquals(80, f1.getFillLevel());
        f1.reduceFillLevel(40);
        assertEquals(40, f1.getFillLevel());
    }

    @Test
    void getColor() {
        FillLevel f1 = new FillLevel();
        FillColor fc = new FillColor();
        assertEquals(fc, f1.getColor());

        FillColor fc2 = new FillColor('G', Palette.GREEN);
        FillLevel f2 = new FillLevel(fc2, 30);
        FillColor fc3 = new FillColor('G', Palette.GREEN);
        assertEquals(fc3, f2.getColor());
    }

    @Test
    void getFillLevel() {
        FillLevel f1 = new FillLevel();
        assertEquals(0, f1.getFillLevel());
    }

    @Test
    void isEmpty() {
        FillLevel f1 = new FillLevel();
        assertTrue(f1.isEmpty());

        f1.refill();
        assertFalse(f1.isEmpty());

        f1.reduceFillLevel(60);
        assertFalse(f1.isEmpty());
    }

    @RepeatedTest(10)
    void generateRandomFillLevelsArray() {
        FillColor[] fc = new FillColor[4];
        for (int i = 0; i < fc.length; i++) {
            fc[i] = new FillColor((char) ('a' + i), FillColor.getDefaultColors()[i]);
        }

        FillLevel[] fl = FillLevel.GenerateRandomFillLevelsArray(fc);
        assertNotNull(fl);
        assertEquals(4, fl.length);
    }
}
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FillColorTest {

    @Test
    void testEquals() {
        FillColor c1 = new FillColor();
        FillColor c2 = new FillColor();
        assertEquals(c1, c2);

        FillColor c3 = new FillColor('B', FillColor.getDefaultColors()[1]);
        FillColor c4 = new FillColor('B', FillColor.getDefaultColors()[2]);
        assertNotEquals(c3, c4);

        FillColor c5 = new FillColor('B', FillColor.getDefaultColors()[1]);
        assertEquals(c3, c5);
    }

    @Test
    void testToString() {
        FillColor c1 = new FillColor();
        assertEquals("FillColor{letter=A, color=java.awt.Color[r=255,g=0,b=0]}", c1.toString());
    }
}
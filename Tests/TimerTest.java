import static org.junit.jupiter.api.Assertions.*;

class TimerTest {

    @org.junit.jupiter.api.Test
    void tick() {
        Timer timer = new Timer();
        timer.tick();
        assertEquals(1, timer.getTime());
    }

    @org.junit.jupiter.api.Test
    void getTime() {
        Timer timer = new Timer();
        assertEquals(0, timer.getTime());
        timer.tick();
        assertEquals(1, timer.getTime());
        for (int i = 0; i < 20; i++) {
            timer.tick();
        }
        assertEquals(21, timer.getTime());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Timer timer = new Timer();
        assertEquals("0:00", timer.toString());
        for (int i = 0; i < 59; i++) {
            timer.tick();
        }
        assertEquals("0:59", timer.toString());
        timer.tick();
        assertEquals("1:00", timer.toString());
        for (int i = 0; i < 30; i++) {
            timer.tick();
        }
        assertEquals("1:30", timer.toString());
    }
}
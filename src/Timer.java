/**
 * Represents a Timer Instance
 */
public class Timer {
    private int time;

    /**
     * creates a new timer instance and initialize it with the value 0
     */
    public Timer() {
        this.time = 0;
    }

    /**
     * Increases the timer value by 1 second
     */
    public void tick() {
        this.time++;
    }

    /**
     * Returns the time in seconds/ticks as an Integer
     *
     * @return the current time as an integer
     */
    public int getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Timer timer = (Timer) o;
        return time == timer.time;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(time);
    }

    /**
     * Returns a string representation of the current Timer
     *
     * <p>The string's format is minutes:seconds (2:08)</p>
     *
     * @return a string containing the current time.
     */
    @Override
    public String toString(){
        return (time / 60) + ":" + (((time % 60) < 10) ? "0" : "") + (time % 60);
    }
}

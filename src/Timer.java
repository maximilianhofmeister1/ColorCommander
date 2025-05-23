public class Timer {
    private int time;

    public Timer() {
        this.time = 0;
    }

    public void tick() {
        this.time++;
    }

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

    @Override
    public String toString(){
        return (time / 60) + ":" + (((time % 60) < 10) ? "0" : "") + (time % 60);
    }
}

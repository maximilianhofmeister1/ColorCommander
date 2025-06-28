import java.util.Arrays;

public class Pipe {
    char letter;
    FillLevel[] fillLevels;

    /**
     * Creates a new Pipe-Object and initializes the FillLevels with the default value.
     */
    public Pipe() {
        letter = 'A';
        fillLevels = new FillLevel[4];
        for (int i = 0; i < fillLevels.length; i++) {
            fillLevels[i] = new FillLevel();
        }
    }

    /**
     * Creates a new Pipe-Object
     *
     * @param letter     the letter assoziated with this pipe
     * @param fillLevels the fillLevels
     */
    public Pipe(char letter, FillLevel[] fillLevels) {
        this.letter = letter;
        this.fillLevels = fillLevels;
    }

    /**
     * Refills the FillLevel of this pipe with the given fillColor to 100%
     *
     * @param fillColor the fillColor that should be refilled in this pipe
     */
    public void refillFillLevel(FillColor fillColor) {
        for (FillLevel f : fillLevels) {
            if (f.getColor().equals(fillColor)) {
                f.refill();
                break;
            }
        }
    }

    /**
     * Checks if any of the fillLevels in the Pipe is empty
     *
     * @return {@code true} when empty, {@code false} otherwise
     */
    public boolean isEmpty() {
        for (FillLevel f : fillLevels) {
            if (f.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Pipe{" +
                "letter=" + letter +
                ", fillLevels=" + Arrays.toString(fillLevels) +
                '}';
    }
}

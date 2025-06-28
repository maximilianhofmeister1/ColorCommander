import java.util.Random;

public class FillLevel {
    private final FillColor color; //includes color and letter
    private double level;

    public FillLevel() {
        this.color = new FillColor();
        this.level = 0;
    }

    public FillLevel(FillColor fillColor, double fillLevel) {
        this.color = fillColor;
        this.level = fillLevel;
    }

    /**
     * Refills the fillLevel to 100%
     */
    public void refill() {
        this.level = 100;
    }

    /**
     * reduces the FillLevel by the given value
     *
     * @param value the amount of which the FillLevel should be reduced
     */
    public void reduceFillLevel(double value) {
        this.level -= value;
    }

    public FillColor getColor() {
        return this.color;
    }

    public double getFillLevel() {
        return level;
    }

    /**
     * Checks if the fillLevel is empty
     *
     * @return {@code true} when empty, {@code false} otherwise
     */
    public boolean isEmpty() {
        return this.level <= 0;
    }

    /**
     * Generates Randomized FillLevels from a provides {@code FillColor[]} array;
     * The order of Colors and the value of fillLevel (between 50-100) will be randomized
     *
     * @param fillColors the fillColors, from which fillLevel instances should be generated
     * @return an randomized {@code FillLevel[]} array from provided fillColors
     */
    public static FillLevel[] GenerateRandomFillLevelsArray(FillColor[] fillColors) {
        FillColor[] colors = fillColors.clone();
        FillLevel[] array = new FillLevel[colors.length];
        //Shuffle colors Array
        //Set Color Order
        for (int j = colors.length - 1; j > 0; j--) {
            int index = new Random().nextInt(j + 1);
            // Simple swap
            FillColor a = colors[index];
            colors[index] = colors[j];
            colors[j] = a;
        }

        //Set random fill levels
        for (int i = 0; i < array.length; i++) {
            double fillLevel = new Random().nextInt(50, 101);
            array[i] = new FillLevel(colors[i], fillLevel);
        }

        return array;
    }

    @Override
    public String toString() {
        return "FillLevel{" +
                "color=" + color +
                ", level=" + level +
                '}';
    }
}

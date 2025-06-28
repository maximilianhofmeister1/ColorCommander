import codedraw.Palette;

import java.awt.*;
import java.util.Objects;

public class FillColor {
    //Class Variables
    private static final Color[] defaultColors = new Color[]{Palette.RED, Palette.GREEN, Palette.BLUE, Palette.ORANGE};

    //Object Variables
    char letter;
    Color color;

    //Constructor
    public FillColor() {
        letter = 'A';
        color = Palette.RED;
    }

    public FillColor(char letter, Color color) {
        this.letter = Character.toUpperCase(letter);
        this.color = color;
    }

    /**
     * Array Representation of the 4 default colors (RED, GREEN, BLUE, ORANGE)
     *
     * @return an {@code Color[]} of the 4 default colors
     */
    public static Color[] getDefaultColors() {
        return defaultColors;
    }

    /**
     * Two FillColors are equals, when the letter and the color are the same.
     *
     * @param o FillColor object to be compared with
     * @return true, when the two objects are the same, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FillColor fillColor = (FillColor) o;
        return letter == fillColor.letter && Objects.equals(color, fillColor.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, color);
    }

    @Override
    public String toString() {
        return "FillColor{" +
                "letter=" + letter +
                ", color=" + color +
                '}';
    }
}

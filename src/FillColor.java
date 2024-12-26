import codedraw.Palette;

import java.awt.*;

public class FillColor {
    //Class Variables
    private static Color[] defaultColors = new Color[]{Palette.RED, Palette.GREEN, Palette.BLUE, Palette.ORANGE};

    //Object Variables
    char letter;
    Color color;
    double fillLevel;

    //Constructor
    public FillColor(){
        letter = 'A';
        color = Palette.RED;
        fillLevel = 0;
    }

    public FillColor(char letter, Color color){
        this.letter = letter;
        this.color = color;
        this.fillLevel = 0;
    }

    public FillColor(char letter, Color color, double fillLevel){
        this.letter = letter;
        this.color = color;
        this.fillLevel = fillLevel;
    }

    //Object Methods
    public void setLetter(char letter) {
        this.letter = Character.toUpperCase(letter);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFillLevel(int fillLevel) {
        this.fillLevel = fillLevel;
    }

    //Class Methods
    public static Color[] getDefaultColors(){
        return defaultColors;
    }
}

import codedraw.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Main {
    static CodeDraw window = new CodeDraw(1000, 600);
    static double marginX = (int) (window.getWidth() * 0.2);
    static double marginY = (int) (window.getHeight() * 0.1);
    static GameStats game = new GameStats();
    static FillColor[] colorPickers = new FillColor[4];
    static Pipe[] pipes = new Pipe[4];


    public static void main(String[] args) {
        //Init Arrays
        for (int i = 0; i < colorPickers.length; i++) {
            colorPickers[i] = new FillColor();
            colorPickers[i].color = FillColor.getDefaultColors()[i];
            colorPickers[i].letter = game.pickerLetters[i];
        }
        for (int i = 0; i < pipes.length; i++) {
            FillColor[] fillLevels = generateRandomFillColorArray();
            pipes[i] = new Pipe(game.pipeLetters[i], fillLevels);
        }

        buildGameInterface(game.speed, game.time);
        char[] keysPressed = new char[2];

        while (!window.isClosed()) {
            for (var e : window.getEventScanner()) {
                switch (e) {
                    case KeyDownEvent a -> {
                        if(a.getKey() == Key.SPACE){
                            keysPressed = new char[2];
                        }
                        else if(keysPressed[0]=='\u0000'){
                            keysPressed[0] = Character.toUpperCase(a.getChar());
                        }
                        else{
                            keysPressed[1] = Character.toUpperCase(a.getChar());
                            fillUp(keysPressed);
                            keysPressed = new char[2];
                        }
                    }
                    case MouseClickEvent a -> {
                        checkWindowClick(a.getX(), a.getY());
                    }
                    default -> {
                    }
                }
            }
            buildGameInterface(game.speed, game.time);
        }


    }

    private static void buildGameInterface(double currentSpeed, int time) {
        window.clear();
        //Menu
        window.setTitle("Multitasking Test");
        window.setColor(Palette.BLACK);
        window.drawText(10, 10, "Restart");
        window.drawText(300, 10, "current Speed: " + currentSpeed);
        window.drawText(600, 10, "Time: " + (time / 60) + ":" + (((time % 60) < 10) ? "0" : "") + (time % 60));
        window.drawText(window.getWidth() - 80, 10, "Settings");

        //Game
        double gameWidth = window.getWidth() - (2 * marginX);
        double gameHeight = window.getHeight() - (2 * marginY);
        double colWidth = gameWidth / 5.5;

        //ColorPickers
        for (int i = 0; i < colorPickers.length; i++) {
            window.setColor(colorPickers[i].color);
            window.fillSquare(
                    (colWidth * 1.5 * i) + marginX,
                    marginY,
                    colWidth
            );

            window.setColor(Palette.BLACK);
            window.drawText(
                    (colWidth * 1.5 * i) + marginX + (colWidth / 2 - 5),
                    marginY + colWidth + 10,
                    "" + colorPickers[i].letter
            );
        }

        //Pipes
        double pipeHeight = gameHeight - (2 * colWidth);
        for (int i = 0; i < pipes.length; i++) {
            window.setColor(Palette.BLACK);
            window.drawRectangle(
                    (colWidth * 1.5 * i) + marginX,
                    marginY + (2 * colWidth),
                    colWidth,
                    pipeHeight
            );

            window.drawText(
                    (colWidth * 1.5 * i) + marginX + (colWidth / 2 - 5),
                    marginY + (2 * colWidth) + pipeHeight + 10,
                    "" + pipes[i].letter
            );

            //Pipe Fills
            for (int fillCount = 0; fillCount < pipes[i].fillLevels.length; fillCount++) {
                FillColor currentFillLevel = pipes[i].fillLevels[fillCount];
                double fillWidth = colWidth / pipes[i].fillLevels.length;
                double fillHeight = pipeHeight / 100 * currentFillLevel.fillLevel;
                Point2D fillsXYCoordinate = new Point2D(
                        (colWidth * 1.5 * i) + marginX + (fillWidth * fillCount),
                        marginY + (2* colWidth) + pipeHeight
                );
                window.setColor(currentFillLevel.color);
                window.fillRectangle(fillsXYCoordinate.getX(), fillsXYCoordinate.getY() - fillHeight, fillWidth, fillHeight);
            }
        }

        window.show(40);
    }

    private static void fillUp(char[] keysPressed){
        Color c = null;
        for (FillColor colorPicker : colorPickers) {
            if (keysPressed[0] == colorPicker.letter) {
                c = colorPicker.color;
                break;
            }
        }
        for(Pipe pipe : pipes){
            if(keysPressed[1] == pipe.letter){
                for(FillColor fc : pipe.fillLevels){
                    if(c == fc.color){
                        fc.fillLevel = 100;
                        break;
                    }
                }
            }
        }
    }

    private static FillColor[] generateRandomFillColorArray(){
        Color[] colors = FillColor.getDefaultColors().clone();
        FillColor[] array = new FillColor[colors.length];
        //Shuffle colors Array
        for (int j = colors.length - 1; j > 0; j--){
            int index = new Random().nextInt(j + 1);
            // Simple swap
            Color a = colors[index];
            colors[index] = colors[j];
            colors[j] = a;
        }

        for (int i = 0; i < array.length; i++) {
            double fillLevel = new Random().nextInt(50, 101);
            array[i] = new FillColor('A', colors[i], fillLevel);
        }

        return array;
    }

    //selects correct function (restart() or settings())
    private static void checkWindowClick(int x, int y) {
        if (x < 100 && y < 40) {
            restart();
        } else if (x > window.getWidth() - 100 && y < 40) {
            settings();
        }
    }

    private static void restart() {
        //TODO: Save Game-Stats as log to File
        game = new GameStats();
    }

    private static void settings() {
        System.out.println("Settings-Function");
        //TODO: Settings-Function
    }
}
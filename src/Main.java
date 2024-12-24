import codedraw.*;

import java.awt.*;
import java.util.Arrays;

public class Main {
    static CodeDraw window = new CodeDraw(1000, 600);
    static double marginX = (int) (window.getWidth() * 0.2);
    static double marginY = (int) (window.getHeight() * 0.1);
    static GameStats game = new GameStats();


    public static void main(String[] args) {
        buildGameInterface(game.speed, game.time, game.pickerLetters, game.pipeLetters);
        char[] keysPressed = new char[2];

        while (!window.isClosed()) {
            for (var e : window.getEventScanner()) {
                switch (e) {
                    case KeyDownEvent a -> {
                        if(keysPressed[0]=='\u0000'){
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
            buildGameInterface(game.speed, game.time, game.pickerLetters, game.pipeLetters);
        }


    }

    private static void buildGameInterface(double currentSpeed, int time, char[] pickerLetters, char[] pipeLetters) {
        window.clear();
        //Menu
        window.setTitle("Multitasking Test");
        window.drawText(10, 10, "Restart");
        window.drawText(300, 10, "current Speed: " + currentSpeed);
        window.drawText(600, 10, "Time: " + (time / 60) + ":" + (((time % 60) < 10) ? "0" : "") + (time % 60));
        window.drawText(window.getWidth() - 80, 10, "Settings");

        //Game
        double gameWidth = window.getWidth() - (2 * marginX);
        double gameHeight = window.getHeight() - (2 * marginY);
        double colWidth = gameWidth / 5.5;
        Color[] colors = {Palette.RED, Palette.GREEN, Palette.BLUE, Palette.ORANGE};

        //ColorPickers
        for (int i = 0; i < 4; i++) {
            window.setColor(colors[i]);
            window.fillSquare(
                    (colWidth * 1.5 * i) + marginX,
                    marginY,
                    colWidth
            );

            window.setColor(Palette.BLACK);
            window.drawText(
                    (colWidth * 1.5 * i) + marginX + (colWidth / 2 - 5),
                    marginY + colWidth + 10,
                    "" + pickerLetters[i]
            );
        }

        //Pipes
        double pipeHeight = gameHeight - (2 * colWidth);
        for (int i = 0; i < 4; i++) {
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
                    "" + pipeLetters[i]
            );

            //Pipe Fills
            for (int fillCount = 0; fillCount < pickerLetters.length; fillCount++) {
                double fillWidth = colWidth / pickerLetters.length;
                Point2D fillsXYCoordinate = new Point2D(
                        (colWidth * 1.5 * i) + marginX + (fillWidth * fillCount),
                        marginY + (2* colWidth) + pipeHeight
                );
                window.fillRectangle(fillsXYCoordinate.getX(), fillsXYCoordinate.getY(), fillWidth, 50);
            }
        }

        window.show(40);
    }

    private static void fillUp(char[] keysPressed){
        //TODO: fillUp-Funktion
        //System.out.println(Arrays.toString(keysPressed));
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
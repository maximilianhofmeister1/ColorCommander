import codedraw.*;

import java.awt.*;

public class Main {
    static CodeDraw window = new CodeDraw(1000, 600);
    static double marginX = (int) (window.getWidth() * 0.2);
    static double marginY = (int) (window.getHeight() * 0.1);


    public static void main(String[] args) {
        //game variables
        GameStats game = new GameStats();

        buildGameInterface(game.speed, game.time, game.pickerLetters, game.pipeLetters);
    }

    private static void buildGameInterface(double currentSpeed, int time, char[] pickerLetters, char[] pipeLetters) {
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
        }

        window.show();
    }
}
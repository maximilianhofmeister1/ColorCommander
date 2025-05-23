import codedraw.*;

public class GameBoard {
    private final CodeDraw window;
    private final int marginX;
    private final int marginY;

    public GameBoard(CodeDraw window) {
        this.window = window;
        this.marginX = (int) (window.getWidth() * 0.2);
        this.marginY = (int) (window.getHeight() * 0.1);
    }

    public void buildGameInterface(Game game, Pipe[] pipes, FillColor[] colorPickers) {
        double currentSpeed = game.speed;

        window.clear();
        //Menu
        window.setTitle("ColorCommander - Game");
        window.setColor(Palette.BLACK);
        window.drawText(10, 10, "Restart");
        window.drawText(300, 10, "current Speed: " + currentSpeed);
        window.drawText(600, 10, "Time: " + game.timer.toString());
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

            //Pipe Fill Levels
            for (int fillCount = 0; fillCount < pipes[i].fillLevels.length; fillCount++) {
                FillColor currentFillLevel = pipes[i].fillLevels[fillCount];
                double fillWidth = colWidth / pipes[i].fillLevels.length;
                double fillHeight = pipeHeight / 100 * currentFillLevel.fillLevel;
                Point2D fillsXYCoordinate = new Point2D(
                        (colWidth * 1.5 * i) + marginX + (fillWidth * fillCount),
                        marginY + (2 * colWidth) + pipeHeight
                );
                window.setColor(currentFillLevel.color);
                window.fillRectangle(fillsXYCoordinate.getX(), fillsXYCoordinate.getY() - fillHeight, fillWidth, fillHeight);
            }
        }

        window.show(40);
    }

    public void gameOver(Game game, String saveUrl) {
        //Game Over Screen
        window.setColor(Palette.RED);
        window.setTextFormat(new TextFormat().setFontSize(50).setTextOrigin(TextOrigin.CENTER));
        window.drawText(marginX / 2, (double) window.getHeight() / 2, "GAME");
        window.drawText(window.getWidth() - (marginX / 2), (double) window.getHeight() / 2, "OVER");
        window.setTextFormat(new TextFormat());
        window.show();

        game.saveToFile(saveUrl);
    }
}

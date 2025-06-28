import codedraw.*;

public class GameBoard {
    private final CodeDraw window;
    private final int marginX;
    private final int marginY;
    private final double gameWidth;
    private final double gameHeight;
    private final double colWidth;

    public GameBoard(CodeDraw window) {
        this.window = window;
        this.marginX = (int) (window.getWidth() * 0.2);
        this.marginY = (int) (window.getHeight() * 0.1);
        this.gameWidth = window.getWidth() - (2 * marginX);
        this.gameHeight = window.getHeight() - (2 * marginY);
        this.colWidth = gameWidth / 5.5;
    }

    /**
     * Builds the current Game Interface with the given values
     *
     * @param game
     * @param pipes
     * @param colorPickers
     */
    public void buildGameInterface(Game game, Pipe[] pipes, FillColor[] colorPickers) {
        window.clear();

        //Menu
        drawMenu(game);
        //ColorPickers
        drawColorPickers(colorPickers);
        //Pipes
        drawPipes(pipes);

        window.show(40);
    }

    /**
     * Shows the gameOver Screen on the current Gameboard
     *
     * @param game
     * @param saveUrl the local Path of the File, where the GameStats should be saved
     */
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

    private void drawMenu(Game game) {
        double currentSpeed = game.speed;

        //Menu
        window.setTitle("ColorCommander - Game");
        window.setColor(Palette.BLACK);
        window.drawText(10, 10, "Restart");
        window.drawText(300, 10, "current Speed: " + currentSpeed);
        window.drawText(600, 10, "Time: " + game.timer.toString());
        window.drawText(window.getWidth() - 80, 10, "Settings");
    }

    private void drawColorPickers(FillColor[] colorPickers) {
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
    }

    private void drawPipes(Pipe[] pipes) {
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
                FillLevel currentFillLevel = pipes[i].fillLevels[fillCount];
                double fillWidth = colWidth / pipes[i].fillLevels.length;
                double fillHeight = pipeHeight / 100 * currentFillLevel.getFillLevel();
                Point2D fillsXYCoordinate = new Point2D(
                        (colWidth * 1.5 * i) + marginX + (fillWidth * fillCount),
                        marginY + (2 * colWidth) + pipeHeight
                );
                window.setColor(currentFillLevel.getColor().color);
                window.fillRectangle(fillsXYCoordinate.getX(), fillsXYCoordinate.getY() - fillHeight, fillWidth, (fillHeight > 0) ? fillHeight : 0);
            }
        }
    }
}

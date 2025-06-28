import codedraw.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Main {
    static CodeDraw window = new CodeDraw(1000, 600);
    static GameBoard gameBoard = new GameBoard(window);

    static int timeCount = 0;
    static int randomizeLettersCount = 0;
    static boolean gameOver = false;
    static boolean gameOverScreen = false;

    static Game game = new Game();
    static FillColor[] colorPickers = new FillColor[4];
    static Pipe[] pipes = new Pipe[4];
    static String saveUrl = "ColorCommander_GameLog.txt";


    public static void main(String[] args) {
        restart();
        gameBoard.buildGameInterface(game, pipes, colorPickers);
        char[] keysPressed = new char[2];
        //KeyVisualizer.KeyVisualiser.main(args);

        while (!window.isClosed()) {
            for (var e : window.getEventScanner()) {
                switch (e) {
                    case KeyDownEvent a -> {
                        //KeyVisualizer.KeyVisualiser.update(Character.toUpperCase(a.getChar()));
                        if (a.getKey() == Key.SPACE) {
                            keysPressed = new char[2];
                        } else if (keysPressed[0] == '\u0000') {
                            keysPressed[0] = Character.toUpperCase(a.getChar());
                        } else {
                            keysPressed[1] = Character.toUpperCase(a.getChar());
                            fillUp(keysPressed);
                            keysPressed = new char[2];
                        }
                    }
                    case MouseClickEvent a -> checkWindowClick(a.getX(), a.getY());
                    default -> {
                    }
                }
            }

            if (!gameOver) {
                //Time
                if (++timeCount >= 25) {
                    game.timer.tick();
                    timeCount = 0;

                    //adjust speed
                    if (game.timer.getTime() % 30 == 0 && game.speed < 5) {
                        game.speed += 0.25;
                    }
                }

                //adjust fill levels
                for (Pipe pipe : pipes) {
                    for (FillLevel fillLevel : pipe.fillLevels) {
                        if (fillLevel.isEmpty()) {
                            gameOver = true;
                            break;
                        } else { //fillLevel.getFillLevel() - game.speed / 20 > 0
                            fillLevel.reduceFillLevel(game.speed / 20);
                        }
                    }
                }

                //is called 25 times per second
                gameBoard.buildGameInterface(game, pipes, colorPickers);
            } else if (gameOver && !gameOverScreen) {
                gameOverScreen = true;
                gameBoard.gameOver(game, saveUrl);
            }
        }
    }


    private static void fillUp(char[] keysPressed) {
        FillColor c = null;
        for (FillColor colorPicker : colorPickers) {
            if (keysPressed[0] == colorPicker.letter) {
                c = colorPicker;
                break;
            }
        }
        //Refill FillLevel of Pipe
        for (Pipe pipe : pipes) {
            if (keysPressed[1] == pipe.letter) {
                pipe.refillFillLevel(c);
            }
        }

        //Randomize Letters if needed and reset counter
        if (game.randomizeLetters) {
            if (++randomizeLettersCount >= game.randomizeLettersWhenCount) {
                RandomizeLetters();
                randomizeLettersCount = 0;
                game.randomizeLettersWhenCount = new Random().nextInt(2, 8);
            }
        }
    }

    private static void RandomizeLetters() {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (Pipe value : pipes) {
            char letter = alphabet[new Random().nextInt(alphabet.length)];
            if (Arrays.stream(pipes).noneMatch(pipe -> pipe.letter == letter) &&
                    Arrays.stream(colorPickers).noneMatch(colorPicker -> colorPicker.letter == letter)) {
                value.letter = letter;
            }
        }
        for (FillColor value : colorPickers) {
            char letter = alphabet[new Random().nextInt(alphabet.length)];
            if (Arrays.stream(pipes).noneMatch(pipe -> pipe.letter == letter) &&
                    Arrays.stream(colorPickers).noneMatch(colorPicker -> colorPicker.letter == letter)) {
                value.letter = letter;
            }
        }
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
        game = new Game();
        gameOver = false;
        gameOverScreen = false;

        char[] pickerLetters = new char[]{'A', 'B', 'C', 'D'};
        char[] pipeLetters = new char[]{'E', 'F', 'G', 'H'};

        //Init Arrays
        for (int i = 0; i < colorPickers.length; i++) {
            colorPickers[i] = new FillColor(
                    pickerLetters[i],
                    FillColor.getDefaultColors()[i]
            );
        }
        for (int i = 0; i < pipes.length; i++) {
            FillLevel[] fillLevels = FillLevel.GenerateRandomFillLevelsArray(colorPickers);
            pipes[i] = new Pipe(pipeLetters[i], fillLevels);
        }
        RandomizeLetters();
    }

    private static void settings() {
        System.out.println("Settings-Function");
        //TODO: Settings-Function
    }
}
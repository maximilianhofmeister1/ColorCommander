import java.io.*;
import java.util.Random;

public class Game {
    Timer timer;
    double speed;
    boolean randomizeLetters;
    int randomizeLettersWhenCount;
    int randomizeLettersCount;
    boolean gameOver;
    boolean gameOverScreen;
    int timeCount;

    /**
     * Creates a Game-Instance
     */
    public Game() {
        timer = new Timer();
        speed = 1;
        randomizeLetters = true;
        randomizeLettersWhenCount = new Random().nextInt(2, 8);

        this.randomizeLettersCount = 0;
        this.gameOver = false;
        this.gameOverScreen = false;
        this.timeCount = 0;
    }

    /**
     * Save current Game-Stats to File
     * @param src Path of the File
     */
    public void saveToFile(String src) {
        System.out.println("GameStats saved to file: " + src);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(src, true));

            bw.write(this.toString());
            bw.newLine();
            bw.close();
        }
        catch (IOException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Game: " + "Time:" + timer.getTime() + "; Speed:" + speed + "; RandomizeLetters:" + randomizeLetters + ";";
    }
}

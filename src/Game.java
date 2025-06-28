import java.io.*;
import java.util.Random;

public class Game {
    Timer timer;
    double speed;
    boolean randomizeLetters;
    int randomizeLettersWhenCount;

    public Game() {
        timer = new Timer();
        speed = 1;
        randomizeLetters = true;
        randomizeLettersWhenCount = new Random().nextInt(2, 8);
    }

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

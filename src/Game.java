import java.io.*;
import java.util.Random;

public class Game {
    int time;
    double speed;
    char[] pickerLetters;
    char[] pipeLetters;
    boolean randomizeLetters;
    int randomizeLettersWhenCount;

    public Game(){
        time = 0;
        speed = 1;
        pickerLetters = new char[]{'A', 'B', 'C', 'D'};
        pipeLetters = new char[]{'E', 'F', 'G', 'H'};
        randomizeLetters = true;
        randomizeLettersWhenCount = new Random().nextInt(2, 8);
    }

    public void saveToFile(String src){
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

    public String toString(){
        return "Game: " + "Time:" + time + "; Speed:" + speed + "; RandomizeLetters:" + randomizeLetters + ";";
    }
}

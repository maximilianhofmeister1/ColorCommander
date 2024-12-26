import java.util.Random;

public class GameStats {
    int time;
    double speed;
    char[] pickerLetters;
    char[] pipeLetters;
    boolean shuffleLetters;
    int shuffleLettersWhenCount;

    public GameStats(){
        time = 0;
        speed = 1;
        pickerLetters = new char[]{'A', 'B', 'C', 'D'};
        pipeLetters = new char[]{'E', 'F', 'G', 'H'};
        shuffleLetters = true;
        shuffleLettersWhenCount = new Random().nextInt(2, 8);
    }

    public void saveToFile(String src){
        //TODO: save GameStats to file
        System.out.println("GameStats saved to file: " + src);
    }

    public String toString(){
        return "Game: " + "Time:" + time + "; Speed:" + speed + "; ShuffleLetters:" + shuffleLetters + ";";
    }
}

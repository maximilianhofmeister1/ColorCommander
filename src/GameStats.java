public class GameStats {
    int time;
    int speed;
    char[] pickerLetters;
    char[] pipeLetters;

    public GameStats(){
        time = 0;
        speed = 1;
        pickerLetters = new char[]{'A', 'B', 'C', 'D'};
        pipeLetters = new char[]{'E', 'F', 'G', 'H'};
    }

    public void saveToFile(String src){
        //TODO: save GameStats to file
    }

    public String toString(){
        return "Game: " + "Time:" + time + "; Speed:" + speed + ";";
    }
}

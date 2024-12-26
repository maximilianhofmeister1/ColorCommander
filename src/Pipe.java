public class Pipe {
    char letter;
    FillColor[] fillLevels;

    public Pipe(){
        letter = 'A';
        fillLevels = new FillColor[4];
    }

    public Pipe(char letter, FillColor[] fillLevels){
        this.letter = letter;
        this.fillLevels = fillLevels;
    }
}

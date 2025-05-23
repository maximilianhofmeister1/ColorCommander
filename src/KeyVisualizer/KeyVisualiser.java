package KeyVisualizer;

import codedraw.*;

import java.awt.*;

public class KeyVisualiser {
    //Basic Version of KeyVisualizer.KeyVisualiser -> very simple and buggy

    static CodeDraw visualiser = new CodeDraw(350, 200);
    static char[] keyHistory = new char[]{' ', ' '};

    static Color backgroundColor = new Color(29, 31, 32);
    static Color keyColor = Palette.GRAY;

    public static void main(String[] args) {
        update(' ');
    }

    public static void update(char key) {
        if(key == ' '){
            keyHistory = new char[]{' ', ' '};
        }
        else if(keyHistory[1] != ' ') {
            keyHistory = new char[2];
            keyHistory[0] = key;
        }
        else if (keyHistory[0] != ' ') {
            keyHistory[1] = key;
        }
        else{
            keyHistory[0] = key;
        }

        visualiser.clear(backgroundColor);
        visualiser.setCorner(Corner.ROUND);
        visualiser.setCornerRadius(20);
        if(keyHistory[0] != ' ') {

            visualiser.setColor(keyColor);
            visualiser.fillSquare(50, 50, 100);
            visualiser.setColor(Palette.WHITE);
            visualiser.setTextFormat(new TextFormat()
                    .setFontSize(50)
                    .setTextOrigin(TextOrigin.CENTER)
            );
            visualiser.drawText(100, 100, "" + keyHistory[0]);
        }

        if(keyHistory[1] != ' ') {

            visualiser.setColor(keyColor);
            visualiser.fillSquare(200, 50, 100);
            visualiser.setColor(Palette.WHITE);
            visualiser.setTextFormat(new TextFormat()
                    .setFontSize(50)
                    .setTextOrigin(TextOrigin.CENTER)
            );
            visualiser.drawText(250, 100, "" + keyHistory[1]);
        }
        visualiser.show();
    }
}

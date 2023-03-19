package kdg.be.parchis.model.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Die {
    private static int thrown = 0;
    private static final Image empty;
    private static boolean rollAgain = false;

    static {
        try {
            empty = new Image(new FileInputStream("resources\\graphics\\die\\empty.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Image die(int roll) {
        try {
            return new Image(new FileInputStream("resources\\graphics\\die\\" + roll + ".png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final ImageView diceFoto = new ImageView(empty);
    private static final Random rn = new Random();

    public static void throwDie() {
        // Get random number between 1 and 6 for the die.
        thrown = rn.nextInt(1, 7);

        getDiceFoto().setImage(die(thrown));

        rollAgain = (thrown == 6);
    }

    public static boolean isRollAgain() {
        return rollAgain;
    }

    public static int getThrown() {
        return thrown;
    }

    public static ImageView getDiceFoto() {
        return diceFoto;
    }

    public static Image getEmpty() {
        return empty;
    }

    public static void setSeven() {
        thrown = 7;
    }

    public static void setTen() {
        thrown = 10;
    }
}
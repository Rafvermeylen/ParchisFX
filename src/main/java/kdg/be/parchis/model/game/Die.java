package kdg.be.parchis.model.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Die {
    private static int thrown=0;
    private static Image empty;
    private static boolean rollAgain = false;
    static {
        try {
            empty = new Image(new FileInputStream("resources\\graphics\\die\\empty.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static Image die1;
    static {
        try {
            die1 = new Image(new FileInputStream("resources\\graphics\\die\\dice_one.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static Image die2;
    static {
        try {
            die2 = new Image(new FileInputStream("resources\\graphics\\die\\dice_two.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static Image die3;
    static {
        try {
            die3 = new Image(new FileInputStream("resources\\graphics\\die\\dice_three.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static Image die4;
    static {
        try {
            die4 = new Image(new FileInputStream("resources\\graphics\\die\\dice_four.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static Image die5;
    static {
        try {
            die5 = new Image(new FileInputStream("resources\\graphics\\die\\dice_five.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static Image die6;
    static {
        try {
            die6 = new Image(new FileInputStream("resources\\graphics\\die\\dice_six.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static ImageView diceFoto = new ImageView(empty);
    private static Random rn = new Random();
    public static void throwDie() {
    // Get random number between 1 and 6 for the die.
        thrown = rn.nextInt(1,7);
        if (thrown == 1){
            getDiceFoto().setImage(die1);
        } else if (thrown == 2){
            getDiceFoto().setImage(die2);
        } else if (thrown == 3){
            getDiceFoto().setImage(die3);
        } else if (thrown == 4){
            getDiceFoto().setImage(die4);
        } else if (thrown == 5){
            getDiceFoto().setImage(die5);
        } else if (thrown == 6){
            getDiceFoto().setImage(die6);
        }
        rollAgain = thrown == 6;
    }

    public static void setSeven(){
        thrown = 7;
    }

    public static void setTen(){
        thrown = 10;
    }

    public static int getThrown() {
        return thrown;
    }

    public static boolean isRollAgain() {
        return rollAgain;
    }

    public static ImageView getDiceFoto() {
        return diceFoto;
    }

    public static Image getEmpty() {
        return empty;
    }
}
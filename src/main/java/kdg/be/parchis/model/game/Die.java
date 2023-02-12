package kdg.be.parchis.model.game;

import kdg.be.parchis.model.menu.Cheats;
import java.util.Random;
import static java.lang.Thread.sleep;

public class Die {
    private static Random rn = new Random();
    public static int throwDie() {
            //dice sound
            /*
            UIMusicStuff musicObject = new UIMusicStuff();
            musicObject.playMusic("src\\be\\kdg\\Parchis\\Resources\\fullroll.wav");
            try {
                sleep(2 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

             */

            // Get random number between 1 and 6 for the die.
            return rn.nextInt(1, 7);
    }
}
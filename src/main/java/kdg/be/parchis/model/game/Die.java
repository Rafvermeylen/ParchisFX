package kdg.be.parchis.model.game;

import kdg.be.parchis.model.menu.Cheats;
import java.util.Random;
import static java.lang.Thread.sleep;

public class Die {
    private int total;
    Random rn;
    Die() {
        rn = new Random();
    }

    public int throwDie() {
        if (!Cheats.getActivated()){
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
            total = rn.nextInt(1, 7);
            return total;
        }
        return Cheats.chooseThrow();
    }
}
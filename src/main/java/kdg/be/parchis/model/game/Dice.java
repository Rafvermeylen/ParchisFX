package kdg.be.parchis.model.game;

import java.util.Random;

public class Dice {
    Random rn;
    public Dice () {
        rn = new Random();
    }
    public int roll(){
        return rn.nextInt(1, 6+1);
    }
}

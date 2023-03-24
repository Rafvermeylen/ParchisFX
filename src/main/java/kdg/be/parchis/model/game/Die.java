package kdg.be.parchis.model.game;

import java.util.Random;

public class Die {
    /**
     * This class is the die and simply returns a random value between one and six. This determines what
     * a player rolls and how many spaces he can move.
     * Authors: Rui Daniel Gomes Vieira & Raf Vermeylen
     */
    Random rn;
    public Die() {
        rn = new Random();
    }
    public int roll(){
        return rn.nextInt(1, 6+1);
    }
}

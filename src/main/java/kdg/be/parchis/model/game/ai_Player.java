package kdg.be.parchis.model.game;

import java.util.Random;

public class ai_Player extends Player {

    public ai_Player(String name, Color color) {
        super(name, color);
    }
    public int throwDie(){
        Random rn = new Random();
        return rn.nextInt(1,7);
    }
}

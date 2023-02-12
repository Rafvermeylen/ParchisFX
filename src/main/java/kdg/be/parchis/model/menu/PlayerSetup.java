package kdg.be.parchis.model.menu;

import kdg.be.parchis.model.Player;

import java.util.List;

public class PlayerSetup {
    private List<Player> players;
    private final int amountPlayers;
    public PlayerSetup(int amountPlayers){
        this.amountPlayers = amountPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getAmountPlayers() {
        return amountPlayers;
    }
}

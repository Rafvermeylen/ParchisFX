package kdg.be.parchis.model.menu;

import kdg.be.parchis.model.game.Color;
import kdg.be.parchis.model.game.Die;
import kdg.be.parchis.model.game.Player;
import kdg.be.parchis.model.game.AiPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerSetup {
    /**
     * This class determines the order in which the players get to play. Here everyone gets to roll and
     * the thrown value gets remembered. Then the players get ordered based on what they threw.
     * Authors: Rui Daniel Gomes Vieira & Raf Vermeylen
     */
    private List<Player> players;
    private final int amountPlayers;
    private int[] rolls;
    private final Die die;
    private int thrown;

    public PlayerSetup(int amountPlayers) {
        this.amountPlayers = amountPlayers;
        rolls = new int[]{0, 0, 0, 0};
        die = new Die();
        players = new ArrayList<>(4);
    }

    public void order() {
    // Sort the rolls in descending order using a simple sorting algorithm
        for (int i = 0; i < 4; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < 4; j++) {
                if (rolls[j] > rolls[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                int temp = rolls[i];
                rolls[i] = rolls[maxIndex];
                rolls[maxIndex] = temp;
                Player tempPlayer = players.get(i);
                players.set(i, players.get(maxIndex));
                players.set(maxIndex, tempPlayer);
            }
        }
    // The players list is now sorted based on the values in the rolls array
    // The player at index 0 in the list corresponds to the highest roll in the rolls array
    // The player at index 3 in the list corresponds to the lowest roll in the rolls array
    }

    public boolean didPlayersRoll() {
        for (Integer roll : rolls) {
            if (roll == 0) {
                return false;
            }
        }
        return true;
    }

    public void addRoll(int index, int rolled) {
        rolls[index] = rolled;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getAmountPlayers() {
        return amountPlayers;
    }

    public void setPlayers(String name1) {
        players.add(new Player(name1, Color.YELLOW));
        players.add(new AiPlayer("Bot_Blue", Color.BLUE));
        players.add(new AiPlayer("Bot_Red", Color.RED));
        players.add(new AiPlayer("Bot_Green", Color.GREEN));

    }

    public void setPlayers(String name1, String name2) {
        players.add(new Player(name1, Color.YELLOW));
        players.add(new Player(name2, Color.BLUE));
        players.add(new AiPlayer("Bot_Red", Color.RED));
        players.add(new AiPlayer("Bot_Green", Color.GREEN));

    }

    public void setPlayers(String name1, String name2, String name3) {
        players.add(new Player(name1, Color.YELLOW));
        players.add(new Player(name2, Color.BLUE));
        players.add(new Player(name3, Color.RED));
        players.add(new AiPlayer("Bot_Green", Color.GREEN));

    }

    public void setPlayers(String name1, String name2, String name3, String name4) {
        players.add(new Player(name1, Color.YELLOW));
        players.add(new Player(name2, Color.BLUE));
        players.add(new Player(name3, Color.RED));
        players.add(new Player(name4, Color.GREEN));
    }

    public void roll(){
        thrown = die.roll();
    }

    public int getThrown(){
        return thrown;
    }
}

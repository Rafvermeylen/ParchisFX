package kdg.be.parchis.model.menu;

import kdg.be.parchis.model.game.Color;
import kdg.be.parchis.model.game.Player;
import kdg.be.parchis.model.game.ai_Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerSetup {
    private List<Player> players = new ArrayList<>(4);
    private final int amountPlayers;
    private int[] rolls;

    public PlayerSetup(int amountPlayers) {
        this.amountPlayers = amountPlayers;
        rolls = new int[]{0, 0, 0, 0};
    }

    public void setPlayers(String naam1) {
        players.add(new Player(naam1, Color.YELLOW));
        players.add(new ai_Player("Bot_Blue", Color.BLUE));
        players.add(new ai_Player("Bot_Red", Color.RED));
        players.add(new ai_Player("Bot_Green", Color.GREEN));

    }

    public void setPlayers(String naam1, String naam2) {
        players.add(new Player(naam1, Color.YELLOW));
        players.add(new Player(naam2, Color.BLUE));
        players.add(new ai_Player("Bot_Red", Color.RED));
        players.add(new ai_Player("Bot_Green", Color.GREEN));

    }

    public void setPlayers(String naam1, String naam2, String naam3) {
        players.add(new Player(naam1, Color.YELLOW));
        players.add(new Player(naam2, Color.BLUE));
        players.add(new Player(naam3, Color.RED));
        players.add(new ai_Player("Bot_Green", Color.GREEN));

    }

    public void setPlayers(String naam1, String naam2, String naam3, String naam4) {
        players.add(new Player(naam1, Color.YELLOW));
        players.add(new Player(naam2, Color.BLUE));
        players.add(new Player(naam3, Color.RED));
        players.add(new Player(naam4, Color.GREEN));

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

}

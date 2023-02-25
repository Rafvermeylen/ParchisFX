package kdg.be.parchis.model.menu;

import kdg.be.parchis.model.game.Colors;
import kdg.be.parchis.model.game.Die;
import kdg.be.parchis.model.game.Player;
import kdg.be.parchis.model.game.ai_Player;

import java.util.*;

public class PlayerSetup {
    private List<Player> players = new ArrayList<>(4);
    private final int amountPlayers;
    private int[] rolls;
    private List<Integer> aiRolls = new ArrayList<>(3);

    public PlayerSetup(int amountPlayers){
        this.amountPlayers = amountPlayers;
        rolls = new int[]{0,0,0,0};
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getAmountPlayers() {
        return amountPlayers;
    }

    public void determineOrder(List<Player> players) {
        Map<Player, Integer> playerRolls = new HashMap<>();
        int roll;
        for (Player player : players) {
            if (!(player instanceof ai_Player)){
                Die.throwDie();
                roll = Die.getThrown();
            } else {
                roll = ((ai_Player) player).throwDie();
            }

            playerRolls.put(player, roll);
        }

        List<kdg.be.parchis.model.game.Player> result = new ArrayList<>();
        while (result.size() < 4) {
            int maxRoll = Collections.max(playerRolls.values());
            List<kdg.be.parchis.model.game.Player> maxPlayers = new ArrayList<>();
            for (Map.Entry<kdg.be.parchis.model.game.Player, Integer> entry : playerRolls.entrySet()) {
                if (entry.getValue() == maxRoll) {
                    maxPlayers.add(entry.getKey());
                }
            }

            if (maxPlayers.size() == 1) {
                result.add(maxPlayers.get(0));
                playerRolls.remove(maxPlayers.get(0));
            } else {
                for (kdg.be.parchis.model.game.Player player : maxPlayers) {
                    if (!(player instanceof ai_Player)){
                        Die.throwDie();
                        roll = Die.getThrown();
                    } else {
                        roll = ((ai_Player) player).throwDie();
                    }
                    playerRolls.put(player, roll);
                }
            }
        }
        players.clear();
        players.addAll(result);
    }

    public void setPlayers(String naam1){
        players.add(new Player(naam1, Colors.YELLOW));
        players.add(new ai_Player("Bot_Blue", Colors.BLUE));
        players.add(new ai_Player("Bot_Red", Colors.RED));
        players.add(new ai_Player("Bot_Green", Colors.GREEN));

    }

    public void setPlayers(String naam1, String naam2){
        players.add(new Player(naam1, Colors.YELLOW));
        players.add(new Player(naam2, Colors.BLUE));
        players.add(new ai_Player("Bot_Red", Colors.RED));
        players.add(new ai_Player("Bot_Green", Colors.GREEN));

    }

    public void setPlayers(String naam1, String naam2, String naam3){
        players.add(new Player(naam1, Colors.YELLOW));
        players.add(new Player(naam2, Colors.BLUE));
        players.add(new Player(naam3, Colors.RED));
        players.add(new ai_Player("Bot_Green", Colors.GREEN));

    }

    public void setPlayers(String naam1, String naam2, String naam3, String naam4){
        players.add(new Player(naam1, Colors.YELLOW));
        players.add(new Player(naam2, Colors.BLUE));
        players.add(new Player(naam3, Colors.RED));
        players.add(new Player(naam4, Colors.GREEN));

    }

    public void order(){
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

    public boolean didPlayersRoll(){
        for (Integer roll : rolls){
            if (roll == 0){
                return false;
            }
        }
        return true;
    }

    public void addRoll(int index, int rolled){
        rolls[index] = rolled;
    }

}

package kdg.be.parchis.model.menu;

import kdg.be.parchis.model.game.Colors;
import kdg.be.parchis.model.game.Die;
import kdg.be.parchis.model.game.Player;
import kdg.be.parchis.model.game.ai_Player;

import java.util.*;

public class PlayerSetup {
    private List<Player> players = new ArrayList<>(4);
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

    public void determineOrder(List<Player> players) {
        Map<Player, Integer> playerRolls = new HashMap<>();
        int roll;
        for (Player player : players) {
            if (!(player instanceof ai_Player)){
                //ui.throwForOrder(player);
                roll = Die.throwDie();
                //ui.printThrow(roll);
            } else {
                roll = ((ai_Player) player).throwDie();
                //ui.printThrowBot(roll, player);
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
                        //ui.throwForOrder(player);
                        roll = Die.throwDie();
                        //ui.printThrow(roll);
                    } else {
                        roll = ((ai_Player) player).throwDie();
                        //ui.printThrowBot(roll, player);
                    }
                    playerRolls.put(player, roll);
                }
            }
        }
        players.clear();
        players.addAll(result);
        //ui.displayOrder(result);
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

}

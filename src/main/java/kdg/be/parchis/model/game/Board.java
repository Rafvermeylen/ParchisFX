/*
This code is used to use create the board. We have divided the board in different parts. That way, we can differentiate
between all the tiles. We use a different class called 'Tile.java' which has the enum 'TileKind'.
*/

package kdg.be.parchis.model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {

    Map<Integer, Tile> board;
    /**
     * This class creates the board in a map and adds all the tiles to it. It can return tiles.
     * Authors: Rui Daniel Gomes Vieira & Raf Vermeylen
     */
    public Board() {
        board = new HashMap<>();
        int id = 0;

        // Create tiles for pawns to land on.
        ArrayList<Integer> safeSpots = new ArrayList<>();
        Collections.addAll(safeSpots, 5, 12, 17, 22, 29, 34, 39, 46, 51, 56, 63, 68);

        ArrayList<Integer> nest = new ArrayList<>();
        Collections.addAll(nest, 69, 70, 71, 72);

        ArrayList<Integer> landingStrip = new ArrayList<>();
        Collections.addAll(landingStrip, 73, 74, 75, 76, 77, 78, 79, 81, 82, 83, 84, 85, 86, 87, 89,
                90, 91, 92, 93, 94, 95, 97, 98, 99, 100, 101, 102, 103);

        ArrayList<Integer> center = new ArrayList<>();
        Collections.addAll(center, 80, 88, 96, 104);

        // Generates the id nr / tile nr
        for (int i = 0; i < 120; i++) {
            id++;

            if (safeSpots.contains(id)) {
                board.put(id, new Tile(TileKind.SAFESPACE, id));
            } else if (nest.contains(id)) {
                board.put(id, new Tile(TileKind.NEST, id));
            } else if (landingStrip.contains(id)) {
                board.put(id, new Tile(TileKind.LANDINGSTRIP, id));
            } else if (center.contains(id)) {
                board.put(id, new Tile(TileKind.CENTER, id));
            } else {
                board.put(id, new Tile(TileKind.WALKTILE, id));
            }
        }
    }
}
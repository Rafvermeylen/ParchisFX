package kdg.be.parchis.model.game;

import java.util.*;

public class Board {
    Map<Integer, Tile> board;

    public Board() {
        board = new HashMap<>();
        int id = 0;

        // Create tiles for pawns to land on.
        ArrayList<Integer> safeSpots = new ArrayList<>();
        safeSpots.add(5);
        safeSpots.add(12);
        safeSpots.add(17);
        safeSpots.add(22);
        safeSpots.add(29);
        safeSpots.add(34);
        safeSpots.add(39);
        safeSpots.add(46);
        safeSpots.add(51);
        safeSpots.add(56);
        safeSpots.add(63);
        safeSpots.add(68);

        ArrayList<Integer> nest = new ArrayList<>();
        nest.add(69);
        nest.add(70);
        nest.add(71);
        nest.add(72);

        int[] spots = new int[]{73, 74, 75, 76, 77, 78, 79, 81, 82, 83, 84, 85, 86, 87, 89, 90, 91, 92, 93, 94, 95, 97, 98, 99, 100, 101, 102, 103};
        ArrayList<Integer> landingStrip = new ArrayList<>();
        for (Integer i : spots){
            landingStrip.add(i);
        }

        ArrayList<Integer> center = new ArrayList<>();
        center.add(80);
        center.add(88);
        center.add(96);
        center.add(104);

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

    // Check to see if tile is free.
    public boolean isTileFree(int tileNr) {
        return board.get(tileNr).isFree();
    }

    public void printBoard() {
        System.out.println(board.values());
    }
}
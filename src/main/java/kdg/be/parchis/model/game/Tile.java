/*
This code is used to make the tile-logic.
*/

package kdg.be.parchis.model.game;

import kdg.be.parchis.model.musiclogic.Sound;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final int NR;
    private List<Pawn> standingPawns;
    private boolean hasPawn;
    private boolean isBarrier;
    private Color barrierColor;
    private boolean safe;

    public Tile(TileKind kind, int id) {
        NR = id;
        standingPawns = new ArrayList<>(2);
        hasPawn = false;
        isBarrier = false;
        safe = kind.equals(TileKind.SAFESPACE);
    }

    public void pawnLands(Pawn landedPawn) {
        if (hasPawn && safe && !isBarrier) {
            for (Pawn p : standingPawns) {
                if (landedPawn.getPawnNumber() != p.getPawnNumber() &&
                        landedPawn.owner.equals(p.owner)) {
                    isBarrier = true;
                    Sound.barrierMade();
                    barrierColor = landedPawn.owner.getColor();
                    landedPawn.owner.setBarrier();
                    break;
                }
            }
        } else if (!hasPawn) {
            hasPawn = true;
        }
        standingPawns.add(landedPawn);
    }

    public void pawnLeaves(Pawn leavingPawn) {
        if (isBarrier && leavingPawn.owner.getColor().equals(barrierColor)) {
            isBarrier = false;
            leavingPawn.owner.setBarrier();
        } else if (standingPawns.size() == 1) {
            hasPawn = false;
        }
        standingPawns.remove(leavingPawn);
    }

    public boolean IsBarrier() {
        return isBarrier;
    }

    public boolean isFree() {
        return !hasPawn;
    }

    public int getNr() {
        return NR;
    }

    public Color getBarrierColor() {
        return barrierColor;
    }

    public boolean getSafe() {
        return safe;
    }

    public List<Pawn> getStandingPawns() {
        return standingPawns;
    }
}
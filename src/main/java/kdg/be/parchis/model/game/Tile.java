package kdg.be.parchis.model.game;

import kdg.be.parchis.model.musiclogic.Sound;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    /**
     * The tile class shows what kind of tile it is and which pawns are standing on it. Also can tell if
     * there is a barrier made on it and says when there is a barrier on it.
     * Authors: Rui Daniel Gomes Vieira & Raf Vermeylen
     */
    private final int nr;
    private List<Pawn> standingPawns;
    private boolean hasPawn;
    private boolean isBarrier;
    private Color barrierColor;
    private boolean safe;

    public Tile(TileKind kind, int id) {
        nr = id;
        standingPawns = new ArrayList<>(2);
        hasPawn = false;
        isBarrier = false;
        safe = kind.equals(TileKind.SAFESPACE);
    }

    public void pawnLands(Pawn landedPawn) {
        if (hasPawn && safe && !isBarrier) {
            for (Pawn p : standingPawns) {
                if (landedPawn.getPawnNumber() != p.getPawnNumber() &&
                        landedPawn.getOwner().equals(p.getOwner())) {
                    isBarrier = true;
                    Sound.barrierMade();
                    barrierColor = landedPawn.getOwner().getColor();
                    landedPawn.getOwner().setBarrier();
                    break;
                }
            }
        } else if (!hasPawn) {
            hasPawn = true;
        }
        standingPawns.add(landedPawn);
    }

    public void pawnLeaves(Pawn leavingPawn) {
        if (isBarrier && leavingPawn.getOwner().getColor().equals(barrierColor)) {
            isBarrier = false;
            leavingPawn.getOwner().setBarrier();
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
        return nr;
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
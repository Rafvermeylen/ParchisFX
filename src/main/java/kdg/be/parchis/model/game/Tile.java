package kdg.be.parchis.model.game;

import kdg.be.parchis.model.musicLogic.Sound;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final TileKind kind;
    private final int nr;
    private List<Pawn> standingPawns;
    private boolean hasPawn;
    private boolean isBarrier;
    private Color barrierColor;
    private boolean safe;

    public Tile(TileKind kind, int id) {
        this.kind = kind;
        nr = id;
        standingPawns = new ArrayList<>(2);
        hasPawn = false;
        isBarrier = false;
        safe = kind.equals(TileKind.SAFESPACE);
    }

    public int getNr() {
        return nr;
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

    public List<Pawn> getStandingPawns() {
        return standingPawns;
    }

    public Color getBarrierColor() {
        return barrierColor;
    }

    public boolean getSafe() {
        return safe;
    }
}
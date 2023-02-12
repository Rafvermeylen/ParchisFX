package kdg.be.parchis.model.game;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private final TileKind kind;
    private final int nr;
    private List<Pawn> standingPawns;
    private boolean hasPawn;
    private boolean isBarrier;
    private Colors barrierColor;
    private boolean isSafe;

    public Tile(TileKind kind, int id) {
        this.kind = kind;
        nr = id;
        standingPawns = new ArrayList<>(2);
        hasPawn = false;
        isBarrier = false;
        isSafe = kind.equals(TileKind.SAFESPACE);
    }

    public TileKind getKind() {
        return kind;
    }

    public int getNr() {
        return nr;
    }

    public void pawnLands(Pawn landedPawn){
        if (hasPawn && isSafe){
            for (Pawn p : standingPawns){
                if (landedPawn.getPawnNumber() != p.getPawnNumber() &&
                        landedPawn.owner.equals(p.owner)){
                    isBarrier = true;
                    barrierColor = landedPawn.owner.getColor();
                    landedPawn.owner.setBarrier();
                    //ui.printBarrierMade();
                    break;
                }
            }
        } else if (!hasPawn) {
            hasPawn = true;
        }
        standingPawns.add(landedPawn);
    }
    public void pawnLeaves(Pawn leavingPawn){
        if (isBarrier && leavingPawn.owner.getColor().equals(barrierColor)){
            isBarrier = false;
            leavingPawn.owner.setBarrier();
            //ui.printBarrierdead();
        } else {
            hasPawn = false;
        }
        standingPawns.remove(leavingPawn);
    }

    public boolean IsBarrier(){
        return isBarrier;
    }

    public boolean isFree(){
        return !hasPawn;
    }

    @Override
    public String toString() {
        return "Tile " + nr;
    }

    public List<Pawn> getStandingPawns(){
        return standingPawns;
    }

    public Pawn getPawnOfPlayer(Player p){
        for (Pawn pawn : standingPawns){
            if (pawn.owner == p){
                return pawn;
            }
        }
        return null;
    }

    public boolean getIsSafe(){
        return isSafe;
    }
}
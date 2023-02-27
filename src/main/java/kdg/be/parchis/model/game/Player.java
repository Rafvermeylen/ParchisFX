package kdg.be.parchis.model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
    private final String name;
    private final Colors color;
    private int startPosition;
    private int nestPosition;
    public List<Pawn> pawns;
    private boolean isFinished;
    private boolean hasBarrier;


    // Initialise players and putting colours on right tile.
    public Player(String name, Colors color) {
        this.name = name;
        this.color = color;
        pawns = new ArrayList<>(4);
        isFinished = false;
        if (color.equals(Colors.YELLOW)) {
            startPosition = 5;
            nestPosition = 69;
        } else if (color.equals(Colors.BLUE)) {
            startPosition = 22;
            nestPosition = 70;
        } else if (color.equals(Colors.RED)) {
            startPosition = 39;
            nestPosition = 71;
        } else if (color.equals(Colors.GREEN)) {
            startPosition = 56;
            nestPosition = 72;
        }
        hasBarrier=false;
    }

    public String getName() {
        return name;
    }


    public Colors getColor() {
        return color;
    }


    public int getStartPosition() {
        return startPosition;
    }


    public int getNestPosition() {
        return nestPosition;
    }

    public boolean canMove(Board board, int thrown) {
        for (Pawn p : pawns) {
            if (p.isCanMove(board, thrown) && !p.isFinished()) {
                return true;
            }
        }
        return false;
    }

    public Pawn firstLeavesNest(Tile startTile) {
        for (Pawn p : pawns) {
            if (!p.isInGame()) {
                p.leaveNest(startTile);
                return p;
            }
        }
        return null;
    }

    public int moveByTile(Pawn p, Tile t) {
        p.move(t);

        //yellow pawn staying on landingstip and finishing
        if (p.getOnLandingstrip() && t.getNr() > 80 && color.equals(Colors.YELLOW)) {
            return (-t.getNr() + 80);
        } else if (p.getOnLandingstrip() && t.getNr() == 80 && color.equals(Colors.YELLOW)) {
            p.Finished();
            return 0;
        }

        //blue pawn staying on landingstip and finishing
        if (p.getOnLandingstrip() && t.getNr() > 88 && color.equals(Colors.BLUE)) {
            return (-t.getNr() + 88);
        } else if (p.getOnLandingstrip() && t.getNr() == 88 && color.equals(Colors.BLUE)) {
            p.Finished();
            return 0;
        }

        //red pawn staying on landingstip and finishing
        if (p.getOnLandingstrip() && t.getNr() > 96 && color.equals(Colors.RED)) {
            return (-t.getNr() + 96);
        } else if (p.getOnLandingstrip() && t.getNr() == 96 && color.equals(Colors.RED)) {
            p.Finished();
            return 0;
        }

        //green pawn staying on landingstip and finishing
        if (p.getOnLandingstrip() && t.getNr() > 104 && color.equals(Colors.GREEN)) {
            return (-t.getNr() + 104);
        } else if (p.getOnLandingstrip() && t.getNr() == 104 && color.equals(Colors.GREEN)) {
            p.Finished();
            return 0;
        }

        if (p.getOnLandingstrip()) {
            return 0;
        }


        //If a yellow pawn goes past 68, it goes to the landingstrip
        if (p.getPosition().getNr() > 68 && color.equals(Colors.YELLOW) && p.HasMoved() && !p.getOnLandingstrip()) {
            p.isOnLandingstrip();
            return (p.getPosition().getNr() + 4);
            //If pawn is not yellow, go around the board.
        } else if (p.getPosition().getNr() > 68 && !color.equals(Colors.YELLOW)) {
            return (p.getPosition().getNr() - 68);
        }

        //If a blue pawn passes tile 17, it goes to landingstrip
        if (p.getPosition().getNr() > 17 && color.equals(Colors.BLUE) && p.HasMoved() && !p.getOnLandingstrip()) {
            p.isOnLandingstrip();
            //p.ui.printMove(p.getPawnNumber(), oldLocation, p.getPosition());
            return (p.getPosition().getNr() + 63);
        }

        //If a red pawn passes tile 34, it goes to landingstrip
        if (p.getPosition().getNr() > 34 && color.equals(Colors.RED) && p.HasMoved() && !p.getOnLandingstrip()) {
            p.isOnLandingstrip();
            //p.ui.printMove(p.getPawnNumber(), oldLocation, p.getPosition());
            return (p.getPosition().getNr() + 54);
        }

        //If a green pawn passes tile 51, it goes to landingstrip
        if (p.getPosition().getNr() > 51 && color.equals(Colors.GREEN) && p.HasMoved() && !p.getOnLandingstrip()) {
            p.isOnLandingstrip();
            //p.ui.printMove(p.getPawnNumber(), oldLocation, p.getPosition());
            return (p.getPosition().getNr() + 45);
        }


        //p.ui.printMove(p.getPawnNumber(), oldLocation, p.getPosition());
        return 0;
    }

    public void checkIfFinished(){
        for (Pawn p : pawns){
            if (!p.isFinished()){
                return;
            }
        }
        finish();
    }

    public void finish() {
        isFinished = true;
    }

    public boolean getIsFinished(){
        return isFinished;
    }

    public void setPawns(Board board) {
        pawns.add(new Pawn(1, this, board.board.get(nestPosition)));
        pawns.add(new Pawn(2, this, board.board.get(nestPosition)));
        pawns.add(new Pawn(3, this, board.board.get(nestPosition)));
        pawns.add(new Pawn(4, this, board.board.get(nestPosition)));
    }

    public boolean isNestEmpty(){
        for (Pawn p : pawns){
            if (!p.isInGame()){
                return false;
            }
        }
        return true;
    }

    public Pawn firstMoveablePawn(Board board) {
        for (Pawn p : pawns) {
            if (p.isCanMove(board, Die.getThrown())) {
                return p;
            }
        }
        return null;
    }

    public Pawn firstBarrierPawn() {
        for (Pawn p : pawns) {
            if (p.getPosition().IsBarrier()) {
                return p;
            }
        }
        return null;
    }

    public boolean canFinish(Board board){
        for (Pawn p : pawns){
            if (p.canFinish(board)){
                return true;
            }
        }
        return false;
    }

    public Pawn getFinisherPawn(Board board){
        for (Pawn p : pawns){
            if (p.canFinish(board)){
                return p;
            }
        }
        return null;
    }

    public void setBarrier(){
        hasBarrier= !hasBarrier;
    }

    public boolean getHasBarrier(){
        return hasBarrier;
    }

    public List<Pawn> getPawns() {
        return pawns;
    }

    public Pawn getKillPawn(Board board){
        for(Pawn p : pawns){
            if (p.canKill(board)){
                return p;
            }
        }
        return null;
    }

    public boolean canKill(Board board){
        for (Pawn p : pawns){
            if (p.canKill(board)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return color == player.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return name;
    }

}
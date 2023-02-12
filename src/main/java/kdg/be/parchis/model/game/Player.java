package kdg.be.parchis.model.game;

import java.util.ArrayList;
import java.util.List;

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
            if (p.isCanMove(board, thrown)) {
                return true;
            }
        }
        return false;
    }


    public boolean isNestFree(Board board) {
        for (Pawn p : pawns) {
            if (p.getLocation() == board.board.get(nestPosition) && !p.isFinished()) {
                return false;
            }
        }
        return true;
    }


    public void firstLeavesNest(Tile startTile) {
        for (Pawn p : pawns) {
            if (!p.isInGame()) {
                p.leaveNest(startTile);
                //p.ui.printEscapeNest();
                break;
            }
        }
    }


    public Pawn choosePawn(Board board, int thrown) {
        List<Integer> moveAblePawns = new ArrayList<>(4);
        for (Pawn p : pawns) {

            if (p.isCanMove(board, thrown) && p.checkNoBarrier(thrown, board)) {
                //p.ui.printLocation(p.getPawnNumber(), p.getPosition(), p.isInGame(), p.isFinished());
                moveAblePawns.add(p.getPawnNumber());
            }
        }
        /*
        int choice = ui.makeChoiceMove(moveAblePawns);
        for (Pawn p : pawns) {
            if (p.getPawnNumber() == choice) {
                return p;
            }
        }

         */
        return pawns.get(0);
    }


    public int moveByTile(Pawn p, Tile t) {
        Tile oldLocation = p.getPosition();

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
            //p.ui.printMove(p.getPawnNumber(), oldLocation, p.getPosition());
            return (p.getPosition().getNr() + 3);
            //If pawn is not yellow, go around the board.
        } else if (p.getPosition().getNr() > 68 && !color.equals(Colors.YELLOW)) {
            //p.ui.printMove(p.getPawnNumber(), oldLocation, p.getPosition());
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
            return (p.getPosition().getNr() + 44);
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


    public void printLocationPawns() {
        int totalNest = 0;
        for (Pawn p : pawns) {
            //p.ui.printLocation(p.getPawnNumber(), p.getPosition(), p.isInGame(), p.isFinished());

            if (!p.isInGame()) {
                totalNest++;
            }

        }

        if (totalNest > 0) {
            //ui.showAmountInNest(totalNest);
        }

    }

    public void setPawns() {
        pawns.add(new Pawn(1, this));
        pawns.add(new Pawn(2, this));
        pawns.add(new Pawn(3, this));
        pawns.add(new Pawn(4, this));
    }

    public boolean isNestEmpty(){
        for (Pawn p : pawns){
            if (!p.isInGame()){
                return false;
            }
        }
        return true;
    }

    public void setBarrier(){
        hasBarrier= !hasBarrier;
    }
    public boolean getHasBarrier(){
        return hasBarrier;
    }
}
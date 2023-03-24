package kdg.be.parchis.model.game;

import kdg.be.parchis.model.musiclogic.Sound;
import java.util.ArrayList;

public class Pawn {
    /**
     * The pawn class represents the pawn that a player has. It has a position and a number to make every pawn
     * unique. Here the pawn can move, checks if it can move or not and shows in what state the pawn is (finished
     * or in game).
     * Authors: Rui Daniel Gomes Vieira & Raf Vermeylen
     */
    private final Player owner;
    private final int pawnnumber;
    private Tile position;
    private boolean inGame;
    private boolean isFinished;
    private boolean hasMoved;
    private boolean onLandingstrip;


    public Pawn(int pawnNumber, Player owner, Tile nest) {
        this.pawnnumber = pawnNumber;
        inGame = false;
        isFinished = false;
        hasMoved = false;
        onLandingstrip = false;
        this.owner = owner;
        position = nest;
    }

    public void leaveNest(Tile startTile) {
        position = startTile;
        inGame = true;
        position.pawnLands(this);
        hasMoved = false;
    }


    public void toNest(Tile nestTile) {
        position.pawnLeaves(this);
        position = nestTile;
        inGame = false;
    }


    public boolean isInGame() {
        return inGame;
    }


    public boolean isFinished() {
        return isFinished;
    }


    public void move(Tile newPos) {
        position.pawnLeaves(this);
        position = newPos;
        if (!hasMoved && position.getNr() < 21) {
            hasMoved = true;
        }
        position.pawnLands(this);
    }


    public boolean isCanMove(Board board, int thrown) {
        return !isFinished && isInGame() && checkNoBarrier(thrown, board);
    }


    public boolean HasMoved() {
        return hasMoved;
    }


    public void isOnLandingstrip() {
        onLandingstrip = true;
    }

    public boolean canKill(Board board, int thrown) {
        int tileToCheck = position.getNr() + thrown;
        if (tileToCheck > 68) {
            tileToCheck -= 68;
        }
        if (!board.board.get(tileToCheck).isFree() && !board.board.get(tileToCheck).IsBarrier() && !board.board.get(tileToCheck).getSafe()) {
            for (Pawn p : board.board.get(position.getNr() + thrown).getStandingPawns()) {
                if (!p.owner.getColor().equals(owner.getColor())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canFinish(Board board, int thrown) {
        ArrayList<Integer> centrumTiles = new ArrayList<>();
        centrumTiles.add(80);
        centrumTiles.add(88);
        centrumTiles.add(96);
        centrumTiles.add(104);
        return getOnLandingstrip() && centrumTiles.contains(position.getNr() + thrown) && !isFinished;
    }

    public void Finished() {
        isFinished = true;
        Sound.playVictory();
    }

    public boolean checkNoBarrier(int thrown, Board board) {
        for (int i = 1; i < thrown + 1; i++) {
            //first check if current position has barrier of another color
            if (board.board.get(position.getNr()).IsBarrier() && !board.board.get(position.getNr()).getBarrierColor().equals(owner.getColor())) {
                return false;
            }

            if (position.getNr() + i > 68 && !owner.getColor().equals(Color.YELLOW)) {
                if (board.board.get(position.getNr() + i - 68).IsBarrier()) {
                    return false;
                }
            } else {
                if (board.board.get(position.getNr() + i).IsBarrier()) {
                    return false;
                }
            }

        }
        return true;
    }

    public boolean getOnLandingstrip() {
        return onLandingstrip;
    }

    public int getPawnNumber() {
        return pawnnumber;
    }

    public Tile getPosition() {
        return position;
    }

    public void setOnLandingstrip(boolean onLandingstrip) {
        this.onLandingstrip = onLandingstrip;
    }

    public Player getOwner() {
        return owner;
    }
}
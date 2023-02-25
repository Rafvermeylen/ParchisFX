package kdg.be.parchis.model.game;

public class Pawn {
    public Player owner;
    private int pawnNumber;
    private Tile position;
    private boolean inGame;
    private boolean isFinished;
    private boolean hasMoved;
    private boolean onLandingstrip;


    public Pawn(int pawnNumber, Player owner, Tile nest) {
        this.pawnNumber = pawnNumber;
        inGame = false;
        isFinished = false;
        hasMoved = false;
        onLandingstrip = false;
        this.owner = owner;
        position = nest;
    }


    public Tile getLocation() {
        return position;
    }


    public int getPawnNumber() {
        return pawnNumber;
    }


    public Tile getPosition() {
        return position;
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


    public boolean getOnLandingstrip() {
        return onLandingstrip;
    }


    public void Finished() {
        isFinished = true;
    }

    public boolean checkNoBarrier(int thrown, Board board) {
        for (int i = 1; i < thrown+1; i++) {
            //first check if current position has barrier of another color
            if (board.board.get(position.getNr()).IsBarrier() && !board.board.get(position.getNr()).getBarrierColor().equals(owner.getColor())){
                return false;
            }

            if (position.getNr()+i > 68 && !owner.getColor().equals(Colors.YELLOW)){
                if (board.board.get(position.getNr() + i -68).IsBarrier()) {
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

    public void setOnLandingstrip(boolean onLandingstrip) {
        this.onLandingstrip = onLandingstrip;
    }

    @Override
    public String toString() {
        return "Pawn is on tile " + position;
    }
}
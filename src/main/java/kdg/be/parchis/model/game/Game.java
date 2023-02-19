package kdg.be.parchis.model.game;

import kdg.be.parchis.model.menu.Score;

import java.util.*;

public class Game {
    private int turn;
    private final List<Player> players;
    private final Board board;
    private Score winner;
    private int indexTurn;
    private int amountThrows;


    public Game(List<Player> players) {
        turn = 1;
        this.players =players;
        board = new Board();
        winner = null;
        indexTurn = 0;
        amountThrows = 0;
    }

    // Check where players will start
    public void startSetup() {
        //ui.nameSetup(players);
        players.get(0).setPawns();
        players.get(1).setPawns();
        players.get(2).setPawns();
        players.get(3).setPawns();
    }


    public void startRound() {
        //ui.printTurn(turn);
        int amountOfThrows;
        int thrown = 0;

        // Check if every player has played.
        for (Player s : players) {
            boolean endTurn = false;
            Pawn lastMoved = null;

            if (s.getIsFinished()){
                continue;
            } else{
                //s.ui.printTurn(s.getName());
                amountOfThrows = 0;
            }

            while (!endTurn) {

                if (!s.getIsFinished()) {

                    // Die is thrown and 1 is added to variable 'amountOfThrows'.
                    if (s instanceof ai_Player){
                        thrown = ((ai_Player) s).throwDie();
                    } else {
                        Die.throwDie();
                        thrown = Die.getThrown();
                    }
                    amountOfThrows++;

                    //ui.printThrow(thrown);

                    //If you throw a six 3x in a row
                    if (amountOfThrows == 3 && thrown == 6 && lastMoved != null && !lastMoved.getOnLandingstrip()) {
                        lastMoved.toNest(board.board.get(s.getNestPosition()));
                        //ui.backNestTripleSix(lastMoved);
                        break;
                    } else if (amountOfThrows == 3 && thrown == 6 && lastMoved != null) {
                        if (lastMoved.owner.getColor().equals(Colors.YELLOW)) {
                            lastMoved.move(board.board.get(73));
                        } else if (lastMoved.owner.getColor().equals(Colors.BLUE)) {
                            lastMoved.move(board.board.get(81));
                        } else if (lastMoved.owner.getColor().equals(Colors.RED)) {
                            lastMoved.move(board.board.get(89));
                        } else if (lastMoved.owner.getColor().equals(Colors.GREEN)) {
                            lastMoved.move(board.board.get(97));
                        }
                        //ui.backToStartLandingStrip(lastMoved);
                        break;
                    } else if (amountOfThrows == 3 && thrown == 6) {
                        //ui.tripleSixThrow();
                        break;
                    }

                    //Check if we can move a pawn out of the nest
                    if (thrown == 5 && !s.canMove(board, thrown) && !s.isNestEmpty()) {
                        s.firstLeavesNest(board.board.get(s.getStartPosition()));
                        s.printLocationPawns();
                        checkNestKill(board.board.get(s.getStartPosition()).getPawnOfPlayer(s));
                        break;
                    } else if (thrown == 5 && !s.isNestEmpty() && !(s instanceof ai_Player)) {
                        /*
                        if (ui.printChoiceExitNest()) {
                            s.firstLeavesNest(board.board.get(s.getStartPosition()));
                            s.printLocationPawns();
                            checkNestKill(board.board.get(s.getStartPosition()).getPawnOfPlayer(s));
                            break;
                        }

                         */
                    }

                    //move 7 tiles in case you throw a six and there are no pawns in your nest
                    if (thrown == 6 && s.isNestEmpty()) {
                        thrown = 7;
                        //ui.moveSeven();
                    }

                    //Break barrier, in case there is one, when you throw a six (or seven)
                    if (s.getHasBarrier()) {
                        if (thrown == 6) {
                            for (Pawn p : s.pawns) {
                                if (p.getPosition().IsBarrier()) {
                                    p.move(board.board.get(p.getPosition().getNr() + 6));
                                    break;
                                }
                            }
                        } else if (thrown == 7) {
                            for (Pawn p : s.pawns) {
                                if (p.getPosition().IsBarrier()) {
                                    p.move(board.board.get(p.getPosition().getNr() + 7));
                                    break;
                                }
                            }
                        }
                    }


                    //Move pawn
                    if (s.canMove(board, thrown)) {
                        Pawn toMove = s.choosePawn(board, thrown);
                        if (amountOfThrows == 2) {
                            lastMoved = toMove;
                        }
                        int value = s.moveByTile(toMove, board.board.get(toMove.getPosition().getNr() + thrown));
                        if (value > 0) {
                            toMove.move(board.board.get(value));
                        } else if (value < 0) {
                            toMove.move(board.board.get(toMove.getPosition().getNr() + value + value));
                        }

                        //check if moved pawn kills
                        checkKill(toMove);


                        //Choose pawn to move 10 tiles after landing in center with another if possible.
                        if (toMove.isFinished() && s.canMove(board, thrown)) {

                            Pawn chosenPawn = s.choosePawn(board, thrown);
                            if (chosenPawn == null) {
                                //ui.printNoJumpAferWin();
                            } else {
                                //ui.printJumpAferWin();
                                value = s.moveByTile(chosenPawn, board.board.get(chosenPawn.getPosition().getNr() + 10));
                                if (value > 0) {
                                    chosenPawn.move(board.board.get(value));
                                } else if (value < 0) {
                                    chosenPawn.move(board.board.get(chosenPawn.getPosition().getNr() + value + value));
                                }
                                if (chosenPawn.owner.getColor().equals(Colors.BLUE) && chosenPawn.getPosition().getNr() < 81 && chosenPawn.getPosition().getNr() > 17) {
                                    switch (chosenPawn.getPosition().getNr()) {
                                        case 80 -> chosenPawn.owner.moveByTile(chosenPawn, board.board.get(17));
                                        case 79 -> chosenPawn.owner.moveByTile(chosenPawn, board.board.get(16));
                                    }
                                } else if (chosenPawn.owner.getColor().equals(Colors.RED) && chosenPawn.getPosition().getNr() < 89 && chosenPawn.getPosition().getNr() > 34) {
                                    switch (chosenPawn.getPosition().getNr()) {
                                        case 88 -> chosenPawn.owner.moveByTile(chosenPawn, board.board.get(34));
                                        case 87 -> chosenPawn.owner.moveByTile(chosenPawn, board.board.get(33));
                                    }
                                } else if (chosenPawn.owner.getColor().equals(Colors.GREEN) && chosenPawn.getPosition().getNr() < 97 && chosenPawn.getPosition().getNr() > 51) {
                                    switch (chosenPawn.getPosition().getNr()) {
                                        case 96 -> chosenPawn.owner.moveByTile(chosenPawn, board.board.get(51));
                                        case 95 -> chosenPawn.owner.moveByTile(chosenPawn, board.board.get(50));
                                    }
                                } else if (chosenPawn.owner.getColor().equals(Colors.YELLOW) && chosenPawn.getPosition().getNr() < 73 && chosenPawn.getPosition().getNr() > 68) {
                                    switch (chosenPawn.getPosition().getNr()) {
                                        case 72 -> chosenPawn.owner.moveByTile(chosenPawn, board.board.get(68));
                                        case 71 -> chosenPawn.owner.moveByTile(chosenPawn, board.board.get(67));
                                    }
                                }
                                checkKill(chosenPawn);
                            }
                            if (amountOfThrows == 2) {
                                lastMoved = chosenPawn;
                            }
                        }

                    } else {
                        //s.ui.printCantMove();
                    }

                    s.printLocationPawns();

                    if (thrown == 6 || thrown == 7) {
                        if (!(s instanceof ai_Player)){
                            //ui.throwAgain();
                        }
                    } else {
                        endTurn = true;
                        amountOfThrows = 0;
                    }

                }

            }
            s.checkIfFinished();
            if (s.getIsFinished() && winner == null && !(s instanceof ai_Player)) {
                winner = new Score(s.getName(), turn);
                //ui.printPlayerWon(s);
            } else if (s.getIsFinished() && winner != null){
                //ui.printPlayerFinished(s);
            }
            if (!(s instanceof ai_Player)){
                //ui.endTurn();
            }
        }

        turn++;
    }


    public boolean hasGameEnded() {
        for (Player s : players) {

            if (!s.getIsFinished() && !(s instanceof ai_Player)) {
                return false;
            }

        }
        //ui.printEndGame(turn, winner);
        return true;
    }

    public void checkKill(Pawn moved) {
        if (moved.getPosition().getStandingPawns().size() >= 2
                && !moved.getPosition().getIsSafe()
                && moved.getPosition().getNr()!= 52) {
            for (Pawn p : moved.getPosition().getStandingPawns()) {
                if (p.owner != moved.owner) {
                    p.toNest(board.board.get(p.owner.getNestPosition()));
                    break;
                }
            }
            //Move killer pawn 20 tiles (if possible)
            if (!moved.isFinished()) {
                if (moved.checkNoBarrier(20, board)) {
                    int value = moved.owner.moveByTile(moved, board.board.get(moved.getPosition().getNr() + 20));
                    if (value > 0) {
                        moved.move(board.board.get(value));
                    } else if (value < 0) {
                        moved.move(board.board.get(moved.getPosition().getNr() + value + value));
                    }
                    //Make sure that if pawn goes on the landingstrip and off again, it won't go on another landingstrip
                    if (moved.owner.getColor().equals(Colors.BLUE) && moved.getPosition().getNr() < 81 && moved.getPosition().getNr() > 17) {
                        switch (moved.getPosition().getNr()) {
                            case 80 -> moved.owner.moveByTile(moved, board.board.get(17));
                            case 79 -> moved.owner.moveByTile(moved, board.board.get(16));
                            case 78 -> moved.owner.moveByTile(moved, board.board.get(15));
                            case 77 -> moved.owner.moveByTile(moved, board.board.get(14));
                        }
                    } else if (moved.owner.getColor().equals(Colors.RED) && moved.getPosition().getNr() < 89 && moved.getPosition().getNr() > 34) {
                        switch (moved.getPosition().getNr()) {
                            case 88 -> moved.owner.moveByTile(moved, board.board.get(34));
                            case 87 -> moved.owner.moveByTile(moved, board.board.get(33));
                            case 86 -> moved.owner.moveByTile(moved, board.board.get(32));
                            case 85 -> moved.owner.moveByTile(moved, board.board.get(31));
                        }
                    } else if (moved.owner.getColor().equals(Colors.GREEN) && moved.getPosition().getNr() < 97 && moved.getPosition().getNr() > 51) {
                        switch (moved.getPosition().getNr()) {
                            case 96 -> moved.owner.moveByTile(moved, board.board.get(51));
                            case 95 -> moved.owner.moveByTile(moved, board.board.get(50));
                            case 94 -> moved.owner.moveByTile(moved, board.board.get(49));
                            case 93 -> moved.owner.moveByTile(moved, board.board.get(48));
                        }
                    } else if (moved.owner.getColor().equals(Colors.YELLOW) && moved.getPosition().getNr() < 73 && moved.getPosition().getNr() > 68) {
                        switch (moved.getPosition().getNr()) {
                            case 72 -> moved.owner.moveByTile(moved, board.board.get(68));
                            case 71 -> moved.owner.moveByTile(moved, board.board.get(67));
                            case 70 -> moved.owner.moveByTile(moved, board.board.get(66));
                            case 69 -> moved.owner.moveByTile(moved, board.board.get(65));
                        }
                    }
                    checkKill(moved);
                }
            }
        }
    }

    public void checkNestKill(Pawn outNest) {
        if (outNest.getPosition().getStandingPawns().size() == 2) {
            for (Pawn p : outNest.getPosition().getStandingPawns()) {
                if (p.owner != outNest.owner) {
                    p.toNest(board.board.get(p.owner.getNestPosition()));
                    //ui.killMessage(p);
                }
            }
        }
    }

    public void endTurn(){
        indexTurn++;
        if (indexTurn == 4){
            indexTurn=0;
            turn++;
        }
        amountThrows = 0;
    }

    public void movePawn(){

    }

    public Score getWinner() {
        return winner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void roll(){
        Die.throwDie();
        amountThrows++;
    }

    public int getIndexTurn() {
        return indexTurn;
    }

    public int getAmountThrows() {
        return amountThrows;
    }

    public Player getYellowPlayer(){
        for (Player p : players){
            if (p.getColor().equals(Colors.YELLOW)){
                return p;
            }
        }
        return null;
    }

    public Player getBluePlayer(){
        for (Player p : players){
            if (p.getColor().equals(Colors.BLUE)){
                return p;
            }
        }
        return null;
    }

    public Player getRedPlayer(){
        for (Player p : players){
            if (p.getColor().equals(Colors.RED)){
                return p;
            }
        }
        return null;
    }

    public Player getGreenPlayer(){
        for (Player p : players){
            if (p.getColor().equals(Colors.GREEN)){
                return p;
            }
        }
        return null;
    }

    public boolean canPlayerMove(Player p){
        return p.canMove(board, Die.getThrown());
    }
}
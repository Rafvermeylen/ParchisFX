package kdg.be.parchis.model.game;

import kdg.be.parchis.model.menu.Score;
import kdg.be.parchis.model.musiclogic.Sound;

import java.util.ArrayList;
import java.util.List;
public class Game {
    /**
     * This class contains the game logic and all the moves a player can do. It will determine whose
     * turn it is and which pawns moves like what.
     * Authors: Rui Daniel Gomes Vieira & Raf Vermeylen
     */
    private int turn;
    private final List<Player> players;
    private final Board board;
    private final Die die;
    private Score winner;
    private int indexTurn;
    private int dieFace;
    private int thrown;
    private boolean throwAgain;
    private int amountThrows;
    private Pawn lastMovedPawn;
    private boolean endAiTurn;

    public Game(List<Player> players) {
        turn = 1;
        this.players = players;
        board = new Board();
        winner = null;
        indexTurn = 0;
        amountThrows = 0;
        startSetup();
        endAiTurn = false;
        die = new Die();
        throwAgain = false;
    }

    public void startSetup() {
        players.get(0).setPawns(board);
        players.get(1).setPawns(board);
        players.get(2).setPawns(board);
        players.get(3).setPawns(board);
    }

    public boolean hasGameEnded() {
        for (Player s : players) {
            s.checkIfFinished();
            if (s.getIsFinished()) {
                if (!(s instanceof AiPlayer)) {
                    winner = new Score(s.getName(), turn);
                }
                return true;
            }
        }
        return false;
    }

    public Player getPlayer(String color) {
        for (Player p : players) {
            if (p.getColor().getName().equals(color)) {
                return p;
            }
        }
        return null;
    }

    public void jump10(Pawn chosenPawn) {
        int value = chosenPawn.getOwner().moveByTile(chosenPawn, board.board.get(chosenPawn.getPosition().getNr() + 10));
        if (value > 0) {
            chosenPawn.move(board.board.get(value));
        } else if (value < 0) {
            chosenPawn.move(board.board.get(chosenPawn.getPosition().getNr() + value + value));
        }
        if (chosenPawn.getOwner().getColor().equals(Color.BLUE) && chosenPawn.getPosition().getNr() < 81 && chosenPawn.getPosition().getNr() > 17) {
            switch (chosenPawn.getPosition().getNr()) {
                case 80 -> chosenPawn.getOwner().moveByTile(chosenPawn, board.board.get(17));
                case 79 -> chosenPawn.getOwner().moveByTile(chosenPawn, board.board.get(16));
            }
        } else if (chosenPawn.getOwner().getColor().equals(Color.RED) && chosenPawn.getPosition().getNr() < 89 && chosenPawn.getPosition().getNr() > 34) {
            switch (chosenPawn.getPosition().getNr()) {
                case 88 -> chosenPawn.getOwner().moveByTile(chosenPawn, board.board.get(34));
                case 87 -> chosenPawn.getOwner().moveByTile(chosenPawn, board.board.get(33));
            }
        } else if (chosenPawn.getOwner().getColor().equals(Color.GREEN) && chosenPawn.getPosition().getNr() < 97 && chosenPawn.getPosition().getNr() > 51) {
            switch (chosenPawn.getPosition().getNr()) {
                case 96 -> chosenPawn.getOwner().moveByTile(chosenPawn, board.board.get(51));
                case 95 -> chosenPawn.getOwner().moveByTile(chosenPawn, board.board.get(50));
            }
        } else if (chosenPawn.getOwner().getColor().equals(Color.YELLOW) && chosenPawn.getPosition().getNr() < 73 && chosenPawn.getPosition().getNr() > 68) {
            switch (chosenPawn.getPosition().getNr()) {
                case 72 -> chosenPawn.getOwner().moveByTile(chosenPawn, board.board.get(68));
                case 71 -> chosenPawn.getOwner().moveByTile(chosenPawn, board.board.get(67));
            }
        }
        if (chosenPawn.getPosition().getNr() < 69) {
            chosenPawn.setOnLandingstrip(false);
        }
        checkKill(chosenPawn);
        if (amountThrows == 2) {
            lastMovedPawn = chosenPawn;
        }
    }

    public void checkKill(Pawn moved) {
        boolean isKilled = false;
        if (!moved.getPosition().getSafe() && moved.getPosition().getStandingPawns().size() > 1) {
            for (Pawn p : moved.getPosition().getStandingPawns()) {
                if (!p.getOwner().equals(moved.getOwner())) {
                    p.toNest(board.board.get(p.getOwner().getNestPosition()));
                    isKilled = true;
                }
            }
            //Move killer pawn 20 tiles (if possible)
            if (!moved.isFinished() && isKilled) {
                if (moved.checkNoBarrier(20, board)) {
                    int value = moved.getOwner().moveByTile(moved, board.board.get(moved.getPosition().getNr() + 20));
                    if (value > 0) {
                        moved.getOwner().moveByTile(moved, board.board.get(value));
                    } else if (value < 0) {
                        moved.getOwner().moveByTile(moved, board.board.get(moved.getPosition().getNr() + value + value));
                    }

                    //Make sure that if pawn goes on the landingstrip and off again, it won't go on another landingstrip
                    if (moved.getOwner().getColor().equals(Color.BLUE) && moved.getPosition().getNr() < 81 && moved.getPosition().getNr() > 17) {
                        switch (moved.getPosition().getNr()) {
                            case 18 -> moved.getOwner().moveByTile(moved, board.board.get(81));
                            case 19 -> moved.getOwner().moveByTile(moved, board.board.get(82));
                            case 80 -> moved.getOwner().moveByTile(moved, board.board.get(17));
                            case 79 -> moved.getOwner().moveByTile(moved, board.board.get(16));
                            case 78 -> moved.getOwner().moveByTile(moved, board.board.get(15));
                            case 77 -> moved.getOwner().moveByTile(moved, board.board.get(14));
                        }
                        // Goes to right position if blue gets a higher position than 88 (landingstrip ends at 88)
                    } else if (moved.getOwner().getColor().equals(Color.BLUE) && moved.getPosition().getNr() > 88) {
                        switch (moved.getPosition().getNr()) {
                            case 89 -> moved.getOwner().moveByTile(moved, board.board.get(87));
                            case 90 -> moved.getOwner().moveByTile(moved, board.board.get(86));
                            case 91 -> moved.getOwner().moveByTile(moved, board.board.get(85));
                            case 92 -> moved.getOwner().moveByTile(moved, board.board.get(84));
                            case 93 -> moved.getOwner().moveByTile(moved, board.board.get(83));
                            case 94 -> moved.getOwner().moveByTile(moved, board.board.get(82));
                            case 95 -> moved.getOwner().moveByTile(moved, board.board.get(81));
                            case 96 -> moved.getOwner().moveByTile(moved, board.board.get(17));
                            case 97 -> moved.getOwner().moveByTile(moved, board.board.get(16));
                            case 98 -> moved.getOwner().moveByTile(moved, board.board.get(15));
                            case 99 -> moved.getOwner().moveByTile(moved, board.board.get(14));
                            case 100 -> moved.getOwner().moveByTile(moved, board.board.get(13));
                            case 101 -> moved.getOwner().moveByTile(moved, board.board.get(12));
                            case 102 -> moved.getOwner().moveByTile(moved, board.board.get(11));
                            case 103 -> moved.getOwner().moveByTile(moved, board.board.get(10));
                            case 104 -> moved.getOwner().moveByTile(moved, board.board.get(9));
                            case 105 -> moved.getOwner().moveByTile(moved, board.board.get(8));
                            case 106 -> moved.getOwner().moveByTile(moved, board.board.get(7));
                            case 107 -> moved.getOwner().moveByTile(moved, board.board.get(6));
                        }
                    } else if (moved.getOwner().getColor().equals(Color.RED) && moved.getPosition().getNr() < 89 && moved.getPosition().getNr() > 34) {
                        switch (moved.getPosition().getNr()) {
                            case 88 -> moved.getOwner().moveByTile(moved, board.board.get(34));
                            case 87 -> moved.getOwner().moveByTile(moved, board.board.get(33));
                            case 86 -> moved.getOwner().moveByTile(moved, board.board.get(32));
                            case 85 -> moved.getOwner().moveByTile(moved, board.board.get(31));
                        }
                    } else if (moved.getOwner().getColor().equals(Color.RED) && moved.getPosition().getNr() > 96) {
                        switch (moved.getPosition().getNr()) {
                            case 97 -> moved.getOwner().moveByTile(moved, board.board.get(95));
                            case 98 -> moved.getOwner().moveByTile(moved, board.board.get(94));
                            case 99 -> moved.getOwner().moveByTile(moved, board.board.get(93));
                            case 100 -> moved.getOwner().moveByTile(moved, board.board.get(92));
                            case 101 -> moved.getOwner().moveByTile(moved, board.board.get(91));
                            case 102 -> moved.getOwner().moveByTile(moved, board.board.get(90));
                            case 103 -> moved.getOwner().moveByTile(moved, board.board.get(89));
                            case 104 -> moved.getOwner().moveByTile(moved, board.board.get(34));
                            case 105 -> moved.getOwner().moveByTile(moved, board.board.get(33));
                            case 106 -> moved.getOwner().moveByTile(moved, board.board.get(32));
                            case 107 -> moved.getOwner().moveByTile(moved, board.board.get(31));
                            case 108 -> moved.getOwner().moveByTile(moved, board.board.get(30));
                            case 109 -> moved.getOwner().moveByTile(moved, board.board.get(29));
                            case 110 -> moved.getOwner().moveByTile(moved, board.board.get(28));
                            case 111 -> moved.getOwner().moveByTile(moved, board.board.get(27));
                            case 112 -> moved.getOwner().moveByTile(moved, board.board.get(26));
                            case 113 -> moved.getOwner().moveByTile(moved, board.board.get(25));
                            case 114 -> moved.getOwner().moveByTile(moved, board.board.get(24));
                            case 115 -> moved.getOwner().moveByTile(moved, board.board.get(23));
                        }
                    } else if (moved.getOwner().getColor().equals(Color.GREEN) && moved.getPosition().getNr() < 97 && moved.getPosition().getNr() > 51) {
                        switch (moved.getPosition().getNr()) {
                            case 96 -> moved.getOwner().moveByTile(moved, board.board.get(51));
                            case 95 -> moved.getOwner().moveByTile(moved, board.board.get(50));
                            case 94 -> moved.getOwner().moveByTile(moved, board.board.get(49));
                            case 93 -> moved.getOwner().moveByTile(moved, board.board.get(48));
                        }
                    } else if (moved.getOwner().getColor().equals(Color.GREEN) && moved.getPosition().getNr() > 104) {
                        switch (moved.getPosition().getNr()) {
                            case 105 -> moved.getOwner().moveByTile(moved, board.board.get(103));
                            case 106 -> moved.getOwner().moveByTile(moved, board.board.get(102));
                            case 107 -> moved.getOwner().moveByTile(moved, board.board.get(101));
                            case 108 -> moved.getOwner().moveByTile(moved, board.board.get(100));
                            case 109 -> moved.getOwner().moveByTile(moved, board.board.get(99));
                            case 110 -> moved.getOwner().moveByTile(moved, board.board.get(98));
                            case 111 -> moved.getOwner().moveByTile(moved, board.board.get(97));
                            case 112 -> moved.getOwner().moveByTile(moved, board.board.get(51));
                            case 113 -> moved.getOwner().moveByTile(moved, board.board.get(50));
                            case 114 -> moved.getOwner().moveByTile(moved, board.board.get(49));
                            case 115 -> moved.getOwner().moveByTile(moved, board.board.get(48));
                            case 116 -> moved.getOwner().moveByTile(moved, board.board.get(47));
                            case 117 -> moved.getOwner().moveByTile(moved, board.board.get(46));
                            case 118 -> moved.getOwner().moveByTile(moved, board.board.get(45));
                            case 119 -> moved.getOwner().moveByTile(moved, board.board.get(44));
                            case 120 -> moved.getOwner().moveByTile(moved, board.board.get(43));
                            case 121 -> moved.getOwner().moveByTile(moved, board.board.get(42));
                            case 122 -> moved.getOwner().moveByTile(moved, board.board.get(41));
                            case 123 -> moved.getOwner().moveByTile(moved, board.board.get(40));
                        }
                    } else if (moved.getOwner().getColor().equals(Color.YELLOW) && moved.getPosition().getNr() < 73 && moved.getPosition().getNr() > 68) {
                        switch (moved.getPosition().getNr()) {
                            case 72 -> moved.getOwner().moveByTile(moved, board.board.get(68));
                            case 71 -> moved.getOwner().moveByTile(moved, board.board.get(67));
                            case 70 -> moved.getOwner().moveByTile(moved, board.board.get(66));
                            case 69 -> moved.getOwner().moveByTile(moved, board.board.get(65));
                        }
                    } else if (moved.getOwner().getColor().equals(Color.YELLOW) && moved.getPosition().getNr() > 80) {
                        switch (moved.getPosition().getNr()) {
                            case 81 -> moved.getOwner().moveByTile(moved, board.board.get(79));
                            case 82 -> moved.getOwner().moveByTile(moved, board.board.get(78));
                            case 83 -> moved.getOwner().moveByTile(moved, board.board.get(77));
                            case 84 -> moved.getOwner().moveByTile(moved, board.board.get(76));
                            case 85 -> moved.getOwner().moveByTile(moved, board.board.get(75));
                            case 86 -> moved.getOwner().moveByTile(moved, board.board.get(74));
                            case 87 -> moved.getOwner().moveByTile(moved, board.board.get(73));
                            case 88 -> moved.getOwner().moveByTile(moved, board.board.get(68));
                            case 89 -> moved.getOwner().moveByTile(moved, board.board.get(67));
                            case 90 -> moved.getOwner().moveByTile(moved, board.board.get(66));
                            case 91 -> moved.getOwner().moveByTile(moved, board.board.get(65));
                            case 92 -> moved.getOwner().moveByTile(moved, board.board.get(64));
                            case 93 -> moved.getOwner().moveByTile(moved, board.board.get(63));
                            case 94 -> moved.getOwner().moveByTile(moved, board.board.get(62));
                            case 95 -> moved.getOwner().moveByTile(moved, board.board.get(61));
                            case 96 -> moved.getOwner().moveByTile(moved, board.board.get(60));
                            case 97 -> moved.getOwner().moveByTile(moved, board.board.get(59));
                            case 98 -> moved.getOwner().moveByTile(moved, board.board.get(58));
                            case 99 -> moved.getOwner().moveByTile(moved, board.board.get(57));
                        }
                    }
                }
                if (moved.getPosition().getNr() < 69) {
                    moved.setOnLandingstrip(false);
                }
                Sound.playKill();
                checkKill(moved);
            }
        }
    }

    public void checkNestKill(Pawn outNest) {
        if (outNest.getPosition().getStandingPawns().size() == 2) {
            for (Pawn p : outNest.getPosition().getStandingPawns()) {
                if (p.getOwner() != outNest.getOwner()) {
                    p.toNest(board.board.get(p.getOwner().getNestPosition()));
                }
            }
        }
    }

    public void endTurn() {
        endAiTurn = false;
        indexTurn++;
        if (indexTurn == 4) {
            indexTurn = 0;
            turn++;
        }
        amountThrows = 0;
        lastMovedPawn = null;
    }

    public void roll() {
        throwAgain = false;
        thrown = die.roll();
        dieFace = thrown;
        amountThrows++;
        if (thrown == 6) {
            throwAgain = true;
            if (players.get(indexTurn).isNestEmpty()) {
                thrown = 7;
            }
        }
    }

    public boolean canPlayerMove(Player p) {
        return p.canMove(board, thrown);
    }

    public void leaveNest(String color) {
        Pawn left = getPlayer(color).firstLeavesNest(board.board.get(getPlayer(color).getStartPosition()));
        checkNestKill(left);
    }

    public void movePawn(Player p, Pawn pawn) {
        lastMovedPawn = pawn;
        int value = p.moveByTile(pawn, board.board.get(pawn.getPosition().getNr() + thrown));
        if (value > 0) {
            p.moveByTile(pawn, board.board.get(value));
        } else if (value < 0) {
            p.moveByTile(pawn, board.board.get(pawn.getPosition().getNr() + value + value));
        }
        checkKill(pawn);
    }

    public boolean isStartOK(Player p) {
        return !board.board.get(p.getStartPosition()).IsBarrier();
    }

    public void lastBackToNest() {
        if (!lastMovedPawn.getOnLandingstrip()) {
            lastMovedPawn.toNest(board.board.get(lastMovedPawn.getOwner().getNestPosition()));
        } else {
            if (lastMovedPawn.getOwner().getColor().equals(Color.YELLOW)) {
                lastMovedPawn.getOwner().moveByTile(lastMovedPawn, board.board.get(73));
            } else if (lastMovedPawn.getOwner().getColor().equals(Color.BLUE)) {
                lastMovedPawn.getOwner().moveByTile(lastMovedPawn, board.board.get(81));
            } else if (lastMovedPawn.getOwner().getColor().equals(Color.RED)) {
                lastMovedPawn.getOwner().moveByTile(lastMovedPawn, board.board.get(89));
            } else if (lastMovedPawn.getOwner().getColor().equals(Color.GREEN)) {
                lastMovedPawn.getOwner().moveByTile(lastMovedPawn, board.board.get(97));
            }
        }
    }

    public void playAiTurn() {
        if (throwAgain && amountThrows == 3) {
            if (lastMovedPawn != null) {
                Sound.playFail();
                lastBackToNest();
            }
            endAiTurn = true;
        } else {
            if (players.get(indexTurn).getHasBarrier() && (throwAgain)) {
                movePawn(players.get(indexTurn), players.get(indexTurn).firstBarrierPawn());
            } else if (players.get(indexTurn).canKill(board, thrown)) {
                movePawn(players.get(indexTurn), players.get(indexTurn).getKillPawn(board, thrown));
            } else if (players.get(indexTurn).canFinish(board, thrown)) {
                movePawn(players.get(indexTurn), players.get(indexTurn).getFinisherPawn(board, thrown));
            } else if (thrown == 5 && !players.get(indexTurn).isNestEmpty() && isStartOK(players.get(indexTurn))) {
                leaveNest(players.get(indexTurn).getColor().getName());
            } else if (players.get(indexTurn).canMove(board, thrown)) {
                movePawn(players.get(indexTurn), players.get(indexTurn).firstMoveablePawn(board, thrown));
            }

            if (lastMovedPawn != null && lastMovedPawn.isFinished() && players.get(indexTurn).canMove(board, 10)) {
                thrown = 10;
                jump10(players.get(indexTurn).firstMoveablePawn(board, thrown));
            }

            if (!throwAgain) {
                endAiTurn = true;
            }
        }
    }

    public boolean isEndAiTurn() {
        return endAiTurn;
    }

    public List<Pawn> getMoveablePawns(Player p) {
        List<Pawn> pawns = new ArrayList<>();
        for (Pawn pwn : p.pawns) {
            if (pwn.isCanMove(board, thrown)) {
                pawns.add(pwn);
            }
        }
        return pawns;
    }

    public List<Pawn> getBarrierPawns(Player p) {
        List<Pawn> pawns = new ArrayList<>();
        for (Pawn pwn : p.pawns) {
            if (pwn.getPosition() != null && pwn.getPosition().IsBarrier() && pwn.isInGame()) {
                pawns.add(pwn);
            }
        }
        return pawns;
    }

    public Score getWinner() {
        return winner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getIndexTurn() {
        return indexTurn;
    }

    public int getAmountThrows() {
        return amountThrows;
    }

    public int getTurn() {
        return turn;
    }

    public Pawn getLastMovedPawn() {
        return lastMovedPawn;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer(int i) {
        return players.get(i);
    }

    public int getCurrentPlayer() {
        return switch (players.get(indexTurn).getColor().getName()) {
            case "yellow" -> 0;
            case "blue" -> 1;
            case "red" -> 2;
            case "green" -> 3;
            default -> throw new IllegalArgumentException("Unexpected value: " + players.get(indexTurn).getColor().getName());
        };
    }

    public Player getRawPlayer(int i) {
        Color desiredColor;
        switch (i) {
            case 0 -> desiredColor = Color.YELLOW;
            case 1 -> desiredColor = Color.BLUE;
            case 2 -> desiredColor = Color.RED;
            case 3 -> desiredColor = Color.GREEN;
            default -> throw new IllegalStateException("Unexpected value: " + i);
        }
        for(Player p : players){
            if (p.getColor().equals(desiredColor)){
                return p;
            }
        }
        return null;
    }

    public boolean pawnCanMove(int index, int player) {
        return getRawPlayer(player).pawns.get(index).isCanMove(board, thrown);
    }

    public int getThrown() {
        return thrown;
    }

    public boolean isThrowAgain() {
        return throwAgain;
    }

    public int getDieFace(){
        return dieFace;
    }
}
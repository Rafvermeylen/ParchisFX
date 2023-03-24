package kdg.be.parchis.model.game;

import kdg.be.parchis.model.menu.Score;
import kdg.be.parchis.model.musiclogic.Sound;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int turn;
    private final List<Player> PLAYERS;
    private final Board BOARD;
    private final Dice DIE;
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
        this.PLAYERS = players;
        BOARD = new Board();
        winner = null;
        indexTurn = 0;
        amountThrows = 0;
        startSetup();
        endAiTurn = false;
        DIE = new Dice();
        throwAgain = false;
    }

    public void startSetup() {
        PLAYERS.get(0).setPawns(BOARD);
        PLAYERS.get(1).setPawns(BOARD);
        PLAYERS.get(2).setPawns(BOARD);
        PLAYERS.get(3).setPawns(BOARD);
    }

    public boolean hasGameEnded() {
        for (Player s : PLAYERS) {
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
        for (Player p : PLAYERS) {
            if (p.getColor().getName().equals(color)) {
                return p;
            }
        }
        return null;
    }

    public void jump10(Pawn chosenPawn) {
        int value = chosenPawn.owner.moveByTile(chosenPawn, BOARD.board.get(chosenPawn.getPosition().getNr() + 10));
        if (value > 0) {
            chosenPawn.move(BOARD.board.get(value));
        } else if (value < 0) {
            chosenPawn.move(BOARD.board.get(chosenPawn.getPosition().getNr() + value + value));
        }
        if (chosenPawn.owner.getColor().equals(Color.BLUE) && chosenPawn.getPosition().getNr() < 81 && chosenPawn.getPosition().getNr() > 17) {
            switch (chosenPawn.getPosition().getNr()) {
                case 80 -> chosenPawn.owner.moveByTile(chosenPawn, BOARD.board.get(17));
                case 79 -> chosenPawn.owner.moveByTile(chosenPawn, BOARD.board.get(16));
            }
        } else if (chosenPawn.owner.getColor().equals(Color.RED) && chosenPawn.getPosition().getNr() < 89 && chosenPawn.getPosition().getNr() > 34) {
            switch (chosenPawn.getPosition().getNr()) {
                case 88 -> chosenPawn.owner.moveByTile(chosenPawn, BOARD.board.get(34));
                case 87 -> chosenPawn.owner.moveByTile(chosenPawn, BOARD.board.get(33));
            }
        } else if (chosenPawn.owner.getColor().equals(Color.GREEN) && chosenPawn.getPosition().getNr() < 97 && chosenPawn.getPosition().getNr() > 51) {
            switch (chosenPawn.getPosition().getNr()) {
                case 96 -> chosenPawn.owner.moveByTile(chosenPawn, BOARD.board.get(51));
                case 95 -> chosenPawn.owner.moveByTile(chosenPawn, BOARD.board.get(50));
            }
        } else if (chosenPawn.owner.getColor().equals(Color.YELLOW) && chosenPawn.getPosition().getNr() < 73 && chosenPawn.getPosition().getNr() > 68) {
            switch (chosenPawn.getPosition().getNr()) {
                case 72 -> chosenPawn.owner.moveByTile(chosenPawn, BOARD.board.get(68));
                case 71 -> chosenPawn.owner.moveByTile(chosenPawn, BOARD.board.get(67));
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
                if (!p.owner.equals(moved.owner)) {
                    p.toNest(BOARD.board.get(p.owner.getNestPosition()));
                    isKilled = true;
                }
            }
            //Move killer pawn 20 tiles (if possible)
            if (!moved.isFinished() && isKilled) {
                if (moved.checkNoBarrier(20, BOARD)) {
                    int value = moved.owner.moveByTile(moved, BOARD.board.get(moved.getPosition().getNr() + 20));
                    if (value > 0) {
                        moved.owner.moveByTile(moved, BOARD.board.get(value));
                    } else if (value < 0) {
                        moved.owner.moveByTile(moved, BOARD.board.get(moved.getPosition().getNr() + value + value));
                    }

                    //Make sure that if pawn goes on the landingstrip and off again, it won't go on another landingstrip
                    if (moved.owner.getColor().equals(Color.BLUE) && moved.getPosition().getNr() < 81 && moved.getPosition().getNr() > 17) {
                        switch (moved.getPosition().getNr()) {
                            case 18 -> moved.owner.moveByTile(moved, BOARD.board.get(81));
                            case 19 -> moved.owner.moveByTile(moved, BOARD.board.get(82));
                            case 80 -> moved.owner.moveByTile(moved, BOARD.board.get(17));
                            case 79 -> moved.owner.moveByTile(moved, BOARD.board.get(16));
                            case 78 -> moved.owner.moveByTile(moved, BOARD.board.get(15));
                            case 77 -> moved.owner.moveByTile(moved, BOARD.board.get(14));
                        }
                        // Goes to right position if blue gets a higher position than 88 (landingstrip ends at 88)
                    } else if (moved.owner.getColor().equals(Color.BLUE) && moved.getPosition().getNr() > 88) {
                        switch (moved.getPosition().getNr()) {
                            case 89 -> moved.owner.moveByTile(moved, BOARD.board.get(87));
                            case 90 -> moved.owner.moveByTile(moved, BOARD.board.get(86));
                            case 91 -> moved.owner.moveByTile(moved, BOARD.board.get(85));
                            case 92 -> moved.owner.moveByTile(moved, BOARD.board.get(84));
                            case 93 -> moved.owner.moveByTile(moved, BOARD.board.get(83));
                            case 94 -> moved.owner.moveByTile(moved, BOARD.board.get(82));
                            case 95 -> moved.owner.moveByTile(moved, BOARD.board.get(81));
                            case 96 -> moved.owner.moveByTile(moved, BOARD.board.get(17));
                            case 97 -> moved.owner.moveByTile(moved, BOARD.board.get(16));
                            case 98 -> moved.owner.moveByTile(moved, BOARD.board.get(15));
                            case 99 -> moved.owner.moveByTile(moved, BOARD.board.get(14));
                            case 100 -> moved.owner.moveByTile(moved, BOARD.board.get(13));
                            case 101 -> moved.owner.moveByTile(moved, BOARD.board.get(12));
                            case 102 -> moved.owner.moveByTile(moved, BOARD.board.get(11));
                            case 103 -> moved.owner.moveByTile(moved, BOARD.board.get(10));
                            case 104 -> moved.owner.moveByTile(moved, BOARD.board.get(9));
                            case 105 -> moved.owner.moveByTile(moved, BOARD.board.get(8));
                            case 106 -> moved.owner.moveByTile(moved, BOARD.board.get(7));
                            case 107 -> moved.owner.moveByTile(moved, BOARD.board.get(6));
                        }
                    } else if (moved.owner.getColor().equals(Color.RED) && moved.getPosition().getNr() < 89 && moved.getPosition().getNr() > 34) {
                        switch (moved.getPosition().getNr()) {
                            case 88 -> moved.owner.moveByTile(moved, BOARD.board.get(34));
                            case 87 -> moved.owner.moveByTile(moved, BOARD.board.get(33));
                            case 86 -> moved.owner.moveByTile(moved, BOARD.board.get(32));
                            case 85 -> moved.owner.moveByTile(moved, BOARD.board.get(31));
                        }
                    } else if (moved.owner.getColor().equals(Color.RED) && moved.getPosition().getNr() > 96) {
                        switch (moved.getPosition().getNr()) {
                            case 97 -> moved.owner.moveByTile(moved, BOARD.board.get(95));
                            case 98 -> moved.owner.moveByTile(moved, BOARD.board.get(94));
                            case 99 -> moved.owner.moveByTile(moved, BOARD.board.get(93));
                            case 100 -> moved.owner.moveByTile(moved, BOARD.board.get(92));
                            case 101 -> moved.owner.moveByTile(moved, BOARD.board.get(91));
                            case 102 -> moved.owner.moveByTile(moved, BOARD.board.get(90));
                            case 103 -> moved.owner.moveByTile(moved, BOARD.board.get(89));
                            case 104 -> moved.owner.moveByTile(moved, BOARD.board.get(34));
                            case 105 -> moved.owner.moveByTile(moved, BOARD.board.get(33));
                            case 106 -> moved.owner.moveByTile(moved, BOARD.board.get(32));
                            case 107 -> moved.owner.moveByTile(moved, BOARD.board.get(31));
                            case 108 -> moved.owner.moveByTile(moved, BOARD.board.get(30));
                            case 109 -> moved.owner.moveByTile(moved, BOARD.board.get(29));
                            case 110 -> moved.owner.moveByTile(moved, BOARD.board.get(28));
                            case 111 -> moved.owner.moveByTile(moved, BOARD.board.get(27));
                            case 112 -> moved.owner.moveByTile(moved, BOARD.board.get(26));
                            case 113 -> moved.owner.moveByTile(moved, BOARD.board.get(25));
                            case 114 -> moved.owner.moveByTile(moved, BOARD.board.get(24));
                            case 115 -> moved.owner.moveByTile(moved, BOARD.board.get(23));
                        }
                    } else if (moved.owner.getColor().equals(Color.GREEN) && moved.getPosition().getNr() < 97 && moved.getPosition().getNr() > 51) {
                        switch (moved.getPosition().getNr()) {
                            case 96 -> moved.owner.moveByTile(moved, BOARD.board.get(51));
                            case 95 -> moved.owner.moveByTile(moved, BOARD.board.get(50));
                            case 94 -> moved.owner.moveByTile(moved, BOARD.board.get(49));
                            case 93 -> moved.owner.moveByTile(moved, BOARD.board.get(48));
                        }
                    } else if (moved.owner.getColor().equals(Color.GREEN) && moved.getPosition().getNr() > 104) {
                        switch (moved.getPosition().getNr()) {
                            case 105 -> moved.owner.moveByTile(moved, BOARD.board.get(103));
                            case 106 -> moved.owner.moveByTile(moved, BOARD.board.get(102));
                            case 107 -> moved.owner.moveByTile(moved, BOARD.board.get(101));
                            case 108 -> moved.owner.moveByTile(moved, BOARD.board.get(100));
                            case 109 -> moved.owner.moveByTile(moved, BOARD.board.get(99));
                            case 110 -> moved.owner.moveByTile(moved, BOARD.board.get(98));
                            case 111 -> moved.owner.moveByTile(moved, BOARD.board.get(97));
                            case 112 -> moved.owner.moveByTile(moved, BOARD.board.get(51));
                            case 113 -> moved.owner.moveByTile(moved, BOARD.board.get(50));
                            case 114 -> moved.owner.moveByTile(moved, BOARD.board.get(49));
                            case 115 -> moved.owner.moveByTile(moved, BOARD.board.get(48));
                            case 116 -> moved.owner.moveByTile(moved, BOARD.board.get(47));
                            case 117 -> moved.owner.moveByTile(moved, BOARD.board.get(46));
                            case 118 -> moved.owner.moveByTile(moved, BOARD.board.get(45));
                            case 119 -> moved.owner.moveByTile(moved, BOARD.board.get(44));
                            case 120 -> moved.owner.moveByTile(moved, BOARD.board.get(43));
                            case 121 -> moved.owner.moveByTile(moved, BOARD.board.get(42));
                            case 122 -> moved.owner.moveByTile(moved, BOARD.board.get(41));
                            case 123 -> moved.owner.moveByTile(moved, BOARD.board.get(40));
                        }
                    } else if (moved.owner.getColor().equals(Color.YELLOW) && moved.getPosition().getNr() < 73 && moved.getPosition().getNr() > 68) {
                        switch (moved.getPosition().getNr()) {
                            case 72 -> moved.owner.moveByTile(moved, BOARD.board.get(68));
                            case 71 -> moved.owner.moveByTile(moved, BOARD.board.get(67));
                            case 70 -> moved.owner.moveByTile(moved, BOARD.board.get(66));
                            case 69 -> moved.owner.moveByTile(moved, BOARD.board.get(65));
                        }
                    } else if (moved.owner.getColor().equals(Color.YELLOW) && moved.getPosition().getNr() > 80) {
                        switch (moved.getPosition().getNr()) {
                            case 81 -> moved.owner.moveByTile(moved, BOARD.board.get(79));
                            case 82 -> moved.owner.moveByTile(moved, BOARD.board.get(78));
                            case 83 -> moved.owner.moveByTile(moved, BOARD.board.get(77));
                            case 84 -> moved.owner.moveByTile(moved, BOARD.board.get(76));
                            case 85 -> moved.owner.moveByTile(moved, BOARD.board.get(75));
                            case 86 -> moved.owner.moveByTile(moved, BOARD.board.get(74));
                            case 87 -> moved.owner.moveByTile(moved, BOARD.board.get(73));
                            case 88 -> moved.owner.moveByTile(moved, BOARD.board.get(68));
                            case 89 -> moved.owner.moveByTile(moved, BOARD.board.get(67));
                            case 90 -> moved.owner.moveByTile(moved, BOARD.board.get(66));
                            case 91 -> moved.owner.moveByTile(moved, BOARD.board.get(65));
                            case 92 -> moved.owner.moveByTile(moved, BOARD.board.get(64));
                            case 93 -> moved.owner.moveByTile(moved, BOARD.board.get(63));
                            case 94 -> moved.owner.moveByTile(moved, BOARD.board.get(62));
                            case 95 -> moved.owner.moveByTile(moved, BOARD.board.get(61));
                            case 96 -> moved.owner.moveByTile(moved, BOARD.board.get(60));
                            case 97 -> moved.owner.moveByTile(moved, BOARD.board.get(59));
                            case 98 -> moved.owner.moveByTile(moved, BOARD.board.get(58));
                            case 99 -> moved.owner.moveByTile(moved, BOARD.board.get(57));
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
                if (p.owner != outNest.owner) {
                    p.toNest(BOARD.board.get(p.owner.getNestPosition()));
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
        thrown = DIE.roll();
        dieFace = thrown;
        amountThrows++;
        if (thrown == 6) {
            throwAgain = true;
            if (PLAYERS.get(indexTurn).isNestEmpty()) {
                thrown = 7;
            }
        }
    }

    public boolean canPlayerMove(Player p) {
        return p.canMove(BOARD, thrown);
    }

    public void leaveNest(String color) {
        Pawn left = getPlayer(color).firstLeavesNest(BOARD.board.get(getPlayer(color).getStartPosition()));
        checkNestKill(left);
    }

    public void movePawn(Player p, Pawn pawn) {
        lastMovedPawn = pawn;
        int value = p.moveByTile(pawn, BOARD.board.get(pawn.getPosition().getNr() + thrown));
        if (value > 0) {
            p.moveByTile(pawn, BOARD.board.get(value));
        } else if (value < 0) {
            p.moveByTile(pawn, BOARD.board.get(pawn.getPosition().getNr() + value + value));
        }
        checkKill(pawn);
    }

    public boolean isStartOK(Player p) {
        return !BOARD.board.get(p.getStartPosition()).IsBarrier();
    }

    public void lastBackToNest() {
        if (!lastMovedPawn.getOnLandingstrip()) {
            lastMovedPawn.toNest(BOARD.board.get(lastMovedPawn.owner.getNestPosition()));
        } else {
            if (lastMovedPawn.owner.getColor().equals(Color.YELLOW)) {
                lastMovedPawn.owner.moveByTile(lastMovedPawn, BOARD.board.get(73));
            } else if (lastMovedPawn.owner.getColor().equals(Color.BLUE)) {
                lastMovedPawn.owner.moveByTile(lastMovedPawn, BOARD.board.get(81));
            } else if (lastMovedPawn.owner.getColor().equals(Color.RED)) {
                lastMovedPawn.owner.moveByTile(lastMovedPawn, BOARD.board.get(89));
            } else if (lastMovedPawn.owner.getColor().equals(Color.GREEN)) {
                lastMovedPawn.owner.moveByTile(lastMovedPawn, BOARD.board.get(97));
            }
        }
    }

    public void playAiTurn() {
        roll();
        Sound.playRoll();
        if (throwAgain && amountThrows == 3) {
            if (lastMovedPawn != null) {
                Sound.playFail();
                lastBackToNest();
            }
            endAiTurn = true;
        } else {
            if (PLAYERS.get(indexTurn).getHasBarrier() && (throwAgain)) {
                movePawn(PLAYERS.get(indexTurn), PLAYERS.get(indexTurn).firstBarrierPawn());
            } else if (PLAYERS.get(indexTurn).canKill(BOARD, thrown)) {
                movePawn(PLAYERS.get(indexTurn), PLAYERS.get(indexTurn).getKillPawn(BOARD, thrown));
            } else if (PLAYERS.get(indexTurn).canFinish(BOARD, thrown)) {
                movePawn(PLAYERS.get(indexTurn), PLAYERS.get(indexTurn).getFinisherPawn(BOARD, thrown));
            } else if (thrown == 5 && !PLAYERS.get(indexTurn).isNestEmpty() && isStartOK(PLAYERS.get(indexTurn))) {
                leaveNest(PLAYERS.get(indexTurn).getColor().getName());
            } else if (PLAYERS.get(indexTurn).canMove(BOARD, thrown)) {
                movePawn(PLAYERS.get(indexTurn), PLAYERS.get(indexTurn).firstMoveablePawn(BOARD, thrown));
            }

            if (lastMovedPawn != null && lastMovedPawn.isFinished() && PLAYERS.get(indexTurn).canMove(BOARD, 10)) {
                thrown = 10;
                jump10(PLAYERS.get(indexTurn).firstMoveablePawn(BOARD, thrown));
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
            if (pwn.isCanMove(BOARD, thrown)) {
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
        return PLAYERS;
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
        return BOARD;
    }

    public Player getPlayer(int i) {
        return PLAYERS.get(i);
    }

    public int getCurrentPlayer() {
        return switch (PLAYERS.get(indexTurn).getColor().getName()) {
            case "yellow" -> 0;
            case "blue" -> 1;
            case "red" -> 2;
            case "green" -> 3;
            default -> throw new IllegalArgumentException("Unexpected value: " + PLAYERS.get(indexTurn).getColor().getName());
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
        for(Player p : PLAYERS){
            if (p.getColor().equals(desiredColor)){
                return p;
            }
        }
        return null;
    }

    public boolean pawnCanMove(int index, int player) {
        return getRawPlayer(player).pawns.get(index).isCanMove(BOARD, thrown);
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
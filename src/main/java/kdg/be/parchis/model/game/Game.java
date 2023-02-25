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
    private Pawn lastMovedPawn;
    private Pawn killedPawn;

    public Game(List<Player> players) {
        turn = 0;
        this.players = players;
        board = new Board();
        winner = null;
        indexTurn = 3;
        amountThrows = 0;
        startSetup();
        if (players.get(0) instanceof ai_Player) {
            playAiTurn();
            endTurn();
        }
    }

    public void startSetup() {
        players.get(0).setPawns();
        players.get(1).setPawns();
        players.get(2).setPawns();
        players.get(3).setPawns();
    }

    public boolean hasGameEnded() {
        for (Player s : players) {
            s.checkIfFinished();
            if (s.getIsFinished()) {
                if (!(s instanceof ai_Player)) {
                    winner = new Score(s.getName(), turn);
                }
                return true;
            }
        }
        return false;
    }

    public void jump10(Pawn chosenPawn) {
        int value = chosenPawn.owner.moveByTile(chosenPawn, board.board.get(chosenPawn.getPosition().getNr() + 10));
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
        if (chosenPawn.getPosition().getNr() < 69){
            chosenPawn.setOnLandingstrip(false);
        }
        checkKill(chosenPawn);
        if (amountThrows == 2) {
            lastMovedPawn = chosenPawn;
        }
    }

    public void checkKill(Pawn moved) {
        boolean isKilled = false;
        if (!moved.getPosition().getSafe() && moved.getPosition().getStandingPawns().size() >= 2) {
            for (Pawn p : moved.getPosition().getStandingPawns()) {
                if (!p.owner.equals(moved.owner)) {
                    p.toNest(board.board.get(p.owner.getNestPosition()));
                    killedPawn = p;
                    isKilled = true;
                    break;
                }
            }
            //Move killer pawn 20 tiles (if possible)
            if (!moved.isFinished() && isKilled) {
                if (moved.checkNoBarrier(20, board)) {
                    int value = moved.owner.moveByTile(moved, board.board.get(moved.getPosition().getNr() + 20));
                    if (value > 0) {
                        moved.owner.moveByTile(moved, board.board.get(value));
                    } else if (value < 0) {
                        moved.owner.moveByTile(moved, board.board.get(moved.getPosition().getNr() + value + value));
                    }

                    System.out.println("Pawn is on : " + moved.getPosition().getNr() + " before correction.");

                    //Make sure that if pawn goes on the landingstrip and off again, it won't go on another landingstrip
                    if (moved.owner.getColor().equals(Colors.BLUE) && moved.getPosition().getNr() < 81 && moved.getPosition().getNr() > 17) {
                        switch (moved.getPosition().getNr()) {
                            case 18 -> moved.owner.moveByTile(moved, board.board.get(81));
                            case 19 -> moved.owner.moveByTile(moved, board.board.get(82));
                            case 80 -> moved.owner.moveByTile(moved, board.board.get(17));
                            case 79 -> moved.owner.moveByTile(moved, board.board.get(16));
                            case 78 -> moved.owner.moveByTile(moved, board.board.get(15));
                            case 77 -> moved.owner.moveByTile(moved, board.board.get(14));
                        }
                        // Goes to right position if blue gets a higher position than 88 (landingstrip ends at 88)
                    } else if (moved.owner.getColor().equals(Colors.BLUE) && moved.getPosition().getNr() > 88) {
                        switch (moved.getPosition().getNr()) {
                            case 89 -> moved.owner.moveByTile(moved, board.board.get(87));
                            case 90 -> moved.owner.moveByTile(moved, board.board.get(86));
                            case 91 -> moved.owner.moveByTile(moved, board.board.get(85));
                            case 92 -> moved.owner.moveByTile(moved, board.board.get(84));
                            case 93 -> moved.owner.moveByTile(moved, board.board.get(83));
                            case 94 -> moved.owner.moveByTile(moved, board.board.get(82));
                            case 95 -> moved.owner.moveByTile(moved, board.board.get(81));
                            case 96 -> moved.owner.moveByTile(moved, board.board.get(17));
                            case 97 -> moved.owner.moveByTile(moved, board.board.get(16));
                            case 98 -> moved.owner.moveByTile(moved, board.board.get(15));
                            case 99 -> moved.owner.moveByTile(moved, board.board.get(14));
                            case 100 -> moved.owner.moveByTile(moved, board.board.get(13));
                            case 101 -> moved.owner.moveByTile(moved, board.board.get(12));
                            case 102 -> moved.owner.moveByTile(moved, board.board.get(11));
                            case 103 -> moved.owner.moveByTile(moved, board.board.get(10));
                            case 104 -> moved.owner.moveByTile(moved, board.board.get(9));
                            case 105 -> moved.owner.moveByTile(moved, board.board.get(8));
                            case 106 -> moved.owner.moveByTile(moved, board.board.get(7));
                            case 107 -> moved.owner.moveByTile(moved, board.board.get(6));
                        }
                    } else if (moved.owner.getColor().equals(Colors.RED) && moved.getPosition().getNr() < 89 && moved.getPosition().getNr() > 34) {
                        switch (moved.getPosition().getNr()) {
                            case 88 -> moved.owner.moveByTile(moved, board.board.get(34));
                            case 87 -> moved.owner.moveByTile(moved, board.board.get(33));
                            case 86 -> moved.owner.moveByTile(moved, board.board.get(32));
                            case 85 -> moved.owner.moveByTile(moved, board.board.get(31));
                        }
                    } else if (moved.owner.getColor().equals(Colors.RED) && moved.getPosition().getNr() > 96) {
                        switch (moved.getPosition().getNr()) {
                            case 97 -> moved.owner.moveByTile(moved, board.board.get(95));
                            case 98 -> moved.owner.moveByTile(moved, board.board.get(94));
                            case 99 -> moved.owner.moveByTile(moved, board.board.get(93));
                            case 100 -> moved.owner.moveByTile(moved, board.board.get(92));
                            case 101 -> moved.owner.moveByTile(moved, board.board.get(91));
                            case 102 -> moved.owner.moveByTile(moved, board.board.get(90));
                            case 103 -> moved.owner.moveByTile(moved, board.board.get(89));
                            case 104 -> moved.owner.moveByTile(moved, board.board.get(34));
                            case 105 -> moved.owner.moveByTile(moved, board.board.get(33));
                            case 106 -> moved.owner.moveByTile(moved, board.board.get(32));
                            case 107 -> moved.owner.moveByTile(moved, board.board.get(31));
                            case 108 -> moved.owner.moveByTile(moved, board.board.get(30));
                            case 109 -> moved.owner.moveByTile(moved, board.board.get(29));
                            case 110 -> moved.owner.moveByTile(moved, board.board.get(28));
                            case 111 -> moved.owner.moveByTile(moved, board.board.get(27));
                            case 112 -> moved.owner.moveByTile(moved, board.board.get(26));
                            case 113 -> moved.owner.moveByTile(moved, board.board.get(25));
                            case 114 -> moved.owner.moveByTile(moved, board.board.get(24));
                            case 115 -> moved.owner.moveByTile(moved, board.board.get(23));
                        }
                    } else if (moved.owner.getColor().equals(Colors.GREEN) && moved.getPosition().getNr() < 97 && moved.getPosition().getNr() > 51) {
                        switch (moved.getPosition().getNr()) {
                            case 96 -> moved.owner.moveByTile(moved, board.board.get(51));
                            case 95 -> moved.owner.moveByTile(moved, board.board.get(50));
                            case 94 -> moved.owner.moveByTile(moved, board.board.get(49));
                            case 93 -> moved.owner.moveByTile(moved, board.board.get(48));
                        }
                    } else if (moved.owner.getColor().equals(Colors.GREEN) && moved.getPosition().getNr() > 104) {
                        switch (moved.getPosition().getNr()) {
                            case 105 -> moved.owner.moveByTile(moved, board.board.get(103));
                            case 106 -> moved.owner.moveByTile(moved, board.board.get(102));
                            case 107 -> moved.owner.moveByTile(moved, board.board.get(101));
                            case 108 -> moved.owner.moveByTile(moved, board.board.get(100));
                            case 109 -> moved.owner.moveByTile(moved, board.board.get(99));
                            case 110 -> moved.owner.moveByTile(moved, board.board.get(98));
                            case 111 -> moved.owner.moveByTile(moved, board.board.get(97));
                            case 112 -> moved.owner.moveByTile(moved, board.board.get(51));
                            case 113 -> moved.owner.moveByTile(moved, board.board.get(50));
                            case 114 -> moved.owner.moveByTile(moved, board.board.get(49));
                            case 115 -> moved.owner.moveByTile(moved, board.board.get(48));
                            case 116 -> moved.owner.moveByTile(moved, board.board.get(47));
                            case 117 -> moved.owner.moveByTile(moved, board.board.get(46));
                            case 118 -> moved.owner.moveByTile(moved, board.board.get(45));
                            case 119 -> moved.owner.moveByTile(moved, board.board.get(44));
                            case 120 -> moved.owner.moveByTile(moved, board.board.get(43));
                            case 121 -> moved.owner.moveByTile(moved, board.board.get(42));
                            case 122 -> moved.owner.moveByTile(moved, board.board.get(41));
                            case 123 -> moved.owner.moveByTile(moved, board.board.get(40));
                        }
                    } else if (moved.owner.getColor().equals(Colors.YELLOW) && moved.getPosition().getNr() < 73 && moved.getPosition().getNr() > 68) {
                        switch (moved.getPosition().getNr()) {
                            case 72 -> moved.owner.moveByTile(moved, board.board.get(68));
                            case 71 -> moved.owner.moveByTile(moved, board.board.get(67));
                            case 70 -> moved.owner.moveByTile(moved, board.board.get(66));
                            case 69 -> moved.owner.moveByTile(moved, board.board.get(65));
                        }
                    } else if (moved.owner.getColor().equals(Colors.YELLOW) && moved.getPosition().getNr() > 80) {
                        switch (moved.getPosition().getNr()) {
                            case 81 -> moved.owner.moveByTile(moved, board.board.get(79));
                            case 82 -> moved.owner.moveByTile(moved, board.board.get(78));
                            case 83 -> moved.owner.moveByTile(moved, board.board.get(77));
                            case 84 -> moved.owner.moveByTile(moved, board.board.get(76));
                            case 85 -> moved.owner.moveByTile(moved, board.board.get(75));
                            case 86 -> moved.owner.moveByTile(moved, board.board.get(74));
                            case 87 -> moved.owner.moveByTile(moved, board.board.get(73));
                            case 88 -> moved.owner.moveByTile(moved, board.board.get(68));
                            case 89 -> moved.owner.moveByTile(moved, board.board.get(67));
                            case 90 -> moved.owner.moveByTile(moved, board.board.get(66));
                            case 91 -> moved.owner.moveByTile(moved, board.board.get(65));
                            case 92 -> moved.owner.moveByTile(moved, board.board.get(64));
                            case 93 -> moved.owner.moveByTile(moved, board.board.get(63));
                            case 94 -> moved.owner.moveByTile(moved, board.board.get(62));
                            case 95 -> moved.owner.moveByTile(moved, board.board.get(61));
                            case 96 -> moved.owner.moveByTile(moved, board.board.get(60));
                            case 97 -> moved.owner.moveByTile(moved, board.board.get(59));
                            case 98 -> moved.owner.moveByTile(moved, board.board.get(58));
                            case 99 -> moved.owner.moveByTile(moved, board.board.get(57));
                        }
                    }
                }
                System.out.println("Pawn is on : " + moved.getPosition().getNr() + " after correction.");
                if (moved.getPosition().getNr() < 69) {
                    moved.setOnLandingstrip(false);
                }
                checkKill(moved);
            }
        }
    }

    public void checkNestKill(Pawn outNest) {
        if (outNest.getPosition().getStandingPawns().size() == 2) {
            for (Pawn p : outNest.getPosition().getStandingPawns()) {
                if (p.owner != outNest.owner) {
                    p.toNest(board.board.get(p.owner.getNestPosition()));
                    killedPawn = p;
                }
            }
        }
    }

    public boolean endTurn() {
        boolean botActivity = false;
        indexTurn++;
        if (indexTurn == 4) {
            indexTurn = 0;
            turn++;
        }

        //if next player is AI, play AI turn and end turn.
        while (players.get(indexTurn) instanceof ai_Player){
            botActivity = true;
            amountThrows = 0;
            lastMovedPawn = null;
            killedPawn = null;
            playAiTurn();
            indexTurn++;
            if (indexTurn == 4) {
                indexTurn = 0;
                turn++;
            }
        }
        amountThrows = 0;
        lastMovedPawn = null;
        killedPawn = null;
        return botActivity;
    }

    public Score getWinner() {
        return winner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void roll() {
        Die.throwDie();
        amountThrows++;
    }

    public int getIndexTurn() {
        return indexTurn;
    }

    public int getAmountThrows() {
        return amountThrows;
    }

    public Player getYellowPlayer() {
        for (Player p : players) {
            if (p.getColor().equals(Colors.YELLOW)) {
                return p;
            }
        }
        return null;
    }

    public Player getBluePlayer() {
        for (Player p : players) {
            if (p.getColor().equals(Colors.BLUE)) {
                return p;
            }
        }
        return null;
    }

    public Player getRedPlayer() {
        for (Player p : players) {
            if (p.getColor().equals(Colors.RED)) {
                return p;
            }
        }
        return null;
    }

    public Player getGreenPlayer() {
        for (Player p : players) {
            if (p.getColor().equals(Colors.GREEN)) {
                return p;
            }
        }
        return null;
    }

    public int getTurn() {
        return turn;
    }

    public boolean canPlayerMove(Player p) {
        return p.canMove(board, Die.getThrown());
    }

    public Pawn yellowLeaveNest() {
        Pawn left = getYellowPlayer().firstLeavesNest(board.board.get(getYellowPlayer().getStartPosition()));
        checkNestKill(left);
        return left;
    }

    public Pawn blueLeaveNest() {
        Pawn left = getBluePlayer().firstLeavesNest(board.board.get(getBluePlayer().getStartPosition()));
        checkNestKill(left);
        return left;
    }

    public Pawn redLeaveNest() {
        Pawn left = getRedPlayer().firstLeavesNest(board.board.get(getRedPlayer().getStartPosition()));
        checkNestKill(left);
        return left;
    }

    public Pawn greenLeaveNest() {
        Pawn left = getGreenPlayer().firstLeavesNest(board.board.get(getGreenPlayer().getStartPosition()));
        checkNestKill(left);
        return left;
    }

    public void movePawn(Player p, Pawn pawn) {
        int value = p.moveByTile(pawn, board.board.get(pawn.getPosition().getNr() + Die.getThrown()));
        if (value > 0) {
            p.moveByTile(pawn, board.board.get(value));
        } else if (value < 0) {
            p.moveByTile(pawn, board.board.get(pawn.getPosition().getNr() + value + value));
        }
        checkKill(pawn);
        if (amountThrows == 2) {
            lastMovedPawn = pawn;
        }
    }

    public boolean isStartOK(Player p) {
        return !board.board.get(p.getStartPosition()).IsBarrier();
    }

    public List<Pawn> getMoveablePawns(Player p) {
        List<Pawn> pawns = new ArrayList<>();
        for (Pawn pwn : p.pawns) {
            if (pwn.isCanMove(board, Die.getThrown())) {
                pawns.add(pwn);
            }
        }
        return pawns;
    }

    public List<Pawn> getBarrierPawns(Player p){
        List<Pawn> pawns = new ArrayList<>();
        for (Pawn pwn : p.pawns) {
            if (pwn.getPosition() != null && pwn.getPosition().IsBarrier() && pwn.isInGame()) {
                pawns.add(pwn);
            }
        }
        return pawns;
    }

    public Pawn lastBackToNest() {
        if (!lastMovedPawn.getOnLandingstrip()){
            lastMovedPawn.toNest(board.board.get(lastMovedPawn.owner.getNestPosition()));
        } else {
            if (lastMovedPawn.owner.getColor().equals(Colors.YELLOW)){
                lastMovedPawn.owner.moveByTile(lastMovedPawn, board.board.get(73));
            } else if (lastMovedPawn.owner.getColor().equals(Colors.BLUE)){
                lastMovedPawn.owner.moveByTile(lastMovedPawn, board.board.get(81));
            }else if (lastMovedPawn.owner.getColor().equals(Colors.RED)){
                lastMovedPawn.owner.moveByTile(lastMovedPawn, board.board.get(89));
            }else if (lastMovedPawn.owner.getColor().equals(Colors.GREEN)){
                lastMovedPawn.owner.moveByTile(lastMovedPawn, board.board.get(97));
            }
        }
        return lastMovedPawn;
    }

    public Pawn getKilledPawn() {
        return killedPawn;
    }

    public Pawn getLastMovedPawn() {
        return lastMovedPawn;
    }

    public Board getBoard() {
        return board;
    }

    public void playAiTurn() {
        boolean turnEnded = false;
        do {
            roll();
            if (Die.getThrown() == 6) {
                System.out.println(players.get(indexTurn) + " threw a six");
            }
            if (Die.getThrown() == 6 && players.get(indexTurn).getHasBarrier()) {
                movePawn(players.get(indexTurn), players.get(indexTurn).firstBarrierPawn());
            } else if (Die.getThrown() == 5 && !players.get(indexTurn).isNestEmpty() && isStartOK(players.get(indexTurn))) {
                if (players.get(indexTurn).getColor().equals(Colors.BLUE)) {
                    blueLeaveNest();
                } else if (players.get(indexTurn).getColor().equals(Colors.RED)) {
                    redLeaveNest();
                } else if (players.get(indexTurn).getColor().equals(Colors.GREEN)) {
                    greenLeaveNest();
                }
            } else if (Die.getThrown() != 5 && players.get(indexTurn).canMove(board, Die.getThrown())) {
                movePawn(players.get(indexTurn), players.get(indexTurn).firstMoveablePawn(board));
            }
            if (Die.getThrown() != 6) {
                turnEnded = true;
            }
        } while (!turnEnded);

    }
}
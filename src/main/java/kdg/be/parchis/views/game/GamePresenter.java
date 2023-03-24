package kdg.be.parchis.views.game;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import kdg.be.parchis.model.game.*;
import kdg.be.parchis.model.menu.Leaderboards;
import kdg.be.parchis.model.musiclogic.Sound;
import kdg.be.parchis.views.endgamescreen.EndgameScreenPresenter;
import kdg.be.parchis.views.endgamescreen.EndgameScreenView;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GamePresenter {
    private final Game GAMESESSION;
    private final CoordinateConverter CONVERTER;
    private final GameView VIEW;
    private volatile boolean stopThread = false;
    public GamePresenter(
            Game model,
            GameView view) {
        CONVERTER = new CoordinateConverter();
        this.GAMESESSION = model;
        this.VIEW = view;
        this.addEventHandlers();
        if (GAMESESSION.getPlayers().get(GAMESESSION.getIndexTurn()) instanceof AiPlayer) {
            playAI();
        }
        this.updateView();
    }

    private boolean isNextAi() {
        return GAMESESSION.getPlayers().get(GAMESESSION.getIndexTurn()) instanceof AiPlayer;
    }

    private void setFinishVisible(int i) {
        VIEW.getFinish(i).setVisible(true);
    }

    private void addEventHandlers() {
        for (Button b : VIEW.getRoll()) {
            b.setOnAction(actionEvent -> {
                b.setVisible(false);
                Sound.playRoll();
                GAMESESSION.roll();

                if (GAMESESSION.isThrowAgain() && GAMESESSION.getAmountThrows() == 3) {
                    //return last pawn when you throw six, 3x if not null
                    if (GAMESESSION.getLastMovedPawn() != null) {
                        GAMESESSION.lastBackToNest();
                        updateAllPawnPositions();
                    }
                    setFinishVisible(GAMESESSION.getCurrentPlayer());
                } else {
                    updateDieFace();

                    Player player = GAMESESSION.getPlayer(GAMESESSION.getIndexTurn());

                    if (!GAMESESSION.canPlayerMove(player) &&
                            !player.getHasBarrier() && GAMESESSION.getThrown() != 5) {
                        //do nothing when you cant do anything
                        if (GAMESESSION.isThrowAgain()) {
                            b.setVisible(true);
                        } else {
                            setFinishVisible(GAMESESSION.getCurrentPlayer());
                        }
                    } else if (!GAMESESSION.canPlayerMove(player) && GAMESESSION.getThrown() == 5
                            && GAMESESSION.isStartOK(player) && !player.isNestEmpty()) {
                        VIEW.addNestGlow(player.getColor().getName());
                        VIEW.getNestGlow().setVisible(true);
                    } else if (GAMESESSION.canPlayerMove(player) && !player.getHasBarrier() && GAMESESSION.getThrown() != 5) {
                        glowMoveablePawn(player);
                    } else if (player.getHasBarrier() && GAMESESSION.isThrowAgain()) {
                        List<Pawn> moveable = GAMESESSION.getBarrierPawns(player);
                        movablePawns(moveable);
                    } else {
                        if (!player.isNestEmpty() && GAMESESSION.isStartOK(player) && GAMESESSION.getThrown() == 5) {
                            VIEW.addNestGlow(player.getColor().getName());
                            VIEW.getNestGlow().setVisible(true);
                        } else if (!player.canMove(GAMESESSION.getBoard(), GAMESESSION.getThrown()) && GAMESESSION.getThrown() == 5) {
                            setFinishVisible(GAMESESSION.getCurrentPlayer());
                        }
                        glowMoveablePawn(player);
                        if (GAMESESSION.isThrowAgain()) {
                            b.setVisible(true);
                            setFinishVisible(GAMESESSION.getCurrentPlayer());
                        }
                    }
                }
            });
        }

        for (Button b : VIEW.getFinish()) {
            b.setOnAction(actionEvent -> {
                String color = GAMESESSION.getPlayer(GAMESESSION.getIndexTurn()).getColor().getName();
                Sound.playClick();
                b.setVisible(false);
                VIEW.getDie(getOffset(color)).setVisible(false);
                GAMESESSION.endTurn();
                updateTurn();
                if (isNextAi()) {
                    playAI();
                } else {
                    displayControlsCurrentPlayer();
                    try {
                        checkIfEnded();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        VIEW.getNestGlow().setOnMouseClicked(mouseEvent -> {
            Sound.playPawnMove();

            GAMESESSION.leaveNest(GAMESESSION.getPlayer(GAMESESSION.getIndexTurn()).getColor().getName());
            VIEW.getFinish(GAMESESSION.getCurrentPlayer()).setVisible(true);

            VIEW.getNestGlow().setVisible(false);
            VIEW.removeAllGlow();
            updateAllPawnPositions();
        });

        for (int i = 0; i < 16; i++) {
            ImageView im = VIEW.getPawn(i);
            int index = i % 4;
            int playerIndex = i / 4;
            im.setOnMouseClicked(mouseEvent -> {
                if (GAMESESSION.pawnCanMove(index, playerIndex) && VIEW.hasGlow(im)) {
                    Sound.playPawnMove();
                    Player player = GAMESESSION.getPlayer(GAMESESSION.getIndexTurn());
                    if (GAMESESSION.getThrown() != 10) {
                        GAMESESSION.movePawn(player, player.pawns.get(index));
                    } else {
                        GAMESESSION.jump10(player.pawns.get(index));
                        if (!GAMESESSION.isThrowAgain()) {
                            VIEW.getFinish(getOffset(player.getColor().getName())).setVisible(true);
                        } else {
                            VIEW.getRoll(getOffset(player.getColor().getName())).setVisible(true);
                        }
                    }

                    VIEW.removeAllGlow();
                    VIEW.getNestGlow().setVisible(false);

                    if (player.pawns.get(index).isFinished() && player.canMove(GAMESESSION.getBoard(), GAMESESSION.getThrown())) {
                        glowJumpKill(player.getColor().getName());
                    }

                    if (!GAMESESSION.isThrowAgain()) {
                        VIEW.getFinish(getOffset(player.getColor().getName())).setVisible(true);
                    } else {
                        if (GAMESESSION.getThrown() != 10) {
                            VIEW.getRoll(getOffset(player.getColor().getName())).setVisible(true);
                        }
                    }

                    updateAllPawnPositions();

                    if (player.pawns.get(index).isFinished()) {
                        Sound.playVictory();
                    }
                }

                try {
                    checkIfEnded();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void glowJumpKill(String color) {
        VIEW.getFinish(getOffset(color)).setVisible(false);
        List<Pawn> moveable = GAMESESSION.getMoveablePawns(GAMESESSION.getPlayer(color));
        movablePawns(moveable);
        if (moveable.size() == 0) {
            if (!GAMESESSION.isThrowAgain()) {
                VIEW.getFinish(getOffset(color)).setVisible(true);
            } else {
                if (GAMESESSION.isThrowAgain()) {
                    VIEW.getRoll(getOffset(color)).setVisible(true);
                }
            }
        }
    }

    private int getOffset(String color) {
        return switch (color) {
            case "yellow" -> 0;
            case "blue" -> 1;
            case "red" -> 2;
            case "green" -> 3;
            default -> -1;
        };
    }

    private void movablePawns(List<Pawn> moveable) {
        for (Pawn p : moveable) {
            int offset = getOffset(p.getOwner().getColor().getName());
            VIEW.addGlow(VIEW.getPawn((p.getPawnNumber() - 1) + (4 * offset)), p.getOwner().getColor().getName());
        }
    }

    private void glowMoveablePawn(Player player) {
        List<Pawn> moveable = GAMESESSION.getMoveablePawns(player);
        movablePawns(moveable);
    }

    private void checkIfEnded() throws FileNotFoundException {
        if (GAMESESSION.hasGameEnded()) {
            if (GAMESESSION.getWinner() != null) {
                Leaderboards.addScore(GAMESESSION.getWinner());
            }
            stopThread();
            EndgameScreenView endgameScreenView = new EndgameScreenView();
            EndgameScreenPresenter presenter = new EndgameScreenPresenter(GAMESESSION, endgameScreenView);
            VIEW.getScene().setRoot(endgameScreenView);
        }
        updateAllPawnPositions();
    }

    private void updateView() {
        //set names
        for (int i = 0; i < GAMESESSION.getPlayers().size(); i++) {
            VIEW.getPlayerName(i).setText(GAMESESSION.getRawPlayer(i).getName());
        }

        if (!(GAMESESSION.getPlayers().get(0) instanceof AiPlayer)) {
            displayControlsCurrentPlayer();
        }
        updateAllPawnPositions();
    }

    public void updateAllPawnPositions() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                VIEW.getPawn(j + 4 * i).setTranslateX(CONVERTER.getX(GAMESESSION.getRawPlayer(i).pawns.get(j).getPosition().getNr()));
                VIEW.getPawn(j + 4 * i).setTranslateY(CONVERTER.getY(GAMESESSION.getRawPlayer(i).pawns.get(j).getPosition().getNr()));
                if (j > 0 && !(GAMESESSION.getRawPlayer(i).pawns.get(j).isInGame())) {
                    int offset = GAMESESSION.getRawPlayer(i).pawns.get(j).getPosition().getNr() * 100;
                    VIEW.getPawn(j + 4 * i).setTranslateX(CONVERTER.getX(offset + j + 1));
                    VIEW.getPawn(j + 4 * i).setTranslateY(CONVERTER.getY(offset + j + 1));
                }
            }
        }
        Platform.runLater(VIEW::rearrangePawns);
        Platform.runLater(this::updateTurn);
    }

    private void playAI() {
        final ReadWriteLock gameSessionLock = new ReentrantReadWriteLock();
        new Thread(() -> {
            while (!stopThread && GAMESESSION.getPlayers().get(GAMESESSION.getIndexTurn()) instanceof AiPlayer) {
                gameSessionLock.readLock().lock(); // acquire read lock
                try {
                    synchronized (GAMESESSION.getPlayers()) {
                        do {
                            GAMESESSION.playAiTurn();
                            updateDieFace();
                            Platform.runLater(this::updateAllPawnPositions);
                        } while (!GAMESESSION.isEndAiTurn());
                    }
                    GAMESESSION.endTurn();
                    checkIfEnded();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } finally {
                    gameSessionLock.readLock().unlock(); // release read lock
                }
            }
            displayControlsCurrentPlayer();
        }).start();
    }

    private void updateDieFace() {
        hideDieFaces();
        int i = GAMESESSION.getCurrentPlayer();
        VIEW.getDie(i).setVisible(true);
        VIEW.getDie(i).setImage(VIEW.getDiePhoto(GAMESESSION.getDieFace()));
    }

    private void hideDieFaces() {
        for (int i = 0; i < 4; i++) {
            VIEW.getDie(i).setVisible(false);
        }
    }

    private void displayControlsCurrentPlayer() {
        int i = GAMESESSION.getCurrentPlayer();
        VIEW.getRoll(i).setVisible(true);
    }

    private void updateTurn() {
        VIEW.getTurns().setText("turn: " + GAMESESSION.getTurn());
    }

    public void stopThread() {
        stopThread = true;
    }
}
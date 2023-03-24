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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
public class GamePresenter {
    private final Game gamesession;
    private final CoordinateConverter converter;
    private final GameView view;
    private volatile boolean stopThread = false;
    public GamePresenter(
            Game model,
            GameView view) {
        converter = new CoordinateConverter();
        this.gamesession = model;
        this.view = view;
        this.addEventHandlers();
        if (gamesession.getPlayers().get(gamesession.getIndexTurn()) instanceof AiPlayer) {
            playAI();
        }
        this.updateView();
    }

    private boolean isNextAi() {
        return gamesession.getPlayers().get(gamesession.getIndexTurn()) instanceof AiPlayer;
    }

    private void setFinishVisible(int i) {
        view.getFinish(i).setVisible(true);
    }

    private void addEventHandlers() {
        for (Button b : view.getRoll()) {
            b.setOnAction(actionEvent -> {
                b.setVisible(false);
                Sound.playRoll();
                gamesession.roll();

                if (gamesession.isThrowAgain() && gamesession.getAmountThrows() == 3) {
                    //return last pawn when you throw six, 3x if not null
                    if (gamesession.getLastMovedPawn() != null) {
                        gamesession.lastBackToNest();
                        updateAllPawnPositions();
                    }
                    setFinishVisible(gamesession.getCurrentPlayer());
                } else {
                    updateDieFace();

                    Player player = gamesession.getPlayer(gamesession.getIndexTurn());

                    if (!gamesession.canPlayerMove(player) &&
                            !player.getHasBarrier() && gamesession.getThrown() != 5) {
                        //do nothing when you cant do anything
                        if (gamesession.isThrowAgain()) {
                            b.setVisible(true);
                        } else {
                            setFinishVisible(gamesession.getCurrentPlayer());
                        }
                    } else if (!gamesession.canPlayerMove(player) && gamesession.getThrown() == 5
                            && gamesession.isStartOK(player) && !player.isNestEmpty()) {
                        view.addNestGlow(player.getColor().getName());
                        view.getNestGlow().setVisible(true);
                    } else if (gamesession.canPlayerMove(player) && !player.getHasBarrier() && gamesession.getThrown() != 5) {
                        glowMoveablePawn(player);
                    } else if (player.getHasBarrier() && gamesession.isThrowAgain()) {
                        List<Pawn> moveable = gamesession.getBarrierPawns(player);
                        movablePawns(moveable);
                    } else {
                        if (!player.isNestEmpty() && gamesession.isStartOK(player) && gamesession.getThrown() == 5) {
                            view.addNestGlow(player.getColor().getName());
                            view.getNestGlow().setVisible(true);
                        } else if (!player.canMove(gamesession.getBoard(), gamesession.getThrown()) && gamesession.getThrown() == 5) {
                            setFinishVisible(gamesession.getCurrentPlayer());
                        }
                        glowMoveablePawn(player);
                        if (gamesession.isThrowAgain()) {
                            b.setVisible(true);
                            setFinishVisible(gamesession.getCurrentPlayer());
                        }
                    }
                }
            });
        }

        for (Button b : view.getFinish()) {
            b.setOnAction(actionEvent -> {
                String color = gamesession.getPlayer(gamesession.getIndexTurn()).getColor().getName();
                Sound.playClick();
                b.setVisible(false);
                view.getDie(getOffset(color)).setVisible(false);
                gamesession.endTurn();
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

        view.getNestGlow().setOnMouseClicked(mouseEvent -> {
            Sound.playPawnMove();

            gamesession.leaveNest(gamesession.getPlayer(gamesession.getIndexTurn()).getColor().getName());
            view.getFinish(gamesession.getCurrentPlayer()).setVisible(true);

            view.getNestGlow().setVisible(false);
            view.removeAllGlow();
            updateAllPawnPositions();
        });

        for (int i = 0; i < 16; i++) {
            ImageView im = view.getPawn(i);
            int index = i % 4;
            int playerIndex = i / 4;
            im.setOnMouseClicked(mouseEvent -> {
                if (gamesession.pawnCanMove(index, playerIndex) && view.hasGlow(im)) {
                    Sound.playPawnMove();
                    Player player = gamesession.getPlayer(gamesession.getIndexTurn());
                    if (gamesession.getThrown() != 10) {
                        gamesession.movePawn(player, player.pawns.get(index));
                    } else {
                        gamesession.jump10(player.pawns.get(index));
                        if (!gamesession.isThrowAgain()) {
                            view.getFinish(getOffset(player.getColor().getName())).setVisible(true);
                        } else {
                            view.getRoll(getOffset(player.getColor().getName())).setVisible(true);
                        }
                    }

                    view.removeAllGlow();
                    view.getNestGlow().setVisible(false);

                    if (player.pawns.get(index).isFinished() && player.canMove(gamesession.getBoard(), gamesession.getThrown())) {
                        glowJumpKill(player.getColor().getName());
                    }

                    if (!gamesession.isThrowAgain()) {
                        view.getFinish(getOffset(player.getColor().getName())).setVisible(true);
                    } else {
                        if (gamesession.getThrown() != 10) {
                            view.getRoll(getOffset(player.getColor().getName())).setVisible(true);
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
        view.getFinish(getOffset(color)).setVisible(false);
        List<Pawn> moveable = gamesession.getMoveablePawns(gamesession.getPlayer(color));
        movablePawns(moveable);
        if (moveable.size() == 0) {
            if (!gamesession.isThrowAgain()) {
                view.getFinish(getOffset(color)).setVisible(true);
            } else {
                if (gamesession.isThrowAgain()) {
                    view.getRoll(getOffset(color)).setVisible(true);
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
            view.addGlow(view.getPawn((p.getPawnNumber() - 1) + (4 * offset)), p.getOwner().getColor().getName());
        }
    }

    private void glowMoveablePawn(Player player) {
        List<Pawn> moveable = gamesession.getMoveablePawns(player);
        movablePawns(moveable);
    }

    private void checkIfEnded() throws FileNotFoundException {
        if (gamesession.hasGameEnded()) {
            if (gamesession.getWinner() != null) {
                Leaderboards.addScore(gamesession.getWinner());
            }
            stopThread();
            EndgameScreenView endgameScreenView = new EndgameScreenView();
            EndgameScreenPresenter presenter = new EndgameScreenPresenter(gamesession, endgameScreenView);
            view.getScene().setRoot(endgameScreenView);
        }
        updateAllPawnPositions();
    }

    private void updateView() {
        //set names
        for (int i = 0; i < gamesession.getPlayers().size(); i++) {
            view.getPlayerName(i).setText(gamesession.getRawPlayer(i).getName());
        }

        if (!(gamesession.getPlayers().get(0) instanceof AiPlayer)) {
            displayControlsCurrentPlayer();
        }
        updateAllPawnPositions();
    }

    public void updateAllPawnPositions() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                view.getPawn(j + 4 * i).setTranslateX(converter.getX(gamesession.getRawPlayer(i).pawns.get(j).getPosition().getNr()));
                view.getPawn(j + 4 * i).setTranslateY(converter.getY(gamesession.getRawPlayer(i).pawns.get(j).getPosition().getNr()));
                if (j > 0 && !(gamesession.getRawPlayer(i).pawns.get(j).isInGame())) {
                    int offset = gamesession.getRawPlayer(i).pawns.get(j).getPosition().getNr() * 100;
                    view.getPawn(j + 4 * i).setTranslateX(converter.getX(offset + j + 1));
                    view.getPawn(j + 4 * i).setTranslateY(converter.getY(offset + j + 1));
                }
            }
        }
        Platform.runLater(view::rearrangePawns);
        Platform.runLater(this::updateTurn);
    }

    private void playAI() {
        final ReadWriteLock gameSessionLock = new ReentrantReadWriteLock();

        new Thread(() -> {
            while (!stopThread && gamesession.getPlayers().get(gamesession.getIndexTurn()) instanceof AiPlayer) {
                gameSessionLock.readLock().lock(); // acquire read lock
                try {
                    synchronized (gamesession.getPlayers()) {
                        do {
                            gamesession.roll();
                            Sound.playRoll();
                            CompletableFuture<Void> future = CompletableFuture.runAsync(gamesession::playAiTurn);
                            future.thenRun(() -> Platform.runLater(() -> {
                                updateDieFace();
                                updateAllPawnPositions();
                            }));
                            Thread.sleep(500); // wait for .5 seconds
                        } while (!gamesession.isEndAiTurn());
                    }
                    gamesession.endTurn();
                    checkIfEnded();
                } catch (InterruptedException | FileNotFoundException e) {
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
        int i = gamesession.getCurrentPlayer();
        view.getDie(i).setVisible(true);
        view.getDie(i).setImage(view.getDiePhoto(gamesession.getDieFace()));
    }

    private void hideDieFaces() {
        for (int i = 0; i < 4; i++) {
            view.getDie(i).setVisible(false);
        }
    }

    private void displayControlsCurrentPlayer() {
        int i = gamesession.getCurrentPlayer();
        view.getRoll(i).setVisible(true);
    }

    private void updateTurn() {
        view.getTurns().setText("turn: " + gamesession.getTurn());
    }

    public void stopThread() {
        stopThread = true;
    }
}
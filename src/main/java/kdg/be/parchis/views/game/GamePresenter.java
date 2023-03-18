package kdg.be.parchis.views.game;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import kdg.be.parchis.model.game.*;
import kdg.be.parchis.model.menu.Leaderboards;
import kdg.be.parchis.model.musicLogic.Sound;
import kdg.be.parchis.views.endgamescreen.EndgameScreenPresenter;
import kdg.be.parchis.views.endgamescreen.EndgameScreenView;

import java.io.FileNotFoundException;
import java.util.List;

public class GamePresenter {
    private final Game gameSession;
    private final CoordinateConverter converter;
    private final GameView view;

    public GamePresenter(
            Game model,
            GameView view) {
        converter = new CoordinateConverter();
        this.gameSession = model;
        this.view = view;
        this.addEventHandlers();
        if (gameSession.getPlayers().get(gameSession.getIndexTurn()) instanceof AiPlayer) {
            playAI();
        }
        this.updateView();
    }

    private boolean isNextAi() {
        return gameSession.getPlayers().get(gameSession.getIndexTurn()) instanceof AiPlayer;
    }

    private void setFinishVisible(int i, boolean visible) {
        view.getFinish(i).setVisible(visible);
    }

    private String getColor(int i) {
        return switch (i) {
            case 0, 1, 2, 3 -> "yellow";
            case 4, 5, 6, 7 -> "blue";
            case 8, 9, 10, 11 -> "red";
            case 12, 13, 14, 15 -> "green";

            default -> throw new IllegalStateException("Unexpected value: " + i);
        };
    }

    private String getColor(ImageView im) {
        String s = im.getImage().getUrl().split("_")[1];
        if (s.contains(".")) {
            return s.split(".")[0];
        }
        return s;
    }

    private void addEventHandlers() {

        for (Button b : view.getRoll()) {
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    b.setVisible(false);
                    Sound.playRoll();
                    gameSession.roll();

                    if (Die.getThrown() == 6 && gameSession.getAmountThrows() == 3) {
                        //return last pawn when you throw six, 3x
                        if (gameSession.getLastMovedPawn() != null) {
                            gameSession.lastBackToNest();
                            updateAllPawnPositions();
                        }
                        // CHECK TO SEE IF IT IS RIGHT ONE, IF INDEXTURN IS CORRECT
                        setFinishVisible(gameSession.getCurrentPlayer(), true);
                    }

                    updateDieFace();

                    Player player = gameSession.getPlayer(gameSession.getIndexTurn());

                    if (!gameSession.canPlayerMove(player) &&
                            !player.getHasBarrier() && Die.getThrown() != 5) {
                        //do nothing when you cant do anything
                        if (Die.isRollAgain()) {
                            b.setVisible(true);
                        } else {
                            setFinishVisible(gameSession.getCurrentPlayer(), true);
                        }
                    } else if (!gameSession.canPlayerMove(player) && Die.getThrown() == 5
                            && gameSession.isStartOK(player) && !player.isNestEmpty()) {
                        view.addNestGlow(player.getColor().getName());
                        view.getNestGlow().setVisible(true);
                    } else if (gameSession.canPlayerMove(player) && !player.getHasBarrier() && Die.getThrown() != 5) {
                        glowMoveablePawn(player);
                    } else if (player.getHasBarrier() && (Die.getThrown() == 6 || Die.getThrown() == 7)) {
                        List<Pawn> moveable = gameSession.getBarrierPawns(player);
                        movablePawns(moveable);
                    } else {
                        if (!player.isNestEmpty() && gameSession.isStartOK(player) && Die.getThrown() == 5) {
                            view.addNestGlow(player.getColor().getName());
                            view.getNestGlow().setVisible(true);
                        } else if (!player.canMove(gameSession.getBoard(), Die.getThrown()) && Die.getThrown() == 5) {
                            setFinishVisible(gameSession.getCurrentPlayer(), true);
                        }
                        glowMoveablePawn(player);
                        if (Die.isRollAgain()) {
                            b.setVisible(true);
                            setFinishVisible(gameSession.getCurrentPlayer(), true);
                        }
                    }
                }
            });
        }

        for (Button b : view.getFinish()) {
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String color = gameSession.getPlayer(gameSession.getIndexTurn()).getColor().getName();
                    Sound.playClick();
                    b.setVisible(false);
                    view.getDie(getOffset(color)).setVisible(false);
                    gameSession.endTurn();
                    updateTurn();
                    if (isNextAi()) {
                        playAI();
                    } else {
                        view.getRoll(getOffset(color)).setVisible(true);

                        try {
                            checkIfEnded();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        }

        view.getNestGlow().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Sound.playPawnMove();

                for (int i = 0; i < 4; i++) {
                    gameSession.leaveNest(gameSession.getPlayer(gameSession.getIndexTurn()).getColor().getName());
                    view.getFinish(i).setVisible(true);
                    break;
                }

                view.getNestGlow().setVisible(false);
                view.removeAllGlow();
                updateAllPawnPositions();
            }
        });

        for (ImageView im : view.getPawn()) {
            im.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int ps = converter.getPosition((int) im.getX(), (int) im.getY());
                    int index = gameSession.getPawnIndex(ps);
                    if (gameSession.pawnCanMove(index) && view.hasGlow(im)) {
                        Sound.playPawnMove();
                        Player player = gameSession.getPlayer(gameSession.getIndexTurn());
                        if (Die.getThrown() != 10) {
                            gameSession.movePawn(player, player.pawns.get(index));
                        } else {
                            gameSession.jump10(player.pawns.get(index));
                            if (!Die.isRollAgain()) {
                                view.getFinish(getOffset(player.getColor().getName())).setVisible(true);
                            } else {
                                view.getRoll(getOffset(player.getColor().getName())).setVisible(true);
                            }
                        }

                        view.removeAllGlow();

                        view.getNestGlow().setVisible(false);

                        if (player.pawns.get(index).isFinished() && player.canMove(gameSession.getBoard(), Die.getThrown())) {
                            glowJumpKill(player.getColor().getName());
                        }

                        if (!Die.isRollAgain()) {
                            view.getFinish(getOffset(player.getColor().getName())).setVisible(true);
                        } else {
                            if (Die.getThrown() != 10) {
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
                }
            });
        }
    }

    private void glowJumpKill(String color) {
        view.getFinish(getOffset(color)).setVisible(false);
        Die.setTen();
        List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getPlayer(color));
        movablePawns(moveable);
        if (moveable.size() == 0) {
            if (Die.getThrown() != 6 && Die.getThrown() != 7) {
                view.getFinish(getOffset(color)).setVisible(true);
            } else {
                if (!Die.isRollAgain()) {
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
        List<Pawn> moveable = gameSession.getMoveablePawns(player);
        movablePawns(moveable);
    }

    private void checkIfEnded() throws FileNotFoundException {
        if (gameSession.hasGameEnded()) {
            if (gameSession.getWinner() != null) {
                Leaderboards.addScore(gameSession.getWinner());
            }
            EndgameScreenView endgameScreenView = new EndgameScreenView();
            EndgameScreenPresenter presenter = new EndgameScreenPresenter(gameSession, endgameScreenView);
            view.getScene().setRoot(endgameScreenView);
        }
        updateAllPawnPositions();
    }

    private void updateView() {
        //set names
        for (int i = 0; i < gameSession.getPlayers().size(); i++) {
            view.getPlayerName(i).setText(gameSession.getRawPlayer(i).getName());
        }

        if (!(gameSession.getPlayers().get(0) instanceof AiPlayer)) {
            displayControlsCurrentPlayer();
        }
        updateAllPawnPositions();
    }

    public void updateAllPawnPositions() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                view.getPawn(j + 4 * i).setTranslateX(converter.getX(gameSession.getRawPlayer(i).pawns.get(j).getPosition().getNr()));
                view.getPawn(j + 4 * i).setTranslateY(converter.getY(gameSession.getRawPlayer(i).pawns.get(j).getPosition().getNr()));
                if (j > 0 && !(gameSession.getRawPlayer(i).pawns.get(j).isInGame())) {
                    int offset = gameSession.getRawPlayer(i).pawns.get(j).getPosition().getNr() * 100;
                    view.getPawn(j + 4 * i).setTranslateX(converter.getX(offset + j + 1));
                    view.getPawn(j + 4 * i).setTranslateY(converter.getY(offset + j + 1));
                }
            }
        }
        Platform.runLater(view::rearrangePawns);
        Platform.runLater(this::updateTurn);
    }

    private void playAI() {
        new Thread(() -> {
            while (gameSession.getPlayers().get(gameSession.getIndexTurn()) instanceof AiPlayer) {
                do {
                    gameSession.playAiTurn();
                    updateDieFace();
                    Platform.runLater(this::updateAllPawnPositions);
                } while (!gameSession.isEndAITurn());
                gameSession.endTurn();
                try {
                    checkIfEnded();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            displayControlsCurrentPlayer();
        }).start();
    }

    private void updateDieFace() {
        hideDieFaces();
        int i = gameSession.getCurrentPlayer();
        view.getDie(i).setVisible(true);
        view.getDie(i).setImage(Die.getDiceFoto().getImage());
    }

    private void hideDieFaces() {
        for (int i = 0; i < 4; i++) {
            view.getDie(i).setVisible(false);
        }
    }

    private void displayControlsCurrentPlayer() {
        int i = gameSession.getCurrentPlayer();
        view.getRoll(i).setVisible(true);
    }

    private void updateTurn() {
        view.getTurns().setText("turn: " + gameSession.getTurn());
    }
}
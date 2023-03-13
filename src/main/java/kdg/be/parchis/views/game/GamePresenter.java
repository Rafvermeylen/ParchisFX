package kdg.be.parchis.views.game;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import kdg.be.parchis.model.game.*;
import kdg.be.parchis.model.menu.Leaderboards;
import kdg.be.parchis.model.musicLogic.Sound;
import kdg.be.parchis.views.endgamescreen.EndgameScreenPresenter;
import kdg.be.parchis.views.endgamescreen.EndgameScreenView;

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
        if (gameSession.getPlayers().get(gameSession.getIndexTurn()) instanceof ai_Player){
            playAI();
        }
        this.updateView();
    }

    private void addEventHandlers() {
        view.getRoll1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll1().setVisible(false);
                Sound.playRoll();
                gameSession.roll();
                if (Die.getThrown() == 6 && gameSession.getAmountThrows() == 3) {
                    //return last pawn when you throw six, 3x
                    if (gameSession.getLastMovedPawn() != null) {
                        gameSession.lastBackToNest();
                        view.getFinish1().setVisible(true);
                        updateAllPawnPositions();
                    } else {
                        view.getFinish1().setVisible(true);
                    }
                    return;
                }


                System.out.println(Die.getThrown());


                view.getDie1().setVisible(true);
                view.getDie1().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getYellowPlayer()) && !gameSession.getYellowPlayer().getHasBarrier() && Die.getThrown() != 5) {
                    //do nothing when you cant do anything
                    if (Die.isRollAgain()) {
                        view.getRoll1().setVisible(true);
                    } else {
                        view.getFinish1().setVisible(true);
                    }
                } else if (!gameSession.canPlayerMove(gameSession.getYellowPlayer()) && Die.getThrown() == 5
                        && gameSession.isStartOK(gameSession.getYellowPlayer()) && !gameSession.getYellowPlayer().isNestEmpty()) {
                    view.getNestGlow().setImage(view.getGlowNestYellow());
                    view.getNestGlow().setVisible(true);
                } else if (gameSession.canPlayerMove(gameSession.getYellowPlayer()) && !gameSession.getYellowPlayer().getHasBarrier() && Die.getThrown() != 5) {
                    glowMoveableYellowPawn();
                } else if (gameSession.getYellowPlayer().getHasBarrier() && (Die.getThrown() == 6 || Die.getThrown() == 7)) {
                    List<Pawn> moveable = gameSession.getBarrierPawns(gameSession.getYellowPlayer());
                    yellowMovablePawns(moveable);
                } else {
                    if (!gameSession.getYellowPlayer().isNestEmpty() && gameSession.isStartOK(gameSession.getYellowPlayer()) && Die.getThrown() == 5) {
                        view.getNestGlow().setImage(view.getGlowNestYellow());
                        view.getNestGlow().setVisible(true);
                    } else if (!gameSession.getYellowPlayer().canMove(gameSession.getBoard(), Die.getThrown()) && Die.getThrown() == 5) {
                        view.getFinish1().setVisible(true);
                    }
                    glowMoveableYellowPawn();
                    if (Die.isRollAgain()) {
                        view.getRoll1().setVisible(true);
                        view.getFinish1().setVisible(false);
                    }
                }
            }
        });
        view.getRoll2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll2().setVisible(false);
                Sound.playRoll();
                gameSession.roll();
                //if three is thrown 3x, return last moved pawn
                if (Die.getThrown() == 6 && gameSession.getAmountThrows() == 3) {
                    if (gameSession.getLastMovedPawn() != null) {
                        gameSession.lastBackToNest();
                        view.getFinish2().setVisible(true);
                        updateAllPawnPositions();
                    } else {
                        view.getFinish2().setVisible(true);
                    }
                    return;
                }
                view.getDie2().setVisible(true);
                view.getDie2().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getBluePlayer()) && !gameSession.getBluePlayer().getHasBarrier() && Die.getThrown() != 5) {
                    //do nothing when you cant do anything
                    if (Die.isRollAgain()) {
                        view.getRoll2().setVisible(true);
                    } else {
                        view.getFinish2().setVisible(true);
                    }
                } else if (!gameSession.canPlayerMove(gameSession.getBluePlayer()) && Die.getThrown() == 5
                        && gameSession.isStartOK(gameSession.getBluePlayer()) && !gameSession.getBluePlayer().isNestEmpty()) {
                    view.getNestGlow().setImage(view.getGlowNestBlue());
                    view.getNestGlow().setVisible(true);
                } else if (gameSession.canPlayerMove(gameSession.getBluePlayer()) && !gameSession.getBluePlayer().getHasBarrier() && Die.getThrown() != 5) {
                    blueMovablePawns();
                } else if (gameSession.getBluePlayer().getHasBarrier() && (Die.getThrown() == 6 || Die.getThrown() == 7)) {
                    List<Pawn> moveable = gameSession.getBarrierPawns(gameSession.getBluePlayer());
                    blueMovablePawns(moveable);
                } else {
                    if (!gameSession.getBluePlayer().isNestEmpty() && gameSession.isStartOK(gameSession.getBluePlayer()) && Die.getThrown() == 5) {
                        view.getNestGlow().setImage(view.getGlowNestBlue());
                        view.getNestGlow().setVisible(true);
                    } else if (!gameSession.getBluePlayer().canMove(gameSession.getBoard(), Die.getThrown()) && Die.getThrown() == 5) {
                        view.getFinish2().setVisible(true);
                    }
                    blueMovablePawns();
                    if (Die.getThrown() == 6 || Die.getThrown() == 7) {
                        view.getRoll2().setVisible(true);
                        view.getFinish2().setVisible(false);
                    }
                }
            }
        });
        view.getRoll3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll3().setVisible(false);
                Sound.playRoll();
                gameSession.roll();
                //if three is thrown 3x, return last moved pawn
                if (Die.getThrown() == 6 && gameSession.getAmountThrows() == 3) {
                    if (gameSession.getLastMovedPawn() != null) {
                        gameSession.lastBackToNest();
                        view.getFinish3().setVisible(true);
                        updateAllPawnPositions();
                    } else {
                        view.getFinish3().setVisible(true);
                    }
                    return;
                }
                view.getDie3().setVisible(true);
                view.getDie3().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getRedPlayer()) && !gameSession.getRedPlayer().getHasBarrier() && Die.getThrown() != 5) {
                    //do nothing when you cant do anything
                    if (Die.getThrown() == 6 || Die.getThrown() == 7) {
                        view.getRoll3().setVisible(true);
                    } else {
                        view.getFinish3().setVisible(true);
                    }
                } else if (!gameSession.canPlayerMove(gameSession.getRedPlayer()) && Die.getThrown() == 5
                        && gameSession.isStartOK(gameSession.getRedPlayer()) && !gameSession.getRedPlayer().isNestEmpty()) {
                    view.getNestGlow().setImage(view.getGlowNestRed());
                    view.getNestGlow().setVisible(true);
                } else if (gameSession.canPlayerMove(gameSession.getRedPlayer()) && !gameSession.getRedPlayer().getHasBarrier() && Die.getThrown() != 5) {
                    redMovablePawns();
                } else if (gameSession.getRedPlayer().getHasBarrier() && (Die.getThrown() == 6 || Die.getThrown() == 7)) {
                    List<Pawn> moveable = gameSession.getBarrierPawns(gameSession.getRedPlayer());
                    redMovablePawns(moveable);
                } else {
                    if (!gameSession.getRedPlayer().isNestEmpty() && gameSession.isStartOK(gameSession.getRedPlayer()) && Die.getThrown() == 5) {
                        view.getNestGlow().setImage(view.getGlowNestRed());
                        view.getNestGlow().setVisible(true);
                    } else if (!gameSession.getRedPlayer().canMove(gameSession.getBoard(), Die.getThrown()) && Die.getThrown() == 5) {
                        view.getFinish3().setVisible(true);
                    }
                    redMovablePawns();
                    if (Die.getThrown() == 6 || Die.getThrown() == 7) {
                        view.getRoll3().setVisible(true);
                        view.getFinish3().setVisible(false);
                    }
                }
            }
        });
        view.getRoll4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll4().setVisible(false);
                Sound.playRoll();
                gameSession.roll();
                //if three is thrown 3x, return last moved pawn
                if (Die.getThrown() == 6 && gameSession.getAmountThrows() == 3) {
                    if (gameSession.getLastMovedPawn() != null) {
                        gameSession.lastBackToNest();
                        view.getFinish4().setVisible(true);
                        updateAllPawnPositions();
                    } else {
                        view.getFinish4().setVisible(true);
                    }
                    return;
                }
                view.getDie4().setVisible(true);
                view.getDie4().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getGreenPlayer()) && !gameSession.getGreenPlayer().getHasBarrier() && Die.getThrown() != 5) {
                    //do nothing when you cant do anything
                    if (Die.getThrown() == 6 || Die.getThrown() == 7) {
                        view.getRoll4().setVisible(true);
                    } else {
                        view.getFinish4().setVisible(true);
                    }
                } else if (!gameSession.canPlayerMove(gameSession.getGreenPlayer()) && Die.getThrown() == 5
                        && gameSession.isStartOK(gameSession.getGreenPlayer())
                        && !gameSession.getGreenPlayer().isNestEmpty()) {
                    view.getNestGlow().setImage(view.getGlowNestGreen());
                    view.getNestGlow().setVisible(true);
                } else if (gameSession.canPlayerMove(gameSession.getGreenPlayer()) && !gameSession.getGreenPlayer().getHasBarrier() && Die.getThrown() != 5) {
                    greenMovablePawns();
                } else if (gameSession.getGreenPlayer().getHasBarrier() && (Die.getThrown() == 6 || Die.getThrown() == 7)) {
                    List<Pawn> moveable = gameSession.getBarrierPawns(gameSession.getGreenPlayer());
                    greenMovablePawns(moveable);
                } else {
                    if (!gameSession.getGreenPlayer().isNestEmpty() && gameSession.isStartOK(gameSession.getGreenPlayer()) && Die.getThrown() == 5) {
                        view.getNestGlow().setImage(view.getGlowNestGreen());
                        view.getNestGlow().setVisible(true);
                    } else if (!gameSession.getGreenPlayer().canMove(gameSession.getBoard(), Die.getThrown()) && Die.getThrown() == 5) {
                        view.getFinish4().setVisible(true);
                    }
                    greenMovablePawns();
                    if (Die.getThrown() == 6 || Die.getThrown() == 7) {
                        view.getRoll4().setVisible(true);
                        view.getFinish4().setVisible(false);
                    }
                }
            }
        });
        view.getFinish1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.playClick();
                view.getFinish1().setVisible(false);
                view.getDie1().setVisible(false);
                gameSession.endTurn();
                updateTurn();
                playAI();
            }
        });
        view.getFinish2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.playClick();
                view.getFinish2().setVisible(false);
                view.getDie2().setVisible(false);
                gameSession.endTurn();
                updateTurn();
                playAI();
            }
        });
        view.getFinish3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.playClick();
                view.getFinish3().setVisible(false);
                view.getDie3().setVisible(false);
                gameSession.endTurn();
                updateTurn();
                playAI();
            }
        });
        view.getFinish4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.playClick();
                view.getFinish4().setVisible(false);
                view.getDie4().setVisible(false);
                gameSession.endTurn();
                updateTurn();
                if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Color.YELLOW)) {
                    view.getRoll1().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Color.BLUE)) {
                    view.getRoll2().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Color.RED)) {
                    view.getRoll3().setVisible(true);
                }
                checkIfEnded();
            }
        });
        view.getNestGlow().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Sound.playPawnMove();
                if (view.getNestGlow().getImage().equals(view.getGlowNestYellow())) {
                    gameSession.yellowLeaveNest();
                    view.getFinish1().setVisible(true);
                } else if (view.getNestGlow().getImage().equals(view.getGlowNestBlue())) {
                    gameSession.blueLeaveNest();
                    view.getFinish2().setVisible(true);
                } else if (view.getNestGlow().getImage().equals(view.getGlowNestRed())) {
                    gameSession.redLeaveNest();
                    view.getFinish3().setVisible(true);
                } else if (view.getNestGlow().getImage().equals(view.getGlowNestGreen())) {
                    gameSession.greenLeaveNest();
                    view.getFinish4().setVisible(true);
                }
                view.getNestGlow().setVisible(false);
                view.getYp_1().setImage(view.getYellowPawn());
                view.getYp_2().setImage(view.getYellowPawn());
                view.getYp_3().setImage(view.getYellowPawn());
                view.getYp_4().setImage(view.getYellowPawn());
                view.getBp_1().setImage(view.getBluePawn());
                view.getBp_2().setImage(view.getBluePawn());
                view.getBp_3().setImage(view.getBluePawn());
                view.getBp_4().setImage(view.getBluePawn());
                view.getRp_1().setImage(view.getRedPawn());
                view.getRp_2().setImage(view.getRedPawn());
                view.getRp_3().setImage(view.getRedPawn());
                view.getRp_4().setImage(view.getRedPawn());
                view.getGp_1().setImage(view.getGreenPawn());
                view.getGp_2().setImage(view.getGreenPawn());
                view.getGp_3().setImage(view.getGreenPawn());
                view.getGp_4().setImage(view.getGreenPawn());
                updateAllPawnPositions();
            }
        });
        view.getYp_1().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getYp_1().getImage().equals(view.getYellowPawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getYellowPlayer(), gameSession.getYellowPlayer().pawns.get(0));
                    } else {
                        gameSession.jump10(gameSession.getYellowPlayer().pawns.get(0));
                        if (!Die.isRollAgain()) {
                            view.getFinish1().setVisible(true);
                        } else {
                            view.getRoll1().setVisible(true);
                        }
                    }

                    view.getYp_1().setImage(view.getYellowPawn());
                    view.getYp_2().setImage(view.getYellowPawn());
                    view.getYp_3().setImage(view.getYellowPawn());
                    view.getYp_4().setImage(view.getYellowPawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getYellowPlayer().pawns.get(0).isFinished() && gameSession.getYellowPlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillYellow();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish1().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll1().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getYellowPlayer().pawns.get(0).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getYp_2().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getYp_2().getImage().equals(view.getYellowPawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getYellowPlayer(), gameSession.getYellowPlayer().pawns.get(1));
                    } else {
                        gameSession.jump10(gameSession.getYellowPlayer().pawns.get(1));
                        if (!Die.isRollAgain()) {
                            view.getFinish1().setVisible(true);
                        } else {
                            view.getRoll1().setVisible(true);
                        }
                    }
                    view.getYp_1().setImage(view.getYellowPawn());
                    view.getYp_2().setImage(view.getYellowPawn());
                    view.getYp_3().setImage(view.getYellowPawn());
                    view.getYp_4().setImage(view.getYellowPawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getYellowPlayer().pawns.get(1).isFinished() && gameSession.getYellowPlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillYellow();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish1().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll1().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getYellowPlayer().pawns.get(1).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getYp_3().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getYp_3().getImage().equals(view.getYellowPawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getYellowPlayer(), gameSession.getYellowPlayer().pawns.get(2));
                    } else {
                        gameSession.jump10(gameSession.getYellowPlayer().pawns.get(2));
                        if (!Die.isRollAgain()) {
                            view.getFinish1().setVisible(true);
                        } else {
                            view.getRoll1().setVisible(true);
                        }
                    }

                    view.getYp_1().setImage(view.getYellowPawn());
                    view.getYp_2().setImage(view.getYellowPawn());
                    view.getYp_3().setImage(view.getYellowPawn());
                    view.getYp_4().setImage(view.getYellowPawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getYellowPlayer().pawns.get(2).isFinished() && gameSession.getYellowPlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillYellow();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish1().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll1().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getYellowPlayer().pawns.get(2).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getYp_4().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getYp_4().getImage().equals(view.getYellowPawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getYellowPlayer(), gameSession.getYellowPlayer().pawns.get(3));
                    } else {
                        gameSession.jump10(gameSession.getYellowPlayer().pawns.get(3));
                        if (!Die.isRollAgain()) {
                            view.getFinish1().setVisible(true);
                        } else {
                            view.getRoll1().setVisible(true);
                        }
                    }
                    view.getYp_1().setImage(view.getYellowPawn());
                    view.getYp_2().setImage(view.getYellowPawn());
                    view.getYp_3().setImage(view.getYellowPawn());
                    view.getYp_4().setImage(view.getYellowPawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getYellowPlayer().pawns.get(3).isFinished() && gameSession.getYellowPlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillYellow();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish1().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll1().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getYellowPlayer().pawns.get(3).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getBp_1().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getBp_1().getImage().equals(view.getBluePawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getBluePlayer(), gameSession.getBluePlayer().pawns.get(0));
                    } else {
                        gameSession.jump10(gameSession.getBluePlayer().pawns.get(0));
                        if (!Die.isRollAgain()) {
                            view.getFinish2().setVisible(true);
                        } else {
                            view.getRoll2().setVisible(true);
                        }
                    }
                    view.getBp_1().setImage(view.getBluePawn());
                    view.getBp_2().setImage(view.getBluePawn());
                    view.getBp_3().setImage(view.getBluePawn());
                    view.getBp_4().setImage(view.getBluePawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getBluePlayer().pawns.get(0).isFinished() && gameSession.getBluePlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillBlue();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish2().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll2().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getBluePlayer().pawns.get(0).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getBp_2().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getBp_2().getImage().equals(view.getBluePawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getBluePlayer(), gameSession.getBluePlayer().pawns.get(1));
                    } else {
                        gameSession.jump10(gameSession.getBluePlayer().pawns.get(1));
                        if (!Die.isRollAgain()) {
                            view.getFinish2().setVisible(true);
                        } else {
                            view.getRoll2().setVisible(true);
                        }
                    }
                    view.getBp_1().setImage(view.getBluePawn());
                    view.getBp_2().setImage(view.getBluePawn());
                    view.getBp_3().setImage(view.getBluePawn());
                    view.getBp_4().setImage(view.getBluePawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getBluePlayer().pawns.get(1).isFinished() && gameSession.getBluePlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillBlue();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish2().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll2().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getBluePlayer().pawns.get(1).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getBp_3().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getBp_3().getImage().equals(view.getBluePawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getBluePlayer(), gameSession.getBluePlayer().pawns.get(2));
                    } else {
                        gameSession.jump10(gameSession.getBluePlayer().pawns.get(2));
                        if (!Die.isRollAgain()) {
                            view.getFinish2().setVisible(true);
                        } else {
                            view.getRoll2().setVisible(true);
                        }
                    }
                    view.getBp_1().setImage(view.getBluePawn());
                    view.getBp_2().setImage(view.getBluePawn());
                    view.getBp_3().setImage(view.getBluePawn());
                    view.getBp_4().setImage(view.getBluePawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getBluePlayer().pawns.get(2).isFinished() && gameSession.getBluePlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillBlue();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish2().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll2().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getBluePlayer().pawns.get(2).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getBp_4().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getBp_4().getImage().equals(view.getBluePawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getBluePlayer(), gameSession.getBluePlayer().pawns.get(3));
                    } else {
                        gameSession.jump10(gameSession.getBluePlayer().pawns.get(3));
                        if (!Die.isRollAgain()) {
                            view.getFinish2().setVisible(true);
                        } else {
                            view.getRoll2().setVisible(true);
                        }
                    }
                    view.getBp_1().setImage(view.getBluePawn());
                    view.getBp_2().setImage(view.getBluePawn());
                    view.getBp_3().setImage(view.getBluePawn());
                    view.getBp_4().setImage(view.getBluePawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getBluePlayer().pawns.get(3).isFinished() && gameSession.getBluePlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillBlue();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish2().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll2().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getBluePlayer().pawns.get(3).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getRp_1().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getRp_1().getImage().equals(view.getRedPawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getRedPlayer(), gameSession.getRedPlayer().pawns.get(0));
                    } else {
                        gameSession.jump10(gameSession.getRedPlayer().pawns.get(0));
                        if (!Die.isRollAgain()) {
                            view.getFinish3().setVisible(true);
                        } else {
                            view.getRoll3().setVisible(true);
                        }
                    }

                    view.getRp_1().setImage(view.getRedPawn());
                    view.getRp_2().setImage(view.getRedPawn());
                    view.getRp_3().setImage(view.getRedPawn());
                    view.getRp_4().setImage(view.getRedPawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getRedPlayer().pawns.get(0).isFinished() && gameSession.getRedPlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillRed();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish3().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll3().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getRedPlayer().pawns.get(0).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getRp_2().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getRp_2().getImage().equals(view.getRedPawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getRedPlayer(), gameSession.getRedPlayer().pawns.get(1));
                    } else {
                        gameSession.jump10(gameSession.getRedPlayer().pawns.get(1));
                        if (!Die.isRollAgain()) {
                            view.getFinish3().setVisible(true);
                        } else {
                            view.getRoll3().setVisible(true);
                        }
                    }

                    view.getRp_1().setImage(view.getRedPawn());
                    view.getRp_2().setImage(view.getRedPawn());
                    view.getRp_3().setImage(view.getRedPawn());
                    view.getRp_4().setImage(view.getRedPawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getRedPlayer().pawns.get(1).isFinished() && gameSession.getRedPlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillRed();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish3().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll3().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getRedPlayer().pawns.get(1).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getRp_3().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getRp_3().getImage().equals(view.getRedPawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getRedPlayer(), gameSession.getRedPlayer().pawns.get(2));
                    } else {
                        gameSession.jump10(gameSession.getRedPlayer().pawns.get(2));
                        if (!Die.isRollAgain()) {
                            view.getFinish3().setVisible(true);
                        } else {
                            view.getRoll3().setVisible(true);
                        }
                    }

                    view.getRp_1().setImage(view.getRedPawn());
                    view.getRp_2().setImage(view.getRedPawn());
                    view.getRp_3().setImage(view.getRedPawn());
                    view.getRp_4().setImage(view.getRedPawn());
                    view.getNestGlow().setVisible(false);
                    if (gameSession.getRedPlayer().pawns.get(2).isFinished() && gameSession.getRedPlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillRed();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish3().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll3().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getRedPlayer().pawns.get(2).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getRp_4().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getRp_4().getImage().equals(view.getRedPawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getRedPlayer(), gameSession.getRedPlayer().pawns.get(3));
                    } else {
                        gameSession.jump10(gameSession.getRedPlayer().pawns.get(3));
                        if (!Die.isRollAgain()) {
                            view.getFinish3().setVisible(true);
                        } else {
                            if (Die.getThrown() != 10) {
                                view.getRoll3().setVisible(true);
                            }
                        }
                    }
                    view.getRp_1().setImage(view.getRedPawn());
                    view.getRp_2().setImage(view.getRedPawn());
                    view.getRp_3().setImage(view.getRedPawn());
                    view.getRp_4().setImage(view.getRedPawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getRedPlayer().pawns.get(3).isFinished() && gameSession.getRedPlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillRed();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish3().setVisible(true);
                    } else {
                        view.getRoll3().setVisible(true);
                    }
                    updateAllPawnPositions();
                    if (gameSession.getRedPlayer().pawns.get(3).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getGp_1().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getGp_1().getImage().equals(view.getGreenPawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getGreenPlayer(), gameSession.getGreenPlayer().pawns.get(0));
                    } else {
                        gameSession.jump10(gameSession.getGreenPlayer().pawns.get(0));
                        if (!Die.isRollAgain()) {
                            view.getFinish4().setVisible(true);
                        } else {
                            view.getRoll4().setVisible(true);
                        }
                    }

                    view.getGp_1().setImage(view.getGreenPawn());
                    view.getGp_2().setImage(view.getGreenPawn());
                    view.getGp_3().setImage(view.getGreenPawn());
                    view.getGp_4().setImage(view.getGreenPawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getGreenPlayer().pawns.get(0).isFinished() && gameSession.getGreenPlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillGreen();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish4().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll4().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getGreenPlayer().pawns.get(0).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getGp_2().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getGp_2().getImage().equals(view.getGreenPawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getGreenPlayer(), gameSession.getGreenPlayer().pawns.get(1));
                    } else {
                        gameSession.jump10(gameSession.getGreenPlayer().pawns.get(1));
                        if (!Die.isRollAgain()) {
                            view.getFinish4().setVisible(true);
                        } else {
                            view.getRoll4().setVisible(true);
                        }
                    }

                    view.getGp_1().setImage(view.getGreenPawn());
                    view.getGp_2().setImage(view.getGreenPawn());
                    view.getGp_3().setImage(view.getGreenPawn());
                    view.getGp_4().setImage(view.getGreenPawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getGreenPlayer().pawns.get(1).isFinished() && gameSession.getGreenPlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillGreen();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish4().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll4().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getGreenPlayer().pawns.get(1).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getGp_3().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getGp_3().getImage().equals(view.getGreenPawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getGreenPlayer(), gameSession.getGreenPlayer().pawns.get(2));
                    } else {
                        gameSession.jump10(gameSession.getGreenPlayer().pawns.get(2));
                        if (!Die.isRollAgain()) {
                            view.getFinish4().setVisible(true);
                        } else {
                            view.getRoll4().setVisible(true);
                        }
                    }

                    view.getGp_1().setImage(view.getGreenPawn());
                    view.getGp_2().setImage(view.getGreenPawn());
                    view.getGp_3().setImage(view.getGreenPawn());
                    view.getGp_4().setImage(view.getGreenPawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getGreenPlayer().pawns.get(2).isFinished() && gameSession.getGreenPlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        glowJumpKillGreen();
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish4().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll4().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getGreenPlayer().pawns.get(2).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
        view.getGp_4().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getGp_4().getImage().equals(view.getGreenPawnGlow())) {
                    Sound.playPawnMove();
                    if (Die.getThrown() != 10) {
                        gameSession.movePawn(gameSession.getGreenPlayer(), gameSession.getGreenPlayer().pawns.get(3));
                    } else {
                        gameSession.jump10(gameSession.getGreenPlayer().pawns.get(3));
                        if (!Die.isRollAgain()) {
                            view.getFinish4().setVisible(true);
                        } else {
                            view.getRoll4().setVisible(true);
                        }
                    }

                    view.getGp_1().setImage(view.getGreenPawn());
                    view.getGp_2().setImage(view.getGreenPawn());
                    view.getGp_3().setImage(view.getGreenPawn());
                    view.getGp_4().setImage(view.getGreenPawn());
                    view.getNestGlow().setVisible(false);

                    if (gameSession.getGreenPlayer().pawns.get(3).isFinished() && gameSession.getGreenPlayer().canMove(gameSession.getBoard(), Die.getThrown())) {
                        view.getFinish4().setVisible(false);
                        Die.setTen();
                        List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getGreenPlayer());
                        greenMovablePawns(moveable);
                        if (moveable.size() == 0) {
                            if (!Die.isRollAgain()) {
                                view.getFinish4().setVisible(true);
                            }
                        }
                    }
                    if (!Die.isRollAgain()) {
                        view.getFinish4().setVisible(true);
                    } else {
                        if (Die.getThrown() != 10) {
                            view.getRoll4().setVisible(true);
                        }
                    }
                    updateAllPawnPositions();
                    if (gameSession.getGreenPlayer().pawns.get(3).isFinished()) {
                        Sound.playVictory();
                    }
                }
                checkIfEnded();
            }
        });
    }

    private void glowJumpKillGreen() {
        view.getFinish4().setVisible(false);
        Die.setTen();
        List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getGreenPlayer());
        greenMovablePawns(moveable);
        if (moveable.size() == 0) {
            if (Die.getThrown() != 6 && Die.getThrown() != 7) {
                view.getFinish4().setVisible(true);
            } else {
                if (!Die.isRollAgain()) {
                    view.getRoll4().setVisible(true);
                }
            }
        }
    }

    private void glowJumpKillRed() {
        view.getFinish3().setVisible(false);
        Die.setTen();
        List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getRedPlayer());
        redMovablePawns(moveable);
        if (moveable.size() == 0) {
            if (Die.getThrown() != 6 && Die.getThrown() != 7) {
                view.getFinish3().setVisible(true);
            } else {
                if (!Die.isRollAgain()) {
                    view.getRoll3().setVisible(true);
                }
            }
        }
    }

    private void glowJumpKillBlue() {
        view.getFinish2().setVisible(false);
        Die.setTen();
        List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getBluePlayer());
        blueMovablePawns(moveable);
        if (moveable.size() == 0) {
            if (Die.getThrown() != 6 && Die.getThrown() != 7) {
                view.getFinish2().setVisible(true);
            } else {
                if (!Die.isRollAgain()) {
                    view.getRoll2().setVisible(true);
                }
            }
        }
    }

    private void glowJumpKillYellow() {
        view.getFinish1().setVisible(false);
        Die.setTen();
        List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getYellowPlayer());
        yellowMovablePawns(moveable);
        if (moveable.size() == 0) {
            if (Die.getThrown() != 6 && Die.getThrown() != 7) {
                view.getFinish1().setVisible(true);
            } else {
                if (!Die.isRollAgain()) {
                    view.getRoll1().setVisible(true);
                }
            }
        }
    }

    private void greenMovablePawns(List<Pawn> moveable) {
        for (Pawn p : moveable) {
            if (p.getPawnNumber() == 1) {
                view.getGp_1().setImage(view.getGreenPawnGlow());
            } else if (p.getPawnNumber() == 2) {
                view.getGp_2().setImage(view.getGreenPawnGlow());
            } else if (p.getPawnNumber() == 3) {
                view.getGp_3().setImage(view.getGreenPawnGlow());
            } else if (p.getPawnNumber() == 4) {
                view.getGp_4().setImage(view.getGreenPawnGlow());
            }
        }
    }

    private void redMovablePawns(List<Pawn> moveable) {
        for (Pawn p : moveable) {
            if (p.getPawnNumber() == 1) {
                view.getRp_1().setImage(view.getRedPawnGlow());
            } else if (p.getPawnNumber() == 2) {
                view.getRp_2().setImage(view.getRedPawnGlow());
            } else if (p.getPawnNumber() == 3) {
                view.getRp_3().setImage(view.getRedPawnGlow());
            } else if (p.getPawnNumber() == 4) {
                view.getRp_4().setImage(view.getRedPawnGlow());
            }
        }
    }

    private void blueMovablePawns(List<Pawn> moveable) {
        for (Pawn p : moveable) {
            if (p.getPawnNumber() == 1) {
                view.getBp_1().setImage(view.getBluePawnGlow());
            } else if (p.getPawnNumber() == 2) {
                view.getBp_2().setImage(view.getBluePawnGlow());
            } else if (p.getPawnNumber() == 3) {
                view.getBp_3().setImage(view.getBluePawnGlow());
            } else if (p.getPawnNumber() == 4) {
                view.getBp_4().setImage(view.getBluePawnGlow());
            }
        }
    }

    private void yellowMovablePawns(List<Pawn> moveable) {
        for (Pawn p : moveable) {
            if (p.getPawnNumber() == 1) {
                view.getYp_1().setImage(view.getYellowPawnGlow());
            } else if (p.getPawnNumber() == 2) {
                view.getYp_2().setImage(view.getYellowPawnGlow());
            } else if (p.getPawnNumber() == 3) {
                view.getYp_3().setImage(view.getYellowPawnGlow());
            } else if (p.getPawnNumber() == 4) {
                view.getYp_4().setImage(view.getYellowPawnGlow());
            }
        }
    }

    private void glowMoveableYellowPawn() {
        List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getYellowPlayer());
        yellowMovablePawns(moveable);
    }

    private void blueMovablePawns() {
        List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getBluePlayer());
        blueMovablePawns(moveable);
    }

    private void redMovablePawns() {
        List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getRedPlayer());
        redMovablePawns(moveable);
    }

    private void greenMovablePawns() {
        List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getGreenPlayer());
        greenMovablePawns(moveable);
    }

    private void checkIfEnded() {
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
        for (Player p : gameSession.getPlayers()) {
            if (p.getColor().equals(Color.YELLOW)) {
                view.getYellowPlayer().setText(p.getName());
            } else if (p.getColor().equals(Color.BLUE)) {
                view.getBluePlayer().setText(p.getName());
            } else if (p.getColor().equals(Color.RED)) {
                view.getRedPlayer().setText(p.getName());
            } else if (p.getColor().equals(Color.GREEN)) {
                view.getGreenPlayer().setText(p.getName());
            }
        }
        if (!(gameSession.getPlayers().get(0) instanceof ai_Player)){
            displayControlsCurrentPlayer();
        }
        updateAllPawnPositions();
    }

    public void addWindowEventHandlers() {
    }

    public void updateAllPawnPositions() {
        view.getYp_1().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(0).getPosition().getNr()));
        view.getYp_1().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(0).getPosition().getNr()));

        view.getYp_2().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(1).getPosition().getNr()));
        view.getYp_2().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(1).getPosition().getNr()));
        if (gameSession.getYellowPlayer().pawns.get(1).getPosition().getNr() == 69) {
            view.getYp_2().setTranslateX(converter.getX(6902));
            view.getYp_2().setTranslateY(converter.getY(6902));
        }

        view.getYp_3().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(2).getPosition().getNr()));
        view.getYp_3().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(2).getPosition().getNr()));
        if (gameSession.getYellowPlayer().pawns.get(2).getPosition().getNr() == 69) {
            view.getYp_3().setTranslateX(converter.getX(6903));
            view.getYp_3().setTranslateY(converter.getY(6903));
        }

        view.getYp_4().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(3).getPosition().getNr()));
        view.getYp_4().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(3).getPosition().getNr()));
        if (gameSession.getYellowPlayer().pawns.get(3).getPosition().getNr() == 69) {
            view.getYp_4().setTranslateX(converter.getX(6904));
            view.getYp_4().setTranslateY(converter.getY(6904));
        }

        view.getBp_1().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(0).getPosition().getNr()));
        view.getBp_1().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(0).getPosition().getNr()));

        view.getBp_2().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(1).getPosition().getNr()));
        view.getBp_2().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(1).getPosition().getNr()));
        if (gameSession.getBluePlayer().pawns.get(1).getPosition().getNr() == 70) {
            view.getBp_2().setTranslateX(converter.getX(7002));
            view.getBp_2().setTranslateY(converter.getY(7002));
        }

        view.getBp_3().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(2).getPosition().getNr()));
        view.getBp_3().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(2).getPosition().getNr()));
        if (gameSession.getBluePlayer().pawns.get(2).getPosition().getNr() == 70) {
            view.getBp_3().setTranslateX(converter.getX(7003));
            view.getBp_3().setTranslateY(converter.getY(7003));
        }

        view.getBp_4().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(3).getPosition().getNr()));
        view.getBp_4().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(3).getPosition().getNr()));
        if (gameSession.getBluePlayer().pawns.get(3).getPosition().getNr() == 70) {
            view.getBp_4().setTranslateX(converter.getX(7004));
            view.getBp_4().setTranslateY(converter.getY(7004));
        }

        view.getRp_1().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(0).getPosition().getNr()));
        view.getRp_1().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(0).getPosition().getNr()));

        view.getRp_2().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(1).getPosition().getNr()));
        view.getRp_2().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(1).getPosition().getNr()));
        if (gameSession.getRedPlayer().pawns.get(1).getPosition().getNr() == 71) {
            view.getRp_2().setTranslateX(converter.getX(7102));
            view.getRp_2().setTranslateY(converter.getY(7102));
        }

        view.getRp_3().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(2).getPosition().getNr()));
        view.getRp_3().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(2).getPosition().getNr()));
        if (gameSession.getRedPlayer().pawns.get(2).getPosition().getNr() == 71) {
            view.getRp_3().setTranslateX(converter.getX(7103));
            view.getRp_3().setTranslateY(converter.getY(7103));
        }

        view.getRp_4().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(3).getPosition().getNr()));
        view.getRp_4().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(3).getPosition().getNr()));
        if (gameSession.getRedPlayer().pawns.get(3).getPosition().getNr() == 71) {
            view.getRp_4().setTranslateX(converter.getX(7104));
            view.getRp_4().setTranslateY(converter.getY(7104));
        }

        view.getGp_1().setTranslateX(converter.getX(gameSession.getGreenPlayer().pawns.get(0).getPosition().getNr()));
        view.getGp_1().setTranslateY(converter.getY(gameSession.getGreenPlayer().pawns.get(0).getPosition().getNr()));

        view.getGp_2().setTranslateX(converter.getX(gameSession.getGreenPlayer().pawns.get(1).getPosition().getNr()));
        view.getGp_2().setTranslateY(converter.getY(gameSession.getGreenPlayer().pawns.get(1).getPosition().getNr()));
        if (gameSession.getGreenPlayer().pawns.get(1).getPosition().getNr() == 72) {
            view.getGp_2().setTranslateX(converter.getX(7202));
            view.getGp_2().setTranslateY(converter.getY(7202));
        }

        view.getGp_3().setTranslateX(converter.getX(gameSession.getGreenPlayer().pawns.get(2).getPosition().getNr()));
        view.getGp_3().setTranslateY(converter.getY(gameSession.getGreenPlayer().pawns.get(2).getPosition().getNr()));
        if (gameSession.getGreenPlayer().pawns.get(2).getPosition().getNr() == 72) {
            view.getGp_3().setTranslateX(converter.getX(7203));
            view.getGp_3().setTranslateY(converter.getY(7203));
        }

        view.getGp_4().setTranslateX(converter.getX(gameSession.getGreenPlayer().pawns.get(3).getPosition().getNr()));
        view.getGp_4().setTranslateY(converter.getY(gameSession.getGreenPlayer().pawns.get(3).getPosition().getNr()));
        if (gameSession.getGreenPlayer().pawns.get(3).getPosition().getNr() == 72) {
            view.getGp_4().setTranslateX(converter.getX(7204));
            view.getGp_4().setTranslateY(converter.getY(7204));
        }
        Platform.runLater(view::rearrangePawns);
        Platform.runLater(this::updateTurn);
    }

    private void playAI(){
        new Thread(() -> {
            while (gameSession.getPlayers().get(gameSession.getIndexTurn()) instanceof ai_Player) {
                gameSession.playAiTurn();
                hideDieFaces();
                Platform.runLater(this::updateDieFace);
                Platform.runLater(this::updateAllPawnPositions);
                gameSession.endTurn();
                checkIfEnded();
            }
            displayControlsCurrentPlayer();
        }).start();
    }

    private void updateDieFace(){
        int index = gameSession.getIndexTurn();
        /*
        if (index == 0){
            index = 4;
        }

         */
        if (gameSession.getPlayers().get(index).getColor().equals(Color.YELLOW)) {
            view.getDie1().setImage(Die.getDiceFoto().getImage());
            view.getDie1().setVisible(true);
        } else if (gameSession.getPlayers().get(index).getColor().equals(Color.BLUE)) {
            view.getDie2().setImage(Die.getDiceFoto().getImage());
            view.getDie2().setVisible(true);
        } else if (gameSession.getPlayers().get(index).getColor().equals(Color.RED)) {
            view.getDie3().setImage(Die.getDiceFoto().getImage());
            view.getDie3().setVisible(true);
        } else if (gameSession.getPlayers().get(index).getColor().equals(Color.GREEN)) {
            view.getDie4().setImage(Die.getDiceFoto().getImage());
            view.getDie4().setVisible(true);
        }
    }

    private void hideDieFaces(){
        view.getDie1().setVisible(false);
        view.getDie2().setVisible(false);
        view.getDie3().setVisible(false);
        view.getDie4().setVisible(false);

    }

    private void displayControlsCurrentPlayer(){
        if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Color.YELLOW)) {
            view.getRoll1().setVisible(true);
        } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Color.BLUE)) {
            view.getRoll2().setVisible(true);
        } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Color.RED)) {
            view.getRoll3().setVisible(true);
        } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Color.GREEN)) {
            view.getRoll4().setVisible(true);
        }
    }

    private void updateTurn(){
        view.getTurns().setText("turn: " + gameSession.getTurn());
    }

}
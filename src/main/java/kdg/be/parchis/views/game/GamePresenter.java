package kdg.be.parchis.views.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kdg.be.parchis.model.game.*;
import kdg.be.parchis.model.musicLogic.SoundClass;

import java.util.List;

import static java.lang.Thread.sleep;

public class GamePresenter {
    private Game gameSession;
    private CoordinateConverter converter;
    private GameView view;
    private final KeyCode fullscreenKey = KeyCode.F;


    public GamePresenter(
            Game model,
            GameView view) {
        converter = new CoordinateConverter();
        this.gameSession = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getRoll1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll1().setVisible(false);
                SoundClass.playRoll();
                gameSession.roll();
                if (Die.getThrown() == 6 && gameSession.getAmountThrows() == 3) {
                    //return last pawn...
                    Pawn back = gameSession.lastBackToNest();
                    if (back.getPawnNumber() == 1){
                        view.getYp_1().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(0).getPosition().getNr()));
                        view.getYp_1().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(0).getPosition().getNr()));
                    } else if (back.getPawnNumber() == 2) {
                        view.getYp_2().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(1).getPosition().getNr()));
                        view.getYp_2().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(1).getPosition().getNr()));
                    } else if (back.getPawnNumber() == 3) {
                        view.getYp_3().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(2).getPosition().getNr()));
                        view.getYp_3().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(2).getPosition().getNr()));
                    } else if (back.getPawnNumber() == 4) {
                        view.getYp_4().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(3).getPosition().getNr()));
                        view.getYp_4().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(3).getPosition().getNr()));
                    }
                    view.getFinish1().setVisible(true);
                    return;
                }

                view.getDie1().setVisible(true);
                view.getDie1().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getYellowPlayer()) && Die.getThrown() != 5) {
                    //do nothing when you cant do anything
                    view.getFinish1().setVisible(true);
                } else if (!gameSession.canPlayerMove(gameSession.getYellowPlayer()) && Die.getThrown() == 5
                        && gameSession.isStartOK(gameSession.getYellowPlayer())) {
                    view.getNestGlow().setImage(view.getGlowNestYellow());
                    view.getNestGlow().setVisible(true);
                } else if (gameSession.canPlayerMove(gameSession.getYellowPlayer()) && Die.getThrown() != 5) {
                    //move pawn automatically if you didn't throw a 5
                    //still needs selection incase you can choose which pawn to move.
                    //...
                    List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getYellowPlayer());
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
                } else {
                    if (!gameSession.getYellowPlayer().isNestEmpty() && gameSession.isStartOK(gameSession.getYellowPlayer())) {
                        view.getNestGlow().setImage(view.getGlowNestYellow());
                        view.getNestGlow().setVisible(true);
                    }
                    List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getYellowPlayer());
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
                    if (Die.getThrown() == 6) {
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
                SoundClass.playRoll();
                gameSession.roll();
                //if three is thrown 3x, return last moved pawn
                if (Die.getThrown() == 6 && gameSession.getAmountThrows() == 3) {
                    Pawn back = gameSession.lastBackToNest();
                    if (back.getPawnNumber() == 1){
                        view.getBp_1().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(0).getPosition().getNr()));
                        view.getBp_1().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(0).getPosition().getNr()));
                    } else if (back.getPawnNumber() == 2) {
                        view.getBp_2().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(1).getPosition().getNr()));
                        view.getBp_2().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(1).getPosition().getNr()));
                    } else if (back.getPawnNumber() == 3) {
                        view.getBp_3().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(2).getPosition().getNr()));
                        view.getBp_3().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(2).getPosition().getNr()));
                    } else if (back.getPawnNumber() == 4) {
                        view.getBp_4().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(3).getPosition().getNr()));
                        view.getBp_4().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(3).getPosition().getNr()));
                    }
                    view.getFinish2().setVisible(true);
                    return;
                }

                view.getDie2().setVisible(true);
                view.getDie2().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getBluePlayer()) && Die.getThrown() != 5) {
                    //do nothing when you cant do anything
                    view.getFinish2().setVisible(true);
                } else if (!gameSession.canPlayerMove(gameSession.getBluePlayer()) && Die.getThrown() == 5
                        && gameSession.isStartOK(gameSession.getBluePlayer())) {
                    view.getNestGlow().setImage(view.getGlowNestBlue());
                    view.getNestGlow().setVisible(true);
                } else if (gameSession.canPlayerMove(gameSession.getBluePlayer()) && Die.getThrown() != 5) {
                    List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getBluePlayer());
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
                } else {
                    if (!gameSession.getBluePlayer().isNestEmpty() && gameSession.isStartOK(gameSession.getBluePlayer())) {
                        view.getNestGlow().setImage(view.getGlowNestBlue());
                        view.getNestGlow().setVisible(true);
                    }
                    List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getBluePlayer());
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
                    if (Die.getThrown() == 6) {
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
                SoundClass.playRoll();
                gameSession.roll();
                //if three is thrown 3x, return last moved pawn
                if (Die.getThrown() == 6 && gameSession.getAmountThrows() == 3) {
                    Pawn back = gameSession.lastBackToNest();
                    if (back.getPawnNumber() == 1){
                        view.getRp_1().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(0).getPosition().getNr()));
                        view.getRp_1().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(0).getPosition().getNr()));
                    } else if (back.getPawnNumber() == 2) {
                        view.getRp_2().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(1).getPosition().getNr()));
                        view.getRp_2().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(1).getPosition().getNr()));
                    } else if (back.getPawnNumber() == 3) {
                        view.getRp_3().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(2).getPosition().getNr()));
                        view.getRp_3().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(2).getPosition().getNr()));
                    } else if (back.getPawnNumber() == 4) {
                        view.getRp_4().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(3).getPosition().getNr()));
                        view.getRp_4().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(3).getPosition().getNr()));
                    }
                    view.getFinish3().setVisible(true);
                    return;
                }

                view.getDie3().setVisible(true);
                view.getDie3().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getRedPlayer()) && Die.getThrown() != 5) {
                    //do nothing when you cant do anything
                    view.getFinish3().setVisible(true);
                } else if (!gameSession.canPlayerMove(gameSession.getRedPlayer()) && Die.getThrown() == 5
                        && gameSession.isStartOK(gameSession.getRedPlayer())) {
                    view.getNestGlow().setImage(view.getGlowNestRed());
                    view.getNestGlow().setVisible(true);
                } else if (gameSession.canPlayerMove(gameSession.getRedPlayer()) && Die.getThrown() != 5) {
                    List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getRedPlayer());
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
                } else {
                    if (!gameSession.getRedPlayer().isNestEmpty() && gameSession.isStartOK(gameSession.getRedPlayer())) {
                        view.getNestGlow().setImage(view.getGlowNestRed());
                        view.getNestGlow().setVisible(true);
                    }
                    List<Pawn> moveable = gameSession.getMoveablePawns(gameSession.getRedPlayer());
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
                    if (Die.getThrown() == 6) {
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
                SoundClass.playRoll();
                gameSession.roll();
                if (Die.getThrown() == 6 && gameSession.getAmountThrows() == 3) {
                    //return last pawn...

                    view.getFinish4().setVisible(true);
                    return;
                }
                view.getDie4().setVisible(true);
                view.getDie4().setImage(Die.getDiceFoto().getImage());
                if (!gameSession.canPlayerMove(gameSession.getGreenPlayer()) && Die.getThrown() != 5) {
                    //do nothing when you cant do anything
                    view.getFinish4().setVisible(true);
                } else if (!gameSession.canPlayerMove(gameSession.getGreenPlayer()) && Die.getThrown() == 5
                        && gameSession.isStartOK(gameSession.getGreenPlayer())) {
                    //automatically get pawn out of nest if there isn't anything else to do
                    //gameSession.yellowLeaveNest();
                    view.getNestGlow().setImage(view.getGlowNestGreen());
                    view.getNestGlow().setVisible(true);
                } else if (gameSession.canPlayerMove(gameSession.getGreenPlayer()) && Die.getThrown() != 5) {
                    //move pawn automatically if you didn't throw a 5
                    //still needs selection incase you can choose which pawn to move.
                    //...
                    view.getFinish4().setVisible(true);
                } else {
                    //choose to move or move out of nest
                    //...
                    view.getFinish4().setVisible(true);
                }

                if (Die.getThrown() == 6) {
                    view.getRoll4().setVisible(true);
                    view.getFinish4().setVisible(false);
                }
            }
        });
        view.getFinish1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SoundClass.playClick();
                view.getFinish1().setVisible(false);
                view.getDie1().setVisible(false);
                gameSession.endTurn();
                if (gameSession.getIndexTurn() == 0) {
                    view.getTurns().setText("turn: " + gameSession.getTurn());
                }
                if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.BLUE)) {
                    view.getRoll2().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.RED)) {
                    view.getRoll3().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.GREEN)) {
                    view.getRoll4().setVisible(true);
                }
            }
        });
        view.getFinish2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SoundClass.playClick();
                view.getFinish2().setVisible(false);
                view.getDie2().setVisible(false);
                gameSession.endTurn();
                if (gameSession.getIndexTurn() == 0) {
                    view.getTurns().setText("turn: " + gameSession.getTurn());
                }
                if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.YELLOW)) {
                    view.getRoll1().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.RED)) {
                    view.getRoll3().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.GREEN)) {
                    view.getRoll4().setVisible(true);
                }
            }
        });
        view.getFinish3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SoundClass.playClick();
                view.getFinish3().setVisible(false);
                view.getDie3().setVisible(false);
                gameSession.endTurn();
                if (gameSession.getIndexTurn() == 0) {
                    view.getTurns().setText("turn: " + gameSession.getTurn());
                }
                if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.YELLOW)) {
                    view.getRoll1().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.BLUE)) {
                    view.getRoll2().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.GREEN)) {
                    view.getRoll4().setVisible(true);
                }
            }
        });
        view.getFinish4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SoundClass.playClick();
                view.getFinish4().setVisible(false);
                view.getDie4().setVisible(false);
                gameSession.endTurn();
                if (gameSession.getIndexTurn() == 0) {
                    view.getTurns().setText("turn: " + gameSession.getTurn());
                }
                if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.YELLOW)) {
                    view.getRoll1().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.BLUE)) {
                    view.getRoll2().setVisible(true);
                } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.RED)) {
                    view.getRoll3().setVisible(true);
                }
            }
        });
        view.getNestGlow().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getNestGlow().getImage().equals(view.getGlowNestYellow())) {
                    Pawn left = gameSession.yellowLeaveNest();
                    if (left.getPawnNumber() == 1){
                        view.getYp_1().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(0).getPosition().getNr()));
                        view.getYp_1().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(0).getPosition().getNr()));
                    } else if (left.getPawnNumber() == 2) {
                        view.getYp_2().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(1).getPosition().getNr()));
                        view.getYp_2().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(1).getPosition().getNr()));
                    } else if (left.getPawnNumber() == 3) {
                        view.getYp_3().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(2).getPosition().getNr()));
                        view.getYp_3().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(2).getPosition().getNr()));
                    } else if (left.getPawnNumber() == 4) {
                        view.getYp_4().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(3).getPosition().getNr()));
                        view.getYp_4().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(3).getPosition().getNr()));
                    }
                    view.getFinish1().setVisible(true);
                } else if (view.getNestGlow().getImage().equals(view.getGlowNestBlue())) {
                    Pawn left = gameSession.blueLeaveNest();
                    if (left.getPawnNumber() == 1){
                        view.getBp_1().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(0).getPosition().getNr()));
                        view.getBp_1().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(0).getPosition().getNr()));
                    } else if (left.getPawnNumber() == 2) {
                        view.getBp_2().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(1).getPosition().getNr()));
                        view.getBp_2().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(1).getPosition().getNr()));
                    } else if (left.getPawnNumber() == 3) {
                        view.getBp_3().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(2).getPosition().getNr()));
                        view.getBp_3().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(2).getPosition().getNr()));
                    } else if (left.getPawnNumber() == 4) {
                        view.getBp_4().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(3).getPosition().getNr()));
                        view.getBp_4().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(3).getPosition().getNr()));
                    }
                    view.getFinish2().setVisible(true);
                } else if (view.getNestGlow().getImage().equals(view.getGlowNestRed())) {
                    Pawn left = gameSession.redLeaveNest();
                    if (left.getPawnNumber() == 1){
                        view.getRp_1().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(0).getPosition().getNr()));
                        view.getRp_1().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(0).getPosition().getNr()));
                    } else if (left.getPawnNumber() == 2) {
                        view.getRp_2().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(1).getPosition().getNr()));
                        view.getRp_2().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(1).getPosition().getNr()));
                    } else if (left.getPawnNumber() == 3) {
                        view.getRp_3().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(2).getPosition().getNr()));
                        view.getRp_3().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(2).getPosition().getNr()));
                    } else if (left.getPawnNumber() == 4) {
                        view.getRp_4().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(3).getPosition().getNr()));
                        view.getRp_4().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(3).getPosition().getNr()));
                    }
                    view.getFinish3().setVisible(true);
                } else if (view.getNestGlow().getImage().equals(view.getGlowNestGreen())) {
                    Pawn left = gameSession.greenLeaveNest();
                    if (left.getPawnNumber() == 1){
                        view.getGp_1().setTranslateX(converter.getX(gameSession.getGreenPlayer().pawns.get(0).getPosition().getNr()));
                        view.getGp_1().setTranslateY(converter.getY(gameSession.getGreenPlayer().pawns.get(0).getPosition().getNr()));
                    } else if (left.getPawnNumber() == 2) {
                        view.getGp_2().setTranslateX(converter.getX(gameSession.getGreenPlayer().pawns.get(1).getPosition().getNr()));
                        view.getGp_2().setTranslateY(converter.getY(gameSession.getGreenPlayer().pawns.get(1).getPosition().getNr()));
                    } else if (left.getPawnNumber() == 3) {
                        view.getGp_3().setTranslateX(converter.getX(gameSession.getGreenPlayer().pawns.get(2).getPosition().getNr()));
                        view.getGp_3().setTranslateY(converter.getY(gameSession.getGreenPlayer().pawns.get(2).getPosition().getNr()));
                    } else if (left.getPawnNumber() == 4) {
                        view.getGp_4().setTranslateX(converter.getX(gameSession.getGreenPlayer().pawns.get(3).getPosition().getNr()));
                        view.getGp_4().setTranslateY(converter.getY(gameSession.getGreenPlayer().pawns.get(3).getPosition().getNr()));
                    }
                    view.getFinish3().setVisible(true);
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
            }
        });
        view.getYp_1().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getYp_1().getImage().equals(view.getYellowPawnGlow())) {
                    gameSession.movePawn(gameSession.getYellowPlayer() ,gameSession.getYellowPlayer().pawns.get(0));
                    view.getYp_1().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(0).getPosition().getNr()));
                    view.getYp_1().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(0).getPosition().getNr()));
                    if (Die.getThrown() != 6) {
                        view.getFinish1().setVisible(true);
                    } else {
                        view.getRoll1().setVisible(true);
                    }
                    view.getYp_1().setImage(view.getYellowPawn());
                    view.getYp_2().setImage(view.getYellowPawn());
                    view.getYp_3().setImage(view.getYellowPawn());
                    view.getYp_4().setImage(view.getYellowPawn());
                    view.getNestGlow().setVisible(false);
                }
            }
        });
        view.getYp_2().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getYp_2().getImage().equals(view.getYellowPawnGlow())) {
                    gameSession.movePawn(gameSession.getYellowPlayer() ,gameSession.getYellowPlayer().pawns.get(1));
                    view.getYp_2().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(1).getPosition().getNr()));
                    view.getYp_2().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(1).getPosition().getNr()));
                    if (Die.getThrown() != 6) {
                        view.getFinish2().setVisible(true);
                    } else {
                        view.getRoll2().setVisible(true);
                    }
                    view.getYp_1().setImage(view.getYellowPawn());
                    view.getYp_2().setImage(view.getYellowPawn());
                    view.getYp_3().setImage(view.getYellowPawn());
                    view.getYp_4().setImage(view.getYellowPawn());
                    view.getNestGlow().setVisible(false);
                }
            }
        });
        view.getYp_3().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getYp_3().getImage().equals(view.getYellowPawnGlow())) {
                    gameSession.movePawn(gameSession.getYellowPlayer() ,gameSession.getYellowPlayer().pawns.get(2));
                    view.getYp_3().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(2).getPosition().getNr()));
                    view.getYp_3().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(2).getPosition().getNr()));
                    if (Die.getThrown() != 6) {
                        view.getFinish3().setVisible(true);
                    } else {
                        view.getRoll3().setVisible(true);
                    }
                    view.getYp_1().setImage(view.getYellowPawn());
                    view.getYp_2().setImage(view.getYellowPawn());
                    view.getYp_3().setImage(view.getYellowPawn());
                    view.getYp_4().setImage(view.getYellowPawn());
                    view.getNestGlow().setVisible(false);
                }
            }
        });
        view.getYp_4().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getYp_4().getImage().equals(view.getYellowPawnGlow())) {
                    gameSession.movePawn(gameSession.getYellowPlayer() ,gameSession.getYellowPlayer().pawns.get(3));
                    view.getYp_4().setTranslateX(converter.getX(gameSession.getYellowPlayer().pawns.get(3).getPosition().getNr()));
                    view.getYp_4().setTranslateY(converter.getY(gameSession.getYellowPlayer().pawns.get(3).getPosition().getNr()));
                    if (Die.getThrown() != 6) {
                        view.getFinish4().setVisible(true);
                    } else {
                        view.getRoll4().setVisible(true);
                    }
                    view.getYp_1().setImage(view.getYellowPawn());
                    view.getYp_2().setImage(view.getYellowPawn());
                    view.getYp_3().setImage(view.getYellowPawn());
                    view.getYp_4().setImage(view.getYellowPawn());
                    view.getNestGlow().setVisible(false);
                }
            }
        });
        view.getBp_1().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getBp_1().getImage().equals(view.getBluePawnGlow())) {
                    gameSession.movePawn(gameSession.getBluePlayer() ,gameSession.getBluePlayer().pawns.get(0));
                    view.getBp_1().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(0).getPosition().getNr()));
                    view.getBp_1().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(0).getPosition().getNr()));
                    if (Die.getThrown() != 6) {
                        view.getFinish2().setVisible(true);
                    } else {
                        view.getRoll2().setVisible(true);
                    }
                    view.getBp_1().setImage(view.getBluePawn());
                    view.getBp_2().setImage(view.getBluePawn());
                    view.getBp_3().setImage(view.getBluePawn());
                    view.getBp_4().setImage(view.getBluePawn());
                    view.getNestGlow().setVisible(false);
                }
            }
        });
        view.getBp_2().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getBp_2().getImage().equals(view.getBluePawnGlow())) {
                    gameSession.movePawn(gameSession.getBluePlayer() ,gameSession.getBluePlayer().pawns.get(1));
                    view.getBp_2().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(1).getPosition().getNr()));
                    view.getBp_2().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(1).getPosition().getNr()));
                    if (Die.getThrown() != 6) {
                        view.getFinish2().setVisible(true);
                    } else {
                        view.getRoll2().setVisible(true);
                    }
                    view.getBp_1().setImage(view.getBluePawn());
                    view.getBp_2().setImage(view.getBluePawn());
                    view.getBp_3().setImage(view.getBluePawn());
                    view.getBp_4().setImage(view.getBluePawn());
                    view.getNestGlow().setVisible(false);
                }
            }
        });
        view.getBp_3().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getBp_3().getImage().equals(view.getBluePawnGlow())) {
                    gameSession.movePawn(gameSession.getBluePlayer() ,gameSession.getBluePlayer().pawns.get(2));
                    view.getBp_3().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(2).getPosition().getNr()));
                    view.getBp_3().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(2).getPosition().getNr()));
                    if (Die.getThrown() != 6) {
                        view.getFinish2().setVisible(true);
                    } else {
                        view.getRoll2().setVisible(true);
                    }
                    view.getBp_1().setImage(view.getBluePawn());
                    view.getBp_2().setImage(view.getBluePawn());
                    view.getBp_3().setImage(view.getBluePawn());
                    view.getBp_4().setImage(view.getBluePawn());
                    view.getNestGlow().setVisible(false);
                }
            }
        });
        view.getBp_4().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getBp_4().getImage().equals(view.getBluePawnGlow())) {
                    gameSession.movePawn(gameSession.getBluePlayer() ,gameSession.getBluePlayer().pawns.get(3));
                    view.getBp_4().setTranslateX(converter.getX(gameSession.getBluePlayer().pawns.get(3).getPosition().getNr()));
                    view.getBp_4().setTranslateY(converter.getY(gameSession.getBluePlayer().pawns.get(3).getPosition().getNr()));
                    if (Die.getThrown() != 6) {
                        view.getFinish2().setVisible(true);
                    } else {
                        view.getRoll2().setVisible(true);
                    }
                    view.getBp_1().setImage(view.getBluePawn());
                    view.getBp_2().setImage(view.getBluePawn());
                    view.getBp_3().setImage(view.getBluePawn());
                    view.getBp_4().setImage(view.getBluePawn());
                    view.getNestGlow().setVisible(false);
                }
            }
        });
        view.getRp_1().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getRp_1().getImage().equals(view.getRedPawnGlow())) {
                    gameSession.movePawn(gameSession.getRedPlayer() ,gameSession.getRedPlayer().pawns.get(0));
                    view.getRp_1().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(0).getPosition().getNr()));
                    view.getRp_1().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(0).getPosition().getNr()));
                    if (Die.getThrown() != 6) {
                        view.getFinish3().setVisible(true);
                    } else {
                        view.getRoll3().setVisible(true);
                    }
                    view.getRp_1().setImage(view.getRedPawn());
                    view.getRp_2().setImage(view.getRedPawn());
                    view.getRp_3().setImage(view.getRedPawn());
                    view.getRp_4().setImage(view.getRedPawn());
                    view.getNestGlow().setVisible(false);
                }
            }
        });
        view.getRp_2().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (view.getRp_2().getImage().equals(view.getRedPawnGlow())) {
                    gameSession.movePawn(gameSession.getRedPlayer() ,gameSession.getRedPlayer().pawns.get(1));
                    view.getRp_2().setTranslateX(converter.getX(gameSession.getRedPlayer().pawns.get(1).getPosition().getNr()));
                    view.getRp_2().setTranslateY(converter.getY(gameSession.getRedPlayer().pawns.get(1).getPosition().getNr()));
                    if (Die.getThrown() != 6) {
                        view.getFinish3().setVisible(true);
                    } else {
                        view.getRoll3().setVisible(true);
                    }
                    view.getRp_1().setImage(view.getRedPawn());
                    view.getRp_2().setImage(view.getRedPawn());
                    view.getRp_3().setImage(view.getRedPawn());
                    view.getRp_4().setImage(view.getRedPawn());
                    view.getNestGlow().setVisible(false);
                }
            }
        });
    }

    private void updateView() {
        view.getTurns().setText("turn: " + gameSession.getTurn());
        //set names
        for (Player p : gameSession.getPlayers()) {
            if (p.getColor().equals(Colors.YELLOW)) {
                view.getYellowPlayer().setText(p.getName());
            } else if (p.getColor().equals(Colors.BLUE)) {
                view.getBluePlayer().setText(p.getName());
            } else if (p.getColor().equals(Colors.RED)) {
                view.getRedPlayer().setText(p.getName());
            } else if (p.getColor().equals(Colors.GREEN)) {
                view.getGreenPlayer().setText(p.getName());
            }
        }
        //View controlls for first rolling player
        if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.YELLOW)) {
            view.getRoll1().setVisible(true);
        } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.BLUE)) {
            view.getRoll2().setVisible(true);
        } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.RED)) {
            view.getRoll3().setVisible(true);
        } else if (gameSession.getPlayers().get(gameSession.getIndexTurn()).getColor().equals(Colors.GREEN)) {
            view.getRoll4().setVisible(true);
        }
    }

    public void addWindowEventHandlers() {
        view.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == fullscreenKey) {
                Scene scene = view.getScene();
                if (scene != null) {
                    Stage stage = (Stage) scene.getWindow();
                    stage.setFullScreen(!stage.isFullScreen());
                }
            }
        });
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }
}

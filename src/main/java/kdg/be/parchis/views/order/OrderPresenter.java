package kdg.be.parchis.views.order;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import kdg.be.parchis.model.game.Die;
import kdg.be.parchis.model.game.Game;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.model.musicLogic.MainMusic;
import kdg.be.parchis.views.game.GamePresenter;
import kdg.be.parchis.views.game.GameView;
import kdg.be.parchis.views.playersetup.PlayerSetupView;

import java.io.File;
import java.io.FileNotFoundException;

import static java.lang.Thread.sleep;

public class OrderPresenter {
    private PlayerSetup setup;
    private OrderView view;
    private PlayerSetupView backView;
    private int roll;
    public OrderPresenter(
            PlayerSetup model,
            OrderView view,
            PlayerSetupView backView) {
        this.setup = model;
        this.view = view;
        this.backView = backView;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
// Koppelt event handlers (anon. inner klassen)
// aan de controls uit de view.
// Event handlers: roepen methodes aan uit het
// model en zorgen voor een update van de view.
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String path = "resources\\audio\\click.mp3";
                Media media = new Media(new File(path).toURI().toString());
                // Just media doesn't work, x.getSource has to be added as well.
                AudioClip sound = new AudioClip(media.getSource());
                sound.play();
                view.getScene().setRoot(backView);
                MainMusic.stopMusic();
                MainMusic.playMenuMusic();
            }
        });
        view.getRoll1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll1().setVisible(false);
                String path = "resources\\audio\\roll.wav";
                Media media = new Media(new File(path).toURI().toString());
                // Just media doesn't work, x.getSource has to be added as well.
                AudioClip sound = new AudioClip(media.getSource());
                    sound.play();
                    view.getRoll1().setDisable(true);
                try {
                    sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                view.getRoll1().setDisable(false);
                Die.throwDie();
                setup.addRoll(0, Die.getThrown());
                view.getDiceFoto1().setImage(Die.getDiceFoto().getImage());
                view.getBack().setVisible(false);
                if (setup.didPlayersRoll()){
                    setup.order();
                    setup.showOrder();
                    //Go to gameView
                    GameView gameView = null;
                    try {
                        gameView = new GameView();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    Game gameSession = new Game(setup.getPlayers());
                    GamePresenter gamePresenter = new GamePresenter(gameSession, gameView);
                    view.getScene().setRoot(gameView);
                }
            }
        });
        view.getRoll2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll2().setVisible(false);
                String path = "resources\\audio\\roll.wav";
                Media media = new Media(new File(path).toURI().toString());
                // Just media doesn't work, x.getSource has to be added as well.
                AudioClip sound = new AudioClip(media.getSource());
                sound.play();
                view.getRoll2().setDisable(true);
                try {
                    sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                view.getRoll2().setDisable(false);
                Die.throwDie();
                setup.addRoll(1, Die.getThrown());
                view.getDiceFoto2().setImage(Die.getDiceFoto().getImage());
                view.getBack().setVisible(false);
                if (setup.didPlayersRoll()){
                    setup.order();
                    setup.showOrder();
                    //Go to gameView
                    GameView gameView = null;
                    try {
                        gameView = new GameView();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    Game gameSession = new Game(setup.getPlayers());
                    GamePresenter gamePresenter = new GamePresenter(gameSession, gameView);
                    view.getScene().setRoot(gameView);
                }
            }
        });
        view.getRoll3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll3().setVisible(false);
                String path = "resources\\audio\\roll.wav";
                Media media = new Media(new File(path).toURI().toString());
                // Just media doesn't work, x.getSource has to be added as well.
                AudioClip sound = new AudioClip(media.getSource());
                sound.play();
                view.getRoll3().setDisable(true);
                try {
                    sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                view.getRoll3().setDisable(false);
                Die.throwDie();
                setup.addRoll(2, Die.getThrown());
                view.getDiceFoto3().setImage(Die.getDiceFoto().getImage());
                view.getBack().setVisible(false);
                if (setup.didPlayersRoll()){
                    setup.order();
                    setup.showOrder();
                    //Go to gameView
                    GameView gameView = null;
                    try {
                        gameView = new GameView();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    Game gameSession = new Game(setup.getPlayers());
                    GamePresenter gamePresenter = new GamePresenter(gameSession, gameView);
                    view.getScene().setRoot(gameView);
                }
            }
        });
        view.getRoll4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getRoll4().setVisible(false);
                String path = "resources\\audio\\roll.wav";
                Media media = new Media(new File(path).toURI().toString());
                // Just media doesn't work, x.getSource has to be added as well.
                AudioClip sound = new AudioClip(media.getSource());
                sound.play();
                view.getRoll4().setDisable(true);
                try {
                    sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                view.getRoll4().setDisable(false);
                Die.throwDie();
                setup.addRoll(3, Die.getThrown());
                view.getDiceFoto4().setImage(Die.getDiceFoto().getImage());
                view.getBack().setVisible(false);
                if (setup.didPlayersRoll()){
                    setup.order();
                    setup.showOrder();
                    //Go to gameView
                    GameView gameView = null;
                    try {
                        gameView = new GameView();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    Game gameSession = new Game(setup.getPlayers());
                    GamePresenter gamePresenter = new GamePresenter(gameSession, gameView);
                    view.getScene().setRoot(gameView);
                }
            }
        });
    }
    private void updateView() {
// Vult de view met data uit model
        try {
            sleep(2 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void addWindowEventHandlers () {
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }
}

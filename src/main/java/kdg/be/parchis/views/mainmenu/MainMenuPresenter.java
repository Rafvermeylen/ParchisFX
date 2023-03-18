package kdg.be.parchis.views.mainmenu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import kdg.be.parchis.model.game.Color;
import kdg.be.parchis.model.game.Game;
import kdg.be.parchis.model.game.Player;
import kdg.be.parchis.model.game.AiPlayer;
import kdg.be.parchis.model.musicLogic.Music;
import kdg.be.parchis.model.musicLogic.Sound;
import kdg.be.parchis.views.credits.CreditsPresenter;
import kdg.be.parchis.views.credits.CreditsView;
import kdg.be.parchis.views.game.GamePresenter;
import kdg.be.parchis.views.game.GameView;
import kdg.be.parchis.views.leaderboards.LeaderboardPresenter;
import kdg.be.parchis.views.leaderboards.LeaderboardView;
import kdg.be.parchis.views.playerselect.PlayerSelectPresenter;
import kdg.be.parchis.views.playerselect.PlayerSelectView;
import kdg.be.parchis.views.rules.RulePresenter;
import kdg.be.parchis.views.rules.RuleView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainMenuPresenter {
    private final KeyCode fullscreenKey = KeyCode.F;
    private final MainMenuView view;

    public MainMenuPresenter(MainMenuView view) {
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    class Cheats {
        public static boolean activated = false;

        public static void clickButton() {
            activated = !activated;
        }

        public static boolean getActivated() {
            return activated;
        }
    }

    private void addEventHandlers() {
        view.getExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        view.getSoundButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Music.muteMenuMusic();

                if (Music.getMediaPlayer().isMute()) {
                    view.getMusicPic().setImage(view.getMusicMute());
                } else {
                    view.getMusicPic().setImage(view.getMusicLoud());
                }
            }
        });

        view.getFxButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.clickMute();

                if (Sound.isMuted()) {
                    view.getSoundPic().setImage(view.getSfxMute());
                } else {
                    view.getSoundPic().setImage(view.getSfxLoud());
                }
            }
        });

        view.getCheat().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Sound.playClick();
                Cheats.clickButton();
                updateView();
            }
        });

        view.getRules().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RuleView ruleView = null;
                try {
                    ruleView = new RuleView();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                RulePresenter rulePresenter = new RulePresenter(ruleView, view);
                Sound.playClick();
                view.getScene().setRoot(ruleView);
            }
        });

        view.getLeaderboards().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LeaderboardView leadView = null;
                try {
                    leadView = new LeaderboardView();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                LeaderboardPresenter leadPres = new LeaderboardPresenter(leadView, view);
                Sound.playClick();
                view.getScene().setRoot(leadView);
            }
        });

        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!Cheats.getActivated()) {
                    PlayerSelectView psView = null;
                    try {
                        psView = new PlayerSelectView();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    PlayerSelectPresenter psPresenter = new PlayerSelectPresenter(psView, view);
                    Sound.playClick();
                    view.getScene().setRoot(psView);
                } else {
                    Music.stopMusic();
                    Music.playGameMusic();
                    ArrayList<Player> players = new ArrayList<>();
                    players.add(new AiPlayer("Bot_Yellow", Color.YELLOW));
                    players.add(new AiPlayer("Bot_Blue", Color.BLUE));
                    players.add(new AiPlayer("Bot_Red", Color.RED));
                    players.add(new AiPlayer("Bot_Green", Color.GREEN));
                    Game gameSession = new Game(players);
                    GameView gameView = null;
                    try {
                        gameView = new GameView();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    GamePresenter gamePresenter = new GamePresenter(gameSession, gameView);
                    view.getScene().setRoot(gameView);
                }
            }
        });

        view.getCredits().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreditsView creditsView = null;
                try {
                    creditsView = new CreditsView();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                CreditsPresenter creditsPresenter = new CreditsPresenter(creditsView, view);
                Sound.playClick();
                view.getScene().setRoot(creditsView);
            }
        });
    }

    private void updateView() {
        if (Cheats.getActivated()) {
            view.getCheat().setText("Cheats: ON");
        } else {
            view.getCheat().setText("Cheats: OFF");
        }
        updateIcons();
    }

    public void addWindowEventHandlers() {
        //make game fullscreen by pressing f
        view.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == fullscreenKey) {
                Scene scene = view.getScene();
                if (scene != null) {
                    Stage stage = (Stage) scene.getWindow();
                    stage.setFullScreen(!stage.isFullScreen());
                }
            }
        });
    }

    public void updateIcons() {
        if (Music.getMediaPlayer().isMute()) {
            view.getMusicPic().setImage(view.getMusicMute());
        } else {
            view.getMusicPic().setImage(view.getMusicLoud());
        }
        if (Sound.isMuted()) {
            view.getSoundPic().setImage(view.getSfxMute());
        } else {
            view.getSoundPic().setImage(view.getSfxLoud());
        }
    }
}
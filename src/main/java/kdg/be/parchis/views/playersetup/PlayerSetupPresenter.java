package kdg.be.parchis.views.playersetup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.views.order.OrderPresenter;
import kdg.be.parchis.views.order.OrderView;
import kdg.be.parchis.views.playerselect.PlayerSelectView;

import java.io.File;
import java.io.FileNotFoundException;

public class PlayerSetupPresenter extends BorderPane {
    private PlayerSetup setup;
    private PlayerSetupView view;
    private PlayerSelectView backView;
    public PlayerSetupPresenter (
            PlayerSetup model,
            PlayerSetupView view,
            PlayerSelectView backView) {
        this.setup = model;
        this.view = view;
        this.backView = backView;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
        view.getBack().setOnAction(actionEvent -> {
            String path = "resources\\audio\\click.mp3";
            Media media = new Media(new File(path).toURI().toString());
            // Just media doesn't work, x.getSource has to be added as well.
            AudioClip sound = new AudioClip(media.getSource());
            sound.play();
            view.getScene().setRoot(backView);
        });
        view.getPlay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String path = "resources\\audio\\click.mp3";
                Media media = new Media(new File(path).toURI().toString());
                // Just media doesn't work, x.getSource has to be added as well.
                AudioClip sound = new AudioClip(media.getSource());
                sound.play();
                //Add players to playerlist
                if (view.getSetup().getAmountPlayers() == 1) {
                    view.getSetup().setPlayers(
                            view.getPlayer1NameArea().getText());
                } else if (view.getSetup().getAmountPlayers() == 2) {
                    view.getSetup().setPlayers(
                            view.getPlayer1NameArea().getText(),
                            view.getPlayer2NameArea().getText());
                } else if (view.getSetup().getAmountPlayers() == 3) {
                    view.getSetup().setPlayers(
                            view.getPlayer1NameArea().getText(),
                            view.getPlayer2NameArea().getText(),
                            view.getPlayer3NameArea().getText());
                } else if (view.getSetup().getAmountPlayers() == 4) {
                    view.getSetup().setPlayers(
                            view.getPlayer1NameArea().getText(),
                            view.getPlayer2NameArea().getText(),
                            view.getPlayer3NameArea().getText(),
                            view.getPlayer4NameArea().getText());
                }
                //change view
                OrderView orderview = null;
                try {
                    orderview = new OrderView(setup);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                OrderPresenter orderPresenter = new OrderPresenter(view.getSetup(), orderview, view);
                view.getScene().setRoot(orderview);
            }
        });
    }
    private void updateView() {
// Vult de view met data uit model
    }
    public void addWindowEventHandlers () {
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }

}

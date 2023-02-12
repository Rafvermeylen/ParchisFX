package kdg.be.parchis.views.order;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import kdg.be.parchis.model.game.Die;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.views.playersetup.PlayerSetupView;

import java.io.File;
import java.util.Set;

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
            }
        });
        view.getRoll().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String path = "resources\\audio\\roll.wav";
                Media media = new Media(new File(path).toURI().toString());
                // Just media doesn't work, x.getSource has to be added as well.
                AudioClip sound = new AudioClip(media.getSource());
                    sound.play();
                    view.getRoll().setDisable(true);
                try {
                    sleep(2 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                view.getRoll().setDisable(false);
                roll = Die.throwDie();
                if (roll == 1){
                    view.getDiceFoto().setImage(view.getDie1());
                } else if (roll == 2){
                    view.getDiceFoto().setImage(view.getDie2());
                } else if (roll == 3){
                    view.getDiceFoto().setImage(view.getDie3());
                } else if (roll == 4){
                    view.getDiceFoto().setImage(view.getDie4());
                } else if (roll == 5){
                    view.getDiceFoto().setImage(view.getDie5());
                } else if (roll == 6){
                    view.getDiceFoto().setImage(view.getDie6());
                }
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

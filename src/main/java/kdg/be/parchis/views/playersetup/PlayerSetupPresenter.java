package kdg.be.parchis.views.playersetup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import kdg.be.parchis.model.menu.PlayerSetup;
import kdg.be.parchis.views.playerselect.PlayerSelectView;

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
// Koppelt event handlers (anon. inner klassen)
// aan de controls uit de view.
// Event handlers: roepen methodes aan uit het
// model en zorgen voor een update van de view.
        view.getBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getScene().setRoot(backView);
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

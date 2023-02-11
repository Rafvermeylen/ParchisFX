package kdg.be.parchis.views.playersetup;

import javafx.scene.layout.BorderPane;
import kdg.be.parchis.model.PlayerSetup;

public class PlayerSetupPresenter extends BorderPane {
    private PlayerSetup setup;
    private PlayerSetupView view;
    public PlayerSetupPresenter (
            PlayerSetup model,
            PlayerSetupView view) {
        this.setup = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {
// Koppelt event handlers (anon. inner klassen)
// aan de controls uit de view.
// Event handlers: roepen methodes aan uit het
// model en zorgen voor een update van de view.
    }
    private void updateView() {
// Vult de view met data uit model
    }
    public void addWindowEventHandlers () {
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }

}

package kdg.be.parchis.views;

import kdg.be.parchis.model.MusicLogic;
import kdg.be.parchis.model.ParchisModel;

public class ParchisPresenter {
    private ParchisModel model;
    private ParchisMenuView view;

    public ParchisPresenter(
            ParchisModel model,
            ParchisMenuView view) {
        this.model = model;
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
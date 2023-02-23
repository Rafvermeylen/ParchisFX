package kdg.be.parchis.views.credits;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import kdg.be.parchis.model.musicLogic.SoundClass;
import kdg.be.parchis.views.mainmenu.MainMenuView;

public class CreditsPresenter {
    private CreditsView view;
    private MainMenuView backView;


    public CreditsPresenter(CreditsView view, MainMenuView backView) {
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
                SoundClass.playClick();
                view.getScene().setRoot(backView);
            }
        });
    }

    private void updateView() {
// Vult de view met data uit model
    }

    public void addWindowEventHandlers() {
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }
}


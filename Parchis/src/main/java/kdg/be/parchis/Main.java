package kdg.be.parchis;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kdg.be.parchis.model.ApplicatieNaamModel;
import kdg.be.parchis.views.ApplicatieNaamPresenter;
import kdg.be.parchis.views.ApplicatieNaamView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        ApplicatieNaamModel model =
                new ApplicatieNaamModel();
        ApplicatieNaamView view =
                new ApplicatieNaamView();
        ApplicatieNaamPresenter presenter =
                new ApplicatieNaamPresenter(model, view);
        primaryStage.setScene(new Scene(view));
        presenter.addWindowEventHandlers();
        primaryStage.setTitle("Parchis");
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
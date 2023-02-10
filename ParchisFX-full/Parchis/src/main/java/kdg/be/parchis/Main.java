package kdg.be.parchis;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kdg.be.parchis.model.ParchisModel;
import kdg.be.parchis.views.ParchisPresenter;
import kdg.be.parchis.views.ParchisMenuView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        ParchisModel model =
                new ParchisModel();
        ParchisMenuView menu =
                new ParchisMenuView();
        ParchisPresenter presenter =
                new ParchisPresenter(model, menu);
        primaryStage.setScene(new Scene(menu));
        presenter.addWindowEventHandlers();
        primaryStage.setTitle("Parchis");
        //primaryStage.getIcons().add(new Image(new FileInputStream("Icon.png")));
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
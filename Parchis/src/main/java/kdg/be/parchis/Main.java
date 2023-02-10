package kdg.be.parchis;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kdg.be.parchis.model.ParchisModel;
import kdg.be.parchis.views.LeaderboardView;
import kdg.be.parchis.views.ParchisPresenter;
import kdg.be.parchis.views.MainMenuView;
import kdg.be.parchis.views.PlayerSelect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        ParchisModel model =
                new ParchisModel();
        MainMenuView menu =
                new MainMenuView();
        LeaderboardView lbView = new LeaderboardView();
        ParchisPresenter presenter =
                new ParchisPresenter(model, menu);
        PlayerSelect ps = new PlayerSelect();
        primaryStage.setScene(new Scene(menu));
        presenter.addWindowEventHandlers();
        primaryStage.setTitle("Parchis");
        primaryStage.getIcons().add(new Image(new FileInputStream("resources\\Icon.png")));
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
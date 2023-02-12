package kdg.be.parchis;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kdg.be.parchis.model.menu.Cheats;
import kdg.be.parchis.views.mainmenu.MainMenuPresenter;
import kdg.be.parchis.views.mainmenu.MainMenuView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Cheats cheats =
                new Cheats();
        MainMenuView menu =
                new MainMenuView();
        MainMenuPresenter presenter =
                new MainMenuPresenter(cheats, menu);
        primaryStage.setScene(new Scene(menu));
        presenter.addWindowEventHandlers();
        primaryStage.setTitle("Parchis");
        primaryStage.getIcons().add(new Image(new FileInputStream("resources\\graphics\\Icon.png")));
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
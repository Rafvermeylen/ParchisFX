package kdg.be.parchis.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.AudioClip;

import java.io.File;

public class SoundLogic {
    static String path = "src\\main\\java\\kdg\\be\\parchis\\resources\\";

    static AudioClip sound = new AudioClip(path + "click.mp3");

    public static void clickOnButton() {
        path = path + "click.mp3";
        Media media = new Media(new File(path).toURI().toString());
        // Just media doesn't work, x.getSource has to be added as well.
        sound = new AudioClip(media.getSource());
        sound.play();
    }
    public void fxMute() {

    }

    public void fxUnmute() {
    }

}

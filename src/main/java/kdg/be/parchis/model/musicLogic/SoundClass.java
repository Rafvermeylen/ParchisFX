package kdg.be.parchis.model.musicLogic;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

public class SoundClass {

    public static void playClick(){
        String path = "resources\\audio\\sfx\\click.mp3";
        Media media = new Media(new File(path).toURI().toString());
        // Just media doesn't work, x.getSource has to be added as well.
        AudioClip sound = new AudioClip(media.getSource());
        sound.play();
    }

    public static void playRoll(){
        String path = "resources\\audio\\sfx\\roll.wav";
        Media media = new Media(new File(path).toURI().toString());
        // Just media doesn't work, x.getSource has to be added as well.
        AudioClip sound = new AudioClip(media.getSource());
        sound.play();
    }

}

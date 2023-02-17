package kdg.be.parchis.model.musicLogic;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

public class SoundClass {
    private static String path = "resources\\audio\\sfx\\";
    private static boolean muted = false;

    private static void initSound(String path, String name) {
        if (!muted) {
            Media media = new Media(new File(path + name).toURI().toString());
            // Just media doesn't work, x.getSource has to be added as well.
            AudioClip sound = new AudioClip(media.getSource());
            sound.play();
        }
    }

    public static void playClick(){
        initSound(path, "click.mp3");
    }

    public static void playRoll(){
        initSound(path, "roll.wav");
    }

    public static void clickMute() {
        muted = !muted;
    }

    public static boolean isMuted() {
        return muted;
    }
}

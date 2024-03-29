package kdg.be.parchis.model.musicLogic;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

import static java.lang.Thread.sleep;

public class Sound {
    private static String path = "resources\\audio\\sfx\\";
    private static boolean muted = false;

    private static void initSound(String path, String name) {
        if (!muted) {
            Media media = new Media(new File(path + name).toURI().toString());
            // Just media doesn't work, x.getSource has to be added as well.
            AudioClip sound = new AudioClip(media.getSource());
            sound.setVolume(100);
            sound.play();
        }
    }

    public static void playClick() {
        initSound(path, "click.mp3");
    }

    public static void playPawnMove() {
        initSound(path, "pawn_move.mp3");
    }

    public static void playKill() {
        initSound(path, "kill.mp3");
    }

    public static void playFail() {
        initSound(path, "fail.mp3");
    }

    public static void barrierMade() {
        initSound(path, "Barrier.mp3");
    }

    public static void playRoll() {
        initSound(path, "roll.wav");
        try {
            sleep(2 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playVictory() {
        initSound(path, "win.mp3");
    }

    public static void clickMute() {
        muted = !muted;
    }

    public static boolean isMuted() {
        return muted;
    }
}

/*
This class does everything with sounds. It uses statics because it's much easier to call it that way.
*/

package kdg.be.parchis.model.musicLogic;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

import static java.lang.Thread.sleep;

public class Sound {
    private static final String PATH = "resources\\audio\\sfx\\";
    private static boolean muted = false;

    private static void initSound(String name) {
        if (!muted) {
            Media media = new Media(new File(PATH + name).toURI().toString());
            // Just media doesn't work, x.getSource has to be added as well.
            AudioClip sound = new AudioClip(media.getSource());
            sound.setVolume(100);
            sound.play();
        }
    }

    public static void playClick() {
        initSound("click.mp3");
    }

    public static void playPawnMove() {
        initSound("pawn_move.mp3");
    }

    public static void playKill() {
        initSound("kill.mp3");
    }

    public static void playFail() {
        initSound("fail.mp3");
    }

    public static void barrierMade() {
        initSound("Barrier.mp3");
    }

    public static void playRoll() {
        initSound("roll.wav");
        try {
            sleep(2 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playVictory() {
        initSound("win.mp3");
    }

    public static void clickMute() {
        muted = !muted;
    }

    public static boolean isMuted() {
        return muted;
    }
}

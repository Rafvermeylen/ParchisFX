package kdg.be.parchis.model.musiclogic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Music {
    /**
     * This class does everything with music. It uses statics because it's much easier to call it that way.
     * Authors: Rui Daniel Gomes Vieira & Raf Vermeylen
     */
    private static MediaPlayer mediaPlayer;
    private static List<Media> songs = new ArrayList<>();
    private final static String PATH = "resources\\audio\\music\\";

    public static void playMenuMusic() {
        Media menu_music = new Media(new File(PATH + "menu_music.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(menu_music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    private static void addMusic(String name) {
        songs.add(new Media(new File(PATH + name).toURI().toString()));
    }

    public static void playGameMusic() {
        addMusic("Bag.mp3");
        addMusic("Bluebird.mp3");
        addMusic("Bluesy.mp3");
        addMusic("Bossa.mp3");
        addMusic("Bumper.mp3");
        addMusic("Called.mp3");
        addMusic("Chances.mp3");
        addMusic("Daydream.mp3");
        addMusic("Forget.mp3");
        addMusic("Groove.mp3");
        addMusic("Jazz.mp3");
        addMusic("Lounge.mp3");
        addMusic("Love.mp3");
        addMusic("Messenger.mp3");
        addMusic("Moon.mp3");
        addMusic("Moonlight.mp3");
        addMusic("Nighttime.mp3");
        addMusic("Old.mp3");
        addMusic("Silent.mp3");
        addMusic("Soul.mp3");
        addMusic("Together.mp3");
        addMusic("Watercolours.mp3");
        addMusic("You.mp3");

        Collections.shuffle(songs);
        // Create a MediaPlayer with the first song
        mediaPlayer = new MediaPlayer(songs.get(0));
        mediaPlayer.setVolume(40);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.dispose();
            playGameMusic();
        });
    }

    public static void stopMusic() {
        mediaPlayer.stop();
    }

    public static void muteMenuMusic() {
        mediaPlayer.setMute(!mediaPlayer.isMute());
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
package kdg.be.parchis.model.musicLogic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Music {
    private static Media menu_music;
    private static MediaPlayer mediaPlayer;
    private static List<Media> songs = new ArrayList<>();
    private static String path = "resources\\audio\\music\\";

    public static void playMenuMusic() {
        menu_music = new Media(new File(path + "menu_music.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(menu_music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    private static void addMusic(String filepath, String name) {
        songs.add(new Media(new File(filepath + name).toURI().toString()));
    }

    public static void playGameMusic() {
        addMusic(path, "Bag.mp3");
        addMusic(path, "Bluebird.mp3");
        addMusic(path, "Bluesy.mp3");
        addMusic(path, "Bossa.mp3");
        addMusic(path, "Bumper.mp3");
        addMusic(path, "Called.mp3");
        addMusic(path, "Chances.mp3");
        addMusic(path, "Daydream.mp3");
        addMusic(path, "Forget.mp3");
        addMusic(path, "Groove.mp3");
        addMusic(path, "Jazz.mp3");
        addMusic(path, "Lounge.mp3");
        addMusic(path, "Love.mp3");
        addMusic(path, "Messenger.mp3");
        addMusic(path, "Moon.mp3");
        addMusic(path, "Moonlight.mp3");
        addMusic(path, "Nighttime.mp3");
        addMusic(path, "Old.mp3");
        addMusic(path, "Silent.mp3");
        addMusic(path, "Soul.mp3");
        addMusic(path, "Together.mp3");
        addMusic(path, "Watercolours.mp3");
        addMusic(path, "You.mp3");

        Collections.shuffle(songs);
        // Create a MediaPlayer with the first song
        mediaPlayer = new MediaPlayer(songs.get(0));
        mediaPlayer.setVolume(50);
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

package kdg.be.parchis.model.musicLogic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainMusic {
    private static Media menu_music;
    private static int currentIndex;
    private static MediaPlayer mediaPlayer;
    private static List<Media> songs = new ArrayList<>();

    /*
    new Media(new File("resources\\audio\\music\\Legend_of_Skullz.mp3").toURI().toString()),
            new Media(new File("resources\\audio\\music\\Jazz_Soul.mp3").toURI().toString()),
            new Media(new File("resources\\audio\\music\\Hairy_Monkey_Balls.mp3").toURI().toString()),
            new Media(new File("resources\\audio\\music\\Guilty.mp3").toURI().toString()),
            new Media(new File("resources\\audio\\music\\J.J..mp3").toURI().toString()),
            new Media(new File("resources\\audio\\music\\Jazz_Crossing.mp3").toURI().toString()),
            new Media(new File("resources\\audio\\music\\Jazz_In_Paris.mp3").toURI().toString()),
            new Media(new File("resources\\audio\\music\\Minor.mp3").toURI().toString()),
            new Media(new File("resources\\audio\\music\\Noire_Clarinet.mp3").toURI().toString()),
            new Media(new File("resources\\audio\\music\\Pleasure.mp3").toURI().toString()),
            new Media(new File("resources\\audio\\music\\Temptation.mp3").toURI().toString()),

     */
    public static void playMenuMusic() {
        menu_music = new Media(new File("resources\\audio\\music\\menu_music.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(menu_music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static void playGameMusic() {
        // https://techwithmaddy.com/java-8-lambda-expression
        // https://techwithmaddy.com/java-method-reference

        songs.add(new Media(new File("resources\\audio\\music\\Jazz_Soul.mp3").toURI().toString()));
        songs.add(new Media(new File("resources\\audio\\music\\Hairy_Monkey_Balls.mp3").toURI().toString()));
        songs.add(new Media(new File("resources\\audio\\music\\Guilty.mp3").toURI().toString()));
        songs.add(new Media(new File("resources\\audio\\music\\Legend_of_Skullz.mp3").toURI().toString()));
        songs.add(new Media(new File("resources\\audio\\music\\J.J..mp3").toURI().toString()));
        songs.add(new Media(new File("resources\\audio\\music\\Jazz_Crossing.mp3").toURI().toString()));
        songs.add(new Media(new File("resources\\audio\\music\\Jazz_In_Paris.mp3").toURI().toString()));
        songs.add(new Media(new File("resources\\audio\\music\\Minor.mp3").toURI().toString()));
        songs.add(new Media(new File("resources\\audio\\music\\Noire_Clarinet.mp3").toURI().toString()));
        songs.add(new Media(new File("resources\\audio\\music\\Pleasure.mp3").toURI().toString()));
        songs.add(new Media(new File("resources\\audio\\music\\Temptation.mp3").toURI().toString()));

        Collections.shuffle(songs);

        // Create a MediaPlayer with the first song
        mediaPlayer = new MediaPlayer(songs.get(0));
        mediaPlayer.play();

        // Set up the onEndOfMedia event to play the next song when the current one finishes
        mediaPlayer.setOnEndOfMedia(() -> {
            // Get the index of the current song
            currentIndex = songs.indexOf(mediaPlayer.getMedia());

            // If there's another song in the list, play it
            if (currentIndex < songs.size() - 1) {
                mediaPlayer.dispose();
                mediaPlayer = new MediaPlayer(songs.get(currentIndex + 1));
                mediaPlayer.play();
            }
            // Otherwise, loop back to the beginning and play the first song
            else {
                mediaPlayer.dispose();
                mediaPlayer = new MediaPlayer(songs.get(0));
                mediaPlayer.play();
            }
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

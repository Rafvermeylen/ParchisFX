package kdg.be.parchis.model.musicLogic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainMusic {
    private static Media menu_music;

    private static int currentSongIndex = 0;

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

        mediaPlayer = new MediaPlayer(songs.get(currentSongIndex));

        // A lambda expression is a function that doesn’t belong to any class.
        // For my understanding, this will happen if the song has ended. It will keep looping until list is completed.
        // If list is completed, currentSongIndex will be set back to 0, restarting the process.
        do {
            mediaPlayer.setOnEndOfMedia(() -> {
                // Increment the index to play the next song
                currentSongIndex++;

                // Create a new MediaPlayer for the next song and play it
                mediaPlayer.dispose();
                mediaPlayer = new MediaPlayer(songs.get(currentSongIndex));

                // :: operator refers to a method reference ==> simplified way
                // of writing a lambda expression in order to call a method.
                //mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);
                mediaPlayer.play();
            });
        } while (currentSongIndex >= songs.size());

        if (currentSongIndex >= songs.size()) {
            // If the index goes above 0, set it back to 0 to loop back to the beginning
            currentSongIndex = 0;
        }
/*
        mediaPlayer.setOnStopped(() -> {
            mediaPlayer.dispose();
        });
 */
        mediaPlayer.play();
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

package kdg.be.parchis.model.musicLogic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Random;

public class MainMusic {
    private static Media menu_music;

    private static MediaPlayer mediaPlayer;

    private static Media[] songs = new Media[]{

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
    };

    public static void playMenuMusic() {
        menu_music = new Media(new File("resources\\audio\\music\\menu_music.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(menu_music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static void playGameMusic() {
        Random rn = new Random();
        int choice = rn.nextInt(0, songs.length);
        mediaPlayer = new MediaPlayer(songs[choice]);
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        /*
        do {
            mediaPlayer = new MediaPlayer(songs[choice]);
            mediaPlayer.play();
        } while (songs.length != 0);

        if (mediaPlayer.getOnEndOfMedia() == true) {

        }
        */
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

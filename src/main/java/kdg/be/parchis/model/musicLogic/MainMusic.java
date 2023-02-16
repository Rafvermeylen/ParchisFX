package kdg.be.parchis.model.musicLogic;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Random;

public class MainMusic {
    private static Media menu_music;
    private static MediaPlayer mediaPlayer;
    private static Media[] songs = new Media[]{
            new Media(new File("resources\\audio\\Legend_of_Skullz.mp3").toURI().toString()),
            new Media(new File("resources\\audio\\Jazz_Soul.mp3").toURI().toString()),
            new Media(new File("resources\\audio\\Hairy_Monkey_Balls.mp3").toURI().toString()),
    };
    public static void playMenuMusic(){
        menu_music = new Media(new File("resources\\audio\\menu_music.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(menu_music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static void playGameMusic(){
        Random rn = new Random();
        int choice = rn.nextInt(0, songs.length);
        mediaPlayer = new MediaPlayer(songs[choice]);
        mediaPlayer.play();

    }

    public static void stopMusic(){
        mediaPlayer.stop();
    }
    public static void muteMenuMusic(){
        mediaPlayer.setMute(!mediaPlayer.isMute());
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}

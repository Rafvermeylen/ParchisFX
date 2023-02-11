package kdg.be.parchis.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Random;

public class MusicLogic {
    String path = "src\\main\\java\\kdg\\be\\parchis\\resources\\";
    String[] musicArray = {};
    Random r = new Random();
    int randomMusic;


    public MusicLogic() {

    }

    MediaPlayer music;
    MediaPlayer soundFx;

    public void mainMenuUp() throws InterruptedException {
        path = path + "menu_music.mp3";
        Media media = new Media(new File(path).toURI().toString());
        music = new MediaPlayer(media);
        music.setVolume(0);
        music.setCycleCount(MediaPlayer.INDEFINITE);
        music.play();
        while (music.getVolume() < 100) {
            music.setVolume(music.getVolume() + 5);
            //This part needs InterruptedException, otherwise, it will not accept.
            Thread.sleep(200);
        }
    }

    public void mainMenuDown() throws InterruptedException {
        while (music.getVolume() > 0) {
            music.setVolume(music.getVolume() - 5);
            Thread.sleep(200);
        }
    }

    public void musicMute() {
        music.setVolume(0);
    }

    public void musicUnmute() {
        music.setVolume(100);
    }

    public void fxMute() {
        soundFx.setVolume(0);
    }

    public void fxUnmute() {
        soundFx.setVolume(100);
    }
    public void gameMusic() {
        while (musicArray != null) {
            r.nextInt(musicArray.length);
            music.play();
        }
    }
}

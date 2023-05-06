package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class SoundManager {

    private Clip clip;
    private final URL[] soundUrl = new URL[30];
    private FloatControl floatControl;
    private int volumeScale = 3;
    private float volume;

    public SoundManager() {
        try {
            soundUrl[0] = new File("././res/sounds/BGM.wav").toURI().toURL();
            soundUrl[1] = new File("././res/sounds/powerup.wav").toURI().toURL();
            soundUrl[2] = new File("././res/sounds/unlock.wav").toURI().toURL();
            soundUrl[3] = new File("././res/sounds/fanfare.wav").toURI().toURL();
            soundUrl[4] = new File("././res/sounds/hitmonster.wav").toURI().toURL();
            soundUrl[5] = new File("././res/sounds/receivedamage.wav").toURI().toURL();
            soundUrl[6] = new File("././res/sounds/cuttree.wav").toURI().toURL();
            soundUrl[7] = new File("././res/sounds/levelup.wav").toURI().toURL();
            soundUrl[8] = new File("././res/sounds/cursor.wav").toURI().toURL();
            soundUrl[9] = new File("././res/sounds/burning.wav").toURI().toURL();
            soundUrl[10] = new File("././res/sounds/gameover.wav").toURI().toURL();
            soundUrl[11] = new File("././res/sounds/stairs.wav").toURI().toURL();
            soundUrl[12] = new File("././res/sounds/coin.wav").toURI().toURL();
            soundUrl[13] = new File("././res/sounds/meow.wav").toURI().toURL();
            soundUrl[14] = new File("././res/sounds/BingChilling.wav").toURI().toURL();
            soundUrl[15] = new File("././res/sounds/jangandong.wav").toURI().toURL();
            soundUrl[16] = new File("././res/sounds/carEngine.wav").toURI().toURL();
            soundUrl[17] = new File("././res/sounds/carIdle.wav").toURI().toURL();
            soundUrl[18] = new File("././res/sounds/upgrade.wav").toURI().toURL();
            soundUrl[19] = new File("././res/sounds/denied.wav").toURI().toURL();
            soundUrl[20] = new File("././res/sounds/dead.wav").toURI().toURL();
            soundUrl[21] = new File("././res/sounds/gameover2.wav").toURI().toURL();
            soundUrl[22] = new File("././res/sounds/pickup.wav").toURI().toURL();
            soundUrl[23] = new File("././res/sounds/doorbell.wav").toURI().toURL();
            soundUrl[24] = new File("././res/sounds/foodbell.wav").toURI().toURL();
            soundUrl[25] = new File("././res/sounds/carclose.wav").toURI().toURL();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setFile(int index) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl[index]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void checkVolume() {
        switch (volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }

        floatControl.setValue(volume);
    }

    public int getVolumeScale() {
        return volumeScale;
    }

    public SoundManager setVolumeScale(int volumeScale) {
        this.volumeScale = volumeScale;
        return this;
    }
}

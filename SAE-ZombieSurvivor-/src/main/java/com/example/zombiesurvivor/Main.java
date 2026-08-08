package com.example.zombiesurvivor;

import com.example.zombiesurvivor.modele.Environnement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Objects;


public class Main extends Application {
    private ParallelCamera camera;
    @Override
    public void start(Stage primaryStage)
    {
        playSound("src/main/resources/com/example/zombiesurvivor/musics/let-the-games-begin-21858.wav", true);
        try {
            BorderPane root = FXMLLoader.load(Main.class.getResource("vue1.fxml"));
            Scene scene = new Scene(root,1600,640);
            primaryStage.setScene(scene);
            primaryStage.show();
            root.requestFocus();
//            AudioClip clip = new AudioClip(Main.class.getResource("images/iamePersoDroite.png").toString());
//            clip.play();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Clip playSound(String path, boolean loop) {
        Clip clip = null;

        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip.open(inputStream);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        if (!Objects.isNull(clip)) {
            clip.loop(loop ? -1 : 0);
            clip.start();
        }

        return clip;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

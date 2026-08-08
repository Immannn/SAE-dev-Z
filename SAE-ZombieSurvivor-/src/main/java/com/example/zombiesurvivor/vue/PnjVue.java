package com.example.zombiesurvivor.vue;

import com.example.zombiesurvivor.modele.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PnjVue {

    private static Pane paneRacine;
    private static ImageView vacheV;
    private Environnement env;
    private ImageView enemieV;
    private ImageView enemieV2;


    private ImageView enemieVV;

    Image vache = new Image("file:src/main/resources/com/example/zombiesurvivor/images/vache.png");
    Image ennemie = new Image("file:src/main/resources/com/example/zombiesurvivor/images/vageta(1).png");
    Image ennemie2 = new Image("file:src/main/resources/com/example/zombiesurvivor/images/vegetaS.png");


    public PnjVue(Pane paneRacine , Environnement env) {
        this.paneRacine = paneRacine;
        this.env = env;
    }

    public void affichePnj(ImageView ImageV , Image Image,Acteur a) {

        ImageV = new ImageView(Image);
        ImageV.translateXProperty().bind(a.xProperty());
        ImageV.translateYProperty().bind(a.yProperty());
        ImageV.scaleXProperty().bind(a.orientationProperty());
        ImageV.setId(a.getId());
        paneRacine.getChildren().add(ImageV);

    }

    public void verifActeur(Acteur a){
        if (a instanceof  Vache){
            affichePnj(vacheV,vache,a);
        }
        else if(a instanceof EnemieNv2){
            affichePnj(enemieV,ennemie,a);
        }
        else if(a instanceof EnemieNv1) {
            affichePnj(enemieV2,ennemie2,a);
        }
    }


}




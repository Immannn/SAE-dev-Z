package com.example.zombiesurvivor.vue;

import com.example.zombiesurvivor.Main;
import com.example.zombiesurvivor.modele.Environnement;
import com.example.zombiesurvivor.modele.Joueur;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class JoueurVue {

    private static Pane paneRacine;
    private final Environnement env;
    private Joueur joueur;
    private static ImageView backD;
    private static ImageView backDr;
    private static ImageView FireBall;

    private int recu = 0;
    private Label labelVie = new Label("5");
    private Label labelRound = new Label("ROUND FINAL");
    private Label labelRound1 = new Label("ROUND 1");
    private Label labelMort = new Label(" VOUS ETES MORT");
    private Label labelGagnant = new Label(" VOUS AVEZ GAGNÉ");
    private int i = 1;


    public  JoueurVue(Pane paneRacine, Joueur joueur, Environnement env) {
        this.paneRacine = paneRacine;
        this.joueur = joueur;
        this.env = env;
        this.afficherJoueur();
    }


    public void afficherJoueur() {


        Image joueurDroite = new Image(Main.class.getResource("images/iamePersoDroite.png").toString());
        Image joueurDroitecourt = new Image(Main.class.getResource("images/doitecourt.png").toString());
        Image fireball = new Image(Main.class.getResource("images/fireball1.png").toString());


        backDr = new ImageView(joueurDroitecourt);
        backDr.xProperty().bind(joueur.xProperty());
        backDr.yProperty().bind(joueur.yProperty());
        backDr.resize(2,2);
        backDr.scaleXProperty().bind(joueur.orientationProperty());
        paneRacine.getChildren().add(backDr);


        backDr.setVisible(false);


        backD = new ImageView(joueurDroite);
        backD.xProperty().bind(joueur.xProperty());
        backD.yProperty().bind(joueur.yProperty());
        backD.scaleXProperty().bind(joueur.orientationProperty());
        paneRacine.getChildren().add(backD);

        FireBall = new ImageView(fireball);
        FireBall.xProperty().bind(joueur.getInventaire().getLance().posxProperty());
        FireBall.yProperty().bind(joueur.getInventaire().getLance().posyProperty());
        paneRacine.getChildren().add(FireBall);

        FireBall.setVisible(false);


        labelVie.setTranslateX(50);
        labelVie.setTranslateY(20);
        labelVie.setTextFill(Color.GREENYELLOW);
        paneRacine.getChildren().add(labelVie);

        labelRound.setTranslateX(700);
        labelRound.setTranslateY(250);
        labelRound.setTextFill(Color.WHITE);
        labelRound.setStyle("-fx-font: 30 arial;");
        paneRacine.getChildren().add(labelRound);
        labelRound.setVisible(false);

        labelRound1.setTranslateX(700);
        labelRound1.setTranslateY(250);
        labelRound1.setTextFill(Color.WHITE);
        labelRound1.setStyle("-fx-font: 30 arial;");
        paneRacine.getChildren().add(labelRound1);
        labelRound1.setVisible(false);


        labelMort.setTranslateX(650);
        labelMort.setTranslateY(250);
        labelMort.setTextFill(Color.RED);
        labelMort.setStyle("-fx-font: 30 arial;");
        paneRacine.getChildren().add(labelMort);
        labelMort.setVisible(false);

        labelGagnant.setTranslateX(620);
        labelGagnant.setTranslateY(250);
        labelGagnant.setTextFill(Color.BLUE);
        labelGagnant.setStyle("-fx-font: 30 arial;");
        paneRacine.getChildren().add(labelGagnant);
        labelGagnant.setVisible(false);
    }



    public static void apparanceDroitecourt(boolean upVer) {
        if(upVer) {
            backD.setVisible(false);
            backDr.setVisible(true);
        }
        else {
            backDr.setVisible(false);
            backD.setVisible(true);
        }
    }

    public static void apparanceGauchecourt(boolean upVer) {

        if(upVer) {
            backD.setVisible(false);
            backDr.setVisible(true);
        }
        else {
            backDr.setVisible(false);

            backD.setVisible(true);
        }
    }

    public  void imageMort() {

        paneRacine.getChildren().remove(backD);
        paneRacine.getChildren().remove(backDr);

    }

    public void affichageVie(){
        labelVie.textProperty().bind(env.getPersonage().vieProperty().asString());
    }
    public void roundfalse () {
        labelRound.setVisible(false);
    }

    public void round1False () {
        labelRound1.setVisible(false);
    }

    public void roundtrue () {
        labelRound.setVisible(true);
    }

    public void round1True () {
        labelRound1.setVisible(true);
    }

    public void joueurMort() {
        labelMort.setVisible(true);
    }

    public void joueurGagne() {
        labelGagnant.setVisible(true);
    }

    public static void bouleDeFeuVisible() {
        FireBall.setVisible(true);
    }

    public static void bouleDeFeuNotVisible() {
        FireBall.setVisible(false);
    }


    public boolean systemeRound(int tic, Integer i, Timeline gameLoop, int miniteur,InventaireVue vue) {

        if (tic == recu + 80) {
            roundfalse();
        }
        if (tic == recu + 80) {
            round1False();
        }

        if(i == 0 && miniteur>=60){
            if (env.getListperso().isEmpty()) {
                i++;
             //   env.ajouterEnnemieNv1();
            //    env.ajouterEnnemieNv1();
              //  env.ajouterEnnemieNv1();
              //  env.ajouterEnnemieNv1();
                round1True();
                recu = tic;
                System.out.println(i);
                return true;
            }
        }
        if (i == 1) {
            if (env.getListperso().isEmpty()) {
                i++;
                roundtrue();
                recu = tic;
                env.ajouterEnnemie();
                env.ajouterEnnemie();
                return true;
            }
        }
        if (i == 2) {
            if (env.getListperso().isEmpty()) {
                Main.playSound("src/main/resources/com/example/zombiesurvivor/musics/sons.wav", true);

                joueurGagne();
                env.jeuTermine(gameLoop);
            }
        }
        return false;
    }


}

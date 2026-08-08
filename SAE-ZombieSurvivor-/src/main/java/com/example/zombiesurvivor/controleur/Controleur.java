package com.example.zombiesurvivor.controleur;

import com.example.zombiesurvivor.Main;
import com.example.zombiesurvivor.modele.Environnement;
//import com.example.zombiesurvivor.modele.Filtre;
import com.example.zombiesurvivor.modele.Terrain;
import com.example.zombiesurvivor.vue.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class Controleur implements Initializable {
    private Terrain terrain;
    private Environnement env;
    private Timeline gameLoop;
    private int tic = 0;
    private int temps;
    private JoueurVue joueurVue;
    private InterfaceCraftVue interfaceCraftVue;
    private InventaireVue inventaireVue;
    private PnjVue pnjVue;
    private Integer i;
    private int tuilemain ;
    private int minuteur;


    @FXML
    private BorderPane cadreJeu;
    @FXML
    private Pane paneRacine;
    @FXML
    private TilePane paneTerrain;
    @FXML
    private Pane interfaceCraft;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        i = 0;

        this.terrain = new Terrain();
        env = new Environnement(terrain);


        TerrainVue  terrainVue = new TerrainVue(env.getTerrain(), paneTerrain,paneRacine);
        interfaceCraftVue = new InterfaceCraftVue(interfaceCraft);
        joueurVue = new JoueurVue(paneRacine,env.getPersonage(),env);
        inventaireVue = new InventaireVue(paneRacine,env, cadreJeu);
        pnjVue = new PnjVue(paneRacine,env);

        //---Listener---
        this.terrain.getCodesTuiles().addListener(new ControleurListeTuiles(terrainVue));
        env.getListeVache().addListener(new MonObservateurVaches(paneRacine,pnjVue));
        env.getListperso().addListener(new MonObservateurEnnemies(paneRacine,pnjVue));

      //  env.ajouterVache();
      //  env.ajouterVache();
      //  env.ajouterVache();
      //  env.ajouterVache();

        initAnimation();

        gameLoop.play();


        // < Key Event--------------------------------------
        cadreJeu.addEventFilter(KeyEvent.KEY_PRESSED, (key)->
        {
            if(key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
                this.env.getPersonage().droite();
                env.getPersonage().setOrientation(1);
            }
            if(key.getCode() == KeyCode.Q || key.getCode() == KeyCode.LEFT) {
                this.env.getPersonage().gauche();
                env.getPersonage().setOrientation(-1);
            }
            if(key.getCode() == KeyCode.Z || key.getCode() == KeyCode.UP) {
                this.env.getPersonage().haut();
            }
            if(key.getCode() == KeyCode.C) {
                this.env.getPersonage().mange();
                Main.playSound("src/main/resources/com/example/zombiesurvivor/musics/FOODEat_Bruits de bouche 3 (ID 0354)_LS.wav", false);
            }
            if(key.getCode() == KeyCode.K) {
                tuilemain=117;
                inventaireVue.curseurBois();
            }
            if(key.getCode() == KeyCode.L) {
                tuilemain=18;
                inventaireVue.curseurPierre();
            }
            if(key.getCode() == KeyCode.P) {
               env.getPersonage().getInventaire().piocheDansLaMain();
               inventaireVue.curseurPioche();
            }
            if(key.getCode() == KeyCode.H) {
                env.getPersonage().getInventaire().hacheDansLaMain();
                inventaireVue.curseurHache();
                Main.playSound("src/main/resources/com/example/zombiesurvivor/musics/WEAPSwrd_Epee (ID 0129)_LS.wav", false);
            }
            if(key.getCode() == KeyCode.E){
                env.getPersonage().getInventaire().epeeDansLaMain();
                if (env.getPersonage().getInventaire().isaLeppe() && env.getPersonage().getInventaire().isEpeeActive()) {
                   inventaireVue.curseurEpee();
                    Main.playSound("src/main/resources/com/example/zombiesurvivor/musics/WEAPSwrd_Epee (ID 0129)_LS.wav", false);
                }
            }
            if(key.getCode() == KeyCode.CONTROL) {
                this.env.getPersonage().attaqueOui();
            }
            if(key.getCode() == KeyCode.F) {
                this.env.getPersonage().getInventaire().bouleDeFeuDansLaMain();
                if (env.getPersonage().getInventaire().isBouleDeFeu() && env.getPersonage().getInventaire().isaFeu()) {
                    inventaireVue.curseurFeu();
                }

            }

        });


        cadreJeu.addEventFilter(KeyEvent.KEY_RELEASED, (key)->
        {
            if(key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
                this.env.getPersonage().neVaPLusADroite();
            }
            if(key.getCode() == KeyCode.Q || key.getCode() == KeyCode.LEFT) {
                this.env.getPersonage().neVaPLusAGauche();
            }
            if(key.getCode() == KeyCode.Z || key.getCode() == KeyCode.UP) {
                this.env.getPersonage().neVaPLusEnHaut();
                Main.playSound("src/main/resources/com/example/zombiesurvivor/musics/FEETHmn_Saut sur beton 2 (ID 1836)_LS.wav", false);
            }
            if(key.getCode() == KeyCode.CONTROL) {
                this.env.getPersonage().attaquePas();
            }
            if(key.getCode() == KeyCode.C) {
                this.env.getPersonage().mangePas();
            }
            if (key.getCode() == KeyCode.I) {
                interfaceCraftVue.afficherInterfaceCraft();
            }
            // -------------------------------------- Key Event>

        });

        paneRacine.setOnMousePressed(mouseEvent -> {
            if(mouseEvent.getButton()== MouseButton.PRIMARY) {
                this.env.getPersonage().attaqueOui();
            }
        });

        paneRacine.setOnMouseReleased(mouseEvent -> {
            this.env.getPersonage().attaquePas();
        });




        //<Mouse Event --------------------------------------

        cadreJeu.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event arg0) {
                Image image = new Image(Main.class.getResource("images/cursor.png").toString());
                cadreJeu.getScene().setCursor(new ImageCursor(image));
            }
        });



        paneTerrain.setOnMouseMoved(mouseEvent -> {

            int xCursor = terrainVue.rec1X((int)mouseEvent.getX());
            int yCursor = terrainVue.rec1Y((int)mouseEvent.getY());

            terrainVue.getRectangleContour().setX(xCursor);
            terrainVue.getRectangleContour().setY(yCursor);
        });


        paneRacine.setOnMousePressed(mouseEvent -> {

           int indiceTuile = terrain.returnIndiceTuile(mouseEvent);

            if (env.verifPorté(mouseEvent,env.getPersonage())){
                    env.ajouteTuileDansInventaire(indiceTuile);
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    env.creuser(indiceTuile);
                }
                else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    env.placer(indiceTuile,tuilemain);

                }
                env.enleveTuilePlaceDeInventaire(indiceTuile);
            }
            else{
                System.out.println("pas de changement possible");
            }
        });
    }


    @FXML
    void craftBotte(ActionEvent event) {
        env.getPersonage().craftBotte(inventaireVue);
    }
    @FXML
    void craftEpee(ActionEvent event) {
        env.getPersonage().craftEpee(inventaireVue);
    }
    @FXML
    void craftBouleDeFeu(ActionEvent event) {
        env.getPersonage().craftLancepierre(inventaireVue);
    }
    void initAnimation() {

        this.gameLoop = new Timeline();
        this.temps=0;
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame( Duration.seconds(0.013),(ev -> {


            if(!interfaceCraftVue.isOn()) {
                tic++;
                minuteur = tic / 60;
                if (joueurVue.systemeRound(tic, i, gameLoop, minuteur, inventaireVue)) {
                    i++;
                }
                    if (!env.getListperso().isEmpty()) {
                        if (env.getPersonage().estMort()) {
                            joueurVue.joueurMort();
                            joueurVue.imageMort();
                        }
                    }
                if (!env.getPersonage().estMort()) {
                    inventaireVue.majInventaire(env);
                    joueurVue.affichageVie();
                    this.env.getPersonage().agir();
                    this.env.vacheDeLaListeAgit();
                    this.env.ennemieDeLaListeAgit();
                }
            }

        }));
        this.gameLoop.getKeyFrames().add(kf);
    }

}


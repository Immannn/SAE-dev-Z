package com.example.zombiesurvivor.vue;

import com.example.zombiesurvivor.Main;
import com.example.zombiesurvivor.modele.Environnement;
import javafx.scene.ImageCursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class InventaireVue {


    private static Pane paneRacine;
    private static ImageView inventaireV;
    private static ImageView boisV;
    private static ImageView pierreV;
    private static ImageView viandeV;
    private static ImageView hacheV;
    private static ImageView piocheV;
    private static ImageView epeeV;
    private static ImageView botteV;
    private static ImageView bouleDeFeuV;
    private Environnement env;
    private static Label labelpierre = new Label("0");
    private static Label labelbois = new Label("0");
    private static Label labelviande = new Label("0");

    private BorderPane border;


    public InventaireVue(Pane paneRacine, Environnement env, BorderPane border) {
        this.paneRacine = paneRacine;
        this.afficherInventaire();
        this.env = env;
        this.border = border;
    }

    private void afficherInventaire() {

        Image inventaire = new Image(Main.class.getResource("images/inventaire.png").toString());
        Image bois = new Image(Main.class.getResource("images/bois.png").toString());
        Image pierre = new Image(Main.class.getResource("images/pierre.png").toString());
        Image vache = new Image(Main.class.getResource("images/food.png").toString());
        Image hache = new Image(Main.class.getResource("images/Hache.png").toString());
        Image pioche = new Image(Main.class.getResource("images/Pioche.png").toString());
        Image epee = new Image(Main.class.getResource("images/epeeInv.png").toString());
        Image botte = new Image(Main.class.getResource("images/bottesInv.png").toString());
        Image bouleDeFeu = new Image(Main.class.getResource("images/fireball1.png").toString());


        inventaireV = new ImageView(inventaire);
        boisV = new ImageView(bois);
        pierreV = new ImageView(pierre);
        viandeV = new ImageView(vache);
        hacheV = new ImageView(hache);
        piocheV = new ImageView(pioche);
        epeeV = new ImageView(epee);
        botteV = new ImageView(botte);
        bouleDeFeuV = new ImageView(bouleDeFeu);

        inventaireV.setTranslateX(630);
        inventaireV.setTranslateY(600);
        paneRacine.getChildren().add(inventaireV);

        boisV.setTranslateX(632);
        boisV.setTranslateY(603);
        paneRacine.getChildren().add(boisV);

        pierreV.setTranslateX(668);
        pierreV.setTranslateY(603);
        paneRacine.getChildren().add(pierreV);

        viandeV.setTranslateX(704);
        viandeV.setTranslateY(603);
        paneRacine.getChildren().add(viandeV);

        hacheV.setTranslateX(741);
        hacheV.setTranslateY(600);
        paneRacine.getChildren().add(hacheV);

        piocheV.setTranslateX(778);
        piocheV.setTranslateY(603);
        paneRacine.getChildren().add(piocheV);

        epeeV.setTranslateX(813);
        epeeV.setTranslateY(600);
        paneRacine.getChildren().add(epeeV);

        epeeV.setVisible(false);

        botteV.setTranslateX(849);
        botteV.setTranslateY(603);
        paneRacine.getChildren().add(botteV);

        botteV.setVisible(false);

        bouleDeFeuV.setTranslateX(885);
        bouleDeFeuV.setTranslateY(600);
        paneRacine.getChildren().add(bouleDeFeuV);

        bouleDeFeuV.setVisible(false);


        labelpierre.setTranslateX(680);
        labelpierre.setTranslateY(588);
        labelpierre.setTextFill(Color.WHITE);
        paneRacine.getChildren().add(labelpierre);

        labelbois.setTranslateX(645);
        labelbois.setTranslateY(588);
        labelbois.setTextFill(Color.WHITE);
        paneRacine.getChildren().add(labelbois);

        labelviande.setTranslateX(717);
        labelviande.setTranslateY(588);
        labelviande.setTextFill(Color.WHITE);
        paneRacine.getChildren().add(labelviande);

    }

    public void curseurEpee() {

        Image image2 = new Image(Main.class.getResource("images/epeeInv.png").toString());
        border.getScene().setCursor(new ImageCursor(image2));
    }

    public void curseurFeu() {

        Image image2 = new Image(Main.class.getResource("images/fireball1.png").toString());
        border.getScene().setCursor(new ImageCursor(image2));
    }

    public void curseurPioche() {

        Image image2 = new Image(Main.class.getResource("images/Pioche.png").toString());
        border.getScene().setCursor(new ImageCursor(image2));
    }

    public void curseurHache() {

        Image image2 = new Image(Main.class.getResource("images/Hache.png").toString());
        border.getScene().setCursor(new ImageCursor(image2));
    }

    public void curseurBois() {

        Image image2 = new Image(Main.class.getResource("images/bois.png").toString());
        border.getScene().setCursor(new ImageCursor(image2));
    }

    public void curseurPierre() {

        Image image2 = new Image(Main.class.getResource("images/pierre.png").toString());
        border.getScene().setCursor(new ImageCursor(image2));
    }

        public void aquesitionDeLepee () {
            epeeV.setVisible(true);
        }

        public void aquesitionDeBotte () {
            botteV.setVisible(true);
        }

        public void aquesitionDeBouleDeFeu () {
            bouleDeFeuV.setVisible(true);
        }

        public void majInventaire (Environnement env){
            labelpierre.textProperty().bind(env.getPersonage().getInventaire().nbPierreProperty().asString());
            labelbois.textProperty().bind(env.getPersonage().getInventaire().nbBoisProperty().asString());
            labelviande.textProperty().bind(env.getPersonage().getInventaire().nbViandeProperty().asString());
        }
}

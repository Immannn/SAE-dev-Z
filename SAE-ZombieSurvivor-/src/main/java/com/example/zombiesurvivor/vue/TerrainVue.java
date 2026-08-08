package com.example.zombiesurvivor.vue;

import com.example.zombiesurvivor.Main;
import com.example.zombiesurvivor.modele.Environnement;
import com.example.zombiesurvivor.modele.Terrain;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TerrainVue {

    private Terrain terrain;
    private Pane paneRacine;
    private TilePane paneTerrain;
    private Image tileset1 = new Image(Main.class.getResource("images/tileset1.png").toString());

    Rectangle rectangle = new Rectangle(32, 32);

    private Environnement env;

    public TerrainVue(Terrain terrain, TilePane paneTerrain, Pane paneRacine ) {
        super();
        this.paneRacine = paneRacine;
        this.terrain = terrain;
        this.paneTerrain = paneTerrain;
        this.afficherTerrain();
        this.creeRectangle();
        this.env = env;
    }

    public void afficherTerrain() {
        ObservableList<Integer> codesTuiles = terrain.getCodesTuiles();


        for (int i = 0; i < codesTuiles.size(); i++) {
            int ligne = codesTuiles.get(i) / 16;
            int colonne = (codesTuiles.get(i) % 16) - 1;
            int y = ligne * 32 ;
            int x = colonne * 32 ;
            ImageView tilesetcomplet = new ImageView(tileset1);
            tilesetcomplet.setViewport(new Rectangle2D(x, y, 32, 32));
            paneTerrain.getChildren().add(tilesetcomplet);
        }
    }

    public void verifModification(Integer i ,int indiceTuile) {
        if (i == 205) {
            ImageView terre = new ImageView(tileset1);
            terre.setViewport(new Rectangle2D(384, 384, 32, 32));
            this.paneTerrain.getChildren().set(indiceTuile, terre);
        } else if (i == 18) {
            ImageView terre = new ImageView(tileset1);
            terre.setViewport(new Rectangle2D(32, 32, 32, 32));
            this.paneTerrain.getChildren().set(indiceTuile, terre);
        } else if (i == 117) {
            ImageView bois = new ImageView(tileset1);
            bois.setViewport(new Rectangle2D(128, 224, 32, 32));
            this.paneTerrain.getChildren().set(indiceTuile, bois);
        }
    }


    public void creeRectangle() {
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.CYAN);
        paneRacine.getChildren().add(rectangle);
    }

    public Rectangle getRectangleContour() {
        return rectangle;
    }

    public int rec1X(int x){
        int i =  (x/32)*32;
        return i ;
    }
    public int rec1Y(int y){
        int i = (y/32)*32;
        return i ;
    }
}
package com.example.zombiesurvivor.modele;

import com.example.zombiesurvivor.vue.JoueurVue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Vache extends Acteur {

    private IntegerProperty XProperty;
    private IntegerProperty YProperty;
    private Environnement env;
    public Vache(int x, int y, Environnement env,Terrain terrain) {
        super(2,2,2,x,y,5,env,terrain);
        this.XProperty = new SimpleIntegerProperty(x);
        this.YProperty = new SimpleIntegerProperty(y);
        this.env = env;
        x = x;
        y = y;
    }
    @Override
    public void agir() {
        verifGravite();
        limitationMapX();
        seDeplpaceDroite();
        seDeplpaceGauche();

        if (isGravite()) {
            setyValue(-3);
            verifGravite();
        }
    }
    @Override
    public void seDeplpaceDroite() {
        if (Math.random() *  4 > 2) {
            int xDest = this.xProperty().getValue() + 2;
            if (this.env.getTerrain().tuileTraversable(getX() + 27, getY())) {
                this.setX(xDest);
                setOrientation(1);
            }
        }
    }
    @Override
    public void seDeplpaceGauche() {
        if (Math.random() * 4 > 2) {
            int yDest = this.xProperty().getValue() - 2;
            if (this.env.getTerrain().tuileTraversable(getX() + 5, getY())) {
                this.setX(yDest);
                setOrientation(-1);
            }
        }
    }
}



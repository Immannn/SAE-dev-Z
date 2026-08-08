package com.example.zombiesurvivor.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Acteur {
    private static int idN = 0;
    private String id;
    protected IntegerProperty XProperty;
    protected IntegerProperty YProperty;
    private IntegerProperty vie;
    private IntegerProperty orientation;
    private int vitesse;
    private int y;
    private int x;
    private int attaque;
    private int saut;
    private Environnement env;

    protected Terrain terrain;
    private boolean gravite;

    public Acteur(int vitesse, int attaque, int saut, int x, int y, int nbVie, Environnement env, Terrain terrain) {
        this.vie = new SimpleIntegerProperty(nbVie);
        this.vitesse = vitesse;
        this.attaque = attaque;
        this.saut = saut;
        this.id = "ennemie" + idN;
        idN++;
        this.x = x;
        this.y = y;
        XProperty = new SimpleIntegerProperty(x);
        YProperty = new SimpleIntegerProperty(y);
        this.terrain = terrain;
        this.orientation = new SimpleIntegerProperty(1);
        this.env = env;
    }

    public boolean isGravite() {
        return gravite;
    }

    public void verifGravite() {
        if (!terrain.tuileSol(this.getX(), this.getY() + 32) && !terrain.tuileSol(this.getX() + 20, this.getY() + 28)) {
            this.gravite = true;
        } else {
            this.gravite = false;
        }
    }

    public Environnement getEnv() {
        return env;
    }

    public int getX() {
        return XProperty.get();
    }

    public int getY() {
        return YProperty.get();
    }

    public void setX(int newX) {
        XProperty.setValue(newX);
    }

    public void setY(int newY) {
        YProperty.setValue(newY);
    }

    public IntegerProperty xProperty() {
        return XProperty;
    }

    public IntegerProperty yProperty() {
        return YProperty;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public void setyValue(int n) {
        this.yProperty().setValue(this.yProperty().getValue() - n);
    }

    public void setxValue(int n) {
        this.xProperty().setValue(this.xProperty().getValue() - n);
    }

    public int getVie() {
        return vie.get();
    }

    public IntegerProperty vieProperty() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie.set(vie);
    }

    public void changeVie(int degat) {
        setVie(getVie() - degat);
    }

    public String getId() {
        return id;
    }

    public void enleveVie(Acteur m) {
        m.changeVie(this.attaque);
        if (m.estMort()) {
            m.setVie(0);
        }
    }

    public void enleveVieJoueur(Acteur m, int damage) {
        m.changeVie(damage);
    }

    public boolean estMort() {
        return getVie() <= 0;
    }

    public void limitationMapX() {
        if (this.xProperty().getValue() > 1577) {
            this.setxValue(5);
        }
        if (this.xProperty().getValue() < -5) {
            this.setxValue(-5);
        }
    }

    public int getOrientation() {
        return orientation.get();
    }

    public IntegerProperty orientationProperty() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation.set(orientation);
    }

    public abstract void seDeplpaceDroite();

    public abstract void seDeplpaceGauche();

    public abstract void agir();


}


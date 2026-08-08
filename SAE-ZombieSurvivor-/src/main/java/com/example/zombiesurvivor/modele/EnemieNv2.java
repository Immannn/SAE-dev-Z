package com.example.zombiesurvivor.modele;

public class EnemieNv2 extends Acteur {
    private boolean attaque;
    private Environnement env;
    private boolean orientation = true;

    public EnemieNv2(int x, int y, Environnement env, Terrain terrain) {
        super(1, 2, 2, x, y, 50, env, terrain);
        this.env = env;
    }

    public Environnement getEnv() {
        return env;
    }

    public void attaqueDroit() {
        if (this.getY() <= env.getPersonage().getY() && env.getPersonage().getY() <= this.getY() + 28 && this.getX() <= env.getPersonage().getX() && env.getPersonage().getX() <= this.getX() + 28) {
            enleveVie(getEnv().getPersonage());
            attaque = true;
        } else {
            attaque = false;
        }
    }

    public void attaqueGauche() {

        if (this.getY() <= env.getPersonage().getY() && env.getPersonage().getY() <= this.getY() + 28 && this.getX() <= env.getPersonage().getX() && env.getPersonage().getX() <= this.getX() + 28) {
            enleveVie(getEnv().getPersonage());
            attaque = true;
        } else {
            attaque = false;
        }
    }

    public boolean estMort() {
        return getVie() <= 0;
    }

    @Override
    public void seDeplpaceDroite() {
        int xDest = this.xProperty().getValue() + getVitesse();
        this.setX(xDest);
        setOrientation(1);
        attaqueDroit();
    }

    @Override
    public void seDeplpaceGauche() {
        int xDest = this.xProperty().getValue() - getVitesse();
        if (this.env.getTerrain().tuileTraversable(xDest, getY())) {
            this.setX(xDest);
            setOrientation(-1);
            attaqueGauche();
        } else {
            if (Math.random() <= 0.8 && getX() > 0) {
                saute();
            } else {
                orientation = true;
            }
        }
    }

    public void saute() {
        int yDest = this.yProperty().getValue() - 60;
        if (!isGravite()) {
            this.setY(yDest);
        }
    }
    public void changement() {
        if (this.orientationProperty().getValue() == 1) {
            setOrientation(1);
        } else {
            setOrientation(-1);
        }
    }
    @Override
    public void agir() {
        limitationMapX();
        verifGravite();
        isGravite();
        attaqueGauche();
        attaqueDroit();

        if (env.getPersonage().getX() < this.getX() + 100 && env.getPersonage().getX() > this.getX() && env.getPersonage().getY() >= this.getY() + 2 || env.getPersonage().getX() < this.getX() + 100 && env.getPersonage().getX() > this.getX() && env.getPersonage().getY() >= this.getY() + 1 || env.getPersonage().getX() < this.getX() + 100 && env.getPersonage().getX() > this.getX() && env.getPersonage().getY() == this.getY()) {
            seDeplpaceDroite();
            if (isGravite()) {
                setyValue(-3);
                verifGravite();
            }
        } else if (env.getPersonage().getX() > this.getX() - 100 && env.getPersonage().getX() < this.getX() && env.getPersonage().getY() >= this.getY() + 2 || env.getPersonage().getX() < this.getX() + 100 && env.getPersonage().getX() > this.getX() && env.getPersonage().getY() >= this.getY() + 1 || env.getPersonage().getX() < this.getX() + 100 && env.getPersonage().getX() > this.getX() && env.getPersonage().getY() == this.getY()) {
            seDeplpaceGauche();
            if (isGravite()) {
                setyValue(-3);
                verifGravite();
            }
        } else {
            if (attaque == false) {
                if (!this.env.getTerrain().tuileTraversable(xProperty().getValue() + getVitesse() + 27, getY()) && orientation) {
                    if (Math.random() <= 0.8 && getX() < 1500) {
                        saute();
                    } else {
                        orientation = false;
                    }
                }
                if (orientation) {
                    seDeplpaceDroite();
                } else {
                    seDeplpaceGauche();
                }
            }
            if (isGravite()) {
                setyValue(-3);
                verifGravite();
            }
        }
    }
}


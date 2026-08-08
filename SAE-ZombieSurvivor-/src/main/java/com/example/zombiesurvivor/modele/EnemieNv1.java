package com.example.zombiesurvivor.modele;

import com.example.zombiesurvivor.vue.JoueurVue;

public class EnemieNv1 extends Acteur {
    private Environnement env;

    public EnemieNv1 (int x, int y, Environnement env, Terrain terrain) {
        super(2, 2, 2, x, y, 50, env, terrain);
        this.env = env;
    }

    public void attaqueDroit () {
       if ((this.getY()-5<= env.getPersonage().getY() && env.getPersonage().getY()<=this.getY()+5) && (this.getX()-5<= env.getPersonage().getX() && env.getPersonage().getX()<=this.getX()+50)){

            enleveVie(getEnv().getPersonage());
        }

        if (getEnv().getPersonage().estMort()) {
        }
    }

    public void attaqueGauche () {
        if ((this.getY()-5<= env.getPersonage().getY() && env.getPersonage().getY()<=this.getY()+5) && (this.getX()-5<= env.getPersonage().getX() && env.getPersonage().getX()<=this.getX()-50)) {
            enleveVie(getEnv().getPersonage());
        }
        if (getEnv().getPersonage().estMort()) {
        }
    }

    @Override
    public void seDeplpaceDroite () {
        if (Math.random() > .7) {
            int xDest = this.xProperty().get() + getVitesse();
            if (this.env.getTerrain().tuileTraversable(getX() + 27, getY()))
                this.setX(xDest);
            setOrientation(1);
        } else seDeplpaceGauche();
    }

    @Override
    public void seDeplpaceGauche () {
        if (Math.random() > .7) {
            int xDest = this.xProperty().get() - getVitesse();
            if (this.env.getTerrain().tuileTraversable(getX() + 5, getY()))
                this.setX(xDest);
            setOrientation(-1);
        } else seDeplpaceDroite();
    }

    @Override
    public void agir () {
        limitationMapX();
        verifGravite();
        attaqueGauche();
        attaqueDroit();
        if (isGravite()) {
            setyValue(-3);
            verifGravite();
        }
        seDeplpaceDroite();
        seDeplpaceGauche();

    }
}

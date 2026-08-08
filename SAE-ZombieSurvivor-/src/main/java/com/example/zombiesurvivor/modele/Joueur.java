package com.example.zombiesurvivor.modele;

import com.example.zombiesurvivor.vue.InventaireVue;
import com.example.zombiesurvivor.vue.JoueurVue;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Joueur extends Acteur {
    private Inventaire inventaire;
    private int directionFlamme;
    private boolean ouiOuNon;
    private boolean mange;
    private int avance = 0;
    private Boolean juste = false;
    private int tic = 0;
    private boolean animation = false;
    private boolean up = false, down = false, left = false, right = false;
    private double Yhere;
    private double délaiSaut;
    private int verifSaut;
    private DoubleProperty dega;
    private boolean fin = false;


    public Joueur (int x, int y, Environnement env,Terrain terrain) {

        super(2, 5, 3, x, y, 500, env,terrain);
        inventaire = new Inventaire();
        directionFlamme = 0;
        dega = new SimpleDoubleProperty(0);
    }
    public DoubleProperty degaProperty () {
        return dega;
    }

    public void agir() {
        if (!up)
            verifGravite();

        if (right) {
            limitationMapX();
            seDeplpaceDroite();

            if (tic % 50 == 0) {
                animation = !animation;
                JoueurVue.apparanceDroitecourt(animation);
            }
        }

        if (left) {
            limitationMapX();
            seDeplpaceGauche();

            if (tic % 15 == 0) {
                animation = !animation;
                JoueurVue.apparanceGauchecourt(animation);
            }
        }

        if (up) {
            if (this.terrain.tuileTraversable(getX(), getY())) {
                verifSaut = (int) verivSaut(Yhere, délaiSaut);
                délaiSaut += .25;
                yProperty().setValue(verivSaut(Yhere, délaiSaut));
                if (verifSaut == YProperty.getValue()) {
                    up = false;
                    verifGravite();
                }
            }
        } else {
            Yhere = yProperty().getValue();
            délaiSaut = .0;
        }


        if (ouiOuNon)
            attaque();
        if (mange)
            recupererVie();

        if(inventaire.getLance().isVerif()){
            inventaire.getLance().setPosx(inventaire.getLance().getPosx()+directionFlamme);

            for(int i = 0;i<getEnv().getListperso().size();++i) {
                if (Math.abs(inventaire.getLance().getPosx() - getEnv().getListperso().get(i).getX()) < 15 && Math.abs(inventaire.getLance().getPosy() - getEnv().getListperso().get(i).getY()) < 10) {
                    if (!this.terrain.tuileTraversable(inventaire.getLance().getPosx(), getY()) && !this.terrain.tuileTraversable(inventaire.getLance().getPosx()-28, getY())) {
                        JoueurVue.bouleDeFeuNotVisible();
                        inventaire.getLance().setPosx(getX());
                        inventaire.getLance().setPosy(getY());
                    }
                    enleveVieJoueur(getEnv().getListperso().get(i), 25);
                    inventaire.getLance().vernot();
                    JoueurVue.bouleDeFeuNotVisible();
                    inventaire.getLance().setPosx(getX());
                    inventaire.getLance().setPosy(getY());
                }
            }
            for (int a = 0; a < getEnv().getListeVache().size(); ++a) {
                if (Math.abs(inventaire.getLance().getPosx() - getEnv().getListeVache().get(a).getX()) < 15 && Math.abs(inventaire.getLance().getPosy() - getEnv().getListeVache().get(a).getY()) < 10) {
                    enleveVieJoueur(getEnv().getListeVache().get(a), 25);
                    if (!this.terrain.tuileTraversable(inventaire.getLance().getPosx(), getY()) && !this.terrain.tuileTraversable(inventaire.getLance().getPosx()-28, getY())) {
                        JoueurVue.bouleDeFeuNotVisible();
                        inventaire.getLance().setPosx(getX());
                        inventaire.getLance().setPosy(getY());
                    }
                    inventaire.getLance().vernot();
                    JoueurVue.bouleDeFeuNotVisible();
                    inventaire.getLance().setPosx(getX());
                    inventaire.getLance().setPosy(getY());
                }
            }
        }

        if (isGravite()) {
            setyValue(-3);
            verifGravite();
        }
    }

    public double verivSaut (double Yhere, double délaiSaut) {

        return (délaiSaut * délaiSaut) - 20 * délaiSaut + Yhere;

    }

    public void seDeplpaceDroite () {

        int xDest = this.xProperty().getValue() + getVitesse();
        if (this.terrain.tuileTraversable(xDest + 20, getY()+28)) {

            this.setX(xDest);
        }
    }

    public void seDeplpaceGauche () {

        int yDest = this.xProperty().getValue() - getVitesse();
        if (this.terrain.tuileTraversable(yDest - 5, getY()+28)) {
            this.setX(yDest);
        }
    }

    public void droite () {
        this.right = true;
        this.left = false;
    }

    public void gauche () {
        this.left = true;
        this.right = false;
    }

    public void haut () {

        if (!isGravite())
            this.up = true;
    }

    public void attaqueOui () {
        this.ouiOuNon = true;

    }

    public void mange () {
        this.mange = true;
    }

    public void neVaPLusADroite () {
        this.right = false;

    }

    public void neVaPLusAGauche () {
        this.left = false;
    }

    public void neVaPLusEnHaut () {

        this.up = false;
    }

    public void attaquePas () {

        this.ouiOuNon = false;
    }


    public void mangePas () {
        this.mange = false;
    }

    public void craftEpee (InventaireVue inventaireVue) {
        inventaire.peutCraftEpee(inventaireVue);
    }

    public void craftBotte (InventaireVue inventaireVue) {
        inventaire.peutCraftBotte(inventaireVue, this);
    }

    public void craftLancepierre (InventaireVue inventaireVue) {

        inventaire.peutCraftBouleDeFeu(inventaireVue);
    }

    public void attaque () {

        for (int i = 0; i < getEnv().getListperso().size(); ++i) {
            if (inventaire.isaLeppe() == true && inventaire.isEpeeActive() == true) {
                if (this.getY() - 5 <= getEnv().getListperso().get(i).getY() && getEnv().getListperso().get(i).getY() <= this.getY() + 5 && this.getX() - 5 <= getEnv().getListperso().get(i).getX() && getEnv().getListperso().get(i).getX() <= this.getX() + 37) {
                    enleveVieJoueur(getEnv().getListperso().get(i), 2);
                }
                if (getEnv().getListperso().get(i).estMort()) {
                }
            }

            if (inventaire.isaFeu() == true && inventaire.isBouleDeFeu() == true) {
                if (this.getY() - 5 <= getEnv().getListperso().get(i).getY() && getEnv().getListperso().get(i).getY() <= this.getY() + 5 && this.getX() - 100 <= getEnv().getListperso().get(i).getX() && getEnv().getListperso().get(i).getX() <= this.getX() + 100) {
                    JoueurVue.bouleDeFeuVisible();
                    inventaire.getLance().ver();
                    directionFlamme=getOrientation();
                    inventaire.getLance().setPosx(getX());
                    inventaire.getLance().setPosy(getY());


                }

                if (getEnv().getListperso().get(i).estMort()) {
                }
            }
        }

        //ATTAQUE VACHE
        for (int i = 0; i < getEnv().getListeVache().size(); ++i) {
            if (inventaire.isaLeppe() == true && inventaire.isEpeeActive() == true) {
                if (this.getY() - 5 <= getEnv().getListeVache().get(i).getY() && getEnv().getListeVache().get(i).getY() <= this.getY() + 5 && this.getX() - 5 <= getEnv().getListeVache().get(i).getX() && getEnv().getListeVache().get(i).getX() <= this.getX() + 37) {
                    enleveVieJoueur(getEnv().getListeVache().get(i), 2);
                }
            }


            if (inventaire.isaFeu() == true && inventaire.isBouleDeFeu() == true) {
                if (this.getY() - 5 <= getEnv().getListeVache().get(i).getY() && getEnv().getListeVache().get(i).getY() <= this.getY() + 5 && this.getX() - 100 <= getEnv().getListeVache().get(i).getX() && getEnv().getListeVache().get(i).getX() <= this.getX() + 100) {
                    JoueurVue.bouleDeFeuVisible();
                    inventaire.getLance().ver();
                    directionFlamme=getOrientation();
                    inventaire.getLance().setPosx(getX());
                    inventaire.getLance().setPosy(getY());
                }

                if (getEnv().getListeVache().get(i).estMort()) {
                }
            }
        }
    }
    public void recupererVie () {
        if (inventaire.getNbViande() > 0) {
            setVie(getVie() + 1);
            inventaire.reductionDeViande(1);
        }

    }

    public Inventaire getInventaire () {
        return inventaire;
    }

}

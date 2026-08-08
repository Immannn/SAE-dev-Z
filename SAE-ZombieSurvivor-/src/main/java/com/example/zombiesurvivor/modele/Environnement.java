package com.example.zombiesurvivor.modele;

import javafx.animation.Timeline;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Environnement {
    private ObservableList<Acteur> listperso;
    private ObservableList<Vache> listeVache;
    private Terrain terrain;
    private Joueur personage;
    public Environnement (Terrain terrain) {
        listperso = FXCollections.observableArrayList();
        listeVache = FXCollections.observableArrayList();
        this.terrain = terrain;
        this.personage = new Joueur(350, 100, this, terrain);
    }

    public void ennemieDeLaListeAgit () {
        for (int i = listperso.size() - 1; i >= 0; i--) {
            listperso.get(i).agir();
            if (listperso.get(i).estMort()) {
                listperso.remove(i);
            }
        }
    }
    public void vacheDeLaListeAgit () {

        for (int i = listeVache.size() - 1; i >= 0; i--) {
            listeVache.get(i).agir();
            if (listeVache.get(i).estMort()) {
                personage.getInventaire().ajouterViande();
                listeVache.remove(i);
            }
        }
    }

    public void ajouterVache () {
        Vache vache = new Vache((int) aleatoire(), 50, this, terrain);
        listeVache.add(vache);
    }

    public double aleatoire () {
        return Math.random() * 1710;
    }

    public void ajouterEnnemie () {
        EnemieNv2 enemieNv1 = new EnemieNv2((int) aleatoire(), 50, this, terrain);
        listperso.add(enemieNv1);
    }

    public void ajouterEnnemieNv1 () {
        EnemieNv1 enemieNv1 = new EnemieNv1((int) aleatoire(), 50, this, terrain);
        listperso.add(enemieNv1);
    }

    public void jeuTermine (Timeline gameLoop) {
        if (listperso.isEmpty()) {
            gameLoop.stop();
        }
    }
    public void creuser (int indiceTuile) {

        if (terrain.codesTuiles(indiceTuile) != 140 && terrain.codesTuiles(indiceTuile) != 205) {
            if (getPersonage().getInventaire().isHaceActive() && terrain.codesTuiles(indiceTuile) == 117) {
                terrain.remplaceTuile(indiceTuile);
            }
            if (getPersonage().getInventaire().isPiocheActive() && (terrain.codesTuiles(indiceTuile) == 69 || terrain.codesTuiles(indiceTuile) == 18)) {
                terrain.remplaceTuile(indiceTuile);
            }
        }
    }
    public void placer (int indiceTuile, int tuile) {
        if (terrain.codesTuiles(indiceTuile) == 205 && (tuile == 117) && personage.getInventaire().getNbBois() > 0) {
            terrain.ajouteTuile(indiceTuile, tuile);
        }
        if (terrain.codesTuiles(indiceTuile) == 205 && (tuile == 18) && personage.getInventaire().getNbPierre() > 0) {
            terrain.ajouteTuile(indiceTuile, tuile);
        }
    }
    public boolean verifPorté (MouseEvent mouseEvent, Joueur jouer) {
        if (Math.abs((int) (mouseEvent.getY() / 32) - ((jouer.getY() / 32))) < 4 && (Math.abs((int) (mouseEvent.getX() / 32) - ((jouer.getX() / 32))) < 4)) {
            return true;
        }
        return false;
    }
    public void ajouteTuileDansInventaire (int indiceTuile) {
        if ((terrain.codesTuiles(indiceTuile)) == 117) {
            getPersonage().getInventaire().ajouterBois();
        } else if ((terrain.codesTuiles(indiceTuile)) == 18) {
            getPersonage().getInventaire().ajouterPierre();
        }
    }
    public void enleveTuilePlaceDeInventaire (int indiceTuile) {
        if ((terrain.codesTuiles(indiceTuile)) == 117) {
            getPersonage().getInventaire().reductionDeBois(1);
        } else if ((terrain.codesTuiles(indiceTuile)) == 18) {
            getPersonage().getInventaire().reductionDePierre(1);
        }
    }
    public ObservableList<Acteur> getListperso () {
        return listperso;
    }

    public ObservableList<Vache> getListeVache () {
        return listeVache;
    }

    public Joueur getPersonage () {
        return personage;
    }

    public Terrain getTerrain () {
        return terrain;
    }

    @Override
    public String toString () {
        return "Environnement [listePerso=" + listperso + "listTerrain" + terrain + "]";
    }



}



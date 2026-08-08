package com.example.zombiesurvivor.controleur;


import com.example.zombiesurvivor.modele.Vache;
import com.example.zombiesurvivor.vue.PnjVue;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public  class MonObservateurVaches implements ListChangeListener<Vache> {
    private Pane panneauJeu;
    private PnjVue pnjVue;
    public MonObservateurVaches(Pane panneauJeu, PnjVue pnjVue) {
        super();
        this.panneauJeu = panneauJeu;
        this.pnjVue = pnjVue;
    }
    private void creerSprite(Vache a) {
        pnjVue.verifActeur(a);
    }

    private void enleverSprite(Vache mort) {
        for (int i = 0;i<panneauJeu.getChildren().size();i++){
            Node elimine = panneauJeu.getChildren().get(i);
                if (mort.getId().equals(elimine.getId())){
                    panneauJeu.getChildren().remove(elimine);
                }
        }
    }
    @Override
    public void onChanged(Change<? extends Vache> c) {
        while (c.next()) {
            for (Vache nouveau : c.getAddedSubList()) {
                if (nouveau instanceof Vache) {
                    creerSprite(nouveau);
                }
            }
            for (Vache mort : c.getRemoved()) {
                enleverSprite(mort);
            }
        }
    }
}
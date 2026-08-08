package com.example.zombiesurvivor.controleur;

import com.example.zombiesurvivor.modele.Acteur;
import com.example.zombiesurvivor.modele.EnemieNv2;
import com.example.zombiesurvivor.modele.EnemieNv1;
import com.example.zombiesurvivor.vue.PnjVue;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class MonObservateurEnnemies implements ListChangeListener<Acteur> {

    private Pane panneauJeu;
    private PnjVue pnjVue;

    public MonObservateurEnnemies(Pane panneauJeu, PnjVue pnjVue) {
        super();
        this.panneauJeu = panneauJeu;
        this.pnjVue = pnjVue;
    }

    private void creerSprite(EnemieNv2 a) {
        pnjVue.verifActeur(a);
    }

    private void creerSprite2(EnemieNv1 a) {
        pnjVue.verifActeur(a);
    }

    private void enleverSprite(Acteur mort) {
        for (int i = 0;i<panneauJeu.getChildren().size();i++){
            Node elimine = panneauJeu.getChildren().get(i);
            if (mort.getId().equals(elimine.getId())){
                panneauJeu.getChildren().remove(elimine);
            }
        }
    }
    @Override
    public void onChanged(Change<? extends Acteur> c) {

        while (c.next()) {
            for (Acteur nouveau : c.getAddedSubList()) {
                if (nouveau instanceof EnemieNv2) {
                    creerSprite((EnemieNv2) nouveau);
                } else if (nouveau instanceof EnemieNv1) {
                    creerSprite2((EnemieNv1) nouveau);
                }
            }
            for (Acteur mort : c.getRemoved()) {
                if (mort instanceof EnemieNv2) {
                    enleverSprite((EnemieNv2) mort);
                }
                else if (mort instanceof EnemieNv1) {
                    enleverSprite((EnemieNv1) mort);
                }
            }
        }
    }

}
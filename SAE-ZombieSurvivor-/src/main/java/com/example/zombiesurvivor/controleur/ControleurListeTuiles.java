package com.example.zombiesurvivor.controleur;

import com.example.zombiesurvivor.vue.TerrainVue;
import javafx.collections.ListChangeListener;
public class ControleurListeTuiles implements ListChangeListener<Integer> {
    private TerrainVue terrainVue;
    public ControleurListeTuiles(TerrainVue terrainVue) {
        this.terrainVue = terrainVue;
    }
    @Override
    public void onChanged(Change<? extends Integer> change) {

        while (change.next()) {
            for (Integer nv : change.getAddedSubList()) {
                terrainVue.verifModification(nv,change.getFrom());

            }
        }
    }
}


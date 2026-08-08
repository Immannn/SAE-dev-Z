package com.example.zombiesurvivor.vue;


import javafx.scene.layout.Pane;

public class InterfaceCraftVue {

    private Pane interfaceCraft;
    private boolean isOn;

    public InterfaceCraftVue (Pane interfaceCraft) {
        this.interfaceCraft = interfaceCraft;
        interfaceCraft.setVisible(true);
        interfaceCraft.toBack();
    }
    public void afficherInterfaceCraft(){
        if(!isOn){
            interfaceCraft.toFront();
            isOn = true;
        }
        else{
            interfaceCraft.toBack();
            isOn = false;
        }

    }

    public boolean isOn () {
        return isOn;
    }
}
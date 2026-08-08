package com.example.zombiesurvivor.junit;

import com.example.zombiesurvivor.modele.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class JoueurTest {


    private Joueur j1 ;
    private EnemieNv1 e1 ;
    private Environnement env ;
    private Terrain terrain ;

    @BeforeEach
    void setUp() throws Exception {
        terrain = new Terrain();
        env = new Environnement(terrain);
        j1 = new Joueur(0,0,env,terrain);
        e1 = new EnemieNv1(0,0,env,terrain);
        j1.getInventaire().ajouterViande();
    }


    @Test
    void seDeplpaceDroite(){
        Assert.assertEquals(2,j1.getX());
    }

    @Test
    void attaque () {
        Assert.assertEquals(48,e1.getVie());
    }

    @Test
    void recupererVie () {
        Assert.assertEquals(501,j1.getVie());
    }
}
package com.example.zombiesurvivor.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.zombiesurvivor.modele.*;
import org.junit.Assert;


class EnvironnementTest {
        private Vache v1 ;
        private Environnement env ;
        private Terrain terrain ;

        @BeforeEach
        void setUp() throws Exception {
            terrain = new Terrain();
            env = new Environnement(terrain);
            v1 = new Vache(0,0,env,terrain);
            env.getListeVache().add(v1);

        }


    @Test
    void ajouterVache () {
            Assert.assertEquals(v1,env.getListeVache());
    }
    @Test
    void vacheDeLaListeAgit () {
            v1.setVie(0);
            Assert.assertEquals(null,env.getListeVache());
    }

}
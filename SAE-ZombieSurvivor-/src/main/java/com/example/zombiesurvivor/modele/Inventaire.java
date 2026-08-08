package com.example.zombiesurvivor.modele;

import com.example.zombiesurvivor.vue.InventaireVue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {
	private ObservableList<Item> items;
	private IntegerProperty NbBois;
	private IntegerProperty NbPierre;
	private IntegerProperty NbViande;
	private boolean aFeu;
	private boolean aLeppe;
	private boolean epeeActive;
	private boolean haceActive ;
	private boolean piocheActive ;
	private boolean bouleDeFeu ;

	Item pierre = new Pierre();
	Item viande = new Viande();
	Item bois = new Bois();
	Item epee = new Epee();
	BouleDeFeu feu = new BouleDeFeu();
	Botte b = new Botte();

	public Inventaire () {
		this.items = FXCollections.observableArrayList();
		NbPierre = new SimpleIntegerProperty();
		NbBois = new SimpleIntegerProperty();
		NbViande = new SimpleIntegerProperty();
	}

	public void piocheDansLaMain(){
		this.piocheActive = true;
		this.epeeActive = false;
		this.haceActive = false ;
		this.bouleDeFeu = false ;
	}
	public void hacheDansLaMain(){
		this.piocheActive = false;
		this.epeeActive = false;
		this.haceActive = true ;
		this.bouleDeFeu = false ;
	}
	public void bouleDeFeuDansLaMain(){
		this.piocheActive = false;
		this.epeeActive = false;
		this.haceActive = false ;
		this.bouleDeFeu = true ;
	}
	public void epeeDansLaMain(){
		this.piocheActive = false;
		this.epeeActive = true;
		this.haceActive = false ;
		this.bouleDeFeu = false ;
	}
	public void ajouterEquip(Item e) {
		items.add(e);
	}

	public void ajouterBois() {
		items.add(bois);
		setNbBois(getNbBois()+1);
	}
	public void ajouterViande() {
        items.add(viande);
		setNbViande(getNbViande()+1);
    }
	public void ajouterPierre() {
		items.add(pierre);
		setNbPierre(getNbPierre()+1);
	}


	public ObservableList<Item> getItems() {
		return items;
	}

	public IntegerProperty nbBoisProperty() {
		return NbBois;
	}

	public IntegerProperty nbPierreProperty() {
		return NbPierre;
	}

	public IntegerProperty nbViandeProperty() {
		return NbViande;
	}


	public int getNbPierre() {
		return NbPierre.get();
	}

	public int getNbViande() {
		return NbViande.get();
	}

	public int getNbBois() {
		return NbBois.get();
	}

	public void setNbBois(int nbBois) {
		this.NbBois.set(nbBois);
	}

	public void setNbPierre(int nbPierre) {
		this.NbPierre.set(nbPierre);
	}

	public void setNbViande(int nbViande) {
		this.NbViande.set(nbViande);
	}

	public boolean isaFeu() {
		return aFeu;
	}

	public boolean isaLeppe () {
		return aLeppe;
	}

	public BouleDeFeu getLance() {
return feu;
	}

	public void reductionDePierre(int i) {
		for(int x = 0;x<i;x++) {
			items.remove(pierre);
		}
		setNbPierre(getNbPierre()-i);
	}

	public void reductionDeBois(int i) {
		for(int x = 0;x<i;x++) {
			items.remove(bois);
		}
		setNbBois(getNbBois()-i);
	}

	public void reductionDeViande(int i) {
		for(int x = 0;x<i;x++) {
			items.remove(viande);
		}
		setNbViande(getNbViande()-i);
	}

	public void peutCraftEpee(InventaireVue vueI){
		if (!getItems().contains(epee)) {
			if (getNbPierre() >= 2 && getNbBois() >= 2) {
				ajouterEquip(epee);
				reductionDePierre(2);
				reductionDeBois(2);
				vueI.aquesitionDeLepee();
				aLeppe = true;
			}
		}
	}

	public void peutCraftBouleDeFeu(InventaireVue vueI) {
		if (!getItems().contains(feu)) {
			if (getNbBois() >= 5 && getNbPierre() >= 5) {
				ajouterEquip(feu);
				reductionDeBois(5);
				reductionDePierre(5);
				vueI.aquesitionDeBouleDeFeu();
				aFeu = true;
			}
		}
	}

	public void peutCraftBotte(InventaireVue vueI,Joueur joueur){
		if (!items.contains(b)) {
			if (getNbViande() >= 3 && getNbBois() >= 1) {
				ajouterEquip(b);
				reductionDeBois(2);
				reductionDeViande(3);
				vueI.aquesitionDeBotte();
				joueur.setVitesse(joueur.getVitesse()+2);
			}
		}
	}


	public boolean isEpeeActive() {
		return epeeActive;
	}

	public boolean isHaceActive() {
		return haceActive;
	}

	public boolean isPiocheActive() {
		return piocheActive;
	}

	public boolean isBouleDeFeu() {
		return bouleDeFeu;
	}

	@Override
	public String toString() {
		return "Inventaire [InvEquipements=" + items + ",InvRessources=" + "]";
	}

}

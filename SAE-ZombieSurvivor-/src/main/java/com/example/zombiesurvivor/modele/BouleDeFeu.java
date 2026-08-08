package com.example.zombiesurvivor.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BouleDeFeu extends Equipement {

	private IntegerProperty posx;
	private IntegerProperty posy;
	private boolean verif = false ;


	public BouleDeFeu () {
		super(4);
		this.posx=new SimpleIntegerProperty();
		this.posy=new SimpleIntegerProperty();
	}

	public int getPosx() {
		return posx.get();
	}

	public IntegerProperty posxProperty() {
		return posx;
	}

	public int getPosy() {
		return posy.get();
	}


	public void setPosx(int posx) {
		this.posx.set(posx);
	}

	public IntegerProperty posyProperty() {
		return posy;
	}

	public  void setPosy(int posy) {
		this.posy.set(posy);
	}

	public void ver(){
		verif=true;
	}

	public void vernot(){
		verif=false;
	}

	public boolean isVerif () {
		return verif;
	}

	@Override
	public String toString() {
		return "BouleDeFeu";
	}
	
}

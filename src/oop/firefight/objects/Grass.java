package oop.firefight.objects;

import java.awt.Point;

public class Grass extends Floresta{

	private double burnGrass=0.2;
	
	private int burntGrass = 7;
	
	public Grass(Point point) {
		super(point);
	}
	
	
	@Override
	public int getLayer() {
		return 1;
	}


	@Override
	public double getProb() {
		return burnGrass;
	}


	@Override
	public int getBurnt() {		
		return burntGrass;
	}


	

}

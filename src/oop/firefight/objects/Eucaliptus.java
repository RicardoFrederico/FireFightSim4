package oop.firefight.objects;

import java.awt.Point;

public class Eucaliptus extends Floresta{

	private double burnEucaliptus=0.1;
	
	private int burntEucaliptus = 10;
	
	public Eucaliptus(Point point) {
		super(point);
	}
	
	@Override
	public int getLayer() {
		return 1;
	}


	@Override
	public double getProb() {
		return burnEucaliptus;
	}

	@Override
	public int getBurnt() {
		return burntEucaliptus;
	}

	
}

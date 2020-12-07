package oop.firefight.objects;
import java.awt.Point;

public class Pine extends Floresta{ 
	
	private double burnPine=0.05;
	
	private int burntPine = 14;
	
	public Pine(Point point) {
		super(point);
	}
	
	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public double getProb() {
		return burnPine;
	}

	@Override
	public int getBurnt() {		
		return burntPine;
	}
	
	

	
	

}

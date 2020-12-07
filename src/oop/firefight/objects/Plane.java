package oop.firefight.objects;

import java.awt.Point;

import oop.firefight.main.FireSimulator;

public class Plane extends FireFightObject{

	public Plane(Point position) {
		super(position);		
	}
	
	@Override
	public int getLayer() {
		return 3;
	}

	
	public void move() {
		Point atual = getPosition();
		Point newPosition = new Point (atual.x, atual.y-1);
		FireSimulator.getInstance().removeFire(getPosition());
		if(inBounds(newPosition))  
			setPosition(newPosition);
		else
			FireSimulator.getInstance().removePlane(FireSimulator.getInstance().getPlane());
		
	}
	
	public void updatePosition() {
		move();
		move();
	}
	

}

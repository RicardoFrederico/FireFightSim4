package oop.firefight.objects;

import java.awt.Point;

public class Burnt extends FireFightObject{

	public Burnt(Point position) {
		super(position);
		
	}
	
	@Override
	public int getLayer() {
		return 1;
	}

	
}

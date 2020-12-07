package oop.firefight.objects;

import java.awt.Point;

public class Land extends FireFightObject {
	
	public Land(Point point) {
		super(point);
	}
	
	@Override
	public int getLayer() {
		return 0;
	}
	
	
}

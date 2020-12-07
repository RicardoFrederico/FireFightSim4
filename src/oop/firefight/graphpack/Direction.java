package oop.firefight.graphpack;

import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 * @author POO2016
 * 
 * Cardinal directions
 *
 */
public enum Direction {
	LEFT(new Point(-1,0)), UP(new Point(0,-1)), RIGHT(new Point(1,0)), DOWN(new Point(0,1));

	private Point vector;

	Direction(Point vector) {
		this.vector = vector;
	}
	

	
	public Point asVector() {
		return vector;
	}
	
	public static Direction directionFor(int keyCode) {
		switch(keyCode){
			case KeyEvent.VK_DOWN: 
				return DOWN;
			case KeyEvent.VK_UP:
				return UP;
			case KeyEvent.VK_LEFT:
				return LEFT;
			case KeyEvent.VK_RIGHT:
				return RIGHT;
		}
		throw new IllegalArgumentException();
	}

	public static boolean isDirection(int lastKeyPressed) {		
		return lastKeyPressed >= KeyEvent.VK_LEFT && lastKeyPressed <= KeyEvent.VK_DOWN;
	}

	public static Point movementVector(Point position, Point to) {
		Point d = new Point(position.x - to.x, position.y - to.y);
		if (Math.abs(d.x) > Math.abs(d.y)) {
			d.x = (int) Math.signum(d.x);
			d.y = 0;
		} else if (Math.abs(d.x) <= Math.abs(d.y)) {
			d.y = (int) Math.signum(d.y);
			d.x = 0;
		}
		return d;
	}
}

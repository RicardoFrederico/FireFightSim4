package oop.firefight.objects;

import java.awt.Point;

import oop.firefight.main.FireSimulator;
import oop.firefight.graphpack.ImageMatrixGUI;
import oop.firefight.graphpack.Direction;


public class Fireman extends FireFightObject implements Movable {

	private boolean onTop=false;
	
	public Fireman(Point position) {
		super(position);
	}

	public int getLayer() {
		return 3;
	}
	
	public void setOnTop(boolean onTop) {
		this.onTop = onTop;
	}

	public boolean isOnTop() {
		Point f = getPosition();
		Point b = FireSimulator.getInstance().getBulldozer().getPosition();
		if(f.equals(b) ) 
			return true;
		return false;
	}
	
	@Override
	public void move(Direction dir) {
		Point atual = getPosition();
		Point newPosition = new Point (atual.x + dir.asVector().x, atual.y + dir.asVector().y);
		
		if(onTop==true) {
			ImageMatrixGUI.getInstance().removeImage(FireSimulator.getInstance().getFireman());
			FireSimulator.getInstance().getBulldozer().move(dir);	
		}		
//		 if(FireSimulator.getInstance().existPlane()) {
//			FireSimulator.getInstance().getPlane().move(dir);
//			FireSimulator.getInstance().getPlane().move(dir);
//		}
		
		boolean onFire = FireSimulator.getInstance().thisPositionOnFire(newPosition);
		if(onFire== true && onTop==false) { //acrescentei isOnTop()==false para o buldozer não apagar fogo  
			FireSimulator.getInstance().removeFire(newPosition);
		}else {
			if(inBounds(newPosition)) 
				setPosition(newPosition);
			
		}
		if(isOnTop() == true)
			setOnTop(true);
			
		
			
				
	}
	
	
}

	



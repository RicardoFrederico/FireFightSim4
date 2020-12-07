package oop.firefight.objects;
import java.awt.Point;

import oop.firefight.main.FireSimulator;
import oop.firefight.graphpack.Direction;


public class Fire extends FireFightObject {
	
	public Fire(Point position) {
		super(position);
	}

	@Override
	public int getLayer() {
		return 2;
	}
	
	
	public void findFloresta() {
		Direction[] directions = Direction.values();
		for(Direction dir : directions) {
			Point coordVizinho = new Point (getPosition().x + dir.asVector().x, getPosition().y + dir.asVector().y);
			Floresta f = FireSimulator.getInstance().getFlorestaAt(coordVizinho);
			if(f!=null ) 
				f.burn();
		}				
	}

}

package oop.firefight.objects;
import java.awt.Point;
import oop.firefight.graphpack.ImageTile;

public abstract class FireFightObject implements ImageTile{
	
	
	private Point position;

	public FireFightObject(Point position) {
		this.position = position;
	}

	@Override
	public String getName() {
		return getClass().getSimpleName().toLowerCase();
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public int getLayer() {
		return 1;
	}

	protected void setPosition(Point point) {
		position = point;
	}	
	
	//esta funcao tem de ser alterada para nao ser 10x10
	public boolean inBounds(Point p) {
		if(p.x < 0)
			return false;
		if(p.y <0)
			return false;
		if(p.x >9)
			return false;
		if(p.y >9)
			return false;
		return true;
	}
	
	public static FireFightObject create(String objeto, int x, int y) {
		switch(objeto) {
		case "e": return new Eucaliptus(new Point(x,y)); 
		case "p": return new Pine(new Point(x,y));
		case "_": return new Grass(new Point(x,y));
		case ".": return new Land(new Point(x,y));
		case "Fireman": return new Fireman(new Point(x,y));
		case "Bulldozer": return new Bulldozer(new Point(x,y));
		case "Fire": return new Fire(new Point(x,y));
		}
		return null;
	}
	
	
}

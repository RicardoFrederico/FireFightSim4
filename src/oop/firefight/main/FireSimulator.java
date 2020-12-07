package oop.firefight.main;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import oop.firefight.graphpack.ImageMatrixGUI;
import oop.firefight.graphpack.Direction;
import oop.firefight.objects.Bulldozer;
import oop.firefight.objects.Burnt;
import oop.firefight.objects.Fire;
import oop.firefight.objects.FireFightObject;
import oop.firefight.objects.Fireman;
import oop.firefight.objects.Floresta;
import oop.firefight.objects.Plane;

public class FireSimulator implements PropertyChangeListener {

	private static final String CONFIG_DIR = "levels";
	private static final String CONFIG_FILE = "landscape.txt";
	private Fireman fireman;
	private Plane plane;
	private ArrayList<FireFightObject> lista =  new ArrayList<FireFightObject>();
			
	
	private static FireSimulator INSTANCE = null;

	public static FireSimulator getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FireSimulator();
		}
		return INSTANCE;
	}

	private FireSimulator() {
		try {
			readConfig(CONFIG_DIR + "/" + CONFIG_FILE);
			ImageMatrixGUI.getInstance().addObserver(this);
			ImageMatrixGUI.getInstance().go();
		} catch (FileNotFoundException e) {
			e.printStackTrace();			
		}
	}


	//read and create map
	
	private void readConfig(String fName) throws FileNotFoundException {
		Scanner scan;
		try{
			scan = new Scanner(new File(fName));
			int j=0;
			while(scan.hasNextLine() && j<10) {
				String line = scan.nextLine();

				for(int i =0; i !=10; i++) {
					String objeto = "" + line.charAt(i);
					lista.add(FireFightObject.create(objeto, i,j));
				}
				j++;
			}
			while(scan.hasNextLine()) {
				String line1 = scan.nextLine();
				String[] info = line1.split(" ");
				lista.add(FireFightObject.create(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2])));
			}		
			scan.close();
			for (FireFightObject ffo : lista)
				ImageMatrixGUI.getInstance().addImage(ffo);
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro nao Encontrado!");
		}
		try {
			fireman = getFireman();
			if(fireman == null) throw new IllegalStateException("Bombeiro null");			
		}catch (IllegalStateException e) {	
			fireman = new Fireman(new Point(9,8));
			lista.add(fireman);
			ImageMatrixGUI.getInstance().addImage(fireman);			
		}
	}
	
	//----------------------------------------------------------------------
	//GETTERS 
	
	
	public Fireman getFireman(){		
		for(FireFightObject ffo : lista)
			if(ffo instanceof Fireman)
				return (Fireman) ffo;
		return null;
	}
	
	public Bulldozer getBulldozer(){
		for(FireFightObject ffo : lista)
			if(ffo instanceof Bulldozer)
				return (Bulldozer) ffo;
		return null;
	}
	
	public ArrayList<Floresta> getFloresta() {
		ArrayList<Floresta> floresta = new ArrayList<>();
		for(FireFightObject f : lista) {
			if(f instanceof Floresta)
				floresta.add((Floresta)f);
		}
		return floresta;
	}
	
	public Floresta getFlorestaAt(Point coordVizinho) {
		ArrayList<Floresta> floresta = getFloresta();
		for(Floresta f : floresta) {
			if(f.getPosition().equals(coordVizinho)) 
				return f;
		}
		return null;
	}

	public ArrayList<Fire> getFogos(){
		ArrayList<Fire> fogos = new ArrayList<>();
		for(FireFightObject ffo : lista)
			if(ffo instanceof Fire)
				fogos.add((Fire)ffo);
		return fogos;
	}
	
	public Plane getPlane() {
		for(FireFightObject ffo : lista)
			if(ffo instanceof Plane)
				return (Plane) ffo;
		return null;
	}
	//------------------------------------------------------------------------------------
	//BOOLEANS
	
	private boolean thisPositionHasFire(Fire fire) {
		for(FireFightObject ffo : lista)
			if(ffo instanceof Fire)
				if(fire.getPosition().equals(ffo.getPosition()))
					return true;	
		return false;
	}
	
	
	public boolean thisPositionOnFire(Point point) {
		for(FireFightObject ffo : getFogos())
			if(point.equals(ffo.getPosition()))
					return true;	
		return false;
	}
	
	public boolean existPlane() {
		for(FireFightObject ffo : lista) 
			if(ffo instanceof Plane) 
				return true;		
		return false;
	}
	
	//--------------------------------------------------------------------------------
	//SETTERS
	
	public void insertFireObject(Fire fire) {
		if(thisPositionHasFire(fire)) 
			return;
		lista.add(fire);	
		ImageMatrixGUI.getInstance().addImage(fire);		
	}
	
	public void insertBurnt(Burnt burnt) {
		lista.add(burnt);	
		ImageMatrixGUI.getInstance().addImage(burnt);		
	}
	
	public void setPlane() {
		if(existPlane())
			return;
		else if(!getFogos().isEmpty()){ //não cria avião se não existirem fogos
			int x = countFire();
			plane = new Plane(new Point(x, 9));
			lista.add(plane);
			ImageMatrixGUI.getInstance().addImage(plane);
		}	
	}
	
	//-------------------------------------------------------------------------------------
	//REMOVES
	
	public void removeFire(Point p) {
		for(FireFightObject ffo : getFogos()) {
			if(p.equals(ffo.getPosition())) {
				lista.remove(ffo);
				ImageMatrixGUI.getInstance().removeImage(ffo);
			}
		}
	}
	
	public void removeFloresta(Point p) {
		for(FireFightObject ffo : getFloresta()) {
			if(p.equals(ffo.getPosition())) {
				lista.remove(ffo);
				ImageMatrixGUI.getInstance().removeImage(ffo);
			}
		}
	}
	
	public void removePlane(Plane plane) {
			lista.remove(plane);
			ImageMatrixGUI.getInstance().removeImage(plane);
	}
	
	//CONTAR FOGOS POR COLUNA
	
	public int countOcorr(int n) {
		int count = 0;
		for(Fire f : getFogos()) {
			if(f.getPosition().x==n)
				count ++;
		
		}
		return count;
	}
	
	//se alterares o tamanho do mapa esta função tem de ser alterada, o intervalo do i
	public int countFire() {
		int result = 0;
		int max_count = 1;
		for(int i = 0; i<10; i++) {
			int count = countOcorr(i);
			if(count >= max_count) {
				//max_count = count;
				result = i;
			}
		}
		return result;
	}
	
	
	/*
	@Override
	public void update(Observable arg0, Object arg1) {
		int key = (int)arg1;
		for(Fire spread : getFogos()) 
			spread.findFloresta();	
		for(Floresta floresta : getFloresta()) 
			floresta.burningFloresta();	
		if (existPlane()) {
			getPlane().updatePosition();
			
		}
		if (Direction.isDirection(key)) 
			fireman.move(Direction.directionFor(key));
		else if (key == KeyEvent.VK_A) 		
			setPlane();			
		else if (key == KeyEvent.VK_ENTER) {
			fireman.setOnTop(false);
			ImageMatrixGUI.getInstance().addImage(fireman);		
		}		
		ImageMatrixGUI.getInstance().update();		
	}*/

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//System.out.println("firesimulator: " + evt.getNewValue());
		int key = (int)evt.getNewValue();
		for(Fire spread : getFogos()) 
			spread.findFloresta();	
		for(Floresta floresta : getFloresta()) 
			floresta.burningFloresta();	
		if (existPlane()) {
			getPlane().updatePosition();
			
		}
		if (Direction.isDirection(key)) 
			fireman.move(Direction.directionFor(key));
		else if (key == KeyEvent.VK_A) 		
			setPlane();			
		else if (key == KeyEvent.VK_ENTER) {
			fireman.setOnTop(false);
			ImageMatrixGUI.getInstance().addImage(fireman);		
		}		
		ImageMatrixGUI.getInstance().update();		
	}

	

	
}
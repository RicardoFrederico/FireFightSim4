package oop.firefight.objects;

import java.awt.Point;

import oop.firefight.main.FireSimulator;
import oop.firefight.graphpack.ImageMatrixGUI;
import oop.firefight.graphpack.Direction;

/**
 * 
 * @author AlexandreSilva_69072_RicardoSilva_69454
 *
 */

public class Bulldozer extends FireFightObject implements Movable {
	
	private String lado = "bulldozer";
		
	/**
	 * 
	 * @param position representa uma localizacao nas coordenadas (x,y)
	 * 
	 */
	public Bulldozer(Point position) {
		super(position);
	}

	/**
	 * @return layer do objeto
	 */
	@Override
	public int getLayer() {
		return 4;
	}
	
	/**
	 * @return nome do objeto
	 */
	@Override
	public String getName() {
		return lado; 
	}
	
	/**
	 * 
	 * @param dir ,conforme a direcao, devolve o nome da direcao do bulldozer
	 */
	public void setTile(Direction dir) {
		switch (dir) {
		case DOWN:
			lado = "bulldozer_down";
			break;
		case UP:
			lado = "bulldozer_up";
			break;
		case RIGHT:
			lado = "bulldozer_right";
			break;
		case LEFT:
			lado = "bulldozer_left";
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * @param dir devolve a nova posicao do bulldozer
	 * Faz verificacoes da nova posicao:
	 * - Se a nova posicao tiver em fogo, nao permite o avanco
	 * - Se nao estiver dentro dos limites, nao permite o avanco
	 * Conforme a direcao, altera a imagem do objeto
	 * Se a nova posicao for do tipo Floresta, remove o objeto floresta e substitui por um objeto Land
	 * 
	 */
	@Override
	public void move(Direction dir) {	
		Point atual = getPosition();
		Point newPosition = new Point (atual.x + dir.asVector().x, atual.y + dir.asVector().y);
		boolean onFire = FireSimulator.getInstance().thisPositionOnFire(newPosition);
		if(onFire == false) {
			if(inBounds(newPosition)) 
				setPosition(newPosition);
			
			setTile(dir);
			FireSimulator.getInstance().removeFloresta(atual);
			Land land = new Land(atual);
			ImageMatrixGUI.getInstance().addImage(land);
		}		
	}
	
	
	
		
}

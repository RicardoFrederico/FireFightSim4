package oop.firefight.objects;

import java.awt.Point;

import oop.firefight.main.FireSimulator;

public abstract class Floresta extends FireFightObject implements Inflamavel{

	private int contador=0;
	
	public Floresta(Point position) {
		super(position);
		
	}
	
	public abstract double getProb();
	
	public abstract int getBurnt();	
	
	@Override
	public void burn() {		
		double valor = Math.random();
			if(valor < getProb()) {
				Fire n = new Fire(getPosition());				
				FireSimulator.getInstance().insertFireObject(n);
			}
	}

	@Override
	public void burnt() {
		Burnt burnt = new Burnt(getPosition());
		FireSimulator.getInstance().insertBurnt(burnt);	
		FireSimulator.getInstance().removeFire(getPosition());
		FireSimulator.getInstance().removeFloresta(getPosition());
	}

	public void burningFloresta() {
		if(FireSimulator.getInstance().thisPositionOnFire(getPosition())) {
			if(contador == getBurnt())
				burnt();
			contador ++;
		}
	}

	
}
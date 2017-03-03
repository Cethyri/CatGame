package edu.neumont.csc150.finalProject;

import javax.swing.JLabel;

public class Attack extends JLabel implements TickListener {
	
	public final double dx, dy, posX, posY, dxEffect, dyEffect;
	
	public final int damage;
	
	public Attack(double dx, double dy, double posX, double posY, double dxEffect, double dyEffect, int damage) {
		this.dx = dx;
		this.dy = dy;
		this.posX = posX;
		this.posY = posY;
		this.dxEffect = dxEffect;
		this.dyEffect = dyEffect;
		this.damage = damage;
	}

	@Override
	public void doTick() {
		// TODO Auto-generated method stub
		
	}
}

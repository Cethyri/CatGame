package edu.neumont.csc150.finalProject;

import java.util.ArrayList;

import javax.swing.JLabel;

public class Attack extends JLabel implements TickListener {
	
	public final double dxEffect, dyEffect;
	public final int damage;
	
	public final PlayerID owner;
	
	private ArrayList<PlayerID> hasBeenHit;
	
	public Attack(double dxEffect, double dyEffect, int damage, PlayerID owner, int width, int height) {
		this.dxEffect = dxEffect;
		this.dyEffect = dyEffect;
		this.damage = damage;
		this.owner = owner;
		
		setSize(width, height);
	}
	
	public void addHasBeenHit(PlayerID id) {
		hasBeenHit.add(id);
	}
	
	public boolean hasHit(PlayerID id) {
		
		return hasBeenHit.contains(id);
	}

	@Override
	public void doTick() {
		// TODO Auto-generated method stub
		
	}
}

package edu.neumont.csc150.finalProject;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Attack extends JLabel implements TickListener {
	
	private double dxEffect, dyEffect;
	public final int damage;
	
	public final PlayerID owner;
	
	private String direction;
	
	private ArrayList<PlayerID> hasBeenHit;
	
	public Attack(double dxEffect, double dyEffect, int damage, PlayerID owner, String direction, int width, int height) {
		this.dxEffect = dxEffect;
		this.dyEffect = dyEffect;
		this.damage = damage;
		this.owner = owner;
		
		initVars(direction);
		initUI(500, 500, width, height);
	}
	
	private void initVars(String direction) {
		this.direction = direction;
		
		hasBeenHit = new ArrayList<>();
	}

	private void initUI(int x, int y, int width, int height) {
		setOpaque(true);
		setBackground(Color.RED);
		setBounds(x, y, width, height);
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

	public double getDxEffect(Rectangle toEffect) {
		return dxEffect;
	}

	public double getDyEffect(Rectangle toEffect) {
		return dyEffect;
	}

	public void updatePosition(Cat owner) {
		int x, y;
		
		direction = owner.getDirection();
		
		x = owner.getIntPosX() + (direction.equals("left") ? -this.getWidth() : owner.getWidth());
		y = owner.getIntPosY() + 32;
		
		this.setLocation(x, y);
	}
}

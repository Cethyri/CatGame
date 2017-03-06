package edu.neumont.csc150.finalProject;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class Surface extends JLabel implements Collidable {
	
	public static int RESOLVE_VAL = 4;
	
	public Surface (int x, int y, int width, int height) {
		initUI(x, y, width, height);
	}

	private void initUI(int x, int y, int width, int height) {
		setOpaque(true);
		setBackground(Color.BLACK);
		setBounds(x, y, width, height);
	}

	@Override
	public void checkForCollisions(ArrayList<Collidable> surfaces, boolean resolve) {
		
	}

	@Override
	public boolean doVerticalCollisionResolution() {
		return true;
	}

	@Override
	public boolean doHorizontalCollisionResolution() {
		return true;
	}

	@Override
	public void resolveCollision(Collidable collidable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getResolveVal() {
		return RESOLVE_VAL;
	}
	
	@Override
	public boolean isGround() {
		return true;
	}
}

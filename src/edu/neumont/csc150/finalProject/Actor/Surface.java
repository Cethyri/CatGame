package edu.neumont.csc150.finalProject.Actor;

import java.awt.Color;
import javax.swing.JLabel;

@SuppressWarnings("serial")
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
	public double getResolveVal() {
		return RESOLVE_VAL;
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
	public boolean isGround() {
		return true;
	}
}

package edu.neumont.csc150.finalProject;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class Surface extends JLabel implements Collidable {
	
	public Surface (int x, int y, int width, int height) {
		setOpaque(true);
		
		setBounds(x, y, width, height);
	}

	@Override
	public void checkForCollisions(ArrayList<Collidable> surfaces) {
		
	}

	@Override
	public boolean isMovable() {
		return false;
	}
}

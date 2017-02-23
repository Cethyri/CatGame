package edu.neumont.csc150.finalProject;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class Surface extends JLabel implements Collidable {
	
	public Surface (int x, int y, int width, int height) {
		setOpaque(true);
		
		setBounds(x, y, width, height);
	}
}

package edu.neumont.csc150.finalProject;

import javax.swing.JComponent;

public class Surface extends JComponent implements Collidable {
	
	public Surface (int x, int y, int width, int height) {
		setBounds(x, y, width, height);
	}
}

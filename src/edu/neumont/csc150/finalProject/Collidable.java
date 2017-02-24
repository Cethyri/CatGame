package edu.neumont.csc150.finalProject;

import java.awt.Rectangle;
import java.util.ArrayList;

public interface Collidable {
	
	public Rectangle getBounds();
	
	public void checkForCollisions(ArrayList<Rectangle> surfaces);
}

package edu.neumont.csc150.finalProject;

import java.awt.Rectangle;
import java.util.ArrayList;

public interface Collidable {
	
	public Rectangle getBounds();
	
	public void checkForCollisions(ArrayList<Collidable> surfaces, boolean resolve);
	
	public boolean doVerticalCollisionResolution();
	public boolean doHorizontalCollisionResolution();
	
	public void resolveCollision(Collidable collidable);
	public double getResolveVal();
}

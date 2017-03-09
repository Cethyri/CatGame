package edu.neumont.csc150.finalProject;

import java.util.ArrayList;

public interface Moveable {

	public void checkForCollisions(ArrayList<Collidable> surfaces, boolean resolve);
	public void resolveCollision(Collidable collidable);
}

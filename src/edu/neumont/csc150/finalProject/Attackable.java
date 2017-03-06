package edu.neumont.csc150.finalProject;

import java.util.ArrayList;

public interface Attackable {

	public void receiveAttack(Attack atk);
	
	public void checkForAttack(ArrayList<Attack> attacks);
	
}

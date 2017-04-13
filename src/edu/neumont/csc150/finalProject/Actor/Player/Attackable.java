package edu.neumont.csc150.finalProject.Actor.Player;

import java.util.ArrayList;

public interface Attackable {

	/**
	 * checks for intersecting attacks and acts upon them
	 * 
	 * @param attacks
	 */
	public void checkForAttack(ArrayList<Attack> attacks);
	
	/**
	 * receive and act on an intersecting attack
	 * 
	 * @param atk is the attack to act upon
	 */
	public void receiveAttack(Attack atk);
	
}

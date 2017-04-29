package edu.neumont.csc150.finalProject.Controller;

import java.net.InetAddress;

import edu.neumont.csc150.finalProject.Actor.Player.PlayerID;

public class ControlHandler {
	public final InetAddress IPAddress;
	public final PlayerID ID;
	
	public ControlHandler(InetAddress IPAddress, PlayerID ID) {
		this.IPAddress = IPAddress;
		this.ID = ID;
	}
	
}

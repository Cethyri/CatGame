package edu.neumont.csc150.finalProject;

import java.net.InetAddress;

public class ControlHandler {
	public final InetAddress IPAddress;
	public final int ID;
	
	public ControlHandler(InetAddress IPAddress, int ID) {
		this.IPAddress = IPAddress;
		this.ID = ID;
	}
	
}

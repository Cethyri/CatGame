package edu.neumont.csc150.finalProject;

import java.net.InetAddress;

public class PacketHandler {
	public final InetAddress IPAddress;
	public final int ID;
	
	private String input;
	
	public PacketHandler(InetAddress IPAddress, int ID) {
		this.IPAddress = IPAddress;
		this.ID = ID;
		
		
	}
	
}

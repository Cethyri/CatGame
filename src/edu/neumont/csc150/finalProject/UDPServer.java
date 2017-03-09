package edu.neumont.csc150.finalProject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Timer;

public class UDPServer implements Runnable{
	
	private byte[] receiveData;
	private byte[] sendData;
	private DatagramSocket serverSocket;
	
	private PacketHandler[] packetHandlers;
	private int assignIDs = 0;
	private String buttonInput;
	
	public UDPServer() throws Exception {
		
		initVars();
		
		System.out.println("UDP Server Started........");
		Thread serverThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {			
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

					//receives any data
					try {
						serverSocket.receive(receivePacket);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//gets rid of excess string
					buttonInput = new String( receivePacket.getData()).substring(0, receivePacket.getLength());
					
					System.out.println("RECEIVED: " + buttonInput);			
					
					//gets IP address of incoming packets
					InetAddress IPAddress = receivePacket.getAddress();
					System.out.println(IPAddress);
					
					//gets port from incoming packets
					int port = receivePacket.getPort();

					String id = Integer.toString(assignIDs);
					sendData = id.getBytes();

					if(assignIDs < PlayerID.values().length ) {
						
						assignIDs++;
						
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

						try {
							serverSocket.send(sendPacket);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		serverThread.start();
//		while(true) {			
//			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//
//			//receives any data
//			serverSocket.receive(receivePacket);
//			
//			//gets rid of excess string
//			buttonInput = new String( receivePacket.getData()).substring(0, receivePacket.getLength());
//			
//			System.out.println("RECEIVED: " + buttonInput);			
//			
//			//gets IP address of incoming packets
//			InetAddress IPAddress = receivePacket.getAddress();
//			System.out.println(IPAddress);
//			
//			//gets port from incoming packets
//			int port = receivePacket.getPort();
//
//			String id = Integer.toString(assignIDs);
//			sendData = id.getBytes();
//
//			if(assignIDs < PlayerID.values().length ) {
//				
//				assignIDs++;
//				
//				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
//
//				serverSocket.send(sendPacket);
//			}
//		}
	}
	
	private void initVars() throws SocketException {
		serverSocket = new DatagramSocket(5555);
		
		receiveData = new byte[10];
		sendData = new byte[10];
		
		packetHandlers = new PacketHandler[PlayerID.values().length];
	}

	@Override
	public void run() {
		try {
			UDPServer s = new UDPServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
}


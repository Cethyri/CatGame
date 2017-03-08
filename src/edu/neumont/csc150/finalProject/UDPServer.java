package edu.neumont.csc150.finalProject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
	
	private static int assignIDs = 0;
	private static String buttonInput;
	
	
	public static void main(String args[]) throws Exception {
		
		DatagramSocket serverSocket = new DatagramSocket(5555);
				
		byte[] receiveData = new byte[10];
		byte[] sendData = new byte[10];
		
		System.out.println("UDP Server Started........");
		
		while(true) {			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

			serverSocket.receive(receivePacket);
			
			
			buttonInput = new String( receivePacket.getData());
			
			System.out.println("RECEIVED: " + buttonInput.trim());			
			

			InetAddress IPAddress = receivePacket.getAddress();
			System.out.println(IPAddress);
			
			int port = receivePacket.getPort();

			String capitalizedSentence = Integer.toString(assignIDs);
			sendData = capitalizedSentence.getBytes();

//			if(assignIDs < 2) {

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

				serverSocket.send(sendPacket);
				assignIDs++;
//			}
		}
	}
	
}

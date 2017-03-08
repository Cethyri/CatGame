package edu.neumont.csc150.finalProject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
	
	private static int assignIDs = 0;
	private static int id = -1;
	private static String buttonInput;
	
	
	public static void main(String args[]) throws Exception {
		
		DatagramSocket serverSocket = new DatagramSocket(5555);
				
		byte[] receiveData = new byte[10];
//		byte[] idData = new byte[1];
		byte[] sendData = new byte[10];
		
		System.out.println("UDP Server Started........");
		
		while(true) {			
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//			DatagramPacket idReceived = new DatagramPacket(idData, idData.length);

			//receives any data
//			serverSocket.receive(idReceived);
			serverSocket.receive(receivePacket);
			
			//gets rid of excess string
			buttonInput = new String( receivePacket.getData()).substring(0, receivePacket.getLength());
			
			
//			try {
//				id = new Integer(new String(idReceived.getData() ) );
//				
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			}
			
			System.out.println("RECEIVED: " + id + "\t" + buttonInput);			
			
			//gets IP address of incoming packets
			InetAddress IPAddress = receivePacket.getAddress();
			System.out.println(IPAddress);
			
			//gets port from incoming packets
			int port = receivePacket.getPort();

			String capitalizedSentence = Integer.toString(assignIDs);
			sendData = capitalizedSentence.getBytes();

//			if(assignIDs < 4 ) {

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

				serverSocket.send(sendPacket);
//				assignIDs++;
//			}
		}
	}
	
}


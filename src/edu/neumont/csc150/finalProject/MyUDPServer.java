package edu.neumont.csc150.finalProject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;

public class MyUDPServer {

	
	public static void main(String args[]) throws Exception {
		
		DatagramSocket serverSocket = new DatagramSocket(5555);
		
		
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		System.out.println("UDP Server Started........");
		
		while(true) {
			DatagramPacket receivePacket = null;
			
			receivePacket.setData(null);
			
			receivePacket = new DatagramPacket(receiveData, receiveData.length);

			serverSocket.receive(receivePacket);
			
			System.out.println(receivePacket.getLength());
			
			String buttonInput = new String( receivePacket.getData());
			
			System.out.println("RECEIVED: " + buttonInput);

			InetAddress IPAddress = receivePacket.getAddress();
			System.out.println(IPAddress);
			
			int port = receivePacket.getPort();

			String capitalizedSentence = buttonInput.toUpperCase();
			sendData = capitalizedSentence.getBytes();

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

			serverSocket.send(sendPacket);
			
		}
	}
}

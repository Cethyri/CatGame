package edu.neumont.csc150.finalProject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MyUDPServer {

	public static void main(String args[]) throws Exception {
		
		DatagramSocket serverSocket = new DatagramSocket(4444);
		
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		System.out.println("UDP Server Started");
		
		while(true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

			serverSocket.receive(receivePacket);
			System.out.println("serversocket recieved packet......");
			
			String buttoninput = new String( receivePacket.getData());
			
			System.out.println("RECEIVED: " + buttoninput);

			InetAddress IPAddress = receivePacket.getAddress();
			System.out.println(IPAddress);
			
			int port = receivePacket.getPort();

			String capitalizedSentence = buttoninput.toUpperCase();
			sendData = capitalizedSentence.getBytes();

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

			serverSocket.send(sendPacket);
		}
	}
}

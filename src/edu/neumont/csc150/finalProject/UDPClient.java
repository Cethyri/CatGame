package edu.neumont.csc150.finalProject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {

	private static Scanner s;
	private static DatagramSocket clientSocket;
	private static InetAddress IPAddress;
	private static byte[] sendData;
	private static byte[] receiveData;
	private static String sentence;
	private static DatagramPacket sendPacket;
	private static DatagramPacket receivePacket;
	private static String modifiedSentence;
	
	private static int id = 99;

	public static void main(String args[]) throws Exception {
		s = new Scanner(System.in);
		clientSocket = new DatagramSocket();
		sendData = new byte[1024];
		receiveData = new byte[1024];
		IPAddress = InetAddress.getByName("localhost");

		while (true) {
			sentence = s.nextLine();
			sendData = sentence.getBytes();
			
			if (sentence.equalsIgnoreCase("close")) {
				clientSocket.close();
			}
			
			sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5555);
			clientSocket.send(sendPacket);
			
			if (id == 99) {
				receivePacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket);
				modifiedSentence = new String(receivePacket.getData()).substring(0, receivePacket.getLength());
				id = Integer.parseInt(modifiedSentence);
				System.out.println("FROM SERVER:" + modifiedSentence);	
			} else {
				System.out.println("send");
			}
			
		}

	}

}

package edu.neumont.csc150.finalProject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {


	   public static void main(String args[]) throws Exception {
	      Scanner s = new Scanner(System.in);
	      DatagramSocket clientSocket = new DatagramSocket();
//	      InetAddress IPAddress = InetAddress.getByName("192.168.43.213");
	      InetAddress IPAddress = InetAddress.getByName("192.168.43.7");
	      byte[] sendData = new byte[1024];
	      byte[] receiveData = new byte[1024];
	      String sentence = s.nextLine();
	      sendData = sentence.getBytes();
	      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5555);
	      clientSocket.send(sendPacket);
	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	      clientSocket.receive(receivePacket);
	      String modifiedSentence = new String(receivePacket.getData());
	      System.out.println("FROM SERVER:" + modifiedSentence);
	      clientSocket.close();
	   }
	
}

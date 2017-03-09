package edu.neumont.csc150.finalProject;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class UDPServer {

	private byte[] receiveData, sendData;
	private DatagramSocket serverSocket;
	protected DatagramPacket receivePacket;
	private InetAddress IPAddress;

	private ArrayList<ControlHandler> controlHandlers;
	private String buttonInput;
	private Thread assignThread, controlThread;

	public UDPServer() throws Exception {

		initVars();

		System.out.println("UDP Server Started........");

		assignThread = new Thread(new Runnable() {

			@Override
			public void run() {

				int assignIDs = 0;
				boolean assigned;

				do {
					assigned = false;

					recievePacket();


					if (buttonInput.equals("give")) {						
						
						for (ControlHandler controlHandler : controlHandlers) {
							if (controlHandler != null) {
								if (controlHandler.IPAddress.equals(IPAddress)) {
									assigned = true;
								}
							}
						}

						if (!assigned) {
							// gets port from incoming packets
							int port = receivePacket.getPort();

							String id = Integer.toString(assignIDs);
							sendData = id.getBytes();


							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

							try {
								serverSocket.send(sendPacket);

								controlHandlers.add(new ControlHandler(IPAddress, assignIDs));
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							assignIDs++;
						}
					}
				} while (assignIDs < Game.getPlayerCount());

				controlThread.start();
			}
		});

		controlThread = new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {
					recievePacket();
					for (ControlHandler controlHandler : controlHandlers) {
						if (controlHandler != null) {
							if (controlHandler.IPAddress.equals(IPAddress)) {
								String[] readInput = buttonInput.toLowerCase().split("_", 2);
								int keyCode = PlayerID.translate(readInput[1], controlHandler.ID);
								int eventType = readInput[0].equals("pressed") ? KeyEvent.KEY_PRESSED : (readInput[0].equals("released") ? KeyEvent.KEY_RELEASED : KeyEvent.KEY_TYPED);
								KeyEvent kE = new KeyEvent(MainFrame.game, eventType, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
								
								MainFrame.game.dispatchToVisible(kE);
//								switch (readInput[0]) {
//								case "pressed":
//									MainFrame.game.keyPressed(kE);
//									break;
//								case "released":
//									MainFrame.game.keyReleased(kE);
//									break;
//								case "typed":
//									break;
//								default:
//									break;
//								}
							}
						}
					}
				}
			}
		});

		assignThread.start();
	}

	private void initVars() throws SocketException {
		serverSocket = new DatagramSocket(5555);

		receiveData = new byte[10];
		sendData = new byte[10];
		receiveData = new byte[20];
		sendData = new byte[20];

		controlHandlers = new ArrayList<>();
	}

	private void recievePacket() {
		receivePacket = new DatagramPacket(receiveData, receiveData.length);

		// receives any data
		try {
			serverSocket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// gets rid of excess string
		buttonInput = new String(receivePacket.getData()).substring(0, receivePacket.getLength());
		System.out.println("RECEIVED: " + buttonInput);

		// gets IP address of incoming packets
		IPAddress = receivePacket.getAddress();
		System.out.println("FROM" + IPAddress);
	}
}

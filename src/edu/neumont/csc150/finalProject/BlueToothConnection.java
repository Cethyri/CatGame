package edu.neumont.csc150.finalProject;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;

public class BlueToothConnection{
	
	private Object lock = new Object();
	private LocalDevice localDevice = null;
	private DiscoveryAgent agent = null;
	UUID[] uuidSet = new UUID[1];
	
	
	public BlueToothConnection() {
		getConnection();
		
	}
	
	public void getConnection() {
//		uuidSet[0] = new UUID(0000110100001000800000805f9b34fb);
		
		try {
			localDevice = LocalDevice.getLocalDevice();
			
			agent = localDevice.getDiscoveryAgent();
			
			System.out.println("start searching for device");
			
			agent.startInquiry(DiscoveryAgent.GIAC, new DiscoveryListener() {
				
				@Override
				public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
					System.out.println("Service Discovered....");
					
				}
				
				@Override
				public void serviceSearchCompleted(int arg0, int arg1) {
					// TODO Auto-generated method stub
					System.out.println("search complete.....");
				}
				
				@Override
				public void inquiryCompleted(int arg0) {
					System.out.println("inquiry Completed.....");
					synchronized(lock) {
						lock.notify();
					}
					
				}
				
				@Override
				public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1) {
					String name, RDName, DCClass;
					
					try {
						RDName = arg0.getFriendlyName(true);
						name = localDevice.getFriendlyName();
						DCClass = "" + arg1.getMajorDeviceClass();
						
						
					} catch (Exception e) {
						RDName = arg0.getBluetoothAddress();
						name = localDevice.getBluetoothAddress();
						DCClass = "" + arg1.getMinorDeviceClass();
						System.out.println("caught exception");
					}
					if (RDName == "SAMSUNG-SM-J320V") {
						try {
							arg0.authenticate();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					System.out.println("Device Discovered " + RDName + " of class: " + DCClass);
				}
			});
			
			
			try{
				System.out.println("Waiting");
				synchronized (lock) {
					lock.wait();
				}
				
			} catch (InterruptedException e) {	
				System.out.println("done");
				e.printStackTrace();
				
			}
			
//			agent.searchServices(null,uuidSet,device, new MyDiscoveryListener());
			
		} catch (BluetoothStateException e) {
			e.printStackTrace();			
			
		}
		
	}
	
	
	
	
	

	public static void main(String[] args) {
		
		BlueToothConnection b = new BlueToothConnection();
		
	}
}

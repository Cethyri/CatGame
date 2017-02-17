package edu.neumont.csc150.finalProject;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

public class BlueToothConnection{
	
	private Object lock = new Object();
	private LocalDevice local;
	private DiscoveryAgent agent;
	
	
	public BlueToothConnection() {
		this.getConnection();
		
	}
	
	public void getConnection() {
		
		try {
			local = LocalDevice.getLocalDevice();
			
			agent = local.getDiscoveryAgent();
			
			System.out.println("start searching for device");
			
			agent.startInquiry(DiscoveryAgent.GIAC, new DiscoveryListener() {
				
				@Override
				public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void serviceSearchCompleted(int arg0, int arg1) {
					// TODO Auto-generated method stub
					System.out.println("search complete");
				}
				
				@Override
				public void inquiryCompleted(int arg0) {
					
					synchronized(lock) {
						lock.notify();
					}
					
				}
				
				@Override
				public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1) {
					String name;
					
					try {
						name = local.getFriendlyName();
						
					} catch (Exception e) {
						name = local.getBluetoothAddress();

					}
					
					System.out.println("Device Found " + name);
				}
			});
			
			try{
				System.out.println("locking....");
				lock.wait();
				
			} catch (InterruptedException e) {	
				System.out.println("done");
				e.printStackTrace();
				
			}
			
		} catch (BluetoothStateException e) {
			e.printStackTrace();
			System.out.println("No Connection");			
			
		}
		
	}
	
	
	
	
	

	public static void main(String[] args) {
		BlueToothConnection b = new BlueToothConnection();
		
	}
}

package edu.neumont.csc150.finalProject;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

public class BlueToothConnection{
	
	private LocalDevice local;
	private DiscoveryAgent agent;
	private Object lock = new Object();
	
	
	public BlueToothConnection() {
		getConnection();
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
					
				}
				
				@Override
				public void inquiryCompleted(int arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1) {
					// TODO Auto-generated method stub
					
				}
			});
			
			try{
				
				lock.wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} catch (BluetoothStateException e) {
			System.out.println("No Connection");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	

	public static void main(String[] args) {
		BlueToothConnection b = new BlueToothConnection();
	}
}

package edu.neumont.csc150.finalProject;

import java.util.ArrayList;

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
	private ArrayList<RemoteDevice> devices;
	
	private LocalDevice localDevice = null;
	private DiscoveryAgent agent = null;
	
	
	public BlueToothConnection() {
		getConnection();
		
	}
	
	public void getConnection() {
		devices = new ArrayList<RemoteDevice>();
		
		
		
		try {
			localDevice = LocalDevice.getLocalDevice();
			
			agent = localDevice.getDiscoveryAgent();
			
			System.out.println("start searching for device");
			
			agent.startInquiry(DiscoveryAgent.GIAC, new MyListener());
			
			
			try{
				System.out.println("Waiting");
				synchronized (lock) {
					lock.wait();
				}
				
			} catch (InterruptedException e) {	
				System.out.println("done");
				e.printStackTrace();
				
			}
			
			
		} catch (BluetoothStateException e) {
			e.printStackTrace();			
			
		}
		
	}
	
	
	public class MyListener implements DiscoveryListener{
		
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
			String name;
			
			try {
				name = localDevice.getFriendlyName();
				
			} catch (Exception e) {
				name = localDevice.getBluetoothAddress();

			}
			
			System.out.println("Device Discovered " + name);
		}
		
	}
	
	

	public static void main(String[] args) {
		
		BlueToothConnection b = new BlueToothConnection();
		
	}
}

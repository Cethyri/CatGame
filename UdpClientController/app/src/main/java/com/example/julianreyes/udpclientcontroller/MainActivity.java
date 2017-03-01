package com.example.julianreyes.udpclientcontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private static final String host = "localhost";
    private int port;
    private String str = null;
    private byte[] send_data = new byte[1024];
    private byte[] receiveData = new byte[1024];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            DatagramSocket client_socket = new DatagramSocket(4444);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            InetAddress IPAddress =  InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public void sendUp(View view) {
        str = "up";
    }

    public void sendDown(View view) {
        str = "down";
        try {
            runUdpClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRight(View view) {
        str = "right";
        try {
            runUdpClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendLeft(View view) {
        str = "left";
        try {
            runUdpClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAttack(View view) {
        str = "attack";
        try {
            runUdpClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSpecial(View view) {
        str = "special";
        try {
            runUdpClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runUdpClient() throws IOException {
//        DatagramSocket client_socket = new DatagramSocket(4444);
//        InetAddress IPAddress =  InetAddress.getByName("10.0.16.1");
//        InetAddress IPAddress =  InetAddress.getLocalHost();

        send_data = str.getBytes();


        DatagramPacket send_packet = new DatagramPacket(send_data,str.length(), IPAddress, 4444);
        client_socket.send(send_packet);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        client_socket.receive(receivePacket);

        client_socket.close();
    }
}
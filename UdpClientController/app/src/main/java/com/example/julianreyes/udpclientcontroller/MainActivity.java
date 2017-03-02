package com.example.julianreyes.udpclientcontroller;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    private static final String host = "localhost";
    private int port = 4444;
    private String str = null;
    private String sendString = null;
    private boolean sendUdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendRight(View view) {
        str = "right";
        sendUDP(str);
    }


    public void sendLeft(View view) {
        str = "left";
        sendUDP(str);
    }

    public void sendAttack(View view) {
        str = "attack";
        sendUDP(str);
    }

    public void sendSpecial(View view) {
        str = "special";
        sendUDP(str);
    }

    private void sendUDP(String s) {
        sendString = s;
        sendUdp = true;
        udpSendThread.run();
    }
    Thread udpSendThread = new Thread(new Runnable() {

        @Override
        public void run() {

            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

//            while (sendUdp) {

                try {
                    Thread.sleep(500);
                }

                catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                if (sendUdp == true) {

                    try {

                        // get server name
                        InetAddress serverAddr = InetAddress.getByName(host);
                        Log.d("UDP", "C: Connecting...");

                        // create new UDP socket
                        DatagramSocket socket = new DatagramSocket();

                        // prepare data to be sent
                        byte[] buf = sendString.getBytes();

                        // create a UDP packet with data and its destination ip & port
                        DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, port);
                        Log.d("UDP", "C: Sending: '" + new String(buf) + "'");

                        // send the UDP packet
                        socket.send(packet);

                        socket.close();

                        Log.d("UDP", "C: Sent.");
                        Log.d("UDP", "C: Done.");


                    }

                    catch (Exception e) {
                        Log.e("UDP", "C: Error", e);

                    }

                    try {
                        Thread.sleep(500);
                    }

                    catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                    sendUdp = false;
                }

            }
//        }

    });

}
package com.example.julianreyes.udpclientcontroller;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {
    //Home network
//    private static final String host = "192.168.1.255";
    private int port = 5555;
    //mobile hotspot
    private static final String host = "192.168.43.213";

    private int id = 99;
    private String str;
    private String sendString;

    private byte[] buf;
    private byte[] newBuf = new byte[10];

    private boolean sendUdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void radioClicked(View view) {
        Log.d("-", "radio clicked");
        if(id == 99){
            str = "give id";
            sendUDP(str);
            Log.d("UDP", "requested id");
            if(id != 99){
                ((RadioButton) view).setChecked(true);
                Log.d("UDP", "received id");
            }else {
                ((RadioButton) view).setChecked(false);
                Log.d("UDP", "did not receive id");
            }
        }

    }

    public void sendUp(View view) {
        str = "up";
        sendUDP(str);
    }

    public void sendDown(View view) {
        str = "dwn";
        sendUDP(str);
    }

    public void sendRight(View view) {
        str = "rit";
        sendUDP(str);
    }


    public void sendLeft(View view) {
        str = "lft";
        sendUDP(str);
    }

    public void sendAttack(View view) {
        str = "atk";
        sendUDP(str);
    }

    public void sendSpecial(View view) {
        str = "spl";
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

                try {
                    Thread.sleep(100);
                }

                catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                if (sendUdp == true) {

                    try {


                        // get server name
                        InetAddress serverAddr = InetAddress.getByName(host);

                        Log.d("UDP", "C: Connecting..."+serverAddr.getHostName());

                        // create new UDP socket
                        DatagramSocket socket = new DatagramSocket();

                        if(id != 99) {
                            // prepare data to be sent
                            byte[] buf = sendString.getBytes();

                            // create a UDP packet with data and its destination ip & port
                            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, port);

                            Log.d("UDP", "C: Sending: '" + new String(buf) + "' of size: " + buf.length);

                            // send the UDP packet
                            socket.send(packet);

                            Log.d("UDP", "C: Sent.");
                            Log.d("UDP", "C: Done.");
                        } else {
                            // prepare data to be sent
                            byte[] buf = sendString.getBytes();

                            // create a UDP packet with data and its destination ip & port
                            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, port);

                            Log.d("UDP", "C: Sending: '" + new String(buf) + "' of size: " + buf.length);

                            // send the UDP packet
                            socket.send(packet);

                            Log.d("UDP", "C: Sent.");
                            Log.d("UDP", "C: Done.");

                            //create new UDP packet to receive from server
                            DatagramPacket serverPacket = new DatagramPacket(newBuf, newBuf.length);

                            //receives UDP packet
                            socket.receive(serverPacket);

                            String receivedPacket = new String(serverPacket.getData());

//                        Log.d("UDP", "C: Received." + receivedPacket);
                            try {
                                id = Integer.parseInt(receivedPacket.trim());

                            } catch (NumberFormatException nfe) {
//                            Log.e("UDP", "C: Error", nfe);
                            }
                            //closes socket
                            socket.close();

                            Log.d("UDP", "C: Received. " + id);
                            Log.d("UDP", "C: Done.");
                        }

                    }

                    catch (Exception e) {
                        Log.e("UDP", "C: Error", e);

                    }

                    try {
                        Thread.sleep(100);
                    }

                    catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    sendUdp = false;
                }

            }
    });

}
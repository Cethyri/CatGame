package com.example.julianreyes.udpclientcontroller;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class MainActivity extends AppCompatActivity {
    //Home network
//    private static final String host = "192.168.1.12";

    private int port = 5555;
    //mobile hotspot
    private static final String host = "192.168.43.213";

    private int id = 99;
    private String str;
    private String sendString;
    private String receivedPacket;

    private DatagramPacket serverPacket;
    private byte[] buf;
    private byte[] receivingBuf = new byte[1];
    private boolean send;

    private Button up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        up = (Button) findViewById(R.id.UP_id);
    }
    public void radioClicked(View view) {
        Log.d("UDP", "radio clicked");
        if(id == 99){
            str = "give";
            Log.d("UDP", "request id.... " + id);
            sendUDP(str);

            if(id != 99){
                ((RadioButton) view).setChecked(true);
                Log.d("UDP", "received id....");

            }else {
                ((RadioButton) view).setChecked(false);
                Log.d("UDP", "did not receive id");
            }
        }

    }
//
//    public void sendUp(View view) {
//        up.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch(event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        Log.d("Pressed", "pressed");
//                        str = "pressed_UP";
//                        sendUDP(str);
//                        return true;
//                    case MotionEvent.ACTION_UP:
//                        Log.d("Released", "released");
//                        str = "released_UP";
//                        sendUDP(str);
//                        return true;
//                }
//                return false;
//            }
//        });
//    }

    public void sendDown(View view) {
        str = "down";
        sendUDP(str);
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
            send = true;
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

                if (send == true) {

                    try {
                        // get server name
                        InetAddress serverAddr = InetAddress.getByName(host);

                        Log.d("UDP", "C: Connecting.... " + serverAddr.getHostName());

                        // create new UDP socket
                        DatagramSocket socket = new DatagramSocket();

                        if(id == 99) {
                            // prepare data to be sent
                            buf = sendString.getBytes();

                            // create a UDP packet with data and its destination ip & port
                            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, port);

                            Log.d("UDP", "C: Sending: '" + new String(buf) + "'");

                            // send the UDP packet
                            socket.send(packet);

                            Log.d("UDP", "C: Sent.");
                            Log.d("UDP", "C: Done.");
                            Log.d("UDP", "C: Attempt To Receive.");

                            //create new UDP packet to receive from server
                            serverPacket = new DatagramPacket(receivingBuf, receivingBuf.length);

                            //receives UDP packet
                            socket.receive(serverPacket);

                            receivedPacket = new String(serverPacket.getData());

                            try {
                                id = Integer.parseInt(receivedPacket.trim());
                                Log.d("UDP", "C: Received. " + id);
                                Log.d("UDP", "C: Done.");

                            } catch (NumberFormatException nfe) {
                                Log.e("UDP", "C: Error", nfe);
                            }
                            //closes socket
                            socket.close();

                        } else {
                            // prepare data to be sent
                            buf = sendString.getBytes();

                            // create a UDP packets with data and its destination ip & port
                            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, port);

                            Log.d("UDP", "C: Sending: '" +  new String(buf) + "'");

                            // send the UDP packet
                            socket.send(packet);

                            Log.d("UDP", "C: Sent.");
                            Log.d("UDP", "C: Done.");

                        }

                    } catch (Exception e) {
                        Log.e("UDP", "C: Error", e);

                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    send = false;
                }

            }
    });

}
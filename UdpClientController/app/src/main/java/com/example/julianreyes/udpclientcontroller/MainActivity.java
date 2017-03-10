package com.example.julianreyes.udpclientcontroller;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class MainActivity extends AppCompatActivity {
    //Home network
//    private String host = "192.168.1.12";
    private String host = "";
    private int port = 5555;
    //mobile hotspot
//    private String host = "192.168.43.213";
//    private String host = "192.168.43.7";

    private int id = 99;
    private String str;
    private String sendString;
    private String receivedPacket;

    private DatagramPacket serverPacket;
    private DatagramSocket socket;
    private byte[] buf;
    private byte[] receivingBuf = new byte[1];
    private boolean send;
    private boolean isUDPConnecting;

    private Button x, y,right,left,attack, special;
    private EditText enterIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterIP = (EditText)findViewById(R.id.Enter_IP_id);
        enterIP.setText("Enter IP");

        try {
            //opens socket
            socket = new DatagramSocket();

        } catch (SocketException s) {
            Log.d("UDP", "C: " + s);
        }
        sendUp();
        sendDown();
        sendLeft();
        sendRight();
        sendAttack();
        sendSpecial();

    }

    public void sendUp() {
        x = (Button) findViewById(R.id.UP_id);
        x.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("UDP", "pressed");
                        str = "pressed_JUMP";
                        sendUDP(str);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("UDP", "released");
                        str = "released_JUMP";
                        sendUDP(str);
                        return true;
                }
                return false;
            }
        });
    }

    public void sendDown() {
        y = (Button) findViewById(R.id.DOWN_id);
        y.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Pressed", "pressed");
                        str = "pressed_Y";
                        sendUDP(str);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("Released", "released");
                        str = "released_Y";
                        sendUDP(str);
                        return true;
                }
                return false;
            }
        });
    }

    public void sendRight() {
        right = (Button) findViewById(R.id.RIGHT_id);
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Pressed", "pressed");
                        str = "pressed_RIGHT";
                        sendUDP(str);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("Released", "released");
                        str = "released_RIGHT";
                        sendUDP(str);
                        return true;
                }
                return false;
            }
        });
    }


    public void sendLeft() {
        left = (Button) findViewById(R.id.LEFT_id);
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Pressed", "pressed");
                        str = "pressed_LEFT";
                        sendUDP(str);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("Released", "released");
                        str = "released_LEFT";
                        sendUDP(str);
                        return true;
                }
                return false;
            }
        });
    }

    public void sendAttack() {
        attack = (Button) findViewById(R.id.A_id);
        attack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Pressed", "pressed");
                        str = "pressed_ATTACK";
                        sendUDP(str);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("Released", "released");
                        str = "released_ATTACK";
                        sendUDP(str);
                        return true;
                }
                return false;
            }
        });
    }

    public void sendSpecial() {
        special = (Button) findViewById(R.id.B_id);
        special.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Pressed", "pressed");
                        str = "pressed_SPECIAL";
                        sendUDP(str);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.d("Released", "released");
                        str = "released_SPECIAL";
                        sendUDP(str);
                        return true;
                }
                return false;
            }
        });

    }

    public void radioClicked(View view) {
        Log.d("UDP", "radio clicked");

//        if(enterIP.getText().equals("Enter IP")){
//
//        }
        if(id == 99){
            host = enterIP.getText().toString();
            Log.d("UDP", "host name:" + host);

            isUDPConnecting = true;
            str = "give";
            Log.d("UDP", "request id.... " + id);
            sendUDP(str);

            if(id != 99){
                ((RadioButton) view).setChecked(true);
                Log.d("UDP", "received id.... " + id);

            }else {
                ((RadioButton) view).setChecked(false);
                Log.d("UDP", "did not receive id");
            }
        }

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

//                try {
////                    Thread.sleep(100);
//                }
//
//                catch (InterruptedException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }

                if (send == true) {

                    try {
                        // get server name
                        InetAddress serverAddr = InetAddress.getByName(host);

                        Log.d("UDP", "C: Connecting.... " + serverAddr.getHostName());

                        // create new UDP socket
//                        DatagramSocket socket = new DatagramSocket();

                        if(isUDPConnecting) {
                            // prepare data to be sent
                            buf = sendString.getBytes();

                            // create a UDP packet with data and its destination ip & port
                            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, port);

                            Log.d("UDP", "C: Sending: '" + new String(buf) + "'");

                            // send the UDP packet
                            socket.send(packet);

                            Log.d("UDP", "C: Sent.");
                            Log.d("UDP", "C: Done.");

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
//                            socket.close();

                            isUDPConnecting = false;

                        } else if (!isUDPConnecting){
                            // prepare data to be sent
                            buf = sendString.getBytes();

                            // create a UDP packets with data and its destination ip & port
                            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, port);

                            Log.d("UDP", "C: Sending: '" +  new String(buf) + "'");

                            // send the UDP packet
                            socket.send(packet);

                            Log.d("UDP", "C: Sent.");
                            Log.d("UDP", "C: Done.");

//                            socket.close();

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
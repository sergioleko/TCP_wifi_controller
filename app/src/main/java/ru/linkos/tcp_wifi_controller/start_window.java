package ru.linkos.tcp_wifi_controller;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class start_window extends AppCompatActivity {
public String targetIp;
public String targetPort;
public boolean interrupt;
public int counter;
    WiFiOperations wfo = new WiFiOperations();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_window);
        interrupt = false;
        counter = 0;
        wfo.isWiFoOK(getApplicationContext());



    }

    public void checkWifi (View view) throws IOException, InterruptedException {
        TextInputLayout ipInput = findViewById(R.id.inputIP);
        //TextInputLayout portInput = findViewById(R.id.inputPort);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            targetIp = ipInput.getEditText().getText().toString();
        }
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            targetPort = Objects.requireNonNull(portInput.getEditText()).toString();
        }*/
        final Thread networkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    interrupt = wfo.pingStation(targetIp, getApplicationContext());

                    } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

            }

        );
        networkThread.setDaemon(true);
        networkThread.start();

        if (interrupt){
            networkThread.interrupt();
        }
        else{
            Toast.makeText(this, "Station is reachable", Toast.LENGTH_SHORT).show();
        }


    }


    }



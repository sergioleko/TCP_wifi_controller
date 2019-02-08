package ru.linkos.tcp_wifi_controller;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class start_window extends AppCompatActivity {

    public int wifistate;
    WifiManager wfm;
    String wifi215 = "\"215\"";
    public int errNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_window);

        wfm = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        isWiFoOK();



    }

    public void checkWifi (View view){


    }
    public void isWiFoOK (){
        assert wfm != null;
        Log.i("wifi ", String.valueOf(wfm.isWifiEnabled()));
        if (wfm.isWifiEnabled()){

            Log.i("WiFi ", "enabled");
        }
        else {

            //Toast.makeText(this, "Enable WiFi", Toast.LENGTH_SHORT).show();
            errNo = 1;
        }
        //Log.i("WiFi 215 ", wfm.getConnectionInfo().getSSID());

        if (wfm.getConnectionInfo().getSSID().equals(wifi215)){
            //Toast.makeText(this, "Wifi connected", Toast.LENGTH_SHORT).show();


        }
        else {
            errNo = 2;
            //Toast.makeText(this, "Connect to 215", Toast.LENGTH_SHORT).show();
        }
        switch (errNo){
            case 1:
                Intent errorIntent = new Intent(this, startErrorActivity.class);
                errorIntent.putExtra("errCode", errNo);
                startActivity(errorIntent);
        }


    }
}

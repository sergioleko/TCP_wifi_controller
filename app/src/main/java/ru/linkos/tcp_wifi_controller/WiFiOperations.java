package ru.linkos.tcp_wifi_controller;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static android.support.v4.content.ContextCompat.getSystemService;

public class WiFiOperations {

    public static final String EXTRA_MESSAGE_ERR_NO = "Error_number";
    public int wifistate;
    WifiManager wfm;
    String wifi215 = "\"215\"";
    public int errNo;


    public void isWiFoOK(Context curContext) {


        wfm = (WifiManager) curContext.getSystemService(Context.WIFI_SERVICE);
        errNo = 0;
        assert wfm != null;
        Log.i("wifi ", String.valueOf(wfm.isWifiEnabled()));
        if (wfm.isWifiEnabled()) {

            Log.i("WiFi ", "enabled");
            if (wfm.getConnectionInfo().getSSID().equals(wifi215)) {
                Log.i("Wifi is: ", "OK");
                // Toast.makeText(this, "Wifi connected", Toast.LENGTH_SHORT).show();
            } else {
                errNo = 2;
                Log.i("err: ", String.valueOf(errNo));

                startError(curContext);
                //Toast.makeText(this, "Connect to 215", Toast.LENGTH_SHORT).show();
            }
        } else {

            //Toast.makeText(this, "Enable WiFi", Toast.LENGTH_SHORT).show();
            errNo = 1;
            Log.i("err: ", String.valueOf(errNo));
            startError(curContext);
        }
    }
    //Log.i("WiFi 215 ", wfm.getConnectionInfo().getSSID());


    public void startError(Context curContext) {
        Intent errorIntent = new Intent(curContext, startErrorActivity.class);
        switch (errNo) {
            case 1:

                errorIntent.putExtra(EXTRA_MESSAGE_ERR_NO, "WiFi not connected");
                curContext.startActivity(errorIntent);
                break;

            case 2:

                errorIntent.putExtra(EXTRA_MESSAGE_ERR_NO, "Wrong network connected");
                curContext.startActivity(errorIntent);
                break;

            case 3:

                errorIntent.putExtra(EXTRA_MESSAGE_ERR_NO, "Station not reachable");
                curContext.startActivity(errorIntent);
                break;



        }

    }

    public boolean pingStation(String ip, Context curContext) throws IOException {
        //InetAddress stationIP = InetAddress.getByName(ip);
        Log.i ("IP is: ", String.valueOf(InetAddress.getByName(ip)));
        if (InetAddress.getByName(ip) != null) {

            InetAddress stationIP = InetAddress.getByName(ip);

            if (stationIP.isReachable(10)){
                Log.i("station is: ", "reachable");


                return false;
            }
            else {
                Log.i("station is: ", "unreachable");
                errNo = 3;
                startError(curContext);
    return true;
            }
        }
        else{
            Log.i("station is: ", "unreachable vasche");
            return true;
        }


    }
}

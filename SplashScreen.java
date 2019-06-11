package com.example.projet_seisme;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        boolean connexion;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        /*
        if (!hasDATA()||!hasWIFI()) {
            connexion = false;
            AlertDialog.Builder adb = new AlertDialog.Builder(SplashScreen.this);
            adb.setTitle("Connexion error");
            adb.setMessage("Network connection is not available!");
            adb.setPositiveButton("OK", null);
            adb.show();
        }
        else {
        */
            Thread myThread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(3000);
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();
            connexion = true;
       // }
       // if(connexion == false) finish();
    }

    public boolean hasNetwork(){
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = connMgr.getAllNetworkInfo();

        for (NetworkInfo info : networkInfo) {

            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                if(info.isConnected())
                    isWifiConn |= true;
            }
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                if(info.isConnected())
                    isMobileConn |= true;
            }
        }
        System.out.println( "Wifi connected: " + isWifiConn);
        System.out.println("Mobile connected: " + isMobileConn);
        return true;
    }

    public boolean hasWIFI(){
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo [] networkInfo = connMgr.getAllNetworkInfo();

        for (NetworkInfo info : networkInfo) {

            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                if(info.isConnected())
                    return true;
            }
            else return false;
        }
        System.out.println( "Wifi connected: " + isWifiConn);
        System.out.println("Mobile connected: " + isMobileConn);
        return true;
    }

    public boolean hasDATA(){
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo [] networkInfo = connMgr.getAllNetworkInfo();

        for (NetworkInfo info : networkInfo) {

            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {

                if(info.isConnected())
                    return true;
            }
            else return false;
        }
        System.out.println( "Wifi connected: " + isWifiConn);
        System.out.println("Mobile connected: " + isMobileConn);
        return true;
    }


}

package com.example.projet_seisme;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.SystemClock.sleep;
/*changes:
ajouter de 2 attributs a la class seisme ainsi que les get et set qui vont avec
ajout dan sle parsing du JSON dans ActivityList pour recuperer longitude latitude
ajout MapsSeisme et son xml
ajout du mot "magnitude:" a la ligne 69 de MyAdapter
ajout du fichier google map api
*/


/*
accueil: ajouter icones
gérer conexion connnectivity manager
splashscreen - fait
savescreen
optionnel : barre de chargement
google map
tester etat de la connexion (wifi ou 3 G
Menu des preferences
 */

@TargetApi(21)
public class MainActivity extends AppCompatActivity {
    Button earthquake, get_data;
    public static String data;
    Intent intent;
    public static final String MESSAGE_FROM_Main = "";
    public static final int REQUEST_CODE_B = 2;
    private static final String DEBUG_TAG = "NetworkStatusExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            refreshRSSFlow();
            sleep(3000);
            //setContentView(R.layout.activity_main);
            //earthquake = findViewById(R.id.earthquake);
            intent = new Intent(MainActivity.this, ActivityList.class);
            intent.putExtra(MESSAGE_FROM_Main, data);
            startActivityForResult(intent, REQUEST_CODE_B);
    }



    public static void refreshRSSFlow(){
        MyAsyncTask process = new MyAsyncTask();
        process.execute();
    }
    @Override
    protected void onRestart() {
        Intent intent = new Intent(MainActivity.this, SplashScreen.class);
        startActivity(intent);
        super.onRestart();
    }

    public void onClickActivityList(View v) {
    }

}



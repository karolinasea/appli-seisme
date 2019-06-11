package com.example.projet_seisme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
accueil: ajouter icones
gérer conexion connnectivity manager
splashscreen
savescreen
optionnel : barre de chargement
google map
tester etat de la connexion (wifi ou 3 G
Menu des preferences
 */
public class MainActivity extends AppCompatActivity {
    Button earthquake, get_data;
    public static TextView data;
    Intent intent;
    public static final String MESSAGE_FROM_Main = "";
    public static final String MESSAGE_FROM_B = "message_from_B";
    public static final int REQUEST_CODE_B = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        earthquake = findViewById(R.id.earthquake);
        get_data = findViewById(R.id.get_data);
        data = findViewById(R.id.fetcheddata);

        get_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyAsyncTask process = new MyAsyncTask();
                process.execute();
            }
        });
        earthquake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, ActivityList.class);
                intent.putExtra(MESSAGE_FROM_Main, data.getText());
                startActivityForResult(intent, REQUEST_CODE_B);
            }
        });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void onClickActivityList(View v) {
    }

}



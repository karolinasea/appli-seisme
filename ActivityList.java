package com.example.projet_seisme;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityList extends AppCompatActivity {
    Button back;
    private ListView myListView;
    public static TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        back = (Button)findViewById(R.id.back);

        tv = findViewById(R.id.title);
        //myInteger = new MyInteger();

        //new MyAsyncTask().execute(REQ_CODE_ASYNC1,(CallBack)this, myInteger, lock);
        //Log.d("Main  async 1","started");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        myListView = (ListView) findViewById(R.id.list);

        ArrayList<HashMap<String, String>> listeSeisme = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> map;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                JSONObject jsr = new JSONObject (MainActivity.data);
                JSONArray features = jsr.getJSONArray("features");
                for(int i = 0; i<features.length(); i++){
                    try {
                        JSONObject seisme = (JSONObject)features.get(i);
                        JSONObject properties = (JSONObject)seisme.getJSONObject("properties");
                        Seisme monSeisme = new Seisme(
                                properties.getString("title"),
                                properties.getString("place"),
                                properties.getInt("time"),
                                properties.getDouble("mag"));
                        //Ondéclare la HashMap qio contiendra les informations pour un item
                        map = new HashMap<String, String>();
                        map.put("reaction", String.valueOf(R.drawable.telechargement));
                        map.put("title", monSeisme.getTitle());
                        map.put("time", String.valueOf(monSeisme.getTime()));
                        map.put("place", monSeisme.getPlace());
                        map.put("magnitude", String.valueOf(monSeisme.getMagnitude()));
                        listeSeisme.add(map);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //JSONArray features = (JSONArray) jsr.get(2);

        MyAdapter mesSeismes = new MyAdapter(listeSeisme, this);

        myListView.setAdapter(mesSeismes);


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
/*
                HashMap<String, String> map = (HashMap<String, String>) myListView.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.putExtra("seisme", map);
                startActivity(intent);
                */
                System.out.println("ca marche");
            }
        });
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) myListView.getItemAtPosition(position);
                AlertDialog.Builder adb = new AlertDialog.Builder(ActivityList.this);
                adb.setTitle("Selection");
                adb.setMessage(map.get("title") + " sélectionné");
                adb.setPositiveButton("OK", null);
                adb.show();
                return true;
            }
        });

    }
    @Override
    public void finish() {
        Intent intentB = new Intent();
        setResult(RESULT_OK, intentB);
        MainActivity.refreshRSSFlow();
        super.finish();
    }
    public void onClickReturn(View v){
        MainActivity.refreshRSSFlow();
        finish();
    }
}
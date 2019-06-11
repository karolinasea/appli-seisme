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
	import android.util.Log;
	import android.widget.ListView;
	import android.widget.TextView;
	import android.net.Uri;
	
	import org.json.JSONArray;
	import org.json.JSONException;
	import org.json.JSONObject;
	
	import java.lang.reflect.Array;
	import java.util.ArrayList;
	import java.util.HashMap;
	
	public class ActivityList extends AppCompatActivity
	{
	    Button back;
	    private ListView myListView;
	    public static TextView tv;
	    static String data="";
	    public static String MESSAGE_FROM_ACTIVITYLIST1 = "MESSAGE_FROM_ACTIVITYLIST1";
	    public static String MESSAGE_FROM_ACTIVITYLIST2 = "MESSAGE_FROM_ACTIVITYLIST2";
	    public static final String MESSAGE_FROM_MAPS = "message_from_Maps";
	    public static final int REQUEST_CODE_FROM_MAPS = 9;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_list_main);
	        back = (Button)findViewById(R.id.back);
	        tv = findViewById(R.id.title);
	        //myInteger = new MyInteger();
	        //new MyAsyncTask().execute(REQ_CODE_ASYNC1,(CallBack)this, myInteger, lock);
	        //Log.d("Main  async 1","started");
	
	        back.setOnClickListener(new View.OnClickListener()
	        {
	            @Override
	            public void onClick(View view)
	            {
	                finish();
	            }
	        });
	
	        myListView = (ListView) findViewById(R.id.list);
	
	        ArrayList<HashMap<String, String>> listeSeisme = new ArrayList<HashMap<String, String>>();
	        HashMap<String, String> map;
	        try {
	            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
	                JSONObject jsr = new JSONObject(data);
	                JSONArray features = jsr.getJSONArray("features");
	                for (int i = 0; i < features.length(); i++) {
	                    try {
	                        JSONObject seisme = (JSONObject) features.get(i);
	                        JSONObject properties = (JSONObject) seisme.getJSONObject("properties");
	                        JSONObject geo = (JSONObject) seisme.getJSONObject("geometry");
	                        Seisme monSeisme = new Seisme(
	                                properties.getString("title"),
	                                properties.getString("place"),
	                                properties.getDouble("time"),
	                                properties.getDouble("mag"),
	                                0,
	                                0);
	                        //on ajoute la longitute et latitude a l'objet monSeisme pour pouvoir l'afficher dans Google Maps
	
	                        JSONArray coordinates = geo.getJSONArray("coordinates");
	                                /*for(int j=0; j < coordinates.length(); j++)
	                                {*/
	                                    monSeisme.setCoordinate1(coordinates.getDouble(0));
	                                    monSeisme.setCoordinate2(coordinates.getDouble(1));
	                               // }
	                        Log.d("COORD  1 et 2 =", monSeisme.getCoordinate1() + " " + monSeisme.getCoordinate2());
	
	                        //On déclare la HashMap qui contiendra les informations pour un item
	                        map = new HashMap<String, String>();
	                        map.put("reaction", String.valueOf(R.drawable.telechargement));
	                        map.put("title", monSeisme.getTitle());
	                        map.put("time", String.valueOf(monSeisme.getTime()));
	                        map.put("place", monSeisme.getPlace());
	                        map.put("magnitude", String.valueOf(monSeisme.getMagnitude()));
	                        Log.d("mag =", map.get("magnitude"));
	                        map.put("coord1", String.valueOf(monSeisme.getCoordinate1()));
	                        map.put("coord2", String.valueOf(monSeisme.getCoordinate2()));
	                        Log.d("map =", map.get("coord1") + " " + map.get("coord2"));
	
	                        listeSeisme.add(map);
	
	                    } catch (JSONException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
	        catch (JSONException e)
	        {
	            e.printStackTrace();
	        }
	
	                MyAdapter mesSeismes = new MyAdapter(listeSeisme, this);
	
	                myListView.setAdapter(mesSeismes);
	
	                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	                    @Override
	                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	
	                        HashMap<String, String> map = (HashMap<String, String>) myListView.getItemAtPosition(position);
	                        Log.d("coord =", map.get("coord1") + " " + map.get("coord2"));
	                        Intent mapIntent = new Intent(ActivityList.this, MapsSeisme.class);
	                        mapIntent.putExtra(MESSAGE_FROM_ACTIVITYLIST1, map.get("coord1"));
	                        mapIntent.putExtra(MESSAGE_FROM_ACTIVITYLIST2, map.get("coord2"));
	                        startActivityForResult(mapIntent, REQUEST_CODE_FROM_MAPS);
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
	        super.finish();
	    }
	
	    public void onClickReturn(View v){
	        finish();
	    }
	} 

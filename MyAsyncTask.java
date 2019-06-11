package com.example.projet_seisme;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyAsyncTask extends AsyncTask<Object, Void, String> {
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    @Override
    protected String doInBackground(Object... voids) {

        try {
            //setting the url connexion
            URL url = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.geojson");
            //since i don't know how to parse the actual jsonarray yet...
            //URL url = new URL("https://api.myjson.com/bins/nvyvj");

            //creating the url connexion
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            //creating an inputStream
            InputStream inputStream = httpURLConnection.getInputStream();
            //to readthe data from the inputStream.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine(); //on parcourt le json file
                data = data + line;//on rajoute chaque ligne lue à la chaine de caractères
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ActivityList.data = this.data;
    }
}

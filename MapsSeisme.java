package com.example.projet_seisme;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsSeisme extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_seisme);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String coord1 = getIntent().getStringExtra(ActivityList.MESSAGE_FROM_ACTIVITYLIST1);
        Double c1 = /*(double)*/Double.parseDouble(coord1);
        String coord2 = getIntent().getStringExtra(ActivityList.MESSAGE_FROM_ACTIVITYLIST2);
        Double c2 = /*(double)*/Double.parseDouble(coord2);
        Log.d("msg from A_liste1", coord1);
        Log.d("msg from A_liste2", coord2);
        Log.d("c1 et c2", "c1 = " + c1 + " c2= " + c2);
        LatLng positionSeisme = new LatLng(c2, c1);
        mMap.addMarker(new MarkerOptions().position(positionSeisme).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(positionSeisme));

    }

    @Override
    public void finish() {
        Intent intentMap = new Intent();
        setResult(RESULT_OK, intentMap);
        super.finish();
    }
}

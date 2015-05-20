package com.tests.omar.androidsample.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tests.omar.androidsample.R;

import org.w3c.dom.Text;

/**
 * Created by omar on 19/05/2015.
 */
public class DetailedInfoActivity extends Activity {

    private String mag;
    private String time;
    private String place;
    private String geometry;
    private GoogleMap googleMap;

    private TextView textMag;
    private TextView textTime;
    private TextView textPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        textMag = (TextView) findViewById(R.id.magtext);
        textPlace = (TextView) findViewById(R.id.placetext);
        textTime = (TextView) findViewById(R.id.timetext);

        mag = getIntent().getStringExtra("MAGNITUDE");
        time = getIntent().getStringExtra("TIME");
        place = getIntent().getStringExtra("PLACE");
        geometry = getIntent().getStringExtra("GEOMETRY");

        createMapView();
        addMarker(geometry);
    }

    private void addMarker(String geometry) {
        geometry = geometry.replace("[", "");
        geometry = geometry.replace("]","");

        Double lat = Double.valueOf(geometry.split(",")[0]);
        Double lon = Double.valueOf(geometry.split(",")[1]);

        if(googleMap != null){
            googleMap.addMarker(new MarkerOptions()
            .position(new LatLng(lon,lat)).title("Marker")
                    .draggable(true));

            CameraUpdate cameraUpdate = CameraUpdateFactory
                    .newLatLngZoom(new LatLng(lon,lat),6);
            googleMap.animateCamera(cameraUpdate);
        }
    }

    private void createMapView() {
        try {
            if(googleMap == null){
                googleMap = ((MapFragment)getFragmentManager().
                        findFragmentById(R.id.mapView)).getMap();
            }
            if(null == googleMap) {
                Toast.makeText(getApplicationContext(),
                        "Error creating map", Toast.LENGTH_SHORT).show();
            }
        }catch (NullPointerException exception){
            Log.e("mapApp",exception.toString());
        }
    }


}

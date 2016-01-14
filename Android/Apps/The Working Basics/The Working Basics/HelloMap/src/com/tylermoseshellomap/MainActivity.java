package com.tylermoseshellomap;

import com.google.android.gms.maps.MapView;
import com.google.android.maps.MapActivity;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends MapActivity {

    private MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        mapView = (MapView) findViewById(R.id.map_view);       
        mapView.setClickable(true);

    }

    protected boolean isRouteDisplayed() {
        return false;
    }

}
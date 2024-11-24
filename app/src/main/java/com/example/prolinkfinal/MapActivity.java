package com.example.prolinkfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configurar OSMDroid
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_map);

        String serviceType = getIntent().getStringExtra("SERVICE_TYPE");
        setTitle("Proveedores de " + serviceType);

        mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        // Centrar el mapa en una ubicación predeterminada (ejemplo: Ciudad de México)
        GeoPoint startPoint = new GeoPoint(19.4326, -99.1332);
        mapView.getController().setCenter(startPoint);
        mapView.getController().setZoom(12.0);

        // Agregar marcadores de ejemplo para proveedores de servicios
        addSampleProviders();
    }

    private void addSampleProviders() {
        addProviderMarker(new GeoPoint(19.4326, -99.1332), "Juan Pérez", "4.5★");
        addProviderMarker(new GeoPoint(19.4226, -99.1432), "María García", "4.8★");
        addProviderMarker(new GeoPoint(19.4426, -99.1232), "Carlos López", "4.2★");
    }

    private void addProviderMarker(GeoPoint point, String name, String rating) {
        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle(name);
        marker.setSnippet(rating);

        // Hacer clic en el marcador para abrir la actividad de contratación
        marker.setOnMarkerClickListener((marker1, mapView1) -> {
            Intent intent = new Intent(MapActivity.this, BookingActivity.class);
            intent.putExtra("PROVIDER_NAME", marker1.getTitle()); // Pasar el nombre del proveedor
            startActivity(intent);
            return true;
        });

        mapView.getOverlays().add(marker);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}

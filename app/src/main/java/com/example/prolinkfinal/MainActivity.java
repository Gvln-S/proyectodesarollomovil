package com.example.prolinkfinal;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ServiceAdapter.OnServiceClickListener {
    private RecyclerView servicesRecyclerView;
    private ServiceAdapter serviceAdapter;
    private List<Service> services;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String serviceType = getIntent().getStringExtra("SERVICE_TYPE");
        int imageResourceId = getIntent().getIntExtra("SERVICE_IMAGE_ID", -1); // Get image resource ID (default -1)


        servicesRecyclerView = findViewById(R.id.servicesRecyclerView);
        services = new ArrayList<>();

        // Agregar servicios de ejemplo
        services.add(new Service("plomeria", 12, R.drawable.plomeria));
        services.add(new Service("jardineria", 8, R.drawable.jardineria));
        services.add(new Service("tutoria", 15, R.drawable.tutoria));
        services.add(new Service("reparacion", 10, R.drawable.reparacion));


        serviceAdapter = new ServiceAdapter(services, this);
        servicesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        servicesRecyclerView.setAdapter(serviceAdapter);
    }

    @Override
    public void onServiceClick(Service service) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("SERVICE_TYPE", service.getTitle());
        startActivity(intent);
    }
}
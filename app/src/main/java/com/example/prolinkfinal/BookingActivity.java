package com.example.prolinkfinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    private TextView providerNameTextView;
    private EditText dateEditText;
    private EditText timeEditText;
    private EditText descriptionEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        providerNameTextView = findViewById(R.id.providerNameTextView);
        dateEditText = findViewById(R.id.dateEditText);
        timeEditText = findViewById(R.id.timeEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        submitButton = findViewById(R.id.submitButton);

        // Obtener la información del proveedor desde el Intent
        String providerName = getIntent().getStringExtra("PROVIDER_NAME");
        providerNameTextView.setText("Contratar a: " + providerName);

        submitButton.setOnClickListener(v -> {
            String date = dateEditText.getText().toString();
            String time = timeEditText.getText().toString();
            String description = descriptionEditText.getText().toString();

            if (date.isEmpty() || time.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                // Aquí podrías guardar la solicitud en una base de datos o hacer un request a un servidor
                Toast.makeText(this, "Servicio contratado", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}

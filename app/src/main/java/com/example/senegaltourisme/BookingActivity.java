package com.example.senegaltourisme;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Réservation");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView tvName = findViewById(R.id.tvBookingHotelName);
        TextView tvPrice = findViewById(R.id.tvBookingPrice);
        EditText editArrivee = findViewById(R.id.editDateArrivee);
        EditText editDepart = findViewById(R.id.editDateDepart);
        Button btnConfirm = findViewById(R.id.btnConfirmBooking);

        Hotel hotel = (Hotel) getIntent().getSerializableExtra("HOTEL");

        if (hotel != null) {
            tvName.setText("Hôtel : " + hotel.getNom());
            tvPrice.setText("Prix : " + hotel.getPrix() + " FCFA / nuit");
        }

        btnConfirm.setOnClickListener(v -> {
            String arrivee = editArrivee.getText().toString().trim();
            String depart = editDepart.getText().toString().trim();

            if (arrivee.isEmpty() || depart.isEmpty()) {
                Toast.makeText(this, "Veuillez renseigner les dates", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Réservation confirmée avec succès !", Toast.LENGTH_LONG).show();
                finish(); // Retourne à l'écran précédent
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

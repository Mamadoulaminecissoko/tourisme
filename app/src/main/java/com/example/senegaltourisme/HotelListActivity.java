package com.example.senegaltourisme;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HotelListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        String siteName = getIntent().getStringExtra("SITE_NAME");
        if (siteName == null) siteName = "ce site";

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Hôtels");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView tvTitle = findViewById(R.id.tvHotelTitle);
        tvTitle.setText("Hôtels près de : " + siteName);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewHotels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Simulation de données
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("1", "Hôtel de la Plage", "50000", R.drawable.lac_rose, 4.5f));
        hotels.add(new Hotel("2", "Résidence Royale", "75000", 0, 4.8f));
        hotels.add(new Hotel("3", "Auberge du Centre", "25000", 0, 3.9f));
        hotels.add(new Hotel("4", "Lodge Nature", "40000", 0, 4.2f));

        HotelAdapter adapter = new HotelAdapter(hotels, this);
        recyclerView.setAdapter(adapter);
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

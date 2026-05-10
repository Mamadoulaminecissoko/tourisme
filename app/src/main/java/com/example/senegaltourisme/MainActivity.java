package com.example.senegaltourisme;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // 1. ON DÉCLARE L'ADAPTER ICI (Variable globale)
    private SiteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sénégal Tourisme");
        }
        // Liaison avec le XML
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Configuration du gestionnaire de layout (Obligatoire pour voir la liste)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Préparation des données
        List<Site> siteList = new ArrayList<>();
        // Note : Vérifie bien que les noms d'images (goree, monument...) existent dans ton dossier drawable
        siteList.add(new Site("Île de Gorée", "Un lieu chargé d'histoire...", R.drawable.goree, "14.6667,-17.4000"));
        siteList.add(new Site("Monument de la Renaissance", "Une statue imposante...", R.drawable.monument, "14.7219,-17.4948"));
        siteList.add(new Site("Lac Rose", "Un lac salé aux reflets roses...", R.drawable.lac_rose, "14.8392,-17.2281"));
        siteList.add(new Site("Cap Skirring", "Les plus belles plages de Casamance, un vrai paradis.", R.drawable.cap, "12.3500,-16.7333"));
        siteList.add(new Site("Îles du Saloum", "Un delta magnifique classé à l'UNESCO, entre mangrove et mer.", R.drawable.saloum, "13.8333,-16.5000"));
        // Liaison avec l'Adapter
        SiteAdapter adapter = new SiteAdapter(siteList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // On récupère la barre de recherche
        MenuItem searchItem = menu.findItem(R.id.action_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();

        searchView.setQueryHint("Chercher un lieu...");
        // Dans onCreateOptionsMenu de MainActivity.java
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText); // Appelle le filtre de l'adapter
                return true;
            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Utilise "else if" pour éviter les conflits
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_home) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_search) {
            Toast.makeText(this, "Recherche en cours...", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
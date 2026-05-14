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
        siteList.add(new Site("Île de Gorée", "Un lieu chargé d'histoire, symbole de la mémoire de la traite négrière.", R.drawable.goree, "14.6667,-17.4000"));
        siteList.add(new Site("Monument de la Renaissance", "Une statue imposante en bronze représentant une famille africaine.", R.drawable.monument, "14.7219,-17.4948"));
        siteList.add(new Site("Lac Rose", "Un lac salé mondialement connu pour ses reflets roses uniques.", R.drawable.lac_rose, "14.8392,-17.2281"));
        siteList.add(new Site("Cap Skirring", "Les plus belles plages de Casamance, un vrai paradis tropical.", R.drawable.cap, "12.3500,-16.7333"));
        siteList.add(new Site("Îles du Saloum", "Un delta magnifique classé à l'UNESCO, entre mangrove et mer.", R.drawable.saloum, "13.8333,-16.5000"));
        siteList.add(new Site("Saint-Louis", "Ancienne capitale au charme colonial, célèbre pour son festival de jazz.", R.drawable.garde, "16.0333,-16.4833"));
        siteList.add(new Site("Désert de Lompoul", "De magnifiques dunes de sable ocre pour une nuit inoubliable à la belle étoile.", R.drawable.lac_rose, "15.4419,-16.6661"));
        siteList.add(new Site("Parc de Djoudj", "La troisième plus grande réserve ornithologique du monde.", R.drawable.cap, "16.3333,-16.2500"));
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
        } else if (id == R.id.action_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
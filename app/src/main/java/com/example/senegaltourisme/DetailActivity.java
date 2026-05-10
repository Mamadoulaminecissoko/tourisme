package com.example.senegaltourisme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sénégal Tourisme");
        }

        // Active la flèche retour en haut à gauche
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView img = findViewById(R.id.detailImage);
        TextView txtNom = findViewById(R.id.detailTitle);
        TextView txtDesc = findViewById(R.id.detailDescription);
        Button btnMap = findViewById(R.id.btnMap);

        // Récupération des données passées par l'Adapter
        String nom = getIntent().getStringExtra("NOM_SITE");
        String desc = getIntent().getStringExtra("DESC_SITE");
        int imageId = getIntent().getIntExtra("IMAGE_SITE", 0);
        String coords = getIntent().getStringExtra("COORDS_SITE");

        txtNom.setText(nom);
        txtDesc.setText(desc);
        img.setImageResource(imageId);

        // Action du bouton Maps avec sécurité
        btnMap.setOnClickListener(v -> {
            if (coords != null && !coords.isEmpty()) {
                try {
                    Uri gmmIntentUri = Uri.parse("geo:" + coords + "?q=" + Uri.encode(nom));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                } catch (Exception e) {
                    // Si Maps n'est pas installé, ouvre le navigateur
                    Uri webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + coords);
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
                    startActivity(webIntent);
                }
            } else {
                Toast.makeText(this, "Coordonnées indisponibles", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Affiche le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Gère les clics (Flèche retour OU Icône Accueil)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish(); // Retourne à la liste
            return true;
        } else if (id == R.id.action_home) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.action_search) {
            // Pour l'instant on affiche juste un message
            Toast.makeText(this, "Recherche bientôt disponible !", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
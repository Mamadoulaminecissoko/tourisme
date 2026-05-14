package com.example.senegaltourisme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Active le mode plein écran
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Gestion des marges système (barre d'état, encoche)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- CODE POUR LE BOUTON ---

        // 1. On lie le bouton Java à l'ID du bouton dans le XML
        Button btnExplore = findViewById(R.id.btnExplore);
        android.widget.ImageView btnProfile = findViewById(R.id.btnProfileHome);

        // 2. On définit ce qui se passe quand on clique dessus
        btnExplore.setOnClickListener(v -> {
            // On crée une intention (Intent) pour aller vers LoginActivity
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // 3. Gestion de l'icône Profil
        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        });
    }
}
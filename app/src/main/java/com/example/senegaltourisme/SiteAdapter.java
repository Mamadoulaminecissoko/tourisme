package com.example.senegaltourisme;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.SiteViewHolder> {

    private List<Site> siteList;
    private List<Site> siteListFull; // Copie complète pour la recherche

    public SiteAdapter(List<Site> siteList) {
        this.siteList = siteList;
        this.siteListFull = new ArrayList<>(siteList); // On initialise la copie
    }

    @NonNull
    @Override
    public SiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_site, parent, false);
        return new SiteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiteViewHolder holder, int position) {
        Site currentSite = siteList.get(position);
        holder.textViewNom.setText(currentSite.getNom());
        holder.textViewDesc.setText(currentSite.getDescription());
        holder.imageView.setImageResource(currentSite.getImageId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("NOM_SITE", currentSite.getNom());
            intent.putExtra("DESC_SITE", currentSite.getDescription());
            intent.putExtra("IMAGE_SITE", currentSite.getImageId());
            intent.putExtra("COORDS_SITE", currentSite.getCoords());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return siteList.size();
    }

    // --- LA MÉTHODE DE FILTRAGE ---
    public void filter(String text) {
        siteList.clear();
        if (text.isEmpty()) {
            siteList.addAll(siteListFull);
        } else {
            text = text.toLowerCase();
            for (Site item : siteListFull) {
                if (item.getNom().toLowerCase().contains(text)) {
                    siteList.add(item);
                }
            }
        }
        notifyDataSetChanged(); // Rafraîchit la liste à l'écran
    }

    public static class SiteViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNom, textViewDesc;
        ImageView imageView;

        public SiteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNom = itemView.findViewById(R.id.nom_site);
            textViewDesc = itemView.findViewById(R.id.description_courte);
            imageView = itemView.findViewById(R.id.image_site);
        }
    }
}
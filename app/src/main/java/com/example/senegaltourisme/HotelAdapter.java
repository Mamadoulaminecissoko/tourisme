package com.example.senegaltourisme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<Hotel> hotelList;
    private Context context;

    public HotelAdapter(List<Hotel> hotelList, Context context) {
        this.hotelList = hotelList;
        this.context = context;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.hotelName.setText(hotel.getNom());
        holder.hotelPrice.setText("Prix : " + hotel.getPrix() + " FCFA/nuit");
        holder.hotelRating.setText("Note : " + hotel.getNote() + "/5");
        
        // Simuler des images si l'ID n'est pas 0 (sinon on laisse gris par defaut)
        if (hotel.getImageId() != 0) {
            holder.hotelImage.setImageResource(hotel.getImageId());
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookingActivity.class);
            intent.putExtra("HOTEL", hotel); // Hotel must implement Serializable
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        ImageView hotelImage;
        TextView hotelName;
        TextView hotelPrice;
        TextView hotelRating;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelImage = itemView.findViewById(R.id.hotelImage);
            hotelName = itemView.findViewById(R.id.hotelName);
            hotelPrice = itemView.findViewById(R.id.hotelPrice);
            hotelRating = itemView.findViewById(R.id.hotelRating);
        }
    }
}

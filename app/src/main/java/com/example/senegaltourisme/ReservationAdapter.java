package com.example.senegaltourisme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private List<Reservation> reservationList;

    public ReservationAdapter(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        Hotel hotel = reservation.getHotel();

        if (hotel != null) {
            holder.resHotelName.setText(hotel.getNom());
            holder.resPrice.setText("Prix : " + hotel.getPrix() + " FCFA/nuit");
            if (hotel.getImageId() != 0) {
                holder.resHotelImage.setImageResource(hotel.getImageId());
            }
        }

        holder.resDates.setText("Du " + reservation.getDateArrivee() + " au " + reservation.getDateDepart());
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        ImageView resHotelImage;
        TextView resHotelName;
        TextView resDates;
        TextView resPrice;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            resHotelImage = itemView.findViewById(R.id.resHotelImage);
            resHotelName = itemView.findViewById(R.id.resHotelName);
            resDates = itemView.findViewById(R.id.resDates);
            resPrice = itemView.findViewById(R.id.resPrice);
        }
    }
}

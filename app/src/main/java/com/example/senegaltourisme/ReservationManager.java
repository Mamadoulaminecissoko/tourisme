package com.example.senegaltourisme;

import java.util.ArrayList;
import java.util.List;

public class ReservationManager {
    private static ReservationManager instance;
    private List<Reservation> reservations;

    private ReservationManager() {
        reservations = new ArrayList<>();
    }

    public static ReservationManager getInstance() {
        if (instance == null) {
            instance = new ReservationManager();
        }
        return instance;
    }

    public void addReservation(Reservation reservation) {
        // Ajouter en haut de la liste pour voir les plus récentes en premier
        reservations.add(0, reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}

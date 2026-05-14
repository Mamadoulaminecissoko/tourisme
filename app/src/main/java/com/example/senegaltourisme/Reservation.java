package com.example.senegaltourisme;

import java.io.Serializable;

public class Reservation implements Serializable {
    private Hotel hotel;
    private String dateArrivee;
    private String dateDepart;

    public Reservation(Hotel hotel, String dateArrivee, String dateDepart) {
        this.hotel = hotel;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public String getDateArrivee() {
        return dateArrivee;
    }

    public String getDateDepart() {
        return dateDepart;
    }
}

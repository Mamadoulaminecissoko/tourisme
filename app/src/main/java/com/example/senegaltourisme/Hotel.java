package com.example.senegaltourisme;

import java.io.Serializable;

public class Hotel implements Serializable {
    private String id;
    private String nom;
    private String prix;
    private int imageId;
    private float note;

    public Hotel(String id, String nom, String prix, int imageId, float note) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.imageId = imageId;
        this.note = note;
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getPrix() { return prix; }
    public int getImageId() { return imageId; }
    public float getNote() { return note; }
}

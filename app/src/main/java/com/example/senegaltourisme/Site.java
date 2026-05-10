package com.example.senegaltourisme;

public class Site {
    private String nom;
    private String description;
    private int imageId;
    private String coords; // Latitude, Longitude

    public Site(String nom, String description, int imageId, String coords) {
        this.nom = nom;
        this.description = description;
        this.imageId = imageId;
        this.coords = coords;
    }

    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public int getImageId() { return imageId; }
    public String getCoords() { return coords; }
}
package com.skaldia.salescodingtest.model;
public class Produit {

    private String nom;
    private double prix;
    private boolean importe;
    Categorie categorie;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean isImporte() {
        return importe;
    }

    public void setImporte(boolean importe) {
        this.importe = importe;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}

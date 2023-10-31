package com.skaldia.salescodingtest.model;

import com.skaldia.salescodingtest.config.ConfigTaxes;
import org.springframework.beans.factory.annotation.Autowired;

public class Produit {

    @Autowired
    private ConfigTaxes configTaxes;

    private String nom;
    private double prix;
    private boolean importe;
    Categorie categorie;

    // Getters & Setters
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

package com.skaldia.salescodingtest.model;

import java.util.ArrayList;
import java.util.List;

public class Recu {

    private List<Produit> listeDeProduits;
    private double taxesTotales;
    private double total;

    public Recu() {
        this.listeDeProduits = new ArrayList<>();  // Initialisation de la liste ici
    }

    // Getters & Setters
    public List<Produit> getListeDeProduits() {
        return listeDeProduits;
    }

    public void setListeDeProduits(List<Produit> listeDeProduits) {
        this.listeDeProduits = listeDeProduits;
    }

    public double getTaxesTotales() {
        return taxesTotales;
    }

    public void setTaxesTotales(double taxesTotales) {
        this.taxesTotales = taxesTotales;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void ajouterProduit(Produit produit) {
        getListeDeProduits().add(produit);
    }
}

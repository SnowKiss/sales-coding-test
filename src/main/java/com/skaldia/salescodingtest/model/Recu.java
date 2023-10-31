package com.skaldia.salescodingtest.model;

import java.util.List;

public class Recu {

    private List<Produit> listeDeProduits;
    private double taxesTotales;
    private double total;


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

}

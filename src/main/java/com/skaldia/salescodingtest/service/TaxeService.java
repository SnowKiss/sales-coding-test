package com.skaldia.salescodingtest.service;

import com.skaldia.salescodingtest.config.ConfigTaxes;
import com.skaldia.salescodingtest.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxeService {

    @Autowired
    private ConfigTaxes configTaxes;

    public double calculerTaxe(Produit produit) {
        double taxe = 0.0;

        // Si le produit n'est pas exempté, ajoutez une taxe de base
        if (!configTaxes.getCategoriesExemptees().contains(produit.getCategorie())) {
            taxe += produit.getPrix() * configTaxes.getBase();
        }

        // Si le produit est importé, ajoutez une taxe supplémentaire
        if (produit.isImporte()) {
            taxe += produit.getPrix() * configTaxes.getImportation();
        }

        // Arrondi la taxe à 0,05 près
        taxe = Math.ceil(taxe * 20) / 20.0;

        return taxe;
    }

    public double getPrixTTC(Produit produit) {
        return produit.getPrix() + calculerTaxe(produit);
    }
}


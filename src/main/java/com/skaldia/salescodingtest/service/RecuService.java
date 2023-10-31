package com.skaldia.salescodingtest.service;

import com.skaldia.salescodingtest.model.Produit;
import com.skaldia.salescodingtest.model.Recu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecuService {

    @Autowired
    private TaxeService taxeService;

    public Recu genererRecu(List<Produit> produits) {
        Recu recu = new Recu();
        double taxesTotales = 0.0;
        double total = 0.0;

        for (Produit produit : produits) {
            double taxe = taxeService.calculerTaxe(produit);
            taxesTotales += taxe;
            total += produit.getPrix() + taxe;
        }

        recu.setListeDeProduits(produits);
        recu.setTaxesTotales(taxesTotales);
        recu.setTotal(total);

        return recu;
    }

    public String convertirRecuEnTexte(Recu recu) {
        StringBuilder resultat = new StringBuilder();

        for (Produit produit : recu.getListeDeProduits()) {
            double prixTotalProduit = produit.getPrix() + taxeService.calculerTaxe(produit);
            resultat.append(String.format("1 %s : %.2f\n", produit.getNom(), prixTotalProduit));
        }

        resultat.append(String.format("Sales Taxes : %.2f Total : %.2f",
                recu.getTaxesTotales(), recu.getTotal()));

        return resultat.toString();
    }
}


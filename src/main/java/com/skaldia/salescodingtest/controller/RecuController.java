package com.skaldia.salescodingtest.controller;

import com.skaldia.salescodingtest.model.Produit;
import com.skaldia.salescodingtest.model.Recu;
import com.skaldia.salescodingtest.service.TaxeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recu")
public class RecuController {

    @Autowired
    private TaxeService taxeService;

    @PostMapping
    public Recu genererRecu(@RequestBody List<Produit> produits) {
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
}

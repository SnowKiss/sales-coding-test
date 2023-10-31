package com.skaldia.salescodingtest.controller;

import com.skaldia.salescodingtest.exception.UnknownProductException;
import com.skaldia.salescodingtest.model.Produit;
import com.skaldia.salescodingtest.model.Recu;
import com.skaldia.salescodingtest.service.ProduitService;
import com.skaldia.salescodingtest.service.RecuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recu")
public class RecuController {

    @Autowired
    private RecuService recuService;

    @Autowired
    private ProduitService produitService;

    @PostMapping
    public ResponseEntity<?> genererRecu(@RequestBody String input) {
        try {
            List<Produit> produits = produitService.convertirTexteEnProduits(input);
            Recu recu = recuService.genererRecu(produits);
            return ResponseEntity.ok(recuService.convertirRecuEnTexte(recu));
        } catch (UnknownProductException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

package com.skaldia.salescodingtest.controller;

import com.skaldia.salescodingtest.model.Produit;
import com.skaldia.salescodingtest.model.Recu;
import com.skaldia.salescodingtest.service.RecuService;
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
    private RecuService recuService;

    @PostMapping
    public String genererRecu(@RequestBody List<Produit> produits) {
        Recu recu = recuService.genererRecu(produits);
        return recuService.convertirRecuEnTexte(recu);
    }
}

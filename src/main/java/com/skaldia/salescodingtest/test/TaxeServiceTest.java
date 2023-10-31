package com.skaldia.salescodingtest.test;

import com.skaldia.salescodingtest.config.ConfigTaxes;
import com.skaldia.salescodingtest.model.Categorie;
import com.skaldia.salescodingtest.model.Produit;
import com.skaldia.salescodingtest.service.TaxeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaxeServiceTest {

    @InjectMocks
    private TaxeService taxeService;

    @Mock
    private ConfigTaxes configTaxes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Simuler le comportement de configTaxes
        List<Categorie> categoriesExemptees = Arrays.asList(Categorie.LIVRE, Categorie.NOURRITURE, Categorie.MEDICAMENT);
        when(configTaxes.getCategoriesExemptees()).thenReturn(categoriesExemptees);
        when(configTaxes.getBase()).thenReturn(0.1);
        when(configTaxes.getImportation()).thenReturn(0.05);
    }

    @Test
    void calculerTaxePourProduitImporte() {
        Produit produit = new Produit();
        produit.setImporte(true);
        produit.setPrix(10.00);
        produit.setCategorie(Categorie.AUTRE);

        double taxe = taxeService.calculerTaxe(produit);
        assertEquals(1.50, taxe, 0.01);  // Hypothèse: 10% de taxe de base + 5% de taxe d'importation
    }

    @Test
    void calculerTaxePourProduitNonImporte() {
        Produit produit = new Produit();
        produit.setImporte(false);
        produit.setPrix(10.00);
        produit.setCategorie(Categorie.AUTRE);

        double taxe = taxeService.calculerTaxe(produit);
        assertEquals(1.00, taxe, 0.01);  // Hypothèse: Seulement 10% de taxe de base
    }

    @Test
    void calculerTaxePourProduitExempte() {
        Produit produit = new Produit();
        produit.setImporte(false);
        produit.setPrix(10.00);
        produit.setCategorie(Categorie.LIVRE);

        double taxe = taxeService.calculerTaxe(produit);
        assertEquals(0.00, taxe, 0.01);  // Hypothèse: Pas de taxe pour les livres
    }

    @Test
    void calculerTaxePourProduitImporteEtExempte() {
        Produit produit = new Produit();
        produit.setImporte(true);
        produit.setPrix(10.00);
        produit.setCategorie(Categorie.LIVRE);

        double taxe = taxeService.calculerTaxe(produit);
        assertEquals(0.50, taxe, 0.01);  // Hypothèse: Seulement 5% de taxe d'importation
    }

}



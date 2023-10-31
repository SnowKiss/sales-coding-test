package com.skaldia.salescodingtest.test;

import com.skaldia.salescodingtest.model.Categorie;
import com.skaldia.salescodingtest.service.RecuService;
import com.skaldia.salescodingtest.service.TaxeService;
import com.skaldia.salescodingtest.model.Produit;
import com.skaldia.salescodingtest.model.Recu;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RecuServiceTest {

    @InjectMocks
    private RecuService recuService;

    @Mock
    private TaxeService taxeService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testConvertirRecuEnTexte_ValidList() {
        Produit produit = new Produit();
        produit.setNom("box of chocolates");
        produit.setPrix(10.00);
        produit.setImporte(true);
        produit.setCategorie(Categorie.NOURRITURE);

        Recu recu = new Recu();
        recu.ajouterProduit(produit);

        when(taxeService.calculerTaxe(produit)).thenReturn(0.5);
        recu.setTaxesTotales(taxeService.calculerTaxe(produit));
        recu.setTotal(produit.getPrix()+recu.getTaxesTotales());

        String expected = "1 imported box of chocolates : 10.50\nSales Taxes : 0.50 Total : 10.50";
        String result = recuService.convertirRecuEnTexte(recu);

        assertEquals(expected, result);
    }

    @Test
    public void testConvertirRecuEnTexte_EmptyList() {
        Recu recu = new Recu();

        String expected = "Sales Taxes : 0.00 Total : 0.00";
        String result = recuService.convertirRecuEnTexte(recu);

        assertEquals(expected, result);
    }

    @Test
    public void testConvertirRecuEnTexte_DifferentTaxes() {
        Produit produit1 = new Produit();
        produit1.setNom("book");
        produit1.setPrix(12.49);
        produit1.setImporte(false);
        produit1.setCategorie(Categorie.LIVRE);

        Produit produit2 = new Produit();
        produit2.setNom("bottle of perfume");
        produit2.setPrix(47.50);
        produit2.setImporte(true);
        produit2.setCategorie(Categorie.AUTRE);

        Recu recu = new Recu();
        recu.ajouterProduit(produit1);
        recu.ajouterProduit(produit2);


        when(taxeService.calculerTaxe(produit1)).thenReturn(0.0);
        when(taxeService.calculerTaxe(produit2)).thenReturn(7.15);
        recu.setTaxesTotales(taxeService.calculerTaxe(produit1)+taxeService.calculerTaxe(produit2));

        recu.setTotal(produit1.getPrix()+produit2.getPrix()+recu.getTaxesTotales());

        String expected = "1 book : 12.49\n1 imported bottle of perfume : 54.65\nSales Taxes : 7.15 Total : 67.14";
        String result = recuService.convertirRecuEnTexte(recu);

        assertEquals(expected, result);
    }

}


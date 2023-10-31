package com.skaldia.salescodingtest.test;

import com.skaldia.salescodingtest.model.Produit;
import com.skaldia.salescodingtest.service.ProduitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProduitServiceTest {

    @InjectMocks
    private ProduitService produitService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertirTexteAvecEntreeValide() {
        String input = "1 imported bottle of perfume at 27.99";
        List<Produit> produits = produitService.convertirTexteEnProduits(input);

        assertEquals(1, produits.size());
        assertTrue(produits.get(0).isImporte());
        assertEquals("bottle of perfume", produits.get(0).getNom());
        assertEquals(27.99, produits.get(0).getPrix());
    }

    @Test
    public void testConvertirTexteAvecProduitImporte() {
        String input = "1 imported chocolate bar at 0.85";
        List<Produit> produits = produitService.convertirTexteEnProduits(input);

        assertTrue(produits.get(0).isImporte());
        assertEquals("chocolate bar", produits.get(0).getNom());
    }

    @Test
    public void testConvertirTexteAvecProduitNonImporte() {
        String input = "1 book at 12.49";
        List<Produit> produits = produitService.convertirTexteEnProduits(input);

        assertFalse(produits.get(0).isImporte());
        assertEquals("book", produits.get(0).getNom());
    }

    @Test
    public void testConvertirTexteAvecEntreeVide() {
        String input = "";
        List<Produit> produits = produitService.convertirTexteEnProduits(input);

        assertTrue(produits.isEmpty());
    }

    @Test
    public void testConvertirTexteAvecEntreeMalFormee() {
        String input = "1 bouteille de parfum 18.99";
        assertThrows(RuntimeException.class, () -> {
            produitService.convertirTexteEnProduits(input);
        });
    }

}

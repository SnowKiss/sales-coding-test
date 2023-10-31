package com.skaldia.salescodingtest.test;

import com.skaldia.salescodingtest.model.Produit;
import com.skaldia.salescodingtest.service.ProduitService;
import com.skaldia.salescodingtest.service.TaxeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProduitService produitService;

    @MockBean
    private TaxeService taxeService;

    @BeforeEach
    void setUp() {
        // Réinitialise les mocks avant chaque test
        reset(produitService, taxeService);
    }

    @Test
    public void testGenererRecuAvecRequeteValide() throws Exception {
        String input = "1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50";
        String expectedOutput = "1 imported box of chocolates : 10.50\n1 imported bottle of perfume : 54.65\nSales Taxes : 7.65 Total : 65.15";

        // Mocking des produits retournés par ProduitService
        Produit produit1 = new Produit();
        produit1.setNom("box of chocolates");
        produit1.setPrix(10.00);
        produit1.setImporte(true);

        Produit produit2 = new Produit();
        produit2.setNom("bottle of perfume");
        produit2.setPrix(47.50);
        produit2.setImporte(true);

        List<Produit> mockedListeProduits = Arrays.asList(produit1, produit2);

        when(produitService.convertirTexteEnProduits(input)).thenReturn(mockedListeProduits);

        // Mocking des taxes retournées par TaxeService pour les produits
        when(taxeService.calculerTaxe(produit1)).thenReturn(0.50);  // exemple de taxe
        when(taxeService.calculerTaxe(produit2)).thenReturn(7.15);  // exemple de taxe

        mockMvc.perform(post("/api/recu").content(input))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedOutput));
    }


    @Test
    public void testGenererRecuSansInput() throws Exception {
        mockMvc.perform(post("/api/recu"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGenererRecuAvecEntreeMalFormee() throws Exception {
        String badInput = "1 bottle of perfume 18.99";  // manque le mot "at"

        mockMvc.perform(post("/api/recu").content(badInput))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGenererRecuAvecEntreeVide() throws Exception {
        mockMvc.perform(post("/api/recu").content(""))
                .andExpect(status().isBadRequest());
    }
}

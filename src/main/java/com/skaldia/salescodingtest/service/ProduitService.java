package com.skaldia.salescodingtest.service;

import com.skaldia.salescodingtest.exception.UnknownProductException;
import com.skaldia.salescodingtest.model.Categorie;
import com.skaldia.salescodingtest.model.Produit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProduitService {

    // TODO: Set 'categorie'
    // La catégorie d'un objet pourrait être définie dans une base de données
    // Ici, je vais définir une map en dur pour assigner les exemples d'objets à des catégories
    // Une exception doit être levée si l'objet n'est pas connu
    private static final Map<String, Categorie> CATEGORIE_MAP = new HashMap<>();

    static {
        CATEGORIE_MAP.put("book", Categorie.LIVRE);
        CATEGORIE_MAP.put("music CD", Categorie.MUSIQUE);
        CATEGORIE_MAP.put("chocolate bar", Categorie.NOURRITURE);
        CATEGORIE_MAP.put("box of chocolates", Categorie.NOURRITURE);
        CATEGORIE_MAP.put("bottle of perfume", Categorie.AUTRE);
        CATEGORIE_MAP.put("packet of headache pills", Categorie.MEDICAMENT);
    }

    public Categorie obtenirCategorieDepuisNom(String nom) throws UnknownProductException {
        Categorie categorie = CATEGORIE_MAP.get(nom);
        if (categorie == null) {
            throw new UnknownProductException("Le produit " + nom + " est inconnu.");
        }
        return categorie;
    }



    ////////////////////////////////////////////////////////////////////

    public List<Produit> convertirTexteEnProduits(String input) {
        List<Produit> produits = new ArrayList<>();

        String[] lignes = input.split("\n");
        Pattern pattern = Pattern.compile("(\\d+) (imported )?(.*?) at (\\d+.\\d{2})");
        for (String ligne : lignes) {
            Matcher matcher = pattern.matcher(ligne);
            if (matcher.find()) {
                Produit produit = new Produit();

                int quantite = Integer.parseInt(matcher.group(1));
                double prix = Double.parseDouble(matcher.group(4));

                // Vérifiez si le produit est marqué comme importé
                boolean isImported = (matcher.group(2) != null) || (matcher.group(3).contains("imported"));
                produit.setImporte(isImported);

                // Retirez "imported" du nom si nécessaire
                String nom = matcher.group(3).replace("imported ", "").trim();
                produit.setNom(nom);

                produit.setPrix(prix);

                Categorie categorie = obtenirCategorieDepuisNom(nom);
                produit.setCategorie(categorie);

                produits.add(produit);
            }
        }

        return produits;
    }





}


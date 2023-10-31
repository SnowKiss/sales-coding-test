package com.skaldia.salescodingtest.config;

import com.skaldia.salescodingtest.model.Categorie;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "taxe")
public class ConfigTaxes {

    private double importation;
    private double base;
    private List<Categorie> categoriesExemptees;

    // Getters & Setters
    public double getImportation() {
        return importation;
    }

    public void setImportation(double importation) {
        this.importation = importation;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public List<Categorie> getCategoriesExemptees() {
        return categoriesExemptees;
    }

    public void setCategoriesExemptees(List<Categorie> categoriesExemptees) {
        this.categoriesExemptees = categoriesExemptees;
    }
}

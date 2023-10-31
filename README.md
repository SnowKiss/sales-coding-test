# Sales Tax Service Documentation

## Introduction

L'objectif de ce service est de fournir un système automatisé pour calculer les taxes de vente sur différents articles. Les taxes sont appliquées selon des règles spécifiques détaillées ci-dessous.

## Architecture du service

J'ai choisi d'exposer un webservice en utilisant Java 21 avec le framework Spring Boot (version 3.1.5) pour une intégration et une exécution aisées. L'application a été construite et gérée via Maven.

## Configuration

Le fichier `application.properties` sert de point central pour configurer les divers taux de taxes et pour déterminer quelles catégories de produits sont exemptées de la taxe de base. Actuellement, les configurations sont les suivantes :

```
taxe.importation=0.05
taxe.base=0.10
taxe.categoriesexemptees=LIVRE,NOURRITURE,MEDICAMENT
```

## Catégories de produits

Les produits sont catégorisés pour faciliter l'application des taxes. Les catégories sont représentées sous forme d'énumération :

- LIVRE
- MUSIQUE
- NOURRITURE
- MEDICAMENT
- AUTRE

Les articles actuellement reconnus par le système incluent :

- book
- music CD
- chocolate bar
- box of chocolates
- bottle of perfume
- packet of headache pills

Il est important de noter que cette version ne gère pas les quantités d'articles. Un article est traité indépendamment de sa quantité.

## Gestion des exceptions

Une gestion basique des exceptions a été mise en place. Cependant, une exploration plus approfondie et une meilleure gestion des exceptions pourraient être envisagées dans des versions futures pour améliorer la robustesse du service.

## Comment utiliser le service ?

Le service est exposé à l'adresse suivante : `http://localhost:8080/api/recu`

### Exemple d'utilisation avec Postman:

**Requête** :
- Méthode : POST
- URL : `http://localhost:8080/api/recu`
- Body (raw text) :
  ```
  1 book at 12.49
  1 music CD at 14.99
  1 chocolate bar at 0.85
  ```

## Conclusion

Ce webservice offre une solution simple et efficace pour calculer les taxes de vente sur différents articles en fonction de catégories prédéfinies. Grâce à l'architecture modulaire basée sur Spring Boot, il est possible d'étendre et d'adapter le service à de futurs besoins et exigences.
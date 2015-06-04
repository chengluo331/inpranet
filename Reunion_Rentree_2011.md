# Tâches à faire #

  * donner les personnes intéressées par un éventuel stage :
    * Luo Cheng
    * _si vous êtes interéssés, rajoutez votre nom !_
  * répartir les macro-tâches
  * prévoir un macro-planning
  * effectuer des recherches sur les différentes problématiques
    * faisabilité
    * technologies
    * intégration dans l'existant

# Répartition des modules #

Objectifs :
  * répartir les membres du projet
  * paralléliser le travail

Règles :
  * mettez-vous où vous voulez, un module qui vous motive !
  * vous pouvez choisir plusieurs modules. Vu qu'il y a 5 membres et 4 modules il faudra certainement que certains membres travaillent sur 2 modules
  * **ce n'est pas définif.** L'idée est de déterminer les motivations de chacun et de voir comment on pourrait répartir le travail

## A. Comment stocker et déduire les habitudes géotemporelles ##

Recherches, étude de faisabilité : [Moteur Habitudes](Moteur_Habitutde.md)

Personnes intéressées :
  * Yiquan
  * Wenshao
  * XXX

## B. Comment stocker/requetter/définir les zones ##

Recherches, étude de faisabilité : [Moteur Zones](Moteur_Zones.md)

Personnes intéressées :

  * Yoann :
    * bien motivé par la problématique de la géolocalisation
    * quelques experiences dans les Systèmes d'Information Géographiques en entreprise
    * motivé pour utiliser une base de données spatiale (genre PostGIS)
  * Wenshao
  * XXX

## C. Comment analyser les documents ##

Recherches, étude de faisabilité : [Indexation Documents](Indexation_Documents.md)

Personnes intéressées :

  * Cheng
    * Rechercher algorithme d'indexation géographique des documents
  * Stéphane
    * intéressé par la recherche sémantique
  * XXX

## D. Comment récupérer les habitudes géotemporelles et proposer du contenu ##

Recherches, étude de faisabilité : [Client Mobile](Client_Mobile.md)

Personnes intéressées :

  * Cheng
    * expériences en développement pour Android
    * motivé pour développer un module en Android pour récupérer les coordonnées d'utilisateur
    * motivé pour analyser, définir les habitudes géotemporelles d'utilisateur
  * Yiquan
    * intéressé par le développement Android
  * XXX

# Questions pour M. Bourret #

Technique :

  * discuter de la distinction des modes "temps réel" et "prédictif"
    * "temps réel" => pas besoin de connaître les habitudes, on push de l'information sur la zone
  * problème de batterie par l'utilisation du GPS en continue sur le mobile. Quels sont les autres moyens (réseau GSM ?) ?
  * intégration à la solution existante
    * intégration dès le début ? après ? pas du tout ?
  * Sources des données géographiques(plan de ville) à nous de trouver? format? utiliser un connecteur existant pour injecter les données dans la base?
  * définir un model qui spécifie comment notre application mobile obtenir la localisation pour diminuer la consomption?
  * Source d'informations routières et promotionnelles? Donner une liste de sites des magasins à connecteur? Filtrer les informations avec expression rationnelles?  Le problème de classer les info en fonction de leur géolocalisation (ex, promo différent dans différents magasins pendant différentes périodes)

Projet :

  * quel doit être le livrable final ?
  * documentation technique à fournir ?
  * ajout de M. Bourret à notre mailing-list/code project ?
  * OK de mettre ces infos publiques ?
  * ...
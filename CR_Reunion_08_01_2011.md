Date : 08/01/2011

Durée : 10h30 - 12h

# Participants #

  * Yoann Buch
  * Wenshao Low
  * Cheng Luo
  * Stéphane Seng
  * Yi Quan Zhou

# Points de discussions #

## Architecture globale du nouveau système ##

Proposition d'une architecture globale de Yi Quan :

  * Un **Module Mobile**, envoi des informations (userID, date, latitude, longitude) vers un **Module Zone** ;
  * Le module Zone convertit les latitudes et les longitudes en zones à l'aide d'une **base de données de zones** ;
  * Transmission de (userID, date, zoneID) vers un **Module Habitudes** ;
  * Le module Habitudes ajoute ces informations à la **base de données d'habitudes** ;
  * Cela effectue également une requête vers le **Module Indexation** qui va envoyer les résultats vers le module mobile.

## Discussions et améliorations de cette architecture ##

  * Remarque sur le fait que l'enregistrement d'une habitude ne doit pas entraîner une requête :
    * Les requêtes s'effectueront automatiquement ou directement à l'initiative de l'utilisateur de l'application (+ il faut que l'utilisateur ait accès à internet donc il est nécessaire que ce soit dans ce sens) ;

  * Division du module Mobile en deux : une application et un service Android :
    * Le service se charge de la géolocalisation (+ stockage dans un cache des positions) ;
    * L'application ne se charge que des requêtes ;

  * Problème de la consommation en batterie du mobile :
    * À voir mais idée d'une interface de communication entre le mobile (ie l'application et le service) et les autres modules (à préciser...) ;

  * Remarque pour améliorer la détection des zones :
    * Ajout d'un filtre géographique (utilisation d'un historique) pour éliminer les mauvaises zones ;

  * Remarque pour la construction de la base de données des habitudes :
    * Effectuer une pondération des habitudes selon la distance entre la zone actuelle et la zone qui a été prévue ;
    * Rmq : Comment effectuer le calcul de cette distance sans communiquer avec le module Zone ?

  * Réflexion sur le mode temps réel :
    * Dans ce cas, le module Indexation ne fera pas de requêtes en se basant sur l'endroit où est supposé être l'utilisateur mais sur le lieu où il est actuellement ;
    * Si l'utilisateur n'est dans aucune zone, alors prendre les zones les plus proches.

## Réflexions sur le module d'indexation ##

  * Fonctionnement de la recherche de mots-clés (Fourvière, Part-Dieu, etc.) ou si on trouve une adresse :
    * Utilisation d'un Web Service (GeoNames par exemple) pour obtenir les coordonnées ;
    * Associer ces coordonnées à une/plusieurs zones ;
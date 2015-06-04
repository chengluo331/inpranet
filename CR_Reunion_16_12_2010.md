Personnes présentes :
  * M. Bourret (sopra)
  * M. Amghar (INSA)
  * M. Aubry (INSA)
  * Equipe :
    * Yoann Buch
    * Yi Quan Zhou
    * Wenshao Low
    * Cheng Luo
    * Stephane Seng

Table des matières :



# 1. Architecture existante #

## References ##

Documents consultables sur le répertoire partagé :
  * Dossier\_Architecture\_V1\_0.pdf
  * cdc\_metadonnees\_v0.3.pdf

## Modules ##

  * Aggrégation (récupération d'informations sur internet/intranet)
  * Adaptation (normalisation des données)
  * Indexation (moteur texte + moteur de vectorisation)
  * Requettage
  * Base de profil
  * Bus de services

# 2. Objectif du projet #

Déterminer les habitudes de l'utilisateur en termes de géolocalisation pour lui fournir/proposer du contenu adapté à la zone dans laquelle il se trouve ou dans laquelle il va se trouver.

Exemples :

  * “je roule sur l'autoroute A7, des informations sur les conditions de circulation m'ont été préalablement envoyé sur mon mobile”

  * “je vais habituellement faire mes courses à la part-dieu le Samedi après-midi, au moment ou je rentre dans le centre commercial des offres promotionnelles me sont proposées”

Des scénarios sont à proposer que ce soit pour du commercial ou de l'aide à la décision objective.

# 3. Nouvelle architecture #

Ce besoin nécessite d'adapter l'architecture existante.

  * Module indexation
    * indexation géotemporelle : où  et quand se trouve le document
    * base d'habitude : déterminer des habitudes dans les déplacements de l'utilisateur
  * Module requettage : attaqué par une application mobile (sous Androïd) :
    * upload des informations géotemporelles de l'utilisateur (id utilisateur, longitude, latitude, date)
    * download d'informations/documents en lien avec la zone dans laquelle l'utilisateur se trouve ou va se trouver
  * Les modules d'aggregation, d'adaptation sembleraient suffire.


# 4. Découpage du projet #

Le projet peut se découper en 4 problématiques distinctes :

## A. Comment stocker et déduire les habitudes géotemporelles ##

  * Où est-ce que l'utilisateur est ?
  * Où est-ce qu'il va être ?
  * Qu'est ce qui va l'intéresser dans une heure ? dans une semaine ?
  * Données à stocker : longitude, latitude, date, id utilisateur
  * Trouver des habitudes par rapport au jour de la semaine, à l'heure, à la zone
  * Possibilité de pondérer la fiabilité par un pourcentage (évenement plus ou moins probable)

## B. Comment stocker/requetter/définir les zones ##

  * Par des coordonnées ? (polygone)
  * Le domaine d'études se bornera à une région volontairement limitée dans un premier temps : lyon et ses alentours.
  * Découpage en zones fonctionnelles (ex : autoroute A7, centre ville, etc.)
  * Utilisation d'une base de données spatiale. Interêts : indexation des données intégrée et requêtes/opérations adaptées aux besoins (ex : à partir d'une longitude/latitude donner la zone qui l'englobe).
  * Possibilité d'utiliser [PostGIS](http://postgis.refractions.net/)

## C. Comment analyser les documents ##

  * Extraire les données géo-temporelles
  * Associer le document à une zone à l'aide d'adresses ou de mots clés (ex : fourvière, part-dieu, mairie de lyon, etc.)
  * Associer au document une plage de temps (date de début, durée/date de fin)
  * Stocker ces informations

## D. Comment récupérer les habitudes géotemporelles et proposer du contenu ##

  * Développement d'une application pour téléphone mobile (Android)
  * 2 rôles :
    * service dédié à la récupération des données géospatiales de l'utilisateur
    * affichage/présentation du contenu requetté/pushé
  * Utilisation d'un web service de type Rest ?
  * Problématique du flux de données : il faudra surement proposer un résumé avant de faire circuler l'information/document dans sa totalité.

## Contraintes ##

  * Chaque module devra être préalablement décrit en termes d'entrées et de sorties comme un service.
    * Exemple d'entrées : longitude/latitude/id utilisateur
  * Ces modules doivent pouvoir être configurables (ex : proposition d'informations en temps réels ou fixées sur un futur proche : par anticipation)
  * Rester simple ! Ce projet doit rester une étude de faisabilité. Cependant toute idée d'amélioration devra être archivée.

# 5. Organisation du projet #

  * MOA : M. Bourret pour Sopra
  * MOE : 5 personnes dont 1 chef de projet
  * 1 réunion hebdomadaire entre le chef de projet et la MOA
  * 1 réunion mensuelle avec l'ensemble de la MOE et la MOA

# 6. A faire #

  * Mettre en place un espace collaboratif (partagé avec la MOA) :
    * google group pour la liste de diffusion
    * repertoire partagé sur google document
    * google code project
  * Prévoir un planning
  * Répartir les macro-tâches
  * Effectuer des recherches, élaborer des ébauches de solutions
  * Préparer des questions
  * Mettre en place/organiser ce 1er RDV (Jeudi de la rentrée, 14h)
  * Stage à la clé. Déclarer les personnes intéressées au plus tôt
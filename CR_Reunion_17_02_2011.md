Date : Jeudi 17/02/2011<br />
Durée : 14h - 15h30

# Participants #

MOA :
  * M. Bourret

MOE :
  * Yoann Buch
  * Wenshao Low
  * Cheng Luo
  * Stéphane Seng
  * Yi Quan Zhou

# Points abordés #

= Premiers retours des spécifications fonctionnelles

  * Il n'y a pas de cookie dans une application mobile (remplacer _cookie_ par _jeton_).
  * De même, remplacer _cache_ par _mémoire locale_.
  * Le login et le mdp crypté sont stocké en local. Le client mobile envoie le login en clair et le mdp crypté au système central.
  * Parler de durée d'inactivité de la session.
  * Le client mobile échange des données avec le serveur en format JSON. A l'intérieur du système central, on fait SOAP. Le service frontal s'occupe de faire la transformation et l'aiguillage.
  * On ne réalise pas la fonctionnalité bookmark.
  * Le calcul des zones à partir de la longitude et la latitude se fera chaque nuit et non pas en temps réel.
  * Une zone est englobée par une autre et inversement à corriger.


# Stage #
Stéphane transmet les documents nécessaire à Sébastien

# Prochaine réunion #
La prochaine réunion est fixée au Jeudi 17 Mars

# Travail à faire #
> Il faut monter le web service pour tous les modules
  * Mobile : arriver à envoyer des données géo-temporelles
  * Service frontal: faire toute les fonctionnalités. Donc service d'authentification et la conversion des données de JSON à xml.
  * Indexation: réaliser la détection de date et la localisation
  * Zone: préparer une base remplie des données prêtes à utiliser. Réaliser la fonction qui renvoie des zones à partir d'une longitude et une latitude.
  * Habitude: arriver à faire des règles de calcul de base.

# Question technique #
  * Les données de tous les modules sont centralisées dans un même schéma d'une base de données.
  * On utilise Hibernate comme unité de persistence commune à tous les modules.
  * On peut utiliser soapUI pour les tests de scénarios.
  * utilisation de Spring/Hibernate
  * OSGI ?
  * Maven ou Ant


# Répartition des tâches #
  * Mobile: yiquan et Cheng
  * Service frontal: Yoann
  * Indexation : Stéphane et Cheng
  * Zones: Wenshao et Yoann
  * Habitude: Yiquan et wenshao
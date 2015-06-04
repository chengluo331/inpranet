Date : Samedi 16/01/2011
Durée : 10h30 - 12h10

# Participants #

MOE :
  * Yoann Buch
  * Wenshao Low
  * Cheng Luo
  * Stéphane Seng
  * Yi Quan Zhou

# Points abordés #

  * TimeSheet;
  * Séparation des espaces (Wiki et Google Doc);
  * Spécifications fonctionnelles;
  * Spécifications techniques;
  * Habitudes;
  * Indexations des documents;
  * Environnement de développement;
  * Mobile
  * Prochaine réunion.

# Timesheet #
  * Faire dupliquer à partir de la feuill de Yoann.
  * Penser à les mettre à jour et bien séparer les différents types de tâches.

# Séparation des espaces(Wiki et Google Doc) #
### Sur Wiki ###
  * On ne mettera que des comptes rendu de réunions car les documents sur wiki sont pulics et c'est plus dur d'éditer sur wiki
  * Le compte rendu sera fait à tour de rôle. Les autres membres le compléteront.
### Sur Google Doc ###
  * On peut mettre tous les autres documents sur Google doc dans le dossier partagé Inpranet.
  * Il faut créer des repertoires pour bien ranger des documents
    * Gestion de projet
    * Draft
      * Un dossier par module
      * Un dossier commun
    * Livrable
  * On peut mettre des annotations dans les drafts
  * Une fois qu'un document est fini d'être rédigé et mis dans le dossier livrable, on est plus censé de le retoucher.

# Spécifications Fonctionnelles #
  * Finir le draft des spécifications fonctionnelles
  * Compléter les Exigences non Fonctionnelles/Choix imposés

# Spécifications techniques #
  * Mettre "Réflexions sur l'architecture"
  * Schéma d'architecture

# Habitudes #
### Stockage ###
  * Pour chaque enregistrement dans la base, on stock la date et l'heure d'entrée et sortie dans une zone d'un utilisateur.
> > ex:  16/01/2011 17:33 | 16/01/2011 19:10 | A | Poids (1)
  * On enregitre toujours l'heure du serveur même si l'utilisateur ne se trouve pas dans la même zone d'horaire que le serveur. Du coup, un chinois peut faire les courses à 1h de lundi matin. Mais il ne faut pas oublier de faire un calcul retrouver l'heure local afin de chercher des informations dans le module indexation.
  * Ajout d'un poid pour le problème de découpage de zone :
> > Tous les enregistrements ont le poid 1 par défaut. Quand par exemple une zone A se découpe en zone B et C, pour chaque enregistrement concernant la zone A, on supprime cet enregistrement et on ajoute un enregistrement de zone B et un autre de zone C avec la même date d'entrée et sortie mais chacun est affecté un poid 1/2.
> > > ex: 16/01/2011 17:33 | 16/01/2011 19:10 | B | Poids (1/2)
        1. /01/2011 17:33 | 16/01/2011 19:10 | C | Poids (1/2)

> > Si une zone se découpe en trois parties, ce poid sera 1/3.
> > Après ce découpage, les données peuvent être peu justes. Mais avec le système de pondération dans le temps qui fait que les anciennes données perdent de l'importance, les données vont se rapproacher de plus en plus de la réalité.

### Requettage ###
  * Utilisation d'une fonction de pondération (exponentiel -1) pour filtrer les résultats moins probable à cause de la longue distance entre la position actuelle de l'utilisateur et l'endroit où il peut y aller.
  * Précalcul : calculer une vue par utilisateur chaque nuit et stocker les zones dans lesquelles l'utilisateur pourra être. Les contenues sont à réflechir et à définir.
  * Trouver les requêtes possibles selon différentes périodicité d'habitudes d'utilisateur
> > Les deux requêtes de base sont: Où est l'utilisateur tous les jours à 10h? (habitude quotidienne)
> > > Où est l'utilisateur tous les samedi à 16h? (habitude hebdomadaire)

> > D'autres fréquences sont possibles: toutes les deux semaines, tous les lundi mercredi et vendredi etc... voir google agenda
> > Définir les priorité de ces périodes.

# Indexation de documents #
  * Problématique du stockage des documents avec les périodes ;
  * Lecture phrase par phrase des documents ;
  * Voir comment stocker les documents (XML ?) ;
  * Validité des informations

# Environnement de développement #

> A mettre en place:
  * PostGre SQL
  * CXF
  * ANT

# Mobile #
Les fonctions possibles:
  * Se Logger, délogger
  * Activer/Desactiver service
  * Activer/Desactiver cache
  * Fréquence de push
  * Mode offline, online
  * Activer/Desactiver notification
  * Configurer les préférences sur les catégories de documents
  * Localisation du cache (où stocker ? Limitation de la taille du cache ? possibilité de changer...)

# À faire #
  * Compléter l'architecture
  * Continuer la recherche, résoudre des problématiques relevés, faire des MCD si possible

# Prochaine réunion #
Il a été convenu de faire une réunion le **Jeudi 20/01/2011 à 16h** avec S.Bourret
pour faire le point sur ces différents problématiques
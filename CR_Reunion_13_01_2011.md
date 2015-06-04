Date : Jeudi 13/01/2011
Durée : 14h10 - 15h

# Participants #

MOA :
  * M. Bourret (par téléphone)

MOE :
  * Yoann Buch
  * Wenshao Low
  * Cheng Luo
  * Stéphane Seng
  * Yi Quan Zhou

# Points abordés #

  * Circuit de l'information ;
  * Priorité des axes de recherches ;
  * Livrables ;
  * Environnement de développement ;
  * Autres points abordés après la réunion téléphonique ;
  * Prochaine réunion.

# Circuit de l'information #

Description du schéma de l'architecture pour la faire valider.
Quelques points sont à revoir :

Général :
  * Interface entre le module Mobile et les autres modules : le module Profil sera une "porte d'entrée" dans le système, ce sera une interface qui proposera plusieurs services.

Module Zones :
  * Stockage des zones dans la base de données des habitudes : il faudrait en plus stocker les coordonnées (latitude, longitude) dans le cas où la base de Zones serait mise à jour ;
  * Pour chaque zone, effectuer un typage (donner des règles de priorités) dans le cas d'un chevauchement. Exemple d'une personne sur l'autoroute avec une offre commerciale à proximité.

Module Indexation :
  * Envoi de la date avec les requêtes : utile si on veut paramétrer l'horizon de planification ;
    * De même que pour la base de données des habitudes, il faudrait stocker les coordonnées en plus des identifiants de zones ;
    * OK pour le cache des coordonnées données par le Web Service (**que pour les mots-clés ou en général ?**).

Module Habitudes :
  * Raisonner plutôt sur des intervalles de temps pour enregistrer des données plutôt que sur une fréquence :
    * Si l'utilisateur reste toute une journée dans une même zone, on n'aura qu'une seule transaction (au lieu des 24\*frequenceParHeure dans l'autre cas) ;
    * Stocker les dates de début-fin zone.

# Priorités des axes de recherche #

Il faudrait au plus vite finir les recherches et développer des prototypes pour les points suivants :
  * Algorithmes prédictifs, prévisions temporelles (ça sera difficile, on le fait dès maintenant car ça risque de prendre du temps) ;
  * Comment extraire les adresses et les dates d'un document (c'est plus facile, à faire pour avoir une base + c'est le point clé du projet).

# Livrables #

On rappelle qu'il avait été prévu de rendre un premier draft des spécifications fonctionnelles pour le Lundi 17/01/2011.
  * Rappel de la demande d'un plan type pour les spécifications fonctionnelles.

# Environnement de développement #

Un document détaillant les procédures pour la mise en place d'un environnement de développement commun a été rédigé. Quelques indications ont été apportées par M. Bourret :
  * Préférer Ant à Maven. Maven n'offre pas assez de _workarounds_ en cas de problèmes + Compatibilité entre les structures de données entre Eclipse, Tomcat et Maven ;
  * Voir WTP (Web Toolkit Package) qui est le module d'Eclipse pour gérer les plugins ;
  * Petite question sur la version d'Android à prendre en compte : à partir de la version 2 (2.1, 2.2, etc.).

# Autres points abordés après la réunion téléphonique #

Discussions sur la maintenance des bases de données des habitudes et des documents (à cause de l'information sur la zone) :
  * Pas d'autre solution trouvée, demandera un temps important, la base de zones ne devra être mise à jour que très rarement ;

Problème sur les habitudes et sur le décalage horaire :
  * La base des habitudes stockera les heures selon une certaine base horaire, faire attention à cela en cas de décalage horaire ;

Problème sur la prédictabilité et les saisons :
  * Le comportement d'un utilisateur dépend assez fortement de la saison (exemple des promenades le matin en été # en hiver), même chose pour les vacances ;
  * Problème important à cause de la pondération de la base des habitudes, voir comment faire...

Étude sur les méthodes de stockage dans la base de données des habitudes :
  * Étudier les deux méthodes (découpage en plage horaire VS découpage en plages arbitraires) pour voir quels sont leurs avantages respectifs.

Réflexions sur l'indexation/l'analyse des documents :
  * Penser à filtrer les dates et les lieux trouvés dans le document, peut avoir des faux-positifs.

# Prochaine réunion #

Il a été convenu de faire une réunion le **Dimanche 16/01/2011 à 10h30**
pour faire le point sur ces différents problèmes, pour avancer sur les spécifications fonctionnelles et pour voir l'avancement des recherches sur les différents modules.
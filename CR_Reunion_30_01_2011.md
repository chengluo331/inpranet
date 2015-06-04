Date : Dimanche 30/01/2011
Durée : 10h30 - 12h00


# Participants #

MOE :
  * Yoann Buch
  * Wenshao Low
  * Cheng Luo
  * Yi Quan Zhou
  * Stephane Seng

# Points abordés #
  * Spécification détaillée
  * IHM mobile
  * Module indexation
  * Module habitude


# Spécification détaillée #
On va faire un seule spécification détaillé qui regroupe tout. L’intérêt du document est de savoir comment le système va fonctionner.

Revenu sur la dernière réunion: on garde les "module" en précisant les données en entrée, en sortie et le traitement.

TODO:
  * Ajouter le schéma de l'architecture
  * Compléter la glossaire
  * Compléter la spec chacun son côté


# IHM mobile #
Présentation de la première version de l'IHM mobile.
Le contenue de document sera un texte avec quelque balises HTML.

TODO:
  * Penser à rajouter une première page d'accueil(évènement de la dernière minute...) donc il n'y a pas d'image à afficher
  * Ajouter la fonctionnalité "Bookmark"
  * Déterminer la fonctionnalité "activer/désactiver la connexion"(choix de GPS? 3G?)
  * Ajouter la fonctionnalité "activer/désactiver le service en démarrage" (A réfléchir, possible de configurer à l'installation de l'application? choix par défaut?)
  * Déterminer la fonctionnalité de cache. (possible de désactiver? configurer la taille? vider la cache par utilisateur?)
  * Penser à ajouter les info geo-tempo(date, lieu)
  * Ajouter les différentes couleurs pour distinguer les évènements(passé, futur proche)
  * La configuration de l'horizon de temps sera différent par catégorie.
NB: l'application va chercher toujours les info dans la cache.


# Indexation #
On a pas besoin de contrôler les date fausses. Il va falloir alléger les regex. Pour chaque regex, il aura un thread de traitement.

Il faut prioriser la zone selon le nombre d’occurrences si plusieurs zones apparaissent dans un doc. L'admin va s'occuper les docs qui ne contiennent pas de zone.

Traitement multi-agent pour faire les Regex pour le TR.

Revenu sur la dernière réunion: les document en entrées sont en format XML pré-traité. Les sources de documents sont connu pas le système. On peut reparser le doc pour savoir l'adresse exacte.

Moteur habitude donne qu'une seule zone en réponse.

TODO:
  * Changement à faire: 1 document = 1 plage de temps
  * Faire le MCD pour les doc indexés.
  * Penser aux dates qui ont pas d'années.


# Habitude #
Pour une requête, la réponse est la zone la plus petite si les zones sont englobées. Pourtant, la complexité de calcul est énorme.

L'admin peut chevaucher les zones. Mais on préfère éviter le chevauchement.

TODO:
  * creuser la corrélation, la similarité entre deux évènements.
  * Réfléchir le chevauchement et le typage de zone.
  * Réfléchir sur le MCD du module habitude.


# Prochain réunion #
  * ???
Date : Jeudi 10/02/2011<br />
Durée : 14h - 16h30

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



# 1. Présentation du nouveau schéma d'architecture #

Le module Profil est à renommer : Il s'agit d'un **service frontal**.<br />
Il ne fait que des redirections pour l'instant : demain, on ne sait pas ce qu'il pourra faire, d'où l'**utilité d'utiliser un ESB.**

# 2. Entreprise Service Bus (ESB) et Service-Oriented Architecture (SOA) #

## 2.1. Problématique ##

Au départ, les SI étaient structurés en plusieurs couches (Présentation, Service, Persistance), chacune des couches ne pouvant communiquer qu'avec les couches connexes uniquement : **développement multi-couches (N-tiers)**.<br />
De plus, questions pour normaliser les couches de présentation (batch, Appli Web, UI...) -> Apparition des **services Web**, ou Web Services (WS).

Il existe un autre point de vue avec lequel les applications existent déjà, regroupées dans des Silots Applicatifs Métiers (SAM). On a alors la création d'applications Web qui vont faire les communications entre les SAM, via des WS -> **Plat de spagetthis**.

Fausse bonne idée : faire des WS CRUD -> **Accentuation du plat de spagetthis**.

## 2.2. Présentation des ESB ##

La solution qui est proposée pour résoudre les problèmes de communication est l'utilisation des ESB.

**ESB = Couche de médiation, de transformation et d'adaptation.**<br />
**Règle : Pas de métier dans les ESB. Si on enlève l'ESB, aucune partie métier ne doit être perdue.**

| Schéma |
|:--------|
Exemple :

Protocole applicatif du client :
```
<xml>
 <user></user>
 <produit></produit>
</xml>
```

↓ Services Publics : CXF - Biding Components<br />
↓ ESB : XSLT - Service Engine<br />
↓ Services Privés : CXF - Biding Components<br />

Protocole applicatif d'un SAM :
```
<xml>
 <user></user>
</xml>
```

L'ESB réalise une **chorégraphie des services** : qu'est ce qui doit être appelé avant, après. Connaît l'enchainement des processus. Peut également faire des transformation de protocoles techniques (REST -> CSV par exemple).

Remarque :
  * La configuration de l'ESB à spécifier.

## 2.3. Solutions existantes ##

Solutions Software :

  * Open Source :
    * ServiceMix/FuseESB ;
    * Petals ;
    * OpenESB ;
    * JBoss ESB ;
  * Propriétaire :
    * OSB Oracle ;
    * Websphere SOA IBM ;

Solutions Hardware :

  * Datapower IBM (optimisation totale).

API :
  * CAMEL (Apache) Approche Ressource utilisé dans ServiceMix ;
  * Spring Integration Approche Bean (ne pas utiliser).

## 2.4. Cas de notre projet ##

**Intérêt d'utiliser ServiceMix 4.2/FuseESB 4.3 dans le projet.**<br />
Intégration : Faire l'intégration avec le service frontal comme spécifié puis remplacer ce service par une passerelle (retransmet sans faire de modifications) puis par un ESB complet.

# 3. Scénario 1 : Enregistrement des positions #

## 3.1. Englobement des zones ##

L'idée de l'englobement des zones a été acceptée.

Par exemple :

  * Lyon (niveau 10) ;
  * Villeurbanne (niveau 20) ;
  * INSA (niveau 30) ;
  * IF (niveau 40).

Quand l'utilisateur fait une requête, on ne garde que la zone qui a le niveau le plus bas.

## 3.2. Chevauchement des zones ##

Désaccord sur le chevauchement. Idée d'origine : on ne permet pas le chevauchement des zones sur un même niveau et on définit des niveaux spéciaux (pour les autoroutes par exemple) pour permettre un chevauchement de 2 zones.

Idée MOA : On va plutôt définir et conserver pour chaque zone un **centre d'intérêt** (Zone : nom/identifiant + centre d'intérêt).
Retour des requêtes sur le module Zone : n zones, 1 zone par centre d'intérêt, la plus englobée dans chaque centre d'intérêt.
Plusieurs centres d'intérêts de zones : commercial, ville, etc.

De plus, si un évènement arrive, peut être rattaché à plusieurs zones.
Utilisateur : proposer les catégories qui l'intéresse

Zones découpées par centres d'intérêt : plusieurs découpages par ville.
On connait déjà les centres d'intérêts des documents à partir des sources.

## 3.3. Exemple de calcul des habitudes ##

Habitudes :
  * Lyon 100% 10 ;
  * Arrondissement 3 60% 20 ;
  * Part-Dieu 65% ;
  * Perrache 5% ;
  * A1 7%.

Seuil : 50% ;

Utilisation des englobements :
  * Arrondissement 3 60% ;
  * Part-Dieu 65% ;

Ici, les paramètres passés à Indexation seront : A3 | Part-Dieu et (H, H+1). Le retour est les documents associés aux CIVille, CICommercial, **CCentres d'affaires**.

Remarques :
  * Indexation : Si on sait que le document est Commercial, chercher les zones qui sont commerciales.
  * Stocker les deux zones (CIVille, CICommercial voire d'autres CI) dans la BD Habitudes.
  * **L'engloblement peut être calculé automatiquement avec SGBDGeo.**<br />
  * **Envoyer les centres d'intérêts avec la requête du client.**
  * Système de MaJ avec la répartition des pondérations sur les nouvelles sous-zones.
  * La somme des probabilités de présence pour les zones d'un centre d'intérêt est de 100% ;
  * Le filtrage par les seuils pourra être remplacé par un filtrage "n plus probables par catégorie" ;
  * On gardera quand même un petit filtrage (on accepte pas les 2 % par exemple).
  * Le filtrage des englobements sera fait par le module Zone.

## 3.4. Centres d'intérêts et catégories des documents ##

Lors de la phase d’agrégation, on force la catégorie d'un document.
Au final, Zone : Nom et Document : Texte, intervalle, zones (selon les centres d'intérêts).

Remarque :
  * 1 document avec une seule catégorie et 1 zone avec 1 catégorie.
Possibilité d'avoir des documents dans 2 catégories, pas trop sinon inutile.
  * Le forçage de la catégorie d'un document pourrait être évité en réutilisant ce qui a été fait dans l'ancien projet Inpr@net. Pas à faire pour le moment.

## 3.5. Précisions sur les BDD Décisionnelles ##

Pour les coordonnées brutes, pas de problème si stockage dans un fichier brut.

Pour le requêtage, idée d'un **précalcul des habitudes des utilisateurs toutes les nuits** + historisation (**pas de calculs/stockage sur 1 an -> 1 mois ou 2 mois**) : **voir si existe des solutions libres** et demander aux professeurs.

Remarque :
  * On pourrait même limiter le stockage sur 2 semaines si on ne veut que des vraies habitudes.

# 4. Scénario 3 : Injection de documents #

Ne pas oublier le **thésaurus**, avec les zones ou (longitude, latitude) (via un batch de lancement) -> À ajouter dans spécifications.

Remarques :
  * Coordonnées des lieux-dits.
  * Recherche sur les zones avec mots-clés et adresses -> voir si matching avec une seule zone ;
  * Filtrage des faux-positifs en utilisant la catégorie des zones selon la catégorie du document.

**Idée générale des améliorations : éliminer le plus possible en amont.**

# 5. Scénario 4 : Service de notification #

Comment est détecté un évènement urgent : Flag sur les documents, possibilité de flagger par l'administrateur.

# 6. Réponses aux questions de la MOE #

Idées :

  * Garder l'URI pour les documents stockés ;
  * Donner les dates dans l'IHM ou pas si surchargé ;
  * Donner zone aussi ? À voir, l'ergonomie sur les mobiles est un domaine assez flou pour l'instant.
  * Les onglets/catégories de l'IHM dépendent du paramètrage effectué par l'utilisateur.

Améliorations à faire :

  * Requête : Requête seulement si pas beaucoup de déplacement pour le service (Différence position initiale, position finale)... ;
  * Problème si changement de zones avec le système proposé ci-dessus : Distance zone >> Distance seuil non envoi.
  * Si arrêt du service et déconnexion, envoi d'un message pour finir l'habitude et la plage de donnée calculée.

Remarques :

  * Système non fait pour beaucoup de documents, pas besoin de beaucoup d'informations -> La quantité d'information à transmettre n'est pas aussi importante que ça ;

# 7. Réflexions sur le cache utilisateur au niveau du serveur #

Comment savoir quels documents à envoyer pour un client :
"Je sais les documents que tu as déjà" -> 1 Cache par utilisateur.

# 8. Réflexions sur l'identification des utilisateurs #

Idée : ne pas stocker d'informations permettant de retrouver l'utilisateur à partir des données qui sont stockées dans le système.
Éviter d'envoyer toujours les mêmes informations au serveur par client pour bloquer les tentatives d'écoutes).

Au final, on garde l'idée d'un login et d'un password pour un utilisateur.

Idée pour les 2 solutions : ne jamais transmettre le password en clair, toujours le crypter avec SHA.

Stocké par le profil : login, password, centres d'intérêt, documents déjà transmis à l'utilisateur.

## 8.1. Cryptage avec la date ##

Rappels (Types de cryptage) :

  * Bijectif : du message crypté on peut retrouver le message et vice-versa ;
  * Injectif : on ne peut plus revenir au password initial et le résultat crypté unique.

But : trouver un système de cryptage bijectif.

Password crypté par SHA injectif stocké en local : on ne s'intéresse pas à la vraie valeur du password mais qu'à sa version cryptée.

Au final, stocké dans le mobile : Login + Password -> SHA (avec 1 clé) ; dans le serveur : Password crypté, qui pourra être comparé avec le password crypté du client mobile.

On peut également, pour la Communication, utiliser un système faisant intervenir la date (pour éviter les écoutes) : Password crypté + cryptage en plus par un système bijectif qui va inclure la date et l'heure courante). Dans ce cas, il faut fournir la date du mobile en clair avec les requêtes pour le déchiffrement.

Système : Cryptage + Date -> Retrouve le password crypté.

Remarque générale :

  * De toute façon, on ne donne pas de priorité au cryptage dans le projet...

## 8.2. Système de sessionID ##

Protocole (Fonctionnement des serveurs d'applications Tomcat) :

  * Au démarrage, envoi du login + password crypté (SHA avec 1 clé) ;
  * Authentification (serveur) ;
  * Retourne un ID session conservé par le serveur ;
  * Pour toute requête suivante, on n'utilise que l'ID session ;
  * Côté serveur, garde mapping ID session ↔ ID Session.

Si déconnexion puis nouvelle connexion, on change de sessionID.

Remarques :

  * Profil : Permettre la création d'un compte, l'authentification ;
  * Ajouter les sessionIDs sur le schéma

# 9. Bilan et travail à faire pour la semaine prochaine #

Bilan :

  * Catgéorisation des zones : on doit parler de centres d'intérêts plutôt que de catégories pour les zones ;
  * Optimisation des communications : on peut optimiser les communications en effectuant le travail de filtrage en amont ;
  * Profil : on peut stocker le login, le password crypté, les infos sur les documents qu'a actuellement l'utilisateur et les centres d'intérêts (paramètres) dans le système pour filtrer directement.

Travail à faire :

  * Spécifications fonctionnelles à finaliser avant **Mercredi midi** pour qu'une première lecture puisse être faite avant la prochaine réunion ;
  * Essayer de trouver un découpage administratif (ou d'autres découpages) de Lyon, voir le format et voir comment importer dans le système (si ce n'est pas déjà fait).

Points à voir lors de la prochaine réunion MOA :

  * Valider les spécifications fonctionnelles ;
  * Spécifications techniques à faire après le développement ;
  * Définir les tâches de développement.

# 10. Prochaine réunion #

Il a été convenu d'une réunion le **Jeudi 17 février à 14 heures**, toujours au même lieu.
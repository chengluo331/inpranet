# Scénarios implémentés #

Envoi d'une coordonnées GPS  :

  * Mobile => Frontal (envoi donnes geospatiale)
  * Frontal => Habitude (envoi donnes geospatiale)
  * Habitude : stockage brut
  * Habitude <=> Zone (recuperation des zones de la coordonnées)
  * Habitude : stockage evenement

Gestion des documents ?

# BDD #

  * postgis
  * tables zones + habitudes

# Mobile #

**Fait :**

  * interface
    * onglets
    * afficher la liste des documents depuis la base de donneés
    * visualiser le document séléctionné
    * paramétrage
  * service: envoie des coordonnées GPS par intervalles de temps réguliers + distance minimum

**A faire :**
  * service: acquisition + sauvegarde des données en local -> priorité
  * finaliser la page de param
  * authentification
# Zone #

**Fait :**
  * definitions des zones depuis UDig
  * import dans la BDD
  * requete SQL spatial des zones à partir d'un point (à finir)
  * WS CXF

**A faire :**

# Service Frontal #

**Fait :**
  * WS CXF REST
  * Uniquement ajout coordonnées GPS
  * client CXF à partir de wsdl Habitude
  * JSON => Java => SOAP/XML

**A faire :**

# Habitude #

**Fait :**
  * service CXF ajout coodonnées brutes
  * persistance coordonnées brutes
  * persistance evenements
  * persistance habitude hebdomadaire

**A faire :**
  * quelques bugs à corriger pour la persistance evenements et habitude
  * intégrer les services pour les persistances définis
  * verifier construction d'objet commun aux differents modules (cf. Question)

# Indexation #

**Fait :**
  * localisation (par coordonnées)
  * datation (dates et expressions)
  * persistence document
  * recuperer documents suivant date aujourd'hui

**A faire :**
  * recuperer zone a partir coordonnées
  * prendre en compte les zones
  * Trésorus de l'ensemble des mots clés
  * Epurer le contenu du html
  * Utiliser un program chef qui appelle les autres services nécessaires pour injecter les documents dans la base de données

# Profil #

**Fait :**

**A faire :**

# Global #

**A faire :**

  * définir au plus tôt l'ensemble des interfaces de services/wsdl


# Question #

  * qui défini la classe java? qui défini le WSDL?
  * le frontal fait l'orchestration ? ou bien chaque module connait l'autre ?
Réponse: Le frontal fait l'orchestration. Les modules sont indépendants et inconscient des uns et des autres.
  * comment gérer les objets métiers transversaux aux modules ?
Réponse: utiliser un jar qui contient les objets java utilisé par plusieurs modules, les interfaces WS et wsdl. Ce jar sera partagé par tous les module.
  * stage pour Stéphane
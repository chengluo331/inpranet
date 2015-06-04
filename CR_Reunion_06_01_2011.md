Date : 06/01/2011

Durée : 14h - 15h30

# Participants #

MOA :
  * M. Bourret
MOE :
  * Yoann Buch
  * Stephane Seng
  * Cheng Luo
  * Wenshao Low
  * Yi Quan Zhou


# Points de discussion #

## Stage Sopra ##

Cheng Luo est intéressé par le stage.
M. Bourret rentrera en contact directement avec lui pour en discuter.

## Temps réel vs Prédicif ##

Il était question de connaître les motivations de M. Bourret vis à vis de ces deux modes de fonctionnement.

=> Le prédictif apporte une réelle valeur ajoutée (le concept temps réel n'est pas nouveau). De plus, si le mode prédictif est implémenté alors le temps réel l'est forcément.

Ce point a soulevé une nouvelle problématique : **la caractérisation des évenements**, quels évènements doivent être envoyés à l'utilisateur en temps réel ou bien en avance ?

Exemple : des évènements urgents comme un accident qui viennent de se produire à proximité de là où l'utilisateur conduit. Il s'agit d'évènements non prédictifs/instantanés et donc accessibles en temps réel.

Il faudra donc décider si l'on catégorise les évenements de manière plus fine (mais pas trop). Ex :
  * ludiques
  * commerciaux
  * de circulation (accident, grève de transport, etc.)
  * ...

C'est un élément à prendre en compte dans l'indexation des documents :
  * la source d'information est choisie et donc oriente la catégorisation

Mais aussi au niveau du client mobile :
  * quel type de présentation ?
  * ex : application mobile 20 minutes => simple, par catégorie
  * présenter sur la 1ere page les évènements urgents/importants ?


## Découpage des zones ##

Uniquement la région de Lyon.

Comment se procurer une carte de Lyon ?
  * pas besoin d'avoir une carte mais seulement les coordonnées des zones intéresssantes
  * partir d'une carte et déduire les zones (polygones), ensuite la carte ne sert plus
  * possibilité de récupérer la carte via [OSM](http://www.openstreetmap.org)  ? => données libre d'utilisation

Quoi découper ?
  * découpage des zones sur la carte de lyon => quelques zones
  * ex : tracé de l'A7, part-dieu, centre ville, 2e arrondissement, etc.
  * peuvent se chevaucher
  * créer un découpage intéressant pour les scénarii
  * viser simple, il faut que ça fonctionne

## Analyse de documents/Sources de données ##

Les sources de données (contrairement à l'ancien projet), doivent être ciblées. On recherche des informations pertinentes qui facilitent la démonstration de scénarios.

Exemples de sources :
  * centres commerciaux
  * [office du tourisme](http://www.lyon-france.com/)
  * [millénaire 3](http://www.millenaire3.com/)

=> cibler des données pertinentes, choisies mais pas trop

L'aggrégation peut se réduire à extraire manuellement des textes de pages web (pour notre prototype)

Documents full texte (page webs nettoyées) => fichier xml. Exemple de métadonnées :
  * coordonnées (ou zone ?)
  * dates
  * catégorie
  * titre
  * contenu

## Affectation d'un texte à une zone ##

Utiliser un WS qui, à partir d'une adresse, retourne lon/lat :
  * WS google ?
  * [geonames](http://www.geonames.org/) ?

Repérer des mots clés : perrache, part-dieu, etc. Donc il doit y avoir une correspondance zone <=> mots clés
=> peu de données mais suffisamment pour prouver que ça marche !

## Intégration à l'existant ##

Ce projet ne nécessite pas d'integration au système existant. Il s'agit uniquement d'un prototype qui fonctionnera indépendamment.

Par contre certains éléments pourraient être potentiellement utilisés pour aider dans l'analyse des documents => ex : aggrégateur/adaptation


## Consommation d'énergie sur le mobile client ##

C'est une problématique à prendre en compte. Le client devra emettre sa position par intervalles de temps (intervalles paramétrables par le client : 5min, 10min, etc.) => il faut donc utiliser (quasiment) tout le temps  le service de géolocalisation de l'appareil mobile, coûteux en énergie.

Pour l'instant :
  * GPS => priorité
  * par défaut GSM (beaucoup moins précis)
  * il est possible d'activer le GPS sans pour autant l'utiliser (c'est l'application elle même qui demande à mettre en marche le GPS ?)

## Mode déconnecté sur le client mobile ##

Le client mobile devra être capable de fonctionner en mode déconnecté (cas de l'absence de réseau 3G ou de wifi). C'est valable pour le service mais aussi pour l'application.

**Service**

Si le client est sans connexion, il y a perte potentielle de données géotemporelles de l'utilisateur. L'idée est de stocker temporairement ces données pour les envoyer dès que la connexion est rétablie.

**Application**

Les documents/informations provenant du serveur doivent être stockés pour que l'application soit capable de les réstituer en mode déconnecté.

Le stockage temporaire pourrait se faire grâce à [sqlite](http://sqlite.org/)


## Livrables ##

  * spec fonctionnelles (quoi) => aspect métier
  * spec techniques (comment), par module (WS, etc.) ex : WS soap, avec quel WSDL. BDD PostGis, schema d'architecture, structure de données échangées, contrats, etc.
  * modèles objets par modules (en annexes)
  * prototype (code source, etc.)
  * documentation d'exploitation (commment on compile, comment on installe, intègre, etc.)


### Spécification fonctionnelles ###

On a tous les éléments pour la rédaction :
  * besoins fonctionnels et non fonctionnelles

### Spécification techniques ###

  * Bien definir les roles des modules => qui fait quoi
  * Définir les contrats d'interface/les services proposés, les données E/S
    * ex pour le module des zones : récupere moi la liste des zones qui comprennent une coordonnée spécifiée
  * faire un diagramme de fux entre les différents modules
    * débouche sur contrat d'interfaces, structures de données
    * dans chaque bulle, identifier les tâches en vue de la réalisation du module
  * bien distinguer les communications
    * intra server : entre les modules (WS lourds, type SOAP)
    * serveur <=> client mobile : web service légér genre Rest/JSON ou bien encore plus léger par HTTP/protocole spécifique : ex CSV
  * cette analyse permettra de déterminer les charges plus finement
    * révision de la répartition des membres
    * fusion de modules ?

Garder à l'esprit, pour chaque module : Quoi, avec Qui, Comment

Pistes pour l'architecture technique :
  * serveur web tomcat
  * 2 servlets simples E/S pour communication avec le client
  * BDD spatiale type PostGIS
  * communication par WS CXF (framework WS) + WSDL (description WS)
    * faire le WSDL avant de monter le WS
    * ou bien faire l'objet qui fait le WSDL qui génére ensuite le WS ?
  * utilisation d'eclipse : intégration de tomcat, etc.

## Problématique des données privées ##

Se renseigner sur les potentielles problématiques liées aux données privées.
  * déclaration à la CNIL nécessaire ? (normalement non, si les données ne peuvent pas être rattachées à une personne physique)
  * utilisation d'une identification anonyme :
    * identifiants SIM ou de l'appareil mobile => problème si l'utilisateur change d'appareil => perte des infos
    * solution : authentification par simple login pour l'instant (pas de mot de passe)
    * cryptahe SHA1 login/mdp permettrait de ne rien connaitre ?

## A faire ##

MOA :
  * transmettre les documents exemples (différence entre spécifications fonctionnelles et techniques)

MOE :
  * ajout M. Bourret aux outils collaboratifs (mailing-list, google code project, google document)
  * RDV téléphonique MOA Jeudi 13 Jan 14h => retransmettre les coordonnées téléphoniques
  * RDV MOE Samedi 8, 10h30 - 12h à la médiathèque de l'INSA

Deadlines :
  * 17 Jan, spec. fonctionnelles/métiers => à mettre sur google doc
  * semaine prochaine : avancer sur les plans des documents de spécifications fonctionnelles et techniques pour les présenter au RDV
  * rendu des spec. techniques à déterminer
## Contenu ##

Ressources :

  * MCD
  * Schémas d'architecture (2)
    * scénario 1 : stocker geo-position
    * scénario 2 : recuperer documents

Thèmes :

  * ~~objectifs de l'application~~
    * ~~intégrer :~~
      * ~~mobilité~~
      * ~~localisation~~
      * ~~habitudes~~
  * ~~nombreux scénarios possibles~~
  * ~~objectifs de l'équipe~~
    * ~~dimension client~~
    * ~~projet complet : spécifications + code~~
    * ~~projet de longue durée~~
    * ~~travail d'équipe~~
    * ~~challenge technique~~
    * ~~mise en application des concepts vus en IF~~
    * ~~faire un produit simple mais fonctionnel~~
  * ~~spécification~~
    * ~~specifications fonctionnelles détaillées~~
  * choix technique
    * architecture distribuée, SOA
    * communication par WS, SOAP et REST
    * Java, Spring, Apache CXF
    * ~~Tomcat~~
    * Android
    * PostgreSQL/PostGIS
    * sqlite
  * environnement de developpement/test
    * ~~Eclipse : plugins spring, maven, svn,~~
    * ~~Jetty~~
    * ~~Maven~~
    * ~~SVN~~
    * ~~soapUI~~
    * ~~uDig~~
  * ~~montée en compétence~~
    * ~~couteux en temps mais bénéfique~~
  * ~~développement~~
    * ~~repartition en modules~~
    * ~~dev et test par module~~
    * ~~integration via bus~~
  * ~~suivi de projet~~
    * ~~souple~~
    * ~~repartition par envie~~
    * ~~réunions MOE et MOA~~
    * ~~communication quotidienne en direct ou email~~
    * ~~sessions de travail en équipe~~
    * ~~timesheet~~
  * la suite
  * parralèles avec le programme de IF
    * SOA
    * BDD sql/admin
    * BDD semi structurées
    * BDD décisionnel/data-mining
    * Java
    * similitudes avec engineering, SOA, PLD
  * bilans (humains, techniques, etc).

## Demo ##

Deux scénarios :

  * scénario 1 : stocker geo-position
  * scénario 2 : recuperer documents

Pour chaque scénario :
  * traces d'execution sur modules
  * parallèle avec la BDD
  * parallèle avec les schémas d'architecture

Plusieurs machine ? (si réseau)
Avantage d'une machine : présentation à l'écran de toutes les executions

Présenter l'IHM avec le mobile de Stéphane


## Donnees test ##

  * 1 seul utilisateur
  * 1 seul interet
  * 4-5 documents
  * geopositions : uniquement sur la plage du Jeudi 31 de 14h-18h

Solutions :
  * rentrer les données manuellement
  * automatisée :
    * creation d'un shapefile dans uDig avec les features : user\_id, date\_time et coordinate
    * chargement du shapefile via sql dans la table position
    * execution d'une procédure stockée qui parcourt les positions, et pour chaque position parcourt les zones pour peupler la table habit
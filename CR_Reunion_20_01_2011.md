Date : Jeudi 20/01/2011
Durée : 16h00 - 18h00


# Participants #

MOE :
  * Yoann Buch
  * Wenshao Low
  * Cheng Luo
  * Yi Quan Zhou

# Points abordés #
  * Spécification fonctionnelle;
  * Indexation document préliminaire proposé par Stéphane;
  * Moteur habitude et zone :
    * Problème de poids affecté lors de découpage zone;
    * Réflexion sur la faisabilité de moteur d’habitude en partant de MCD;
    * Problème de chevauchement de zone;

# Spécification fonctionnelle #
  * D’après les modèles proposés par M. Bourret, notre spécification fonctionnelle version1 n’est pas assez détaillée.
  * Problème de vocabulaire entre spec fonctionnelle vs. spec technique ?

  * A décider : quoi mettre dans spec fonctionnelle et spec technique.

# Indexation document préliminaire #
  * Le code proposé par Stéphane marche bien pour trouver une date de la forme 01/01/2011
  * Ajouter des contraintes sur les dates par exemple 50/50/5005 n’a pas de sens.

  * A faire : Trouver des regexp plus générique et mettre des contraintes.

# Moteur habitude et zone #
**Découpage zone**

  * Problème de poids affecté lors de découpage de zone A en B et C (cf. CR\_Reunion\_16\_01\_2011)

  * Raison : la prédiction va être fortement perturbé s'il y a une autre zone (D par ex.) qui devient la zone le plus probable après la découpage (poids 1/2 va diminuer fortement la possibilité de B et C face à D).

  * Proposition : remplacer le poids (zone) par un stockage de coordonnées utilisateur en définissant une distance de déplacement.

  * Scénario :
    * On récupère les coordonnées d’un utilisateur U1 (qui est dans la zone A) à un moment t. On défini une distance de déplacement minimal de 4m.
    * Il se déplace dans la zone. A chaque récupération de position de U1, on compare avec les coordonnées stockées auparavant pour voir si on stocke une nouvelle ligne de sa position.
    * Lors de découpage de zone A en B et C, on peut recalculer l’habitude de U1.

**MCD**
  * [modèle MCD](https://doc-0g-7s-docs.googleusercontent.com/docs/secure/mk0g0uco09e0dpubkqkmb98kmfq5ltf5/5leegof2omi15oaggiq074mk84bj2d9t/1295632800000/03207346889125339895/03207346889125339895/0B8VOBMwtxcZQMmVlZTcyM2MtYTdiOS00YWM2LWJhOWQtMjg3NTBmNjE4YWMw?nonce=l5sjnt64q675a&user=03207346889125339895&hash=oogqautlupms7bb02gacno6cf6iopugl)

**tables correspondants**
  * table User
    * _id\_User_ | _zone courante_ | _date entrée_

  * table Position-utilisateur
    * _id\_User_ | _date_ | _longitude_ | _latitude_

  * table Evénement
    * _id\_User_ | _date\_deb_ | _date\_fin_ | _id\_zone_

  * table Zone\_utilisateur
    * _id\_User_ | _zone_



**Problème de chevauchement de zones**

  * On pourra avoir plusieurs zones qui se chevauchent (tunnel et part-dieu) ou qui s’englobent (Département IF de l’INSA et INSA).
  * Le stockage d’habitude d’un utilisateur sur ces zones pose donne un nouveau problématique.
  * Scénario : (on prendra des chiffres comme des dates pour simplifier)
    * _Date_ | _User_ | _Zones_
    * 100 | U1 | A
    * 110 | U1 | B
    * 120 | U1 | B, D
    * 130 | U1 | B
    * 140 | U1 | C

  * Remarque : on pourra voir B comme département IF et D comme INSA

  * Solution proposé : utilise une table de zone\_utilisateur pour un instant donné (cf. MCD)

# Prochain réunion #
  * 28/01/2010
  * à vérifier car l'absence de Wenshao et Cheng est prévu.
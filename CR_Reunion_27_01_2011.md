Durée : 14h - 16h00

## Participants ##

MOA :
  * M. Bourret
MOE :
  * Yoann Buch
  * Stephane Seng
  * Yi Quan Zhou

## Spécifications fonctionnelles ##

Présentation de la nouvelle version des spécifications fonctionnelles détaillées.

M. Bourret nous a exposé son expérience vis à vis des documents contractuels : appels d'offre publics VS appels d'offres privés, notion de précédence dans les documents (CdC > specifications > ...).

Importance du vocabulaire choisi : faire un glossaire (document, donnée géo-temporelle, etc.) => enlever toute ambiguité

Deux options :
  * on modifie le document initial
  * on garde le document initial = spec. générales et on établit un autre document pour les spec. détaillées (sans les besoins non fonctionnels, plus précis, etc.)

Ok pour le plan. L'idée est de présenter chaque fonction comme un module de traitement : des données en entrées => traitement => données en sorties et exceptions.

Bien travailler la partie "Mécanisme général" => faire un diagramme de flux entre les modules => vue générale. Ok pour mettre l'architecture globale que l'on a réalisé => à simplifier par contre

Ok de parler de "modules" ou de "Web services" => on dit ce que l'on veut, on essaie d'éviter la possibilité d'avoir de trop grosses différences d'implémentation.

Bien inclure les maquettes IHM pour l'application mobile => maquettes en blocs simples => on veut juste mettre en évidence les informations à présenter et les actions accessibles => peut être fait sous powerpoint

## Indexation ##

**L'indexation ne se fera pas sur du full text** mais sur un fichier pré-traité que l'on peut considéré structuré comme suit :

```
<root>
  <header>
    <données geo suffisant="true/false">
      <!-- représente les données du connecteur/aggrégateur => on sait donc d'ùou vient l'information, l'adresse précise  est donc connue. Ex: document extrait du centrel commercial Part-Dieu => je connais son adresse-->

      <!-- données géo brutes, formats hétérogènes -->
    </données geo>
    <données temporelles suffisant="true/false">
      <!-- idem, les dates ont été déduites -->
    </données temporelles>
    <titre></titre>
    <date creation></date creation>   
    <id></id>
  </header>
  <texte>
     <!-- partie optionnel si le header est considéré comme suffisant : on sait deja le lieu et la plage de temps -->
     contenu du document. HTML nettoyé ?
  </texte>
</root>
```

**Conflits sur les lieux ou dates**. Exemple : le 1er mot clé est part-dieu mais ensuite on parle de Villeurbanne. Régle à suivre :
  * le lieu ayant le plus d'occurence est selectionné
  * sinon si nb occurence pareil alors on prend le 1er

Pour les dates il faudra souvent **compléter l'année** car elle est généralement renseignée uniquement avec le jour et le mois. Attention donc au choix de l'année (suivant date courante).

**Stockage**.
  * titre
  * corps (sous quel forme, nettoyé ?)
  * métadonnées ?
  * ID => trouver un identifiant pertinent pour pouvoir éliminer les doublons. Si même ID alors on prend le dernier.

**Problématique du coût de l'analyse** => les optimisations ne doivent pas être nos préoccupations sur ce projet. Mais il existe de nombreuses solutions pour répondre à ce problème (surtout que l'on sera surement amené à faire de l'analyse en temps réel pour détecter à temps les évenements comme les accidents). Utilisation de threads, chaque thread traite une regexp ?

## Habitudes ##

Il est clair que le système n'est pas prévu pour l'échelle nationale mais à l'échelel d'une ville.

### Problématiques de l'englobement ###

Ok pour filtrer les zones qui englobent. EX. user X est dans zone A et B, or B englobe A, alors X est considéré uniquement dans A.

### Problématiques du chevauchement ###

Problèmes :
  * si user X est dans A, B et C (A,B,C se chevauchent), X est dans lesquelles ?
  * gestion de l'entrée et la sortie de plusieurs zones qui se chevauchent => calculs sales

Bilan de la reflexion :
  * il n'est **pas possible de chevaucher des zones** => on peut très bien s'en sortir à partir du moment où l'on considère qu'**une zone = plusieurs polygones**. L'administrateur découpe suivant le type de l'application (ex. 2 découpage possible pour le chevauchement zone commercial/autoroute => un axé circulation l'autre axé commercial)
  * **on peut chevaucher des zones uniquement si elles ne sont pas du même type d'habitude (considéré de même intérêt)**. EX. zones commerciales, zones d'evenements, zones de travaux, etc. C'est un piste mais ça reste à creuser surtout avec tous les impacts qui s'en suivent.

Typer des zones => regles les problèmes de zone du coté moteur habitudes ?

Typer des zones => le module d'habitude renverrait alors au module d'indexation potentiellement plusieurs zones (les zones ayant des types différents). Les habitutdes sont donc calculés en fonction des types d'habitudes (il faut donc veiller à filtrer les zones qui après calcul des habitudes ne sont pas viables). => ou est-ce que le user sera par type d'habitude ?

### Stockage ###

Ok pour stocker dans deux tables :
  * les habitudes (par zone)
  * les coordonnées brutes lon/lat

=> les optimisations mémoires ne sont pas notre problème !

### Détermination des habitudes ###

Notre point de vue : calcul de statistiques naif basé sur le nombre d'occurences de présence dans la zone sur une plage de temps donné pour tenir compte de l'historique complet.

Point de vue MOA : habitudes déterminées à partir de corrélation effectué entre J et J-1, et J et J - 1 semaine. Corrélation basée sur une matrice semblable à la notre (axe 1 : temps, axe 2 : jour de la semaine, croisement = zone).

Notre solution est ok mais on peut imaginer utiliser aussi ce concept de corrélation.

Pour l'instant on ne se focalisera uniquement sur les tendances journalières et hebdomadaires. Les autres tendances, plus complexes (saisons, tous les 2 semaines, etc.) ne sont finalement pas utiles si on se remet dans le contexte de l'application => ce n'est pas grave de donner une info commerciale par erreur.

Idée pour la prise en compte du passé (données anciennes comptent moins mais inclure d'autre facteurs) => représenter l'importance des jours passés par une fonction dans le temps. Ex :
  * classique : maintenant = le plus important, 1 moment donné dans le temps (ex : 1 an) considéré comme sans valeur, progression "exponentielle"
  * prend en compte les saisons : une sinusoide qui diminue dans le passé mais qui accorde tout de même plus d'importance à la saison actuelle
  * hebdomadaire => idem que saison (sinusoidale) mais à l'échelle des jours. Ex: si calcul est fait sur un Lundi, Lundi actuel = coeff de 1, J-1 = dimanche = 0.2, mais J - 1 semaine = lundi précédent = 0.9.

## Suite ##

  * continuer specifications fonctionnelles
  * continuer les recherches sur l'indexation et les habitudes
  * Réunion MOE Dimanche 28/01 10h30 local ETIC
    * assignation des parties à faire dans les specs fonctionnelles
    * reflexion sur les problématiques d'indexation + habitudes

## Documents MOA ##

M. Bourret doit nous transmettre :
  * un exemple de maquette et les composants powerpoint pour en faire une
  * des bouts de code divers et variés (nettoyage d'un fichier HTML, etc.)
  * documentation inpr@net sur le stockage/indexation des documents
  * spec techniques
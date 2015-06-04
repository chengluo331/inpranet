# Script PostgreSQL #

  * À exécuter en ligne de commande dans le shell Postgre.

```
-- Schema: indexation

-- DROP SCHEMA indexation;

CREATE SCHEMA indexation
  AUTHORIZATION postgres;

-- Table: indexation."document"

-- DROP TABLE indexation."document";

CREATE TABLE indexation."document"
(
  id integer NOT NULL,
  title character varying,
  uri character varying,
  start_date timestamp without time zone,
  end_date timestamp without time zone,
  latitude real,
  longitude real,
  data character varying,
  CONSTRAINT document_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE indexation."document" OWNER TO postgres;
```

# Fichiers de tests #

## Document à analyser ##

### doc.xml ###

  * Emplacement : Ce fichier peut être placé n'importe où dans le système de fichiers

```
<?xml version="1.0" ?>
<document urgent="true">
	<metaData>
		<id>0123456789</id>
		<title>Titre du document</title>
		<uri>www.test.com</uri>
		<temporalCreationData>Document créé le 03-06</temporalCreationData>
		<temporalSourceData temporalSourceReliable="false">
			<temporalSourceStartData>02:45</temporalSourceStartData>
			<temporalSourceEndData>07:30</temporalSourceEndData>
		</temporalSourceData>
		<geographicalSourceData geographicalSourceReliable="true">
			INSA
		</geographicalSourceData>
	</metaData>
	<data>
		Ceci est un évènement qui aura lieu lundi, entre 03:40 et 10:00.
		La véritable date de fin a est fixee pour le jour du 2011-3-20, a midi.
	</data>
</document>
```

## Fichiers de configuration ##

### temporalRegexList.txt ###

  * Emplacement : C:\Temp

```
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Regex temporels (A classer par priorite)                                    %
% Mettre les plus grands d'abord et les sous-ensembles ensuite                %
% Pour l'instant, le fichier doit etre dans C:\Temp                           %
% Format : Regex \n Format Mapper                                             %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Jours                                                                       %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

(^| )\d\d\d\d-(\d?)\d-(\d?)\d
yyyy-MM-dd

(^| )(\d?)\d-(\d?)\d-\d\d\d\d
dd-MM-yyyy

(^| )(\d?)\d-(\d?)\d
MM-dd

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Heures                                                                      %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

(^| )(\d?)\d:\d\d
HH:mm
```

### temporalLexicalList.txt ###

  * Emplacement : C:\Temp

```
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Expressions lexicales temporelles (A classer par priorite)                  %
% Pour l'instant, le fichier doit etre dans C:\Temp                           %
% Format : Regex lexicale \n Texte remplaçant                                 %
%          Regex lexicale \n §Formule temporelle O y O M O d O H O m [O = s/e]%
% Rq : Dimanche = 1, Janvier = 1                                              %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Jours                                                                       %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

dimanche
§s 0 s 0 e 1 s 0 s 0

lundi
§s 0 s 0 e 2 s 0 s 0

lundi
§s 0 s 0 e 3 s 0 s 0

mardi
§s 0 s 0 e 4 s 0 s 0

mercredi
§s 0 s 0 e 5 s 0 s 0

jeudi
§s 0 s 0 e 6 s 0 s 0

vendredi
§s 0 s 0 e 7 s 0 s 0

janvier
§s 0 e 1 s 0 s 0 s 0

fevrier
§s 0 e 2 s 0 s 0 s 0

mars
§s 0 e 3 s 0 s 0 s 0

avril
§s 0 e 4 s 0 s 0 s 0

mai
§s 0 e 5 s 0 s 0 s 0

juin
§s 0 e 6 s 0 s 0 s 0

juillet
§s 0 e 7 s 0 s 0 s 0

aout
§s 0 e 8 s 0 s 0 s 0

septembre
§s 0 e 9 s 0 s 0 s 0

octobre
§s 0 e 10 s 0 s 0 s 0

novembre
§s 0 e 11 s 0 s 0 s 0

decembre
§s 0 e 12 s 0 s 0 s 0

hier
§s 0 s 0 s -1 s 0 s 0

demain
§s 0 s 0 s 1 s 0 s 0

mois prochain
§s 0 s 1 s 0 s 0 s 0

l'année prochaine
§s 1 s 0 s 0 s 0 s 0

l'annee prochaine
§s 1 s 0 s 0 s 0 s 0

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Heures                                                                      %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

matin
00:00 12:00

midi
12:00
```

### geographicalRegexList.txt ###

  * Emplacement : C:\Temp

```
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Regex geographiques (A classer par priorite)                                %
% Mettre les plus grands d'abord et les sous-ensembles ensuite                %
% Pour l'instant, le fichier doit etre dans C:\Temp                           %
% Format : Regex \n Format Mapper                                             %
% Attention aux valeurs negatives, a faire au cas par cas                     %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Latitude et longitude                                                       %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

-(\d?)(\d?)\d.\d\d\d\d\d\d, -(\d?)(\d?)\d.\d\d\d\d\d\d
l -1 l -0.000001 L -1 L -0.000001

-(\d?)(\d?)\d.\d\d\d\d\d\d, (\d?)(\d?)\d.\d\d\d\d\d\d
l -1 l -0.000001 L 1 L 0.000001

(\d?)(\d?)\d.\d\d\d\d\d\d, -(\d?)(\d?)\d.\d\d\d\d\d\d
l 1 l 0.000001 L -1 L -0.000001

(\d?)(\d?)\d.\d\d\d\d\d\d, (\d?)(\d?)\d.\d\d\d\d\d\d
l 1 l 0.000001 L 1 L 0.000001
```

### geographicalLexicalList.txt ###

  * Emplacement : C:\Temp

```
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Expressions lexicales geographiques (A classer par priorite)                %
% Pour l'instant, le fichier doit etre dans C:\Temp                           %
% Format : Regex geographique \n Texte remplaçant                             %
%          Regex lexicale \n § Indique qu'une recherche doit etre faite       %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Lieux celebres                                                              %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

INSA
45.781400, 4.872200

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Adresses                                                                    %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


```

## Types de documents ##

  * Documents qui traitent d’évènements et non actualités (sauf si on veut des articles “intéressants” : Problème sur la prédictivité des informations (agenda ET articles ?) ;
  * Durée de vie d’une information ? Penser à supprimer les infos en trop, périmées...
  * Catégories, en rapport avec un lieu et une date :
    * Actualités (société, politique, économie, international, technologies) ;
    * Évènements importants (traffic info ?) ;
    * Loisirs (culture, sport) ;
    * Shopping

## Sources pour les actualités ##

  * Journaux locaux :
    * http://www.20minutes.fr/ville/Lyon
    * http://www.lyoncapitale.fr/

  * Newsletter/flux RSS
    * ex: http://www.groupon.fr/deals/lyon
Q: pb de copyright
  * Service web API
    * ex: http://developer.shopping.com/
  * page web brut
    * utiliser l'expression rationnelle pour faire le filtrage de document(plus compliqué mais intéressant. A réfléchir !)
    * regex tester: http://regexpal.com/
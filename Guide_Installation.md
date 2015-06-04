  1. /DB\_admin/Sql :
```
// mise en place du contenu de la base de données

// linux
./db_init.sh

// windows
db_init.bat
```
  1. /core :
```
// creation du jar core et mis à disposition du jar dans le repository maven
mvn install
```
  1. /Service :
```
// lancement du module service sur le port par defaut : 8080
mvn jetty:run
```
  1. /Zone :
```
// lancement du module zone sur le port 9000
mvn -Djetty.port=9000 jetty:run
```
  1. /Habit :
```
// lancement du module habit sur le port 9001
mvn -Djetty.port=9001 jetty:run
```
  1. /Indexation :
```
// lancement du module habit sur le port 9002
mvn -Djetty.port=9002 jetty:run
```


## URL des Services ##

Service :
```
// WSDL 
http://localhost:8080/service/bus?wsdl
// WADL (description des web services REST
http://localhost:8080/service/public?_wadl&_type=xml
```

## Simulation requetes HTTP sur le service frontal ##

Télécharger curl : [curl](http://code.google.com/p/inpranet/downloads/detail?name=curl-7.21.4-ssl-sspi-zlib-static-bin-w32.zip&can=2&q=#makechanges)
```
// utilisation de l'utilitaire curl (curl.exe pour windows)

// simulation de la requete POST pour simuler l'envoi d'une donnée GPS par le mobile
// req.json contient la donner GPS sous format JSON (voir exemple dessous)
curl --header "content-type: application/json" --data @req.json  -X POST http://localhost:8080/service/public/geo/1234567890

// simulation de la requete GET pour recuperer les documents
http://localhost:8080/service/public/doc/1234567890
```


```
// exemple de donnee GPS sous format JSON
// aux environs de la Part-Dieu
{"geopos":{"longitude":4.857,"latitude":45.76,"time":"2011-03-27T16:47:15.523+02:00"}}
```

```
// exemple retour documents
{"document":[{"idDocument":13,"reference":"ELITE-2011","title":"La Part-Dieu accueille Elite Model Look","urgent":false,"categoriesList":{"idCategory":6,"name":"Shopping"},"uri":"http:\/\/www.centrecommercial-partdieu.com\/W\/do\/centre\/evenements-centre","start_date":"2011-02-05T00:00:00+01:00","end_date":"2011-08-06T00:00:00+02:00","latitude":45.76,"longitude":4.85967,"data":"- Decouvrez l`univers des top models : Conseils, make up, coiffure, colorimetrie, photos...\r\n- Prenez des cours de marche en talons\r\n- Devenez top model : Participez au concours international de la prestigieuse agence de mannequins Elite\r\nCasting Elite du samedi 05\/02 au vendredi 09\/04 de 11h a 14h en place centrale."}]}
```
#!/bin/sh

#Fichier pour initialiser la base de donnee
#pour que la commande psql puisse etre execute, il faut ajouter le variable d'environnement
#dans Path qui reference au repertoire postgreSQL\bin
url=localhost
db_name=postgis
password=soprasopra
username=postgres

echo `psql -h $url -d $db_name -U $username -W $password < ./creation_schema.sql` 
echo `psql -h $url -d $db_name -U $username -W $password < ./creation_profil.sql`
echo `psql -h $url -d $db_name -U $username -W $password < ./create_zone.sql`
echo `psql -h $url -d $db_name -U $username -W $password < ./load_zone.sql`
echo `psql -h $url -d $db_name -U $username-W $password < ./creation_document.sql`
echo `psql -h $url -d $db_name -U $username -W $password < ./creation_habit.sql`




# doc : http://postgis.refractions.net/docs/ch04.html
# Chargement du shapefile dans la bonne table
# shp2pgsql -s 4326 -c -D -G -I -W "utf-8" zone.shp zone.zone > load_zone.sql
# psql -d roadsdb -f roads.sql
#

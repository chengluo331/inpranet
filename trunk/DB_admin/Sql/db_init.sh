#!/bin/bash

#Fichier pour initialiser la base de donnee
#pour que la commande psql puisse etre execute, il faut ajouter le variable d'environnement
#dans Path qui reference au repertoire postgreSQL\bin
url=localhost
db_name='postgis'
password='soprasopra'
username='postgres'

# creation schemas
psql -h localhost -d postgis -U postgres -W soprasopra < creation_schema.sql

# creation tables
psql -h localhost -d postgis -U postgres -W soprasopra < creation_profil.sql
psql -h localhost -d postgis -U postgres -W soprasopra < creation_zone.sql
psql -h localhost -d postgis -U postgres -W soprasopra < creation_document.sql
psql -h localhost -d postgis -U postgres -W soprasopra < creation_habit.sql

# fonctions
psql -h localhost -d postgis -U postgres -W soprasopra < function_insertweeklyhabit.sql
psql -h localhost -d postgis -U postgres -W soprasopra < function_loadHabitFromPos.sql
psql -hlocalhost -d postgis -U postgres -W soprasopra < requete_gethabit.sql

# load some data
psql -hlocalhost -d postgis -U postgres -W soprasopra < load_interests.sql
psql -hlocalhost -d postgis -U postgres -W soprasopra < load_zone.sql
psql -hlocalhost -d postgis -U postgres -W soprasopra < load_positions.sql

# doc : http://postgis.refractions.net/docs/ch04.html
# Chargement du shapefile dans la bonne table
# /opt/PostgreSQL/9.0/bin/shp2pgsql.bin -s 4326 -c -D -G -I -W "utf-8" zone.shp zone.zone > load_zone.sql
# psql -d roadsdb -f roads.sql
#

# psql -h localhost -d postgis -U postgres -w soprasopra < creation_schema.sql

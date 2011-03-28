REM Fichier pour initialiser la base de donnee
REM pour que la commande psql puisse etre execute, il faut ajouter le variable d'environnement
REM dans Path qui reference au repertoire postgreSQL\bin
set url=localhost
set db_name=postgis
set password=soprasopra
set username=postgres

psql -h %url% -d %db_name% -U %username% -w %password%<./creation_schema.sql 
psql -h %url% -d %db_name% -U %username% -w %password%<./creation_profil.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./creation_zone.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./creation_document.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./creation_habit.sql

psql -h %url% -d %db_name% -U %username% -w %password%<./function_insertweeklyhabit.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./function_loadHabitFromPos.sql
psql -h %url% -d %db_name% -U %username% -W %password%<./requete_gethabit.sql

psql -h %url% -d %db_name% -U %username% -W %password%<./load_interests.sql
psql -h %url% -d %db_name% -U %username% -W %password%<./load_zone.sql

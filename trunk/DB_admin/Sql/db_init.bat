rem Fichier pour initialiser la base de donnee
rem pour que la commande psql puisse etre execute, il faut ajouter le variable d'environnement
rem dans Path qui reference au repertoire postgreSQL\bin
set url=localhost
set db_name=postgis
set password=soprasopra
set username=postgres

rem create schemas
psql -h %url% -d %db_name% -U %username% -w %password%<./creation_schema.sql 

rem create tables
psql -h %url% -d %db_name% -U %username% -w %password%<./creation_profil.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./creation_zone.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./creation_document.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./creation_habit.sql

rem create functions
psql -h %url% -d %db_name% -U %username% -w %password%<./function_insertweeklyhabit.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./function_loadHabitFromPos.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./requete_gethabit.sql

rem load some data
psql -h %url% -d %db_name% -U %username% -w %password%<./load_interests.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./load_user_habit_choice.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./load_zone.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./load_positions.sql
psql -h %url% -d %db_name% -U %username% -w %password%<./load_document.sql
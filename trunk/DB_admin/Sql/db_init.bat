REM Fichier pour initialiser la base de donnee
REM pour que la commande psql puisse etre execute, il faut ajouter le variable d'environnement
REM dans Path qui reference au repertoire postgreSQL\bin
set url=localhost
set db_name=postgres
set password=soprasopra

psql -h %url% -d %db_name% -U postgres<./creation_schema.sql
psql -h %url% -d %db_name% -U postgres<./creation_profil.sql
psql -h %url% -d %db_name% -U postgres<./creation_zone.sql
psql -h %url% -d %db_name% -U postgres<./creation_document.sql
psql -h %url% -d %db_name% -U postgres<./creation_habit.sql
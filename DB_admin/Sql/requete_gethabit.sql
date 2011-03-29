-- Function: habit.gethabit(integer, integer, integer, integer, integer)

-- DROP FUNCTION habit.gethabit(integer, integer, integer, integer, integer);

CREATE OR REPLACE FUNCTION habit.gethabit(uid integer, idinter integer, pday integer, phour integer, pminutes integer)
  RETURNS integer AS
$BODY$
DECLARE
	maxbuffer integer; --stocker la valeur de retour de requete
	zonebuffer integer;
	rtuser profil.habit_choice%rowtype;
	today_daily boolean; --variable de verification de choix utilisateur concernant le temps requetee
	
BEGIN
	--recuperer la valeur du choix du jour
	-- si c'est un choix habitude quotidien, on recupere la zone ou se trouve l'utilisateur quotidiennement 
	-- (ne prend pas en compte les habitudes hebdomadaire) pour un centre interet donné
	-- sinon, si c'est un choix habitude hebdomadaire, on recupere la zone d'un centre interet donné ou se trouve l'utilisateur le jour de la semaine
	SELECT daily INTO today_daily FROM profil.habit_choice WHERE user_id=uid AND day_of_week=pday;

	IF today_daily = TRUE THEN 
	SELECT MAX(sum), idzone INTO maxbuffer, zonebuffer FROM (
			SELECT SUM(nb_occurrence) AS sum, zone_id AS idzone FROM habit.weekly_habit, zone.zone, habit.interval, profil.habit_choice
				WHERE weekly_habit.zone_id=zone.id 
					AND weekly_habit.time_of_week=interval.id
					AND weekly_habit.user_id=habit_choice.user_id
					AND interval.day_of_week=habit_choice.day_of_week
					AND habit_choice.daily=TRUE
					AND weekly_habit.user_id=uid
					AND interest_id=idinter
					AND hour_of_day=phour
					AND pminutes between begin_minute and end_minute
					GROUP BY zone_id)
					AS sumbuffer
					GROUP BY idzone
					ORDER BY max DESC;
	ELSE
		SELECT MAX(nb_occurrence), zone_id INTO maxbuffer, zonebuffer FROM habit.weekly_habit, zone.zone, habit.interval, profil.habit_choice
			WHERE weekly_habit.zone_id=zone.id 
				AND weekly_habit.time_of_week=interval.id
				AND weekly_habit.user_id=habit_choice.user_id
				AND interval.day_of_week=habit_choice.day_of_week
				AND habit_choice.weekly=TRUE
				AND weekly_habit.user_id=uid 
				AND interest_id=idinter
				AND interval.day_of_week=pday
				AND hour_of_day=phour
				AND pminutes between begin_minute and end_minute
			GROUP BY zone_id
			ORDER BY max DESC;
	END IF;

return zonebuffer;


END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION habit.gethabit(integer, integer, integer, integer, integer) OWNER TO postgres;

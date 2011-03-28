-- Function: habit.gethabit(integer, integer, integer, integer, integer)

-- DROP FUNCTION habit.gethabit(integer, integer, integer, integer, integer);

CREATE OR REPLACE FUNCTION habit.gethabit(uid integer, idinter integer, pday integer, phour integer, pminutes integer)
  RETURNS integer AS
$BODY$
DECLARE
	maxbuffer integer;
	rtuser habit.habit_choice%rowtype;
	curs refcursor;
	today_daily boolean;
	
BEGIN
	SELECT daily INTO today_daily FROM habit.habit_choice WHERE user_id=uid AND day_of_week=pday;
	
	IF today_daily = TRUE THEN 
	SELECT MAX(sum) INTO maxbuffer FROM (
			SELECT SUM(nb_occurrence) AS sum FROM habit.weekly_habit, zone.zone, habit.interval, habit.habit_choice
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
					AS sumbuffer;
	ELSE
		SELECT MAX(nb_occurrence) INTO maxbuffer FROM habit.weekly_habit, zone.zone, habit.interval, habit.habit_choice
			WHERE weekly_habit.zone_id=zone.id 
				AND weekly_habit.time_of_week=interval.id
				AND weekly_habit.user_id=habit_choice.user_id
				AND interval.day_of_week=habit_choice.day_of_week
				AND weekly_habit.user_id=uid 
				AND interest_id=idinter
				AND interval.day_of_week=pday
				AND hour_of_day=phour
				AND pminutes between begin_minute and end_minute;
	END IF;

return maxbuffer;


END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION habit.gethabit(integer, integer, integer, integer, integer) OWNER TO postgres;

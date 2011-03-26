-- Function: habit.getweeklyhabit(integer, integer, integer, integer, integer)

-- DROP FUNCTION habit.getweeklyhabit(integer, integer, integer, integer, integer);

CREATE OR REPLACE FUNCTION habit.getweeklyhabit(uid integer, idinter integer, pday integer, phour integer, pminutes integer)
  RETURNS integer AS
$BODY$
DECLARE
	maxbuffer integer;
BEGIN

-- daily habit
IF pday=1 THEN
	SELECT monday FROM habit_habit_choice where user_id=uid;
END IF;


-- weekly habit
	
	SELECT MAX(nb_occurrence) INTO maxbuffer FROM habit.weekly_habit, zone.zone, habit.interval, habit.habit_choice
		WHERE weekly_habit.zone_id=zone.id 
			AND weekly_habit.time_of_week=interval.id
			AND weekly_habit.user_id=habit_choice.user_id
			AND weekly_habit.user_id=uid 
			AND interest_id=idinter
			AND day_of_week=pday
			AND hour_of_day=phour
			AND pminutes between begin_minute and end_minute;
			

return maxbuffer;


	
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION habit.getweeklyhabit(integer, integer, integer, integer, integer) OWNER TO postgres;

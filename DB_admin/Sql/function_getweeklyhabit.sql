-- Function: habit.getweeklyhabit(integer, integer, integer)

-- DROP FUNCTION habit.getweeklyhabit(integer, integer, integer);

CREATE OR REPLACE FUNCTION habit.getweeklyhabit(uid integer, ptime integer, idinter integer)
  RETURNS integer AS
$BODY$
DECLARE
	maxbuffer integer;

BEGIN
	SELECT MAX(nb_occurrence) INTO maxbuffer FROM habit.weekly_habit, zone.zone WHERE weekly_habit.zone_id=zone.id AND user_id=uid AND time_of_week=ptime AND interest_id=idinter;
return maxbuffer;
	
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION habit.getweeklyhabit(integer, integer, integer) OWNER TO postgres;

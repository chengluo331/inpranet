-- Function: habit.insertweeklyhabit(integer, integer, integer)

-- DROP FUNCTION habit.insertweeklyhabit(integer, integer, integer);

CREATE OR REPLACE FUNCTION habit.insertweeklyhabit(uid integer, ptime integer, pzone integer)
  RETURNS integer AS
$BODY$
DECLARE
	rt1 habit.weekly_habit%ROWTYPE;
-- 	rt2 habit.event%ROWTYPE;	
	curs1 refcursor;
-- 	uidbuf integer;
-- 	zoneidbuf integer;
BEGIN
	OPEN curs1 FOR SELECT * FROM habit.weekly_habit WHERE user_id=uid AND time_of_week=ptime AND zone_id=pzone;
	FETCH curs1 INTO rt1;
	IF rt1 is NOT NULL AND rt1.flag=0 THEN
		UPDATE habit.weekly_habit SET nb_occurrence = nb_occurrence+1, flag = 1 WHERE CURRENT OF curs1;
		CLOSE curs1;
		return 40;
	ELSE 
		IF rt1 is NULL THEN
			INSERT INTO habit.weekly_habit VALUES (uid,ptime,pzone,1,0);
			return 41;
		END IF;
	END IF;
 	
	return 42;

	
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION habit.insertweeklyhabit(integer, integer, integer) OWNER TO postgres;

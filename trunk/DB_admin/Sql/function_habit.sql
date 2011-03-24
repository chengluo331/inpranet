-- Function: habit.inserttimein(integer, timestamp without time zone, integer)

-- DROP FUNCTION habit.inserttimein(integer, timestamp without time zone, integer);

CREATE OR REPLACE FUNCTION habit.inserttimein(uid integer, tin timestamp without time zone, zoneid integer)
  RETURNS void AS
$BODY$BEGIN
	INSERT INTO habit.event(user_id, time_in, zone_id) 
		VALUES (uId, tIn, zoneId);
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION habit.inserttimein(integer, timestamp without time zone, integer) OWNER TO postgres;


-- Function: habit.inserttimeout(integer, timestamp without time zone, integer)

-- DROP FUNCTION habit.inserttimeout(integer, timestamp without time zone, integer);

CREATE OR REPLACE FUNCTION habit.inserttimeout(uid integer, tout timestamp without time zone, zoneid integer)
  RETURNS void AS
$BODY$
DECLARE
cursBuffer refcursor; 

BEGIN
	UPDATE habit.event SET time_out = tout WHERE user_id=uid AND time_out=NULL;
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION habit.inserttimeout(integer, timestamp without time zone, integer) OWNER TO postgres;


-- Function: habit.insertevent(integer, timestamp without time zone, integer, integer, integer)

-- DROP FUNCTION habit.insertevent(integer, timestamp without time zone, integer, integer, integer);

CREATE OR REPLACE FUNCTION habit.insertevent(uid integer, curtime timestamp without time zone, z1 integer, z2 integer, z3 integer)
  RETURNS void AS
$BODY$
DECLARE

BEGIN
	SELECT * FROM habit.temp_zones AS tab2 WHERE user_id=uid;

	IF tab2.zone1 != z1 THEN 
		SELECT habit.inserttimeout(tab2.user_id, curtime, tab2.zone1);
		SELECT habit.inserttimein(userNum, curtime, z1);
	END IF;
-- 	IF curZone2 != z2 THEN 
-- 		SELECT habit.inserttimeout(userNum, curtime, curZone2);
-- 		SELECT habit.inserttimein(userNum, curtime, z2);
-- 	END IF;
-- 	IF curZone3 != z3 THEN 
-- 		SELECT habit.inserttimeout(userNum, curtime, curZone3);
-- 		SELECT habit.inserttimein(userNum, curtime, z3);
-- 	END IF;

-- 	UPDATE habit.temp_zones SET zone1 = z1, zone2= z2, zone3 = z3 WHERE user_id=tab2.user_id;
	
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION habit.insertevent(integer, timestamp without time zone, integer, integer, integer) OWNER TO postgres;

-- Function: habit.loadHabitFromPos()
-- DROP FUNCTION habit.loadHabitFromPos();

CREATE OR REPLACE FUNCTION habit.loadHabitFromPos()
 RETURNS integer  AS
$BODY$
DECLARE
        c_pos REFCURSOR;
        rt_pos habit.position%ROWTYPE;
        
        c_zone REFCURSOR;
        t_idZone zone.zone.id%TYPE;
        
        idInterval integer;
        t_dow integer;
        t_hour integer;
        t_minute integer;

BEGIN
        OPEN c_pos FOR SELECT * FROM habit.position ORDER BY date_time;
        LOOP
                FETCH c_pos INTO rt_pos;
                EXIT WHEN NOT FOUND;
                        -- Recuperer id intervalle
                        SELECT EXTRACT(DOW FROM rt_pos.date_time) INTO t_dow;
                        SELECT EXTRACT(HOUR FROM rt_pos.date_time) INTO t_hour;
                        SELECT EXTRACT(MINUTE FROM rt_pos.date_time) INTO t_minute;
                        SELECT id INTO idInterval FROM habit.interval 
                                WHERE day_of_week=t_dow+1
                                AND hour_of_day=t_hour
                                AND begin_minute<=t_minute
                                AND end_minute>t_minute;
                                
                        -- Recuperer les zones contenant ce point
                        OPEN c_zone FOR SELECT id FROM zone.zone WHERE geog && rt_pos.point;
                        LOOP
                                -- Pour chaque zone
                                FETCH c_zone INTO t_idZone;
                                EXIT WHEN NOT FOUND;
                                        PERFORM habit.insertweeklyhabit(rt_pos.user_id, idInterval, t_idZone);                                                  
                        END LOOP;
                        CLOSE c_zone;
                
        END LOOP;
        CLOSE c_pos;
        RETURN 1;
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION habit.loadHabitFromPos() OWNER TO postgres;

select habit.loadHabitFromPos();

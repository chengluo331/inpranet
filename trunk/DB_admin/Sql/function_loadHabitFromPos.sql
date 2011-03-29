-- Function: habit.loadHabitFromPos()
/* La fonction permet de creer des donnees habitudes dans la table
	habit ¨¤ partir des donn¨¦es positions
*/

-- DROP FUNCTION habit.loadHabitFromPos();
CREATE OR REPLACE FUNCTION habit.loadHabitFromPos()
 RETURNS integer  AS
$BODY$
DECLARE
        c_pos REFCURSOR; -- Curseur position
        rt_pos habit.position%ROWTYPE; 
        
        c_zone REFCURSOR; -- Curseur zone
        t_idZone zone.zone.id%TYPE;
        
        idInterval integer; -- Identifiant d'intervalle de la date
        t_dow integer; -- le jour de la semaine
        t_hour integer; -- l'heure du jour
        t_minute integer; -- la minute
		t_week integer; -- la semaine de l'ann¨¦e
		current_week integer; -- la semaine courante du traitement

BEGIN
		current_week:=0;
		-- R¨¦cup¨¦rer tous les enregistrements dans la table position
        OPEN c_pos FOR SELECT * FROM habit.position ORDER BY date_time;
        LOOP
			-- Pour chaque position
                FETCH c_pos INTO rt_pos;
                EXIT WHEN NOT FOUND;
						
						-- Recuperer la semaine de la date
						SELECT EXTRACT(WEEK FROM rt_pos.date_time) INTO t_week;
						-- Si c'est une nouvelle semaine differente que la semaine courante,
						-- Remise a zero de tous les flags
						IF t_week <> current_week THEN
							UPDATE habit.weekly_habit SET flag=0;
							current_week:=t_week;
						END IF;
						
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
										-- Appel ¨¤ la proc¨¦dure stock¨¦e insertweeklyhabit
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


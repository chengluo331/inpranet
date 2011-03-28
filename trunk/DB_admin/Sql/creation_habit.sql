﻿-- Table: habit.position

-- DROP TABLE habit.position;

CREATE TABLE habit.position
(
  user_id integer NOT NULL,
  date_time timestamp without time zone NOT NULL,
  longitude real,
  latitude real,
  CONSTRAINT pkey_position PRIMARY KEY (user_id, date_time)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE habit.position OWNER TO postgres;


-- Table: habit.event

-- DROP TABLE habit.event;

CREATE TABLE habit.event
(
  user_id integer NOT NULL,
  time_in timestamp without time zone NOT NULL,
  time_out timestamp without time zone,
  zone_id integer,
  CONSTRAINT pkey_event PRIMARY KEY (user_id, time_in, zone_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE habit.event OWNER TO postgres;


-- Table: habit.weekly_habit

-- DROP TABLE habit.weekly_habit;

CREATE TABLE habit.weekly_habit
(
  user_id integer NOT NULL,
  time_of_week integer NOT NULL,
  zone_id integer NOT NULL,
  nb_occurrence integer NOT NULL,
  flag integer NOT NULL,
  CONSTRAINT pkey_weekly_habit PRIMARY KEY (user_id, time_of_week, zone_id),
  CONSTRAINT fkey_weekly_habit_interval FOREIGN KEY habit.interval(
)
WITH (
  OIDS=FALSE
);
ALTER TABLE habit.weekly_habit OWNER TO postgres;

-- Table: habit.interval

-- DROP TABLE habit.interval;

CREATE TABLE habit.interval
(
  id integer NOT NULL,
  day_of_week integer NOT NULL,
  hour_of_day integer NOT NULL,
  begin_minute integer NOT NULL,
  end_minute integer NOT NULL,
  CONSTRAINT pkey_interval PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE habit.interval OWNER TO postgres;

CREATE OR REPLACE FUNCTION init_interval() RETURNS integer
AS $$
DECLARE
        id integer;
        day_of_week integer;
        time_of_day integer;
BEGIN
        id := 1;
  FOR day_of_week IN 1..7 LOOP
        FOR time_of_day IN 0..23 LOOP
                INSERT INTO habit.interval
                VALUES (id, day_of_week, time_of_day, 0, 15);
                id := id + 1;
                INSERT INTO habit.interval
                VALUES (id, day_of_week, time_of_day, 15, 30);
                id := id + 1;
                INSERT INTO habit.interval
                VALUES (id, day_of_week, time_of_day, 30, 45);
                id := id + 1;
                INSERT INTO habit.interval
                VALUES (id, day_of_week, time_of_day, 45, 59);
                id := id + 1;
        END LOOP;
  END LOOP;

  RETURN 1;
END;
$$ LANGUAGE plpgsql;

SELECT init_interval();


-- Table: habit.habit_choice

-- DROP TABLE habit.habit_choice;

CREATE TABLE habit.habit_choice
(
  user_id integer NOT NULL,
  day_of_week integer NOT NULL,
  daily boolean NOT NULL,
  weekly boolean NOT NULL,
  CONSTRAINT pkey_choice PRIMARY KEY (user_id, day_of_week),
  CONSTRAINT fkey_choice FOREIGN KEY (user_id)
      REFERENCES profil."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE habit.habit_choice OWNER TO postgres;


-- Function: habit.fillchoice()

-- DROP FUNCTION habit.fillchoice();

CREATE OR REPLACE FUNCTION habit.fillchoice()
  RETURNS void AS
$BODY$
DECLARE
	uid integer;
	i integer;
	a boolean;
	b boolean;

BEGIN
	FOR uid IN 1001..1007 loop
		FOR i IN 1..7 loop
			a := trunc(random()*(1+1));
			IF a=TRUE THEN b:=FALSE;
			ELSE b:=TRUE;
			END IF;
				INSERT INTO habit.habit_choice VALUES (uid, i, a, b);
		END loop;
	END loop;
	
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION habit.fillchoice() OWNER TO postgres;

﻿-- Table: habit.position

-- DROP TABLE habit.position;

CREATE TABLE habit.position
(
  user_id integer NOT NULL,
  date_time timestamp without time zone NOT NULL,
  point geography(POINT,4326),
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
  CONSTRAINT pkey_events PRIMARY KEY (user_id, time_of_week, zone_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE habit.weekly_habit OWNER TO postgres;

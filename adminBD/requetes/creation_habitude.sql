-- Table: habitude.position

-- DROP TABLE habitude.position;

CREATE TABLE habitude.position
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
ALTER TABLE habitude.position OWNER TO postgres;


-- Table: habitude.event

-- DROP TABLE habitude.event;

CREATE TABLE habitude.event
(
  user_id integer NOT NULL,
  time_in timestamp without time zone NOT NULL,
  time_out timestamp without time zone,
  zone_id integer,
  CONSTRAINT pkey_events PRIMARY KEY (user_id, time_in)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE habitude.event OWNER TO postgres;


-- Table: habitude.weekly_habit

-- DROP TABLE habitude.weekly_habit;

CREATE TABLE habitude.weekly_habit
(
  user_id integer NOT NULL,
  day_of_week integer NOT NULL,
  time_of_day time without time zone NOT NULL,
  zone_id integer NOT NULL,
  nb_occurrence integer NOT NULL,
  CONSTRAINT pkey_events PRIMARY KEY (user_id, day_of_week, time_of_day)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE habitude.weekly_habit OWNER TO postgres;

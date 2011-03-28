-- Table: profil.user

DROP TABLE IF EXIST profil.user;

CREATE TABLE profil.user
(
  id SERIAL,
  login varchar(30) NOT NULL,
  password varchar(30) NOT NULL,
  CONSTRAINT pkey_user PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE profil.user OWNER TO postgres;

/*DROP TABLE IF EXIST profil.habitType;
CREATE TABLE profil.habitType
(
  user_id integer NOT NULL,
  monday integer,
  tuesday integer,
  wednesday integer,
  thursday integer,
  friday integer,
  saturday integer,
  sunday integer,
  CONSTRAINT pkey_habitType PRIMARY KEY (user_id),
  CONSTRAINT fkey_habitType_user FOREIGN KEY (user_id) REFERENCES profil.user(id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE profil.habitType OWNER TO postgres;*/


-- Table: profil.habit_choice

DROP TABLE IF EXIST profil.habit_choice;

CREATE TABLE profil.habit_choice
(
  user_id integer NOT NULL,
  day_of_week integer NOT NULL,
  daily boolean NOT NULL,
  weekly boolean NOT NULL,
  CONSTRAINT pkey_choice PRIMARY KEY (user_id, day_of_week),
  CONSTRAINT fkey_choice FOREIGN KEY (user_id)
      REFERENCES profil.user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE profil.habit_choice OWNER TO postgres;


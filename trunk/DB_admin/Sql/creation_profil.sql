-- Table: profil.user

-- DROP TABLE profil.user;

CREATE TABLE profil.user
(
  id integer NOT NULL,
  login varchar(30) NOT NULL,
  password varchar(30) NOT NULL,
  CONSTRAINT pkey_user PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE profil.user OWNER TO postgres;

-- Table: profil.habitType

-- DROP TABLE profil.habitType;
CREATE TABLE profil.habitType
(
  user_id integer NOT NULL,
  day_of_week integer NOT NULL,
  habit_type integer NOT NULL,
  CONSTRAINT pkey_habitType PRIMARY KEY (user_id, day_of_week, habit_type),
  CONSTRAINT fkey_habitType_user FOREIGN KEY (user_id) REFERENCES profil.user(id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE profil.habitType OWNER TO postgres;

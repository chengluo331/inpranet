-- Table: profil.user

-- DROP TABLE profil.user;

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

-- DROP TABLE profil.habitType;
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
ALTER TABLE profil.habitType OWNER TO postgres;


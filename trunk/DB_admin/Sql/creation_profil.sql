﻿-- Table: profil.user

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
ALTER TABLE habit.position OWNER TO postgres;
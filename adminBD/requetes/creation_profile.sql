-- Table: profile.user

-- DROP TABLE profile.user;

CREATE TABLE profile.user
(
  id integer NOT NULL,
  login varchar(30) NOT NULL,
  password varchar(30) NOT NULL
  CONSTRAINT pkey_user PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE habitude.position OWNER TO postgres;

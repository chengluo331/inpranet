-- Table: indexation.category

DROP TABLE indexation.category CASCADE;

CREATE TABLE indexation.category
(
  id integer NOT NULL,
  "name" character varying,
  CONSTRAINT category_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE indexation.category OWNER TO postgres;
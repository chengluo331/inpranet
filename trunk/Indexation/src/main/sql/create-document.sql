-- Table: indexation."document"

DROP TABLE indexation."document" CASCADE;

CREATE TABLE indexation."document"
(
  id integer NOT NULL,
  title character varying,
  uri character varying,
  start_date timestamp without time zone,
  end_date timestamp without time zone,
  latitude real,
  longitude real,
  data character varying,
  reference character varying,
  urgent boolean,
  CONSTRAINT document_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE indexation."document" OWNER TO postgres;
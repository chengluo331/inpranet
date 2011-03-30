-- Table: indexation.category

DROP TABLE IF EXISTS indexation.category CASCADE;

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

-- Table: indexation."document"

DROP TABLE IF EXISTS indexation."document" CASCADE;

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

-- Table: indexation.document_category

DROP TABLE IF EXISTS indexation.document_category CASCADE;

CREATE TABLE indexation.document_category
(
  document_id integer,
  category_id integer,
  CONSTRAINT category FOREIGN KEY (category_id)
      REFERENCES indexation.category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "document" FOREIGN KEY (document_id)
      REFERENCES indexation."document" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE indexation.document_category OWNER TO postgres;

-- Table: indexation.document_zone

DROP TABLE IF EXISTS indexation.document_zone;

CREATE TABLE indexation.document_zone
(
  document_id integer NOT NULL,
  zone_id integer NOT NULL,
  CONSTRAINT document_zone_document_id_fkey FOREIGN KEY (document_id)
      REFERENCES indexation."document" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT document_zone_zone_id_fkey FOREIGN KEY (zone_id)
      REFERENCES "zone"."zone" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE indexation.document_zone OWNER TO postgres;

-- Sequence: indexation.document_id_sequence

DROP SEQUENCE IF EXISTS indexation.document_id_sequence;

CREATE SEQUENCE indexation.document_id_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 23
  CACHE 1;
ALTER TABLE indexation.document_id_sequence OWNER TO postgres;

-- Sequence: indexation.document_id_sequence

DROP SEQUENCE IF EXISTS indexation.document_id_sequence;

CREATE SEQUENCE indexation.document_id_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE indexation.document_id_sequence OWNER TO postgres;

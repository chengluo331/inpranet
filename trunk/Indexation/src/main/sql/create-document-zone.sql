-- Table: indexation.document_zone

-- DROP TABLE indexation.document_zone;

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
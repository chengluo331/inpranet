-- Table: indexation.document_category

-- DROP TABLE indexation.document_category CASCADE;

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
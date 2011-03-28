
DROP TABLE IF EXISTS zone.interest;
CREATE TABLE zone.interest
(
  id SERIAL,
  name varchar(30),
  description text,
  CONSTRAINT pkey_interest PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE zone.interest OWNER TO postgres;

DROP TABLE zone.zone;

SET CLIENT_ENCODING TO UTF8;
SET STANDARD_CONFORMING_STRINGS TO ON;
BEGIN;
CREATE TABLE zone.zone (
  id serial NOT NULL,
  interest_id integer NOT NULL,
  geog geography(MULTIPOLYGON,4326),
  CONSTRAINT pkey_zone PRIMARY KEY (id),
  CONSTRAINT fkey_zone_interest FOREIGN KEY (interest_id) REFERENCES zone.interest (id)
);
CREATE INDEX "zone_geog_gist" ON "zone"."zone" using gist ("geog" gist_geography_ops);
COMMIT;

-- Table: zone.interest

-- DROP TABLE zone.interest;

CREATE TABLE zone.interest
(
  id integer NOT NULL,
  name varchar(30),
  description text,
  CONSTRAINT pkey_interest PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE zone.zone OWNER TO postgres;


-- Table: zone.zone

-- DROP TABLE zone.zone;

CREATE TABLE zone.zone
(
  id integer NOT NULL,
  interest_id integer NOT NULL,
  CONSTRAINT pkey_zone PRIMARY KEY (id),
  CONSTRAINT fkey_zone_interest FOREIGN KEY (interest_id) REFERENCES zone.interest (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE zone.zone OWNER TO postgres;
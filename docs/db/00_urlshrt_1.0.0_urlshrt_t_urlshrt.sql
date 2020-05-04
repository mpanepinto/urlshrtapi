-- Table: urlshrt_t_url_shortener

DROP TABLE IF EXISTS urlshrt_t_url_shortener CASCADE;

CREATE TABLE urlshrt_t_url_shortener
(
  id numeric NOT NULL,
  url character varying(4000) NOT NULL,
  code character varying(200) NOT NULL,
  data_inserimento timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT urlshrt_t_url_shortener_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE urlshrt_t_url_shortener
  OWNER TO @@user.name@@;
COMMENT ON TABLE urlshrt_t_url_shortener
  IS 'Tabella di censimento delle url "accorciate"';

-- Index: idx_id_url_shortener

-- DROP INDEX idx_id_url_shortener;

CREATE INDEX idx_id_url_shortener
  ON urlshrt_t_url_shortener
  USING btree
  (id);


-- Sequence: t_url_shortener_id_seq

DROP SEQUENCE IF EXISTS t_url_shortener_id_seq CASCADE;

CREATE SEQUENCE t_url_shortener_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 32
  CACHE 1;
ALTER TABLE t_url_shortener_id_seq
  OWNER TO @@user.name@@;

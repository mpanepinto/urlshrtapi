GRANT SELECT, UPDATE, INSERT, TRUNCATE, DELETE ON TABLE urlshrt_t_url_shortener TO @@user.name@@_rw;
GRANT SELECT, UPDATE ON SEQUENCE t_url_shortener_id_seq TO @@user.name@@_rw;

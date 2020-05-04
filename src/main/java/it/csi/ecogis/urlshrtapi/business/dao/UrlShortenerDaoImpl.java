/*******************************************************************************
 * Copyright CSI-Piemonte - 2017-2020
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.ecogis.urlshrtapi.business.dao;

import java.math.BigDecimal;

import it.csi.ecogis.urlshrtapi.exception.DaoException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerDaoImpl extends JdbcDaoSupport implements
		UrlShortenerDao {

	protected transient Logger LOG = Logger.getLogger(this.getClass().getSimpleName());

	@Autowired
	public UrlShortenerDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public String readUrlById(String id) throws DaoException {
		LOG.debug("[UrlShortenerDaoImpl::readUrlById] BEGIN");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT url FROM urlshrt_t_url_shortener where urlshrt_t_url_shortener.code = ?");
		String url = null;
		try {
			url = getJdbcTemplate().queryForObject(sql.toString(), new Object[] { id }, String.class);
		} catch (RuntimeException ex) {
			LOG.error(
					"[UrlShortenerDaoImpl::readUrlById] esecuzione query",
					ex);
			throw new DaoException("Query failed", ex);
		} finally {
			LOG.debug("[UrlShortenerDaoImpl::readUrlById] END");
		}
		return url;
	}
	
	private BigDecimal generateNewShortnedUrlKey() {
		LOG.debug("[UrlShortenerDaoImpl::generateNewShortnedUrlKey] START");
		final String sql = "select nextval('t_url_shortener_id_seq');";
		java.math.BigDecimal newKey = getJdbcTemplate().queryForObject(sql,
				java.math.BigDecimal.class);
		LOG.debug("[UrlShortenerDaoImpl::generateNewShortnedUrlKey] END");
		return newKey;
	}

	@Override
	public void insertShortenedUrl(String url, String id) throws DaoException {
		LOG.debug("[UrlShortenerDaoImpl::insertShortenedUrl] BEGIN");
		StringBuilder sql = new StringBuilder();
		java.math.BigDecimal newKey = generateNewShortnedUrlKey();
		sql.append("INSERT INTO urlshrt_t_url_shortener(id, url, code) VALUES (?, ?, ?)");
		int affectedRows = -1;
		try {
			affectedRows = getJdbcTemplate().update(sql.toString(),newKey,url,id);
			if (affectedRows!=1) {
				LOG.error("[UrlShortenerDaoImpl::insertShortenedUrl] Errore nell'inserimento dello shortened url");
				throw new DaoException("Errore nell'inserimento dello shortened url");
			}
		} catch (RuntimeException ex) {
			LOG.error(
					"[UrlShortenerDaoImpl::insertShortenedUrl] esecuzione query",
					ex);
			throw new DaoException("Query failed", ex);
		} finally {
			LOG.debug("[UrlShortenerDaoImpl::insertShortenedUrl] END");
		}		
	}

	@Override
	public String readIdByUrl(String url) throws DaoException {
		LOG.debug("[UrlShortenerDaoImpl::readIdByUrl] BEGIN");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT code FROM urlshrt_t_url_shortener where urlshrt_t_url_shortener.url = ?");
		String id = null;
		try {
			id = getJdbcTemplate().queryForObject(sql.toString(), new Object[] { url }, String.class);
		} 
		catch (EmptyResultDataAccessException ex) {
			id = null;
		}
		catch (RuntimeException ex) {
			LOG.error(
					"[UrlShortenerDaoImpl::readIdByUrl] esecuzione query",
					ex);
			throw new DaoException("Query failed", ex);
		} finally {
			LOG.debug("[UrlShortenerDaoImpl::readIdByUrl] END");
		}
		return id;
	}
}

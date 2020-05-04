/*******************************************************************************
 * Copyright CSI-Piemonte - 2017-2020
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.ecogis.urlshrtapi.business.dao;

import it.csi.ecogis.urlshrtapi.business.dao.rowmapper.ConfigRowMapper;
import it.csi.ecogis.urlshrtapi.dto.geoapi.ConfigDto;
import it.csi.ecogis.urlshrtapi.exception.DaoException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class ConfigDaoImpl extends JdbcDaoSupport implements
		ConfigDao {

	protected transient Logger LOG = Logger.getLogger(this.getClass().getSimpleName());

	@Autowired
	public ConfigDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public ConfigDto readConfigByCode(String codice) throws DaoException {
		LOG.debug("[ConfigDaoImpl::readConfigByCode] BEGIN");
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, code, value FROM geoapi_t_config where geoapi_t_config.code = ?");
		ConfigDto config = null;
		try {
			config = (ConfigDto) getJdbcTemplate().queryForObject(
					sql.toString(), new Object[] { codice }, new ConfigRowMapper());
		} catch (RuntimeException ex) {
			LOG.error(
					"[ConfigDaoImpl::readConfigByCode] esecuzione query",
					ex);
			throw new DaoException("Query failed", ex);
		} finally {
			LOG.debug("[ConfigDaoImpl::readConfigByCode] END");
		}
		return config;
	}
}

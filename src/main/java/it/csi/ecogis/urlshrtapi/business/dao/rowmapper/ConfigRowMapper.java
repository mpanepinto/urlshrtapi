/*******************************************************************************
 * Copyright CSI-Piemonte - 2017-2020
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.ecogis.urlshrtapi.business.dao.rowmapper;

import it.csi.ecogis.urlshrtapi.dto.geoapi.ConfigDto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

	public class ConfigRowMapper implements RowMapper<ConfigDto>
	{
		public ConfigDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			ConfigDto config = new ConfigDto();
			config.setId(rs.getBigDecimal("id"));
			config.setCode(rs.getString("code"));
			config.setValue(rs.getString("value"));
			return config;
		}
		
	}

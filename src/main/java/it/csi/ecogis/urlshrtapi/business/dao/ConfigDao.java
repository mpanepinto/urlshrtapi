/*******************************************************************************
 * Copyright CSI-Piemonte - 2017-2020
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.ecogis.urlshrtapi.business.dao;

import it.csi.ecogis.urlshrtapi.dto.geoapi.ConfigDto;
import it.csi.ecogis.urlshrtapi.exception.DaoException;

public interface ConfigDao {

	public ConfigDto readConfigByCode(String codice) throws DaoException;

}

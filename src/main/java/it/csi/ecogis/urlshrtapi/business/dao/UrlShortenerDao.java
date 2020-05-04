/*******************************************************************************
 * Copyright CSI-Piemonte - 2017-2020
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.ecogis.urlshrtapi.business.dao;

import it.csi.ecogis.urlshrtapi.exception.DaoException;

public interface UrlShortenerDao {

	public String readUrlById(String id) throws DaoException;

	public void insertShortenedUrl(String url, String id) throws DaoException;

	public String readIdByUrl(String url) throws DaoException;

}

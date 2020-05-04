/*******************************************************************************
 * Copyright CSI-Piemonte - 2017-2020
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.ecogis.urlshrtapi.business.service;

import it.csi.ecogis.urlshrtapi.exception.DaoException;

public interface BusinessLogic {
	
	public String retrieveUrlFromId(String id) throws DaoException;
	public void saveShortenedUrl(String url, String id) throws DaoException;
	public String retrieveIdFromUrl(String url) throws DaoException;
	public long getMaxCacheCapacity() throws DaoException;
}

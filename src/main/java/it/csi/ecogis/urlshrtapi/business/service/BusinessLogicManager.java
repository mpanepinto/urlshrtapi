/*******************************************************************************
 * Copyright CSI-Piemonte - 2017-2020
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.ecogis.urlshrtapi.business.service;

import it.csi.ecogis.urlshrtapi.business.dao.ConfigDao;
import it.csi.ecogis.urlshrtapi.business.dao.UrlShortenerDao;
import it.csi.ecogis.urlshrtapi.exception.DaoException;
import it.csi.ecogis.urlshrtapi.util.Constants;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessLogicManager implements BusinessLogic {

	protected transient Logger LOG = Logger
			.getLogger(Constants.LOGGER + ".urlshrtapi.bl");

	@Autowired
	private UrlShortenerDao urlShortenerDao;
	
	@Autowired
	private ConfigDao configDao;

	public BusinessLogicManager() {
		LOG.info("NewBusinessLogicManager - IN");
	}


	private static Long MAX_CACHE_CAPACITY = null;

	@Override
	public long getMaxCacheCapacity() throws DaoException {
		if (MAX_CACHE_CAPACITY == null) {
			MAX_CACHE_CAPACITY = new Long(configDao.readConfigByCode(
					Constants.MAX_CACHE_CAPACITY_CODE).getValue());
		}
		return MAX_CACHE_CAPACITY.longValue();
	}

	@Override
	public String retrieveUrlFromId(String id) throws DaoException {
		return urlShortenerDao.readUrlById(id);
	}

	@Override
	public String retrieveIdFromUrl(String url) throws DaoException {
		return urlShortenerDao.readIdByUrl(url);
	}

	@Override
	public void saveShortenedUrl(String url, String id) throws DaoException {
		urlShortenerDao.insertShortenedUrl(url, id);
	}



}

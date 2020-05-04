/*******************************************************************************
 * Copyright CSI-Piemonte - 2017-2020
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.ecogis.urlshrtapi.context;

import it.csi.ecogis.urlshrtapi.business.service.BusinessLogic;
import it.csi.ecogis.urlshrtapi.exception.DaoException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServletContextContainter implements ServletContextListener {
	
	public static BusinessLogic BL = null;
	private static ClassPathXmlApplicationContext ctx = null;
	public static long MAX_CACHE_CAPACITY = -1l;
	protected transient Logger LOG = Logger.getLogger(this.getClass().getSimpleName());

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ctx = null;
		BL = null;
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		LOG.info("contextInitialized - IN");
		try {
			ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			BL = (BusinessLogic) ctx.getBean("businessLogicManager");
			LOG.debug("\n\n\n\n\n\n\n\n["+BL.getClass()+"]\n\n\n\n\n\n\n\n\n\n\n\n");
			MAX_CACHE_CAPACITY = BL.getMaxCacheCapacity();
			LOG.debug("\n\n\n\n\n\n\n\n["+MAX_CACHE_CAPACITY+"]\n\n\n\n\n\n\n\n\n\n\n\n");
		} catch (DaoException e) {
			LOG.error("DAOEX");
			e.printStackTrace();
		}
		catch (Throwable e) {
			LOG.error("GENERICEX");
			e.printStackTrace();
		}
		finally {
			LOG.info("contextInitialized - OUT");
		}
	}

}

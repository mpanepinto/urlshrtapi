/*******************************************************************************
 * Copyright CSI-Piemonte - 2017-2020
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.ecogis.urlshrtapi.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


public class CORSFilter implements javax.servlet.Filter {
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, HEAD");
		res.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		res.addHeader("Access-Control-Allow-Credentials", "true");
		chain.doFilter(request, response);
	}

	public void destroy() {}

	public void init(FilterConfig filterConfig) throws ServletException {}
}

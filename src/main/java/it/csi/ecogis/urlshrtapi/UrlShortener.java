/*******************************************************************************
 * Copyright CSI-Piemonte - 2017-2020
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.ecogis.urlshrtapi;

import static it.csi.ecogis.urlshrtapi.context.ServletContextContainter.BL;
import it.csi.ecogis.urlshrtapi.exception.DaoException;
import it.csi.ecogis.urlshrtapi.util.ResponseUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.util.StringUtils;

import com.google.common.hash.Hashing;
import com.google.gson.JsonObject;

@Path("/")
public class UrlShortener {

	@GET
	@Path("/{id}")
	public Response redirect(@PathParam("id") String id,
			@Context HttpServletResponse response) throws IOException {
		Response result = null;
		if (StringUtils.isEmpty(id)) {
			result = Response
					.status(Response.Status.PRECONDITION_FAILED)
					.entity(ResponseUtils.createJSONResponseMessage(
							Response.Status.PRECONDITION_FAILED.name(),
							"Id param is mandatory!")).build();
		}
		try {
			String url = BL.retrieveUrlFromId(id);
			if (url == null) {
				result = Response
						.status(Response.Status.PRECONDITION_FAILED)
						.entity(ResponseUtils.createJSONResponseMessage(
								Response.Status.PRECONDITION_FAILED.name(),
								"Cannot find url for id [" + id + "]")).build();
			} else {
				result = Response.seeOther(new URI(url)).build();
			}
		} catch (DaoException e) {
			result = Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(ResponseUtils.createJSONResponseMessage(
							Response.Status.INTERNAL_SERVER_ERROR.name(),
							"Ouch something went wrong!")).build();
		} catch (URISyntaxException e) {
			result = Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(ResponseUtils.createJSONResponseMessage(
							Response.Status.INTERNAL_SERVER_ERROR.name(),
							"Ouch something went wrong!")).build();
		}
		return result;
	}

	@GET
	@Produces("application/json")
	public Response save(@QueryParam("url") String url,
			@Context HttpServletRequest request) throws MalformedURLException {
		Response result = null;
		if (new UrlValidator(new String[] { "http", "https" },UrlValidator.ALLOW_LOCAL_URLS).isValid(url)) {
			String id = null;
			try {
				id = BL.retrieveIdFromUrl(url);
				if (StringUtils.isEmpty(id)) {
					id = Hashing.murmur3_32()
							.hashString(url, StandardCharsets.UTF_8).toString();
					BL.saveShortenedUrl(url, id);
				}
//				TODO Gestire l'url nel caso di location
				String createdUrl = new URL(request.getScheme(), request.getServerName(),
						request.getServerPort(), "/us/" + id).toString()
						.replace(":80/", "/");
				JsonObject json = new JsonObject();
				json.addProperty("shorturl", createdUrl);
				result = Response.ok(json.toString()).build();
			} catch (DaoException e) {
				result = Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(ResponseUtils.createJSONResponseMessage(
								Response.Status.INTERNAL_SERVER_ERROR.name(),
								"Ouch something went wrong!")).build();
			}
		}
		else {
			result = Response
					.status(Response.Status.PRECONDITION_FAILED)
					.entity(ResponseUtils.createJSONResponseMessage(
							Response.Status.PRECONDITION_FAILED.name(),
							"Url ["+url+"]cannot be parsed correctly ")).build();
		}
		return result;
	}
	
}

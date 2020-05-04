/*******************************************************************************
 * Copyright CSI-Piemonte - 2017-2020
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.ecogis.urlshrtapi.util;

import it.csi.ecogis.urlshrtapi.dto.util.Exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResponseUtils {

	public static String createJSONResponseMessage(String code, String message) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		Exception ex = new Exception();
		ex.setCode(code);
		ex.setMessage(message);
		return gson.toJson(ex);
	}
}

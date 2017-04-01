package br.org.eufraten.web;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gustavo
 *
 */
/**
 * @author gustavo
 *
 */
public class JSendResponse {

	private String status;
	private String message;
	private Map<String, Object> data;

	private JSendResponse() {
	}

	public static JSendResponse sucess(String message) {
		JSendResponse response = new JSendResponse();
		response.status = "success";
		response.message = message;
		return response;
	}

	public static JSendResponse error(Exception exception, String message) {
		JSendResponse response = new JSendResponse();
		response.status = "error";
		response.message = message;
		response.data = new HashMap<>();
		response.data.put("exception", exception.getMessage());

		return response;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Map<String, Object> getData() {
		return data;
	}

}

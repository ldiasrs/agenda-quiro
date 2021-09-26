package br.com.agendaquiro.domain.to;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;


/**
 * Container for every response made by the middleware.
 */
public class HttpResponseTO {

	private Boolean success;

	private String  message;

	private Map<String, Object> content;

	private HttpStatus status;

	public Boolean isSuccess() {
		return this.success;
	}

	
	public void setSuccess(final Boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * Get the map representing the content.
	 *
	 * @return {@link Map} with the content.
	 */
	public Map<String, Object> getContent() {

		if (Objects.isNull(this.content)) {
			this.content = new HashMap<>();
		}

		return this.content;
	}

	/**
	 * Get the value of the specified key.
	 *
	 * @param key
	 *            {@link String} key to be searched in the map.
	 * @return {@link Object} for the key.
	 */
	public Object getContent(final String key) {
		if (!Objects.isNull(this.getContent())) {
			return this.getContent().get(key);
		} else {
			return null;
		}

	}

	/**
	 * Adds the object as the specified key.
	 *
	 * @param key
	 *            {@link String} containing a key for the map.
	 * @param value
	 *            {@link Object} being the value for the key.
	 */
	public void addContent(final String key, final Object value) {
		if (null != value) {
			this.getContent().put(key, value);
		}
	}

	/**
	 * Adds the content using the {@link Object} class name as key.
	 *
	 * @param value
	 *            {@link Object} to be part of content.
	 */
	public void addContent(final Object value) {
		if (null != value) {
			final String key = value.getClass().getSimpleName();
			this.getContent().put(key, value);
		}
	}


	public void setStatus(final HttpStatus status) {
		this.status = status;
	}


	public HttpStatus getStatus() {
		/*if (null != this.status) {
			return HttpStatus.OK;
		}*/
		return this.status;
	}

	
	public static HttpResponseTO failStri(String message) {
		final HttpResponseTO response = new HttpResponseTO();
		response.setMessage(message);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return response;
	}

	
	public static HttpResponseTO fail(final String code, String message) {
		final HttpResponseTO response = new HttpResponseTO();
		response.setMessage(message);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return response;
	}

	public static HttpResponseTO fail(final String code, final Object content) {
		final HttpResponseTO response = new HttpResponseTO();
		response.addContent(content);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return response;
	}

	public static HttpResponseTO fail(final String message) {
		final HttpResponseTO response = new HttpResponseTO();
		response.setMessage(message);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return response;
	}

	/**
	 * Fail response with message by code and message
	 *
	 * @param code
	 *            message code
	 * @param message
	 *            message text
	 * @param status
	 * @return fail response
	 */
	public static HttpResponseTO fail(final String code, final String message, final HttpStatus status) {
		final HttpResponseTO response = new HttpResponseTO();
		response.setMessage(message);
		response.setSuccess(Boolean.FALSE);
		response.setStatus(status);
		return response;
	}

	/**
	 * Success response without content.
	 *
	 * @return a success response.
	 */
	public static HttpResponseTO success() {
		final HttpResponseTO response = new HttpResponseTO();
		response.setSuccess(Boolean.TRUE);
		response.setStatus(HttpStatus.OK);
		return response;
	}


	public static HttpResponseTO success(final String message) {
		final HttpResponseTO response = new HttpResponseTO();
		response.setMessage(message);
		response.setSuccess(Boolean.TRUE);
		response.setStatus(HttpStatus.OK);
		return response;
	}


	public static HttpResponseTO success(final String key, final Object content) {
		final HttpResponseTO response = new HttpResponseTO();
		response.setSuccess(Boolean.TRUE);
		response.addContent(key, content);
		response.setStatus(HttpStatus.OK);
		return response;
	}


	public static HttpResponseTO success(final String key, final Object content, final HttpStatus status) {
		final HttpResponseTO response = new HttpResponseTO();
		response.setSuccess(Boolean.TRUE);
		response.addContent(key, content);
		response.setStatus(status);
		return response;
	}
}
package br.com.agendaquiro.controller.customer;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BaseController {

	protected static final String ENCODING = "UTF-8";

	protected static final String CHARSET_UTF_8 = "charset=utf-8";

	protected static final String APPLICATION_JSON = "application/json;" + CHARSET_UTF_8;

	protected static final String SUCCESS = "SUCCESS";

	/*public Long getTenant() {
		return SecurityTenantFilter.getTenant();
	}*/

	public <T> ResponseEntity<T> response(final T body, final HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

    public <T> ResponseEntity<T> response(final T body, final HttpStatus status, final AuthenticationPrincipal loggedUser) throws UnsupportedEncodingException {
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(body, headers, status);
    }


}

package br.com.agendaquiro.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.data.domain.Page;
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

    public static final String HEADER_META_TOTAL_COUNT = "x-meta-total-count";

    public static final String HEADER_META_TOTAL_PAGES = "x-meta-total-pages";

    public static final String HEADER_META_CURRENT_PAGE = "x-meta-current-page";


    public <T> ResponseEntity<List<T>> pageResult(final Page<T> page){
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_META_TOTAL_COUNT, String.valueOf(page.getTotalElements()));
        headers.add(HEADER_META_TOTAL_PAGES, String.valueOf(page.getTotalPages()));
        headers.add(HEADER_META_CURRENT_PAGE, String.valueOf(page.getNumber()));
        return new ResponseEntity<List<T>>(page.getContent(), headers, HttpStatus.OK);
    }

	public <T> ResponseEntity<T> response(final T body, final HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

    public ResponseEntity responseMessage(final String msg, final HttpStatus status) {
        return new ResponseEntity<>(MessageHttpResponse.builder().message(msg).build(), status);
    }

    public <T> ResponseEntity<T> response(final T body, final HttpStatus status, final AuthenticationPrincipal loggedUser) throws UnsupportedEncodingException {
        final HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(body, headers, status);
    }


}

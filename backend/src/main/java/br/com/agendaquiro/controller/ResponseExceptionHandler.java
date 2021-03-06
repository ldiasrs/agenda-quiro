package br.com.agendaquiro.controller;


import br.com.agendaquiro.domain.exception.NotFoundException;
import br.com.agendaquiro.domain.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger LOGGER = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageHttpResponse> handleGenericFailure(Exception exception) {
        HttpStatus returnStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Internal Server Error. Exception: " + exception.getClass() + "-" + exception.getMessage();
        MessageHttpResponse responseBody = buildErrorResponse(message);
        LOGGER.error("Generic http error", exception);
        return new ResponseEntity<>(responseBody, returnStatus);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageHttpResponse> handleNoFound(NotFoundException exception) {
        HttpStatus returnStatus = HttpStatus.NOT_FOUND;
        MessageHttpResponse responseBody = buildErrorResponse(exception.getMessage());
        LOGGER.debug(responseBody.toString());
        return new ResponseEntity<>(responseBody, returnStatus);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MessageHttpResponse> handleNoFound(BadRequestException exception) {
        HttpStatus returnStatus = HttpStatus.BAD_REQUEST;
        MessageHttpResponse responseBody = buildErrorResponse(exception.getMessage());
        LOGGER.debug(responseBody.toString());
        return new ResponseEntity<>(responseBody, returnStatus);
    }

    public MessageHttpResponse buildErrorResponse(String message) {
        return MessageHttpResponse.builder()
                .message(message)
                .build();
    }
}

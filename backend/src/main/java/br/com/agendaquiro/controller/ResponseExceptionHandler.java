package br.com.agendaquiro.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageHttpResponse> handleGenericFailure(Exception exception) {
        HttpStatus returnStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Internal Server Error. Exception: " + exception.getClass() + "-" + exception.getMessage();
        MessageHttpResponse responseBody = buildErrorResponse(message);
        return new ResponseEntity<>(responseBody, returnStatus);
    }

    public MessageHttpResponse buildErrorResponse(String message) {
        return MessageHttpResponse.builder()
                .message(message)
                .build();
    }
}

package br.com.agendaquiro.controller;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseExceptionHandlerTest {

    @Test
    public void shouldHandleException() {
        ResponseExceptionHandler exceptionHandler = new ResponseExceptionHandler();
        String mensagemEsperada = "Mensagem esperada";
        Exception exception = new Exception(mensagemEsperada);

        ResponseEntity<MessageHttpResponse> response = exceptionHandler.handleGenericFailure(exception);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertThat(response.getBody().getMessage()).contains(mensagemEsperada);
    }

}

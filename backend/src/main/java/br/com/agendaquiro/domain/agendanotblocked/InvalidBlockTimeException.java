package br.com.agendaquiro.domain.agendanotblocked;

public class InvalidBlockTimeException extends RuntimeException {
    public InvalidBlockTimeException(String message) {
        super(message);
    }
}

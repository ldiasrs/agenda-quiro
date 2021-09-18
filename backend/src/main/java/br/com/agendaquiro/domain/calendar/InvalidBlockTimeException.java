package br.com.agendaquiro.domain.calendar;

public class InvalidBlockTimeException extends RuntimeException {
    public InvalidBlockTimeException(String message) {
        super(message);
    }
}

package br.com.agendaquiro.domain.daysofweekblocked;

public class InvalidBlockTimeException extends RuntimeException {
    public InvalidBlockTimeException(String message) {
        super(message);
    }
}

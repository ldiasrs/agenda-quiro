package br.com.agendaquiro.domain.timeblockedconfig;

public class InvalidBlockTimeException extends RuntimeException {
    public InvalidBlockTimeException(String message) {
        super(message);
    }
}

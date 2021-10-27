package by.ita.je.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessException extends RuntimeException {
    public AccessException(String message) {
        super(message);
        log.error(message);
    }
}
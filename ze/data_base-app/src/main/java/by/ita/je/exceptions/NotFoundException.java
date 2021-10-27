package by.ita.je.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
package by.ita.je.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RuntimeException.class)
    public String handleException(HttpStatusCodeException exception, Model model) {
        int code = exception.getRawStatusCode();
        String message = exception.getMessage();
        model.addAttribute("exceptionNumber", code);
        model.addAttribute("message", message);
        return "error";
    }
}
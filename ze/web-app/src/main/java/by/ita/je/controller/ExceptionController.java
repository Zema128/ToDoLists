package by.ita.je.controller;

import by.ita.je.exceptions.UserNotFoundExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RuntimeException.class)
    public String handleException(HttpStatusCodeException exception, Model model) {
        int code = exception.getRawStatusCode();
        String message = exception.getMessage();
        log.error(message);
        model.addAttribute("exceptionNumber", code);
        model.addAttribute("message", message); //.replaceAll("[^\\а-яёА-ЯЁ ]", "")
        return "error";
    }

    @ExceptionHandler(UserNotFoundExceptions.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception)
            throws Exception {
        if (AnnotationUtils.findAnnotation(exception.getClass(),
                ResponseStatus.class) != null)
            throw exception;
        ModelAndView mav = new ModelAndView();
        log.error(exception.getMessage());
        mav.addObject("exception", exception);
        mav.addObject("status", 500);
        mav.setViewName("support");
        return mav;
    }
}
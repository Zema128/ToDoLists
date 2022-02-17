package by.ita.je.controller;

import by.ita.je.config.TestControllerIT;
import by.ita.je.model.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class ToDoControllerTest extends TestControllerIT {

    @Test
    void delete_whenNoEntities_thenException() {
        ResponseEntity<ToDo> responseEntity = restTemplate.exchange("/delete/1", HttpMethod.DELETE, HttpEntity.EMPTY, ToDo.class);
        assertEquals(responseEntity.getStatusCode().isError(), true);
    }

    @Test
    void update() {
    }

    @Test
    void readAll() {
    }

    @Test
    void todos() {
    }

    @Test
    void todosForMessage() {
    }

    @Test
    void sentToDo() {
    }
}
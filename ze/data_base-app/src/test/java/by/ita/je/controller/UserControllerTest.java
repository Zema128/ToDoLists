package by.ita.je.controller;

import by.ita.je.config.TestControllerIT;
import by.ita.je.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.*;


class UserControllerTest extends TestControllerIT {

    @Test
    void create() throws Exception {
        ResponseEntity<User> entity = restTemplate.getForEntity("/user/1", User.class);
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void findById() throws Exception {
        ResponseEntity<Long[]> ids = restTemplate.getForEntity("/friends/1", Long[].class);
        assertEquals(ids.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void userList() {
        ResponseEntity<User[]> users = restTemplate.getForEntity("/users", User[].class);
        assertEquals(users.getStatusCode(), HttpStatus.OK);
        assertEquals(users.getBody().length, 1);
    }
}
package by.ita.je.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Controller
public class ToDoController {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8003/data_base-app";


}

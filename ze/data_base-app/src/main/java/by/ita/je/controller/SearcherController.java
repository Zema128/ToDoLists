package by.ita.je.controller;

import by.ita.je.dto.SearcherDateDto;
import by.ita.je.dto.SearcherDto;
import by.ita.je.dto.ToDoDto;
import by.ita.je.model.enams.Categories;
import by.ita.je.service.api.SearcherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class SearcherController {

    private final SearcherService searcherService;
    private final ObjectMapper objectMapper;

    @PostMapping("/searchcategories")
    public List<ToDoDto> searchByCategories(@RequestBody SearcherDto searcherDto){
        return searcherService.listCategories(searcherDto.getUserId(), Categories.valueOf(searcherDto.getCategories()))
                .stream()
                .map(toDo -> objectMapper.convertValue(toDo, ToDoDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/betweendates/{id}")
    public List<ToDoDto> searchBetweenDates(@PathVariable("id") Long id,
                                            @RequestBody SearcherDateDto searcherDto){
        return searcherService.listBetweenDated(id, searcherDto.getDateFrom(), searcherDto.getDateTo())
                .stream()
                .map(toDo -> objectMapper.convertValue(toDo, ToDoDto.class))
                .collect(Collectors.toList());
    }
}
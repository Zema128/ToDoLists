package by.ita.je.controller;

import by.ita.je.dto.SharedListDto;
import by.ita.je.dto.ToDoDto;
import by.ita.je.model.SharedList;
import by.ita.je.model.ToDo;
import by.ita.je.service.api.BusinessService;
import by.ita.je.service.api.SharedService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SharedController {
    private final ObjectMapper objectMapper;
    private final SharedService sharedService;
    private final BusinessService businessService;

    @PostMapping("/createShared")
    public void createShared (@RequestBody SharedListDto sharedListDto){
        sharedService.createShared(objectMapper.convertValue(sharedListDto, SharedList.class));
    }

    @GetMapping("/sharedallread/{toId}")
    public List<ToDoDto> readAllByToIdRead(@PathVariable("toId") Long toId){
        List<ToDo> sharedLists = businessService.sharedListsRead(toId);
        List<ToDoDto> list = sharedLists
                .stream()
                .map(sharedList -> objectMapper.convertValue(sharedList, ToDoDto.class))
                .collect(Collectors.toList());
        for (ToDoDto t: list) {
            for (ToDo td: sharedLists) {
                if (td.getId() == t.getId()){
                    t.setUserId(td.getUser().getId());
                }
            }
        }
        return list;
    }

    @GetMapping("/sharedallchange/{toId}")
    public List<ToDoDto> readAllByToIdChange(@PathVariable("toId") Long toId){
        List<ToDo> sharedLists = businessService.sharedListsChange(toId);
        List<ToDoDto> list = sharedLists
                .stream()
                .map(sharedList -> objectMapper.convertValue(sharedList, ToDoDto.class))
                .collect(Collectors.toList());
        for (ToDoDto t: list) {
            for (ToDo td: sharedLists) {
                if (td.getId() == t.getId()){
                    t.setUserId(td.getUser().getId());
                }
            }
        }
        return list;
    }

    @DeleteMapping("/deleteshared/{toDoId}/{toId}")
    public void deleteShared(@PathVariable("toDoId") Long toDoId,
                             @PathVariable("toId") Long toId){
        sharedService.delete(toDoId, toId);
    }
}
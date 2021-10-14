package by.ita.je.dto;

import by.ita.je.model.enams.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDoDto {
    private long id;
    private ZonedDateTime timeNotification;
    private String text;
    private boolean done;
    @Enumerated(EnumType.STRING)
    private Categories categories;
    private List<SubTaskDto> subTask;
}
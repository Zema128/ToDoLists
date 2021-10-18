package by.ita.je.dto;

import by.ita.je.model.enams.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDoDto {
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private ZonedDateTime timeNotification;
    private String text;
    private boolean done;
    private UserDto userDto;
    @Enumerated(EnumType.STRING)
    private Categories categories;
    private List<SubTaskDto> subTask;
}
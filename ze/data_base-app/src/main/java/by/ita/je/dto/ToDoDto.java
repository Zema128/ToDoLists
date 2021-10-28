package by.ita.je.dto;

import by.ita.je.model.enams.Categories;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDoDto {
    private long id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeNotification;
    private String text;
    private boolean done;
    @Enumerated(EnumType.STRING)
    private Categories categories;
    private List<SubTaskDto> subTask;
    private UserDto userDto;
    private Long userId;
    private boolean sentMessage;
}
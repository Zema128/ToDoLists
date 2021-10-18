package by.ita.je.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String text;
    private ZonedDateTime timeCreated;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private ZonedDateTime timeNotification;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "todo_id")
    private ToDo toDo;
}

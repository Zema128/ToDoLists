package by.ita.je.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String text;
    private ZonedDateTime timeCreated;
    private ZonedDateTime timeNotification = null;

    @ManyToOne()
    private ToDo toDo;
}

package by.ita.je.model;

import by.ita.je.model.enams.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private ZonedDateTime timeCreated;
    private ZonedDateTime timeNotification = null;
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "categories")
    private Categories categories;

    @ManyToOne()
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toDo")
    @JoinColumn(name = "todo_id")
    private List<SubTask> subTask;
}

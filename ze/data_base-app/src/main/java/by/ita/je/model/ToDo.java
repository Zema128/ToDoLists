package by.ita.je.model;

import by.ita.je.model.enams.Categories;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private ZonedDateTime timeNotification;

    @NotEmpty
    private String text;
    private boolean done;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "categories")
    private Categories categories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toDo",orphanRemoval = true)
    private List<SubTask> subTask;
}
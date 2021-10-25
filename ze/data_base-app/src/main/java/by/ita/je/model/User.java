package by.ita.je.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timeCreated;
    @Column(unique = true, nullable = false)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ToDo> toDos;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Set<User> friends;

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<User> getFriends() {
        return friends;
    }

}
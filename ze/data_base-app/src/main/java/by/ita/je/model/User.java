package by.ita.je.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timeCreated;
    @Column(unique = true, nullable = false)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ToDo> toDos;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friends_id"))
    private Set<User> friends;

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<User> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", timeCreated=" + timeCreated +
                ", toDos=" + toDos +
                ", friends=" + friends +
                '}';
    }
}
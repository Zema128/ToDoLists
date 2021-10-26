package by.ita.je.dto;

import by.ita.je.model.ToDo;
import by.ita.je.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    private Set<User> friends;
}

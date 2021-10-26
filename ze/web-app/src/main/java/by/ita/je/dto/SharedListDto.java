package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SharedListDto {
    private boolean forChanges;
    private Long fromId;
    private Long toId;
    private Long toDoId;
    private String username;
}
package by.ita.je.dto;


import by.ita.je.model.InviteStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InviteDto {
    private Long fromUser_id;
    private Long toUser_id;
    @Enumerated(EnumType.STRING)
    InviteStatus status;
}
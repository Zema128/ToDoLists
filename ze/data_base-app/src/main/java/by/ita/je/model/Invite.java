package by.ita.je.model;

import by.ita.je.model.enams.InviteStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fromUser_id;
    private Long toUser_id;
    @Enumerated(EnumType.STRING)
    InviteStatus status;
}
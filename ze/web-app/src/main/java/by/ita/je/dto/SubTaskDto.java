package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubTaskDto {
    private long id;
    private String text;
    private ZonedDateTime timeNotification;
}
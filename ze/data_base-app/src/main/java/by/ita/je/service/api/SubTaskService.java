package by.ita.je.service.api;

import by.ita.je.model.SubTask;

import java.util.Arrays;
import java.util.List;

public interface SubTaskService {
    SubTask create(SubTask subTask);

    SubTask readById(Long id);

    void deleteById(Long id);

    SubTask update(SubTask subTask, Long id);

    List<SubTask> readAll();
}
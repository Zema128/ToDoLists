package by.ita.je.service.api;

import by.ita.je.model.SubTask;

import java.util.List;

public interface SubTaskService {
    SubTask create(SubTask subTask);

    SubTask readById(Long id);

    List<SubTask> readAll();

    void deleteById(Long id);

    void deleteAllById(List<Long> list);

    SubTask update(SubTask subTask, Long id);
}
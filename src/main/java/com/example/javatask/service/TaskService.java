package com.example.javatask.service;

import com.example.javatask.model.Task;
import com.example.javatask.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }

    public Task saveOrUpdateTask(Task task){
        return taskRepository.save(task);
    }

    public void deleteTaskByIdentifier(Long id){
        taskRepository.deleteById(id);
    }

  public Optional<Task> findById(long l) {
        return taskRepository.findById(l);
    }
}

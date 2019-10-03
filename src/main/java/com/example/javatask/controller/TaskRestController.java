package com.example.javatask.controller;

import com.example.javatask.model.Task;
import com.example.javatask.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TaskRestController {

    private TaskRepository taskRepository;
    @Autowired
    public TaskRestController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public List<Task> getTaskList(){
        return taskRepository.findAll();
    }

    @PostMapping(path = "/tasks", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
    public ResponseEntity<Object> createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTask.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}

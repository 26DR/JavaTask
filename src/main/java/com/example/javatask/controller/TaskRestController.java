package com.example.javatask.controller;

import com.example.javatask.model.Task;
import com.example.javatask.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TaskRestController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private TaskRepository taskRepository;

    private Task taskOne = new Task(counter.incrementAndGet(), "Do Java task", false);
    private Task taskTwo = new Task(counter.incrementAndGet(), "Drink coffee", true);
    List<Task> taskList = new ArrayList<>();

    @RequestMapping(value = "/tasks-rest", method = RequestMethod.GET)
    public List<Task> task(){
        taskList.add(taskOne);
        taskList.add(taskTwo);
        return taskList;
    }

    @PostMapping(path = "/tasks/createTask", consumes = "application/json; charset=utf-8")
    public ResponseEntity<Object> createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTask.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/tasks/deleteTask/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}

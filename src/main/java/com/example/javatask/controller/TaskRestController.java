package com.example.javatask.controller;

import com.example.javatask.model.Task;
import com.example.javatask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTaskList() {
        List<Task> listOfTasks = taskService.findAllTasks();
        if (listOfTasks != null && !listOfTasks.isEmpty()) {
            return new ResponseEntity<>(listOfTasks, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/tasks", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task taskToCreate) {
        Task createdTask = taskService.saveOrUpdateTask(taskToCreate);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        taskService.deleteTaskByIdentifier(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

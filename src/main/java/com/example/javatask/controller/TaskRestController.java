package com.example.javatask.controller;

import com.example.javatask.model.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TaskRestController {

    private final AtomicLong counter = new AtomicLong();

    private Task taskOne = new Task(counter.incrementAndGet(), "Do Java task", false);
    private Task taskTwo = new Task(counter.incrementAndGet(), "Drink coffee", true);
    List<Task> taskList = new ArrayList<>();

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<Task> task(){
        taskList.add(taskOne);
        taskList.add(taskTwo);
        return taskList;
    }
}

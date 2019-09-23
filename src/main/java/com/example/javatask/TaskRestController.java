package com.example.javatask;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TaskRestController {

    private final AtomicLong counter = new AtomicLong();

    Task taskOne = new Task(counter.incrementAndGet(), "Do Java task", false);
    Task taskTwo = new Task(counter.incrementAndGet(), "Drink coffee", true);
    List<Task> taskList = new ArrayList<>();



    @RequestMapping("/tasks")
    public List<Task> task(){
        taskList.add(taskOne);
        taskList.add(taskTwo);
        return taskList;
    }
}

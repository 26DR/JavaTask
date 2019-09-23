package com.example.javatask;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TaskRestController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/tasks")
    public Task task(){
        return new Task(counter.incrementAndGet(), "Task description", false);
    }
}

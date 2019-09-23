package com.example.javatask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {

    @GetMapping("/get-tasks")
    public String tasks(){
        return "tasks";
    }
}

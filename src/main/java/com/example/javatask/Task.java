package com.example.javatask;


public class Task {

    private final long id;
    private final String description;
    private final boolean isCompleted;

    public Task(long id, String description, boolean isCompleted) {
        this.id = id;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}

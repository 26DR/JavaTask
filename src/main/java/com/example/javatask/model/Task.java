package com.example.javatask.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="description")
    private String description;
    @Column(name="is_completed")
    private boolean isCompleted;

    public Task(){}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                isCompleted == task.isCompleted &&
                description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isCompleted);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}

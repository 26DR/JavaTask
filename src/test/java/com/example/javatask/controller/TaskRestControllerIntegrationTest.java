package com.example.javatask.controller;

import com.example.javatask.model.Task;
import com.example.javatask.repositories.TaskRepository;
import com.example.javatask.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class TaskRestControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void createTask() throws Exception {
        Task taskToSave = new Task(1, "Drink coffee", false);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(taskToSave)))
                .andExpect(status().isCreated());

        assertEquals(taskToSave, taskService.findById(1L).get());
    }

    @Test
    public void retrieveAllTasks() throws Exception {

        List<Task> expectedListOfTasks = Arrays.asList(new Task(1, "Drink coffee", true),
                new Task(2, "Do Java task", false),
                new Task(3, "Try not to fall asleep", false),
                new Task(4, "Go home", false));

        assertEquals(expectedListOfTasks, taskService.findAllTasks());
    }

    @Test
    public void deleteTask() throws Exception {

        List<Task> expectedListOfTasks = Arrays.asList(new Task(2, "Do Java task", false),
                new Task(3, "Try not to fall asleep", false),
                new Task(4, "Go home", false));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/tasks/{id}", 1))
                .andExpect(status().isOk());

        assertEquals(expectedListOfTasks, taskService.findAllTasks());
    }

    /*auxiliary methods*/

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @EnableWebMvc
    static class TestConfig implements WebMvcConfigurer {
        @Bean
        TaskRepository mockRepo() {
            return mock(TaskRepository.class);
        }

    }
}
package com.example.javatask.controller;

import com.example.javatask.model.Task;
import com.example.javatask.repositories.TaskRepository;
import com.example.javatask.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TaskRestController.class, TaskService.class, TaskRestControllerTest.TestConfig.class})
@WebAppConfiguration
public class TaskRestControllerTest {

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
    public void getTaskListOk() throws Exception {

        List<Task> listOfTasks = Arrays.asList(new Task(1, "Drink coffee", true),
                new Task(2, "Do Java task", true));

        when(taskService.findAllTasks())
                .thenReturn(listOfTasks);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/tasks")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(listOfTasks)));
    }

    @Test
    public void getTaskListNotFound() throws Exception {
        when(taskService.findAllTasks())
                .thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/tasks")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createTask() throws Exception {
        Task taskToSave = new Task(1, "Drink coffee", false);

        when(taskService.saveOrUpdateTask(taskToSave))
                .thenReturn(taskToSave);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/tasks")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(taskToSave)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/tasks/{id}", 1))
                .andExpect(status().isOk());
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
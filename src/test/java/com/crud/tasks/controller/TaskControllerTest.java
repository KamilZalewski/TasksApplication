package com.crud.tasks.controller;


import com.crud.tasks.domain.Task;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {


    @Autowired
    private TaskMapper taskMapper;
    @Mock
    private DbService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        Task task = new Task(
                1L,
                "name",
                "content"
        );
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);

        when(service.getAllTasks()).thenReturn(taskList);
        //When & Then

        mockMvc.perform(get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(
                1L,
                "name",
                "content"
        );

        when(service.getTask(1L)).thenReturn(task);
        //When & Then
        mockMvc.perform(get("/v1/task/getTask")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id", Matchers.is(1L)))
                .andExpect(jsonPath("$[0].name", Matchers.is("name")))
                .andExpect(jsonPath("$[0].content", Matchers.is("content")));

    }

    @Test
    public void shouldDeleteTask() {
        //Given

        //When & Then
    }

    @Test
    public void shouldUpdateTask() {
        //Given

        //When & Then
    }

    @Test
    public void shouldCreateTask() {
        //Given

        //When & Then


    }
}

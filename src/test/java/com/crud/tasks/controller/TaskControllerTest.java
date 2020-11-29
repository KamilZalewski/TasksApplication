package com.crud.tasks.controller;


import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @MockBean
    private TaskMapper taskMapper;
    @MockBean
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
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(task.getId(), task.getTitle(), task.getContent()));

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(taskDtoList);
        //When & Then
        mockMvc.perform(get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(
                1L,
                "title",
                "content"
        );

        when(service.getTask(1L)).thenReturn(Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(new TaskDto(task.getId(), task.getTitle(), task.getContent()));
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);
        //When & Then
        mockMvc.perform(get("/v1/tasks?taskId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.title", Matchers.is("title")))
                .andExpect(jsonPath("$.content", Matchers.is("content")));

    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        Task task = new Task(
                1L,
                "title",
                "content"
        );

        //When & Then
        mockMvc.perform(delete("/v1/tasks?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        Mockito.verify(service, times(1)).deleteTask(any());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(
                1L,
                "title",
                "content"
        );

        TaskDto taskDto = new TaskDto(
                1L,
                "title",
                "content"
        );

        when(taskMapper.mapToTask(taskDto)).thenReturn(new Task(taskDto.getId(), taskDto.getTitle(), taskDto.getContent()));
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.title", Matchers.is("title")))
                .andExpect(jsonPath("$.content", Matchers.is("content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(
                1L,
                "name",
                "content"
        );

        TaskDto taskDto = new TaskDto(
                1L,
                "name",
                "content"
        );

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
        Mockito.verify(service, times(1)).saveTask(any());
    }
}

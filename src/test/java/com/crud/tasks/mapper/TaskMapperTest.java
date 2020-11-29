package com.crud.tasks.mapper;


import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Testing title.", "Testing content.");
        //When
        Long id = taskMapper.mapToTask(taskDto).getId();
        String title = taskMapper.mapToTask(taskDto).getTitle();
        String content = taskMapper.mapToTask(taskDto).getContent();
        //Then
        Assert.assertEquals(1L, (Object) id);
        Assert.assertEquals("Testing title.", title);
        Assert.assertEquals("Testing content.", content);
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "Testing title.", "Testing content.");
        //When
        Long id = taskMapper.mapToTaskDto(task).getId();
        String title = taskMapper.mapToTaskDto(task).getTitle();
        String content = taskMapper.mapToTaskDto(task).getContent();
        //Then
        Assert.assertEquals(1L, (Object) id);
        Assert.assertEquals("Testing title.", title);
        Assert.assertEquals("Testing content.", content);
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "Testing title1.", "Testing content.");
        Task task2 = new Task(2L, "Testing title2.", "Testing content.");
        Task task3 = new Task(3L, "Testing title3.", "Testing content.");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        //When
        int size = taskMapper.mapToTaskDtoList(taskList).size();
        //Then
        Assert.assertEquals(3, size);
    }
}

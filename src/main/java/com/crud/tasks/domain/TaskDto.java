package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TaskDto {
    private Long id;
    private String name;
    private String content;
}

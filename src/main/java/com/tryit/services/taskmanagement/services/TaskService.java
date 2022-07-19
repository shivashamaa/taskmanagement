package com.tryit.services.taskmanagement.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import com.tryit.services.taskmanagement.dtomodel.TaskDto;
import com.tryit.services.taskmanagement.exception.InvalidTaskException;
import com.tryit.services.taskmanagement.models.Task;

public interface TaskService {
	TaskDto createTask(@ModelAttribute("taskDto") TaskDto taskDto);
	TaskDto updateTask(@RequestBody TaskDto taskDto);
	List<Task> getAllTask() throws InvalidTaskException;
	TaskDto getById(TaskDto taskDto);
}

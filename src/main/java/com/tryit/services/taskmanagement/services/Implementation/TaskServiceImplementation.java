package com.tryit.services.taskmanagement.services.Implementation;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tryit.services.taskmanagement.businessmodel.TaskBusinessModel;
import com.tryit.services.taskmanagement.dtomodel.TaskDto;
import com.tryit.services.taskmanagement.exception.InvalidTaskException;
import com.tryit.services.taskmanagement.models.Task;
import com.tryit.services.taskmanagement.repository.TaskRepository;
import com.tryit.services.taskmanagement.services.TaskService;
import com.tryit.services.taskmanagement.services.businesslogic.TaskBusinessLogic;
import com.tryit.services.taskmanagement.utilities.CalenderOperation;
@Service
public class TaskServiceImplementation implements TaskService {

	@Autowired
	TaskBusinessLogic taskBusinessLogic;
	
	public TaskDto createTask(TaskDto taskDto) {
		TaskBusinessModel taskBusinessModel = new TaskBusinessModel(taskDto);
		taskBusinessModel = taskBusinessLogic.createTask(taskBusinessModel);
		TaskDto responseTaskDto = new TaskDto(taskBusinessModel);
		return responseTaskDto;
	}
	
	public TaskDto updateTask(TaskDto taskDto) {
		TaskBusinessModel taskBusinessModel = new TaskBusinessModel(taskDto);
		taskBusinessModel = taskBusinessLogic.updateTask(taskBusinessModel);
		TaskDto responseTaskDto = new TaskDto(taskBusinessModel);
		return responseTaskDto;
	}
	
	public  List<Task> getAllTask() throws InvalidTaskException {
		List<Task> taskResponse = taskBusinessLogic.getAllTask();
		return taskResponse;
	}
	
	public TaskDto getById(TaskDto taskDto) {
		TaskBusinessModel taskBusinessModel = new TaskBusinessModel(taskDto);
		taskBusinessModel = taskBusinessLogic.getById(taskBusinessModel);
		TaskDto responseTaskDto = new TaskDto(taskBusinessModel);
		return responseTaskDto;
	}

	

}

package com.tryit.services.taskmanagement.services.dao;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tryit.services.taskmanagement.dtomodel.TaskDto;
import com.tryit.services.taskmanagement.exception.InvalidTaskException;
import com.tryit.services.taskmanagement.models.Task;
import com.tryit.services.taskmanagement.repository.TaskRepository;
import com.tryit.services.taskmanagement.utilities.CalenderOperation;

@Component
public class TaskDao {

	@Autowired
	private TaskRepository taskRepository;
	
	public Task createTask(Task taskEntityModel) {
		System.err.println("dao"+taskEntityModel);
		Task responsetaskEntityModel = taskRepository.save(taskEntityModel);
		return responsetaskEntityModel;
	}

	public Task updateTask(Task taskEntityModel) {
		Optional<Task> taskData = taskRepository.findById(taskEntityModel.getId());
System.err.println("taskEntityModel "+taskEntityModel.toString());
		if(taskData.isPresent()) {
			CalenderOperation calender = new CalenderOperation();
			taskEntityModel.setLastUpdatedDate(calender.getDefaultCurrentDateByTimezone("GMT+5:30"));
			Task responsetaskEntityModel = taskRepository.save(taskEntityModel);
			return responsetaskEntityModel;
		}
		return taskEntityModel;
	}
	
	public  List<Task> getAllTask() throws InvalidTaskException {
		return taskRepository.findAll();
	}

	public Task getById(Task taskEntityModel) {
		Optional<Task> optionalEntityModel = taskRepository.findById(taskEntityModel.getId());
		return optionalEntityModel.get();
	}

	public Task UpdateTask(Task taskEntityModel) {
		// TODO Auto-generated method stub
		return null;
	}
}

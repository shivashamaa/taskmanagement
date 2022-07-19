package com.tryit.services.taskmanagement.services.businesslogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tryit.services.taskmanagement.businessmodel.TaskBusinessModel;
import com.tryit.services.taskmanagement.dtomodel.TaskDto;
import com.tryit.services.taskmanagement.exception.InvalidTaskException;
import com.tryit.services.taskmanagement.models.Task;
import com.tryit.services.taskmanagement.services.dao.TaskDao;

@Component
public class TaskBusinessLogic {

	@Autowired
	TaskDao taskDao;
	
	public TaskBusinessModel createTask(TaskBusinessModel taskBusinessModel) {
		System.err.println("business");
		Task taskEntityModel = new Task(taskBusinessModel);
		taskEntityModel = taskDao.createTask(taskEntityModel);
		TaskBusinessModel responseTaskBusinessModel = new TaskBusinessModel(taskEntityModel);
		return responseTaskBusinessModel;
	}

	public TaskBusinessModel updateTask(TaskBusinessModel taskBusinessModel) {
		Task taskEntityModel = new Task(taskBusinessModel);
		taskEntityModel = taskDao.updateTask(taskEntityModel);
		TaskBusinessModel responseTaskBusinessModel = new TaskBusinessModel(taskEntityModel);
		return responseTaskBusinessModel;
	}
	
	public List<Task> getAllTask() throws InvalidTaskException {
		List<Task> taskResponse = taskDao.getAllTask();
		return taskResponse;
	}

	public TaskBusinessModel getById(TaskBusinessModel taskBusinessModel) {
		Task taskEntityModel = new Task(taskBusinessModel);
		taskEntityModel = taskDao.getById(taskEntityModel);
		TaskBusinessModel responseTaskBusinessModel = new TaskBusinessModel(taskEntityModel);
		return responseTaskBusinessModel;
	}

	
	
}

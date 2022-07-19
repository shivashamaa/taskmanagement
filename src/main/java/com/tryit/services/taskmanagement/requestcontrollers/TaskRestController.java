package com.tryit.services.taskmanagement.requestcontrollers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tryit.services.taskmanagement.dtomodel.TaskDto;
import com.tryit.services.taskmanagement.exception.InvalidTaskException;
import com.tryit.services.taskmanagement.models.Task;
import com.tryit.services.taskmanagement.repository.TaskRepository;
import com.tryit.services.taskmanagement.services.TaskService;
import com.tryit.services.taskmanagement.utilities.CalenderOperation;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TaskRestController {

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	TaskService taskService;
	
	@PostMapping(value = "/createTask") 
	public ResponseEntity<Task> create(@RequestBody Task task) {
		CalenderOperation calender = new CalenderOperation();
		 Date createdDate = calender.getDefaultCurrentDateByTimezone("GMT+5:30");
		    Date lastUpdatedDate = calender.getDefaultCurrentDateByTimezone("GMT+5:30");
		task.setCreatedDate(createdDate);
		task.setLastUpdatedDate(lastUpdatedDate);
		taskRepository.save(task);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();
		System.err.println("uri =>"+uri);
		return ResponseEntity.created(uri).body(task);
	}
	
	@PostMapping(value = "/updateTask")
	public ResponseEntity<Task> update(@RequestBody Task task) {
		Optional<Task> taskData = taskRepository.findById(task.getId());

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();
		if(taskData.isPresent()) {
			CalenderOperation calender = new CalenderOperation();
//			task.setLastUpdatedDate(LocalDateTime.parse(calender.getDefaultCurrentDateByTimezone("GMT+5:30")));
			taskRepository.save(task);
			return ResponseEntity.created(uri).body(task);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/deleteTask")
	@ResponseStatus(code = HttpStatus.OK)
	public String deleteTask(@RequestBody Task task) {
		Optional<Task> taskData = taskRepository.findById(task.getId());
		if(taskData.isPresent()) {
			CalenderOperation calender = new CalenderOperation();
//			taskData.get().setLastUpdatedDate(LocalDateTime.parse(calender.getDefaultCurrentDateByTimezone("GMT+5:30")));
			taskData.get().setStatus("deleted");
			taskRepository.save(taskData.get());
			return "deleted successfully";
		}
		return "record not found";
	}
	
	@PostMapping(value = "/getTask")
	public Task getTask(@RequestBody Task task) throws InvalidTaskException {
		if(task.getId()!=null) {
			Optional<Task> existingTask = taskRepository.findById(task.getId());
			if(existingTask.isPresent()) {
				return existingTask.get();
			}else {
				throw new InvalidTaskException("the task you trying to get is not exist");
			}
		}else {
			throw new InvalidTaskException("invalid or empty task id");
		}
	}
	
	@GetMapping(value = "/getAllTask")
	public  List<Task> getAllTask() throws InvalidTaskException {
	return taskRepository.findAll();
	}
	
	@GetMapping(value = "/editTasks/{id}")
	public TaskDto editTasks(@PathVariable("id") Long id,Model model) { 
		TaskDto task = new TaskDto();
		task.setId(id);
		TaskDto responseTask  = taskService.getById(task);
		System.err.println("here =>"+responseTask.toString());
		model.addAttribute("taskDto", responseTask);
		List<String> status = new ArrayList<String>();
		status.add("to-do");
		status.add("in-progress");
		status.add("completed");
		
		List<String> priority = new ArrayList<String>();
		priority.add("low");
		priority.add("medium");
		priority.add("high");
//		
//		model.addAttribute("priority", priority);
//		model.addAttribute("status", status);
//		model.addAttribute("isEdit",true);
//	    model.addAttribute("professionList", professionList);
	    return responseTask; 
	}
	
	@PostMapping("/updateStartEndTime")
	public String updateStartEndTime(@RequestBody Task task) {
		System.err.println("task "+task);
		System.err.println("updateStartEndTime "+task.getId());
		Optional<Task> taskData = taskRepository.findById(task.getId());

		if(taskData.isPresent()) {
			CalenderOperation calender = new CalenderOperation();
//			task.setLastUpdatedDate(LocalDateTime.parse(calender.getDefaultCurrentDateByTimezone("GMT+5:30")));
			taskData.get().setStartDate(task.getStartDate());
			taskData.get().setEndDate(task.getEndDate());
			taskRepository.save(taskData.get());
			return "success";
		}
		return "failed";
	}
}

package com.tryit.services.taskmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tryit.services.taskmanagement.dtomodel.TaskDto;
import com.tryit.services.taskmanagement.exception.InvalidTaskException;
import com.tryit.services.taskmanagement.models.Task;
import com.tryit.services.taskmanagement.services.TaskService;

@Controller
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@RequestMapping(value = "/task", method = RequestMethod.GET) 
	public String displayLogin(Model model) { 
		Task task = new Task();
		List<String> status = new ArrayList<String>();
		status.add("to-do");
		status.add("in-progress");
		status.add("completed");
		
		List<String> priority = new ArrayList<String>();
		priority.add("low");
		priority.add("medium");
		priority.add("high");
		
		model.addAttribute("priority", priority);
		model.addAttribute("status", status);
		model.addAttribute("isEdit",false);
	     System.err.println("here");
	    model.addAttribute("taskDto", task);
//	    model.addAttribute("professionList", professionList);
	    return "task"; 
	}
	
	@RequestMapping(value = "/editTask/{id}", method = RequestMethod.GET)
	public String editTask(Model model,@PathVariable("id") Long id) { 
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
		
		model.addAttribute("priority", priority);
		model.addAttribute("status", status);
		model.addAttribute("isEdit",true);
//	    model.addAttribute("professionList", professionList);
	    return "task"; 
	}
	
	@PostMapping("/createForm")
	public RedirectView createForm(@ModelAttribute("taskDto") TaskDto taskDto) {
		TaskDto responseDto = new TaskDto();
		responseDto = taskService.createTask(taskDto);
		return new RedirectView("/calander"); 
	}
	
	@PostMapping("/editTask/updateForm")
	public RedirectView updateForm(@ModelAttribute("taskDto") TaskDto taskDto) {
		TaskDto responseDto = new TaskDto();
		responseDto = taskService.updateTask(taskDto);
	    return new RedirectView("/calander"); 
	}
	
	@RequestMapping(value = "/taskList", method = RequestMethod.GET)
	public ModelAndView listAction() {

		ModelAndView mv = new ModelAndView();
		List<Task> taskList = null;
		try {
			taskList = taskService.getAllTask();
		} catch (InvalidTaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("taskList");
		mv.addObject("taskList", taskList);

		return mv;

	}
	
	@RequestMapping(value = "/calander", method = RequestMethod.GET) 
	public String calander(Model model) {
		Task task = new Task();
		List<String> status = new ArrayList<String>();
		status.add("to-do");
		status.add("in-progress");
		status.add("completed");
		
		List<String> priority = new ArrayList<String>();
		priority.add("low");
		priority.add("medium");
		priority.add("high");
		
		model.addAttribute("priority", priority);
		model.addAttribute("status", status);
		model.addAttribute("isEdit",false);
	     System.err.println("here");
	    model.addAttribute("taskDto", task);
	    return "calander"; 
    }	
	
	@RequestMapping(value = "/booking", method = RequestMethod.GET) 
	public String booking(Model model) {
	    return "onlinebooking"; 
    }	
}

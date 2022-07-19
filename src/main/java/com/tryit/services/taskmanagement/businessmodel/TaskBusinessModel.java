package com.tryit.services.taskmanagement.businessmodel;

import com.tryit.services.taskmanagement.dtomodel.TaskDto;
import com.tryit.services.taskmanagement.models.Task;
import com.tryit.services.taskmanagement.utilities.CalenderOperation;

import lombok.Data;

@Data
public class TaskBusinessModel {
	String modelDateFormal = "yyyy-MM-dd'T'hh:mm" ;
	private Long id;
	private String userId;
	private String createdBy;
	private String createdDate;
	private String lastUpdatedDate;
	private String taskName;
	private String description;
	private String startDate;
	private String endDate;
	private String expectedEndDate;
	private String status;
	private String module;
	private String preority;
	
	public TaskBusinessModel(TaskDto taskDto) {
		this.id = taskDto.getId();
		this.userId = taskDto.getUserId();
		this.createdBy = taskDto.getCreatedBy();
		this.createdDate = taskDto.getCreatedDate();
		this.lastUpdatedDate = taskDto.getLastUpdatedDate();
		this.taskName = taskDto.getTaskName();
		this.description = taskDto.getDescription();
		this.startDate = taskDto.getStartDate();
		this.endDate = taskDto.getEndDate();
		this.expectedEndDate = taskDto.getExpectedEndDate();
		this.status = taskDto.getStatus();
		this.module = taskDto.getModule();
		this.preority = taskDto.getPreority();
	}

	public TaskBusinessModel(Task taskEntityModel) {
		CalenderOperation calender = new CalenderOperation();
		this.id = taskEntityModel.getId();
		this.userId = taskEntityModel.getUserId();
		this.createdBy = taskEntityModel.getCreatedBy();
		this.createdDate = taskEntityModel.getCreatedDate().toString();
		this.lastUpdatedDate =taskEntityModel.getLastUpdatedDate().toString();
		this.taskName = taskEntityModel.getTaskName();
		this.description = taskEntityModel.getDescription();
		
		String startDate = calender.convertDateFormartString(taskEntityModel.getStartDate(),modelDateFormal);
		this.startDate = startDate;
		
		String endDate = calender.convertDateFormartString(taskEntityModel.getEndDate(),modelDateFormal);
		this.endDate = endDate;
		
		String expectedEndDate = calender.convertDateFormartString(taskEntityModel.getExpectedEndDate(),modelDateFormal);
		this.expectedEndDate = expectedEndDate;
		
		this.status = taskEntityModel.getStatus();
		this.module = taskEntityModel.getModule();
		this.preority = taskEntityModel.getPreority();
	}
}

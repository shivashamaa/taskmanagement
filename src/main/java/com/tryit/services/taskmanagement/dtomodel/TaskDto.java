package com.tryit.services.taskmanagement.dtomodel;

import com.tryit.services.taskmanagement.businessmodel.TaskBusinessModel;
import com.tryit.services.taskmanagement.models.Task;

import lombok.Data;
@Data
public class TaskDto {
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
	private String status = "active";
	private String module;
	private String preority;
	
	public TaskDto() {
		
	}
	
	public TaskDto(TaskBusinessModel taskBusinessModel) {
		System.err.println("taskBusinessModel "+taskBusinessModel);
		this.id = taskBusinessModel.getId();
		this.userId = taskBusinessModel.getUserId();
		this.createdBy = taskBusinessModel.getCreatedBy();
		this.createdDate = taskBusinessModel.getCreatedDate().toString();
		this.lastUpdatedDate =taskBusinessModel.getLastUpdatedDate().toString();
		this.taskName = taskBusinessModel.getTaskName();
		this.description = taskBusinessModel.getDescription();
		this.startDate = taskBusinessModel.getStartDate().toString();
		this.endDate = taskBusinessModel.getEndDate().toString();
		this.expectedEndDate = taskBusinessModel.getExpectedEndDate() != null ? taskBusinessModel.getExpectedEndDate().toString():"";
		this.status = taskBusinessModel.getStatus();
		this.module = taskBusinessModel.getModule();
		this.preority = taskBusinessModel.getPreority();
	}



	
}

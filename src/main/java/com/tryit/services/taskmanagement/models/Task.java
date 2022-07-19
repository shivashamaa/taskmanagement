package com.tryit.services.taskmanagement.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import com.tryit.services.taskmanagement.businessmodel.TaskBusinessModel;
import com.tryit.services.taskmanagement.utilities.CalenderOperation;

import lombok.Data;

@Entity
@Data
public class Task {
	@Id
	@Column(nullable = false, length = 100)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
//	@NotBlank
	@Column(nullable = false, length = 100)
	private String userId;
	
	@NotBlank
	@Column(nullable = false, length = 100)
	@CreatedBy
	private String createdBy;
	
//	@NotBlank
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false,updatable = false)
	@CreatedDate
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date lastUpdatedDate;
	
	@NotBlank
	@Column(nullable = false, length = 250)
	private String taskName;
	
	@Column(columnDefinition="TEXT")
	private String description;

    @Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date startDate;
	
    @Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date expectedEndDate;
	
//	@NotBlank
	@Column(length = 100,name= "status")
	private String status = "active";
	
	@Column(length = 100)
	private String module;
	
	@Column(length = 100)
	private String preority;

	public Task(@NotBlank Long id, @NotBlank String userId, @NotBlank String createdBy, @NotBlank Date createdDate,
			Date lastUpdatedDate, @NotBlank String taskName, String description, @NotBlank Date startDate,
			@NotBlank Date endDate, @NotBlank Date expectedEndDate, @NotBlank String status, String module,
			String preority) {
		super();
		this.id = id;
		this.userId = userId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.taskName = taskName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.expectedEndDate = expectedEndDate;
		this.status = status;
		this.module = module;
		this.preority = preority;
	}

//	public Task(String taskName, String description, String preority, String status, String module, String userId,
//			String createdBy,Date startDate,Date endDate, Date expectedEndDate,Date createdDate,
//			Date lastUpdatedDate) {
//		this.userId = userId;
//		this.createdBy = createdBy;
//		this.taskName = taskName;
//		this.description = description;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.expectedEndDate = expectedEndDate;
//		this.status = status;
//		this.module = module;
//		this.preority = preority;
//		this.createdDate = createdDate;
//		this.lastUpdatedDate = lastUpdatedDate;
//	}

	public Task(TaskBusinessModel taskBusinessModel) {
		System.err.println("taskBusinessModel "+taskBusinessModel);
		System.err.println("start date "+taskBusinessModel.getStartDate());
		CalenderOperation calender = new CalenderOperation();
		Date startDate= calender.convetStringToDate(calender.convertDateFormat(taskBusinessModel.getStartDate(),"yyyy-MM-dd'T'hh:mm","yyyy-MM-dd hh:mm:ss"),"yyyy-MM-dd hh:mm:ss");
	    Date endDate = calender.convetStringToDate(calender.convertDateFormat(taskBusinessModel.getEndDate(),"yyyy-MM-dd'T'hh:mm","yyyy-MM-dd hh:mm:ss"),"yyyy-MM-dd hh:mm:ss");
	    Date expectedEndDate = calender.convetStringToDate(calender.convertDateFormat(taskBusinessModel.getExpectedEndDate(),"yyyy-MM-dd'T'hh:mm","yyyy-MM-dd hh:mm:ss"),"yyyy-MM-dd hh:mm:ss");
	    Date createdDate = calender.getDefaultCurrentDateByTimezone("GMT+5:30");
	    Date lastUpdatedDate = calender.getDefaultCurrentDateByTimezone("GMT+5:30");
	    this.id = taskBusinessModel.getId();
		this.userId = taskBusinessModel.getUserId();
		this.createdBy = taskBusinessModel.getCreatedBy();
		this.taskName = taskBusinessModel.getTaskName();
		this.description = taskBusinessModel.getDescription();
		this.startDate = startDate;
		this.endDate = endDate;
		this.expectedEndDate = expectedEndDate;
		this.status = taskBusinessModel.getStatus();
		this.module = taskBusinessModel.getModule();
		this.preority = taskBusinessModel.getPreority();
		this.createdDate = createdDate;
		this.lastUpdatedDate = lastUpdatedDate;
		System.err.println("start date "+startDate);
	}

	public Task() {
	}
	
	
}

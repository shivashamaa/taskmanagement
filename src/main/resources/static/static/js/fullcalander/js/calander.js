var baseUrl = window.location.origin;
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	var calanderEvent = [];
	function getAllTasks() {
		console.log(baseUrl + "/getAllTask")
		$.ajax({
			type: "GET",
			url: baseUrl + "/getAllTask",
			success: function(response) {
				console.log(response);
				calanderEvent = response;
				response.forEach(function(element) {
					console.log(element);
					calendar.addEvent({
						title: element.taskName,
						//						start: "2022-05-26T00:10:00",
						start: element.startDate,
						end: element.endDate,
						eventColor: '#378090',
						id: element.id,
						description: element.description,
						allDay: false,
						textColor: 'black',
						borderColor: 'black',
						color: element.status == "in-progress" ? "#cfcffc" : element.status == "to-do" ? "#b3b3b3" : element.status == "completed" ? "#a3f5b8" : "blue",
					});
				});
			}
		});
	}
	getAllTasks();

	function editTask(id) {
		$.ajax({
			type: "GET",
			url: baseUrl + "/editTasks/" + id,
			//			type: "POST",
			//			data:{"Task":{"id":49}},
			//			url: baseUrl + "/getTask",
			success: function(response) {
				console.log("d" + response);
				document.getElementById('taskName').value = response.taskName;
				document.getElementById('description').value = response.description;
				document.getElementById('module').value = response.module;
				document.getElementById('userId').value = response.userId;
				document.getElementById('createdBy').value = response.createdBy;
				document.getElementById('startDate').value = response.startDate;
				document.getElementById('endDate').value = response.endDate;
				document.getElementById('expectedEndDate').value = response.expectedEndDate;
				document.getElementById('preority').value = response.preority;
				document.getElementById('status').value = response.status;
				document.getElementById('id').value = response.id;
				$('#update').removeClass("fade");
				$('#add').addClass("fade");
			}
		});
	}
	var calendar = new FullCalendar.Calendar(calendarEl, {
		contentHeight: "600",
		themeSystem: 'bootstrap5',
		initialView: 'timeGridDay',
		initialDate: new Date(),
		editable: true,
		selectable: true,
		eventLimit: true,
		height: $(window).height() - 50,
		allDaySlot: false,
		headerToolbar: {
			left: 'prev,next today custom1',
			center: 'title',
			right: 'timeGridWeek timeGridDay listMonth'
		},
		views: {
			timeGridWeek: {
				titleFormat: { weekday: 'short', month: 'numeric', day: 'numeric', omitCommas: true },
				slotDuration: '00:15:00',
			},
			timeGridDay: {
				type: 'timeGrid',
				duration: { days: 1 },
				buttonText: 'day'
			}
		},
		events: calanderEvent,
		customButtons: {
			custom1: {
				text: 'Add Task',
				click: function() {
					clearForm();
					$('#schedule-add').modal('show');
					$('.modal').removeClass("fade");
					$('#add').removeClass("fade");
					$('#update').addClass("fade");
				}
			}
		},
		//		events: [
		//			{
		//				title: 'create database',
		//				start: '2022-05-25T23:00:00',
		//				end: '2022-05-25T23:30:00'
		//			}
		//		],
		eventClick: function(info) {
			$('#schedule-add').modal('show');
			$('.modal').removeClass("fade");
			console.log("inf =" + info.event.id)
			console.log('Event: ' + info.event.title + '\n start at ' + info.event.start); info.el.style.borderColor = 'red';
			editTask(info.event.id);
		},
		eventDrop: function(info, delta, revertFunc) {
			console.log(event);
			$.confirm({
				title: 'Confirmation',
				content: 'Are you sure about this change?',
				buttons: {
					confirm: function() {
						var task = {};
						task.startDate = info.event.start;
						task.endDate = info.event.end;
						task.id = info.event.id;
						updateTime(task);
						$.notify("Updated successfully", "success");
					},
					cancel: function() {
						info.revert();
					},
				}
			});
			//			location.reload()
		},
		select: function(start, end, jsEvent, view) {
			clearForm();
			document.getElementById('startDate').value = moment(start.start).format("yyyy-MM-DDThh:mm");
			console.log(moment(start).format("yyyy-MM-DDThh:mm"));
			console.log(start)
			console.log("start " + start.start)
			$('#schedule-add').modal('show');
			$('.modal').removeClass("fade");
			$('#add').removeClass("fade");
			$('#update').addClass("fade");

		},
		eventResize: function(event, delta, revertFunc, ui, view, jsEvent) {
			$.confirm({
				title: 'Confirmation',
				content: 'Are you sure about this change?',
				buttons: {
					confirm: function() {
						var task = {};
						console.log("event =>" + event)
						task.startDate = event.event.start;
						task.endDate = event.event.end;
						task.id = event.event.id;
						updateTime(task);
						$.notify("Updated successfully", "success");
					},
					cancel: function() {
						event.revert();
					},
				}
			});

		}
	});
	calendar.render();
});

function updateTime(task) {
	console.log(task);
	$.ajax({
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify(task),
		dataType: 'json',
		headers: {
			Accept: "application/json",
			"Content-Type": "application/json"
		},
		url: baseUrl + "/updateStartEndTime",
		success: function(response) {
			console.log(response);
		}
	});
}


function clearForm() {
	document.getElementById('taskName').value = "";
	document.getElementById('description').value = "";
	document.getElementById('module').value = "";
	document.getElementById('userId').value = "";
	document.getElementById('createdBy').value = "";
	document.getElementById('startDate').value = "";
	document.getElementById('endDate').value = "";
	document.getElementById('expectedEndDate').value = "";
	document.getElementById('preority').value = "";
	document.getElementById('status').value = "";
	document.getElementById('id').value = "";
}

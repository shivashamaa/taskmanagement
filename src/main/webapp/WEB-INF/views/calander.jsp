<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<script src="static/js/fullcalander/js/main.js"></script>
<script src="static/js/fullcalander/js/main.min.js"></script>
<script src="static/js/fullcalander/js/locales-all.js"></script>
<script src="static/js/fullcalander/js/locales-all.min.js"></script>
<script src="static/js/fullcalander/js/calander.js"></script>
<script src="static/js/fullcalander/js/moment.js"></script>
<script src="static/jquery/jQuery-3.6.0.js"></script>
<script src="static/js/fullcalander/js/jquery-confirm.js"></script>
<script src="static/js/fullcalander/js/notify.js"></script>
<link rel="stylesheet" href="static/css/fullcalandercss/main.css">
<link rel="stylesheet" href="static/css/fullcalandercss/main.min.css">
<link rel="stylesheet" href="static/css/calander.css">
<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet'>
<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css' rel='stylesheet'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
  <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<div id='calendar'></div>

<!-- <div class="center hideform"> -->
<!--     <button id="close" style="float: right;">X</button> -->
<!--     <form action="/action_page.php"> -->
<!--         First name:<br> -->
<!--         <input type="text" name="firstname" value="Mickey"> -->
<!--         <br> -->
<!--         Last name:<br> -->
<!--         <input type="text" name="lastname" value="Mouse"> -->
<!--         <br><br> -->
<!--         <input type="submit" value="Submit"> -->
<!--     </form> -->
<!-- </div> -->

<div class="modal fade" id="schedule-add">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Add Your Task</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
				<form:form action="${isEdit ? 'updateForm' : 'createForm'}"
					modelAttribute="taskDto">

					<form:label path="taskName">TaskName:</form:label>
					<form:input path="taskName" />
					<br />

					<form:label path="description">Description:</form:label>
					<form:input path="description" />
					<br />

					<form:label path="module">Module:</form:label>
					<form:input path="module" />
					<br />

					<form:label path="userId">Assignee:</form:label>
					<form:input path="userId" />
					<br />

					<form:label path="createdBy">created by:</form:label>
					<form:input path="createdBy" />
					<br />

					<form:label path="startDate">Start date:</form:label>
					<form:input type="datetime-local" path="startDate" />
					<br />

					<form:label path="endDate">End Date:</form:label>
					<form:input type="datetime-local" path="endDate" />
					<br />

					<form:label path="expectedEndDate">Expected Date:</form:label>
					<form:input type="datetime-local" path="expectedEndDate" />
					<br />

					<form:label path="preority">Priority:</form:label>
					<form:select path="preority" items="${priority}">
					</form:select>
					<br>
					<form:label path="status">Status:</form:label>
					<form:select path="status" items="${status}">
					</form:select>
					<form:input type="hidden" path="id" />
					<br>
					<div class="modal-footer">
					<form:button id ="update" onclik="test({})" class="btn btn-success fade">Update Task</form:button>
							
					<form:button id="add" onclick = "test({})" class="btn btn-success">Add Task</form:button>
							
					</div>


					<!-- <input type="submit" value="Submit" />  -->
				</form:form>
			</div>
            <!-- Modal footer -->
        </div>
    </div>
</div>
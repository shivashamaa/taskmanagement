<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!-- <meta charset="ISO-8859-1">
 --><head>
<style type="text/css">
    label {
        display: inline-block;
        width: 200px;
        margin: 5px;
        text-align: left;
    }
    input[type=text], input[type=password],input[type=date] {
        width: 200px;  
        text-align: right;
    }
    select {
       width: 200px;  
        padding: 2.5px;
        text-align: right;
    }
    button {
        padding: 7px;
         margin: 10px;
        ext-align: right;
    }
</style>
<title>Task Form</title>
</head>
<body>
<div align="left">
<h1> <c:choose>
    <c:when test="${isEdit}">Update Task </c:when>    
    <c:otherwise>Create Task</c:otherwise>
</c:choose> </h1>
<table style="with: 50%">
	    <form:form action="${isEdit ? 'updateForm' : 'createForm'}" modelAttribute="taskDto">
	    
			    <form:label path="taskName">TaskName:</form:label>
	            <form:input path="taskName"/><br/>
	             
	            <form:label path="description">Description:</form:label>
	            <form:input path="description"/><br/>
	 
	            <form:label path="module">Module:</form:label>
	            <form:input path="module"/><br/>
	            
	            <form:label path="userId">Assignee:</form:label>
	            <form:input path="userId"/><br/>
	            
	            <form:label path="createdBy">created by:</form:label>
	            <form:input path="createdBy"/><br/>
	            
	            <form:label path="startDate">Start date:</form:label>
	            <form:input type="date" path="startDate"/><br/>
	            
	            <form:label path="endDate">End Date:</form:label>
	            <form:input type="date" path="endDate"/><br/>
	            
	            <form:label  path="expectedEndDate">Expected Date:</form:label>
	            <form:input type="date" path="expectedEndDate"/><br/>
	            
	            <form:label path="preority">Priority:</form:label>
	           	<form:select path="preority" items="${priority}"> </form:select> 
	             <br>
	           	<form:label path="status">Status:</form:label>
	           	<form:select path="status" items="${status}"> </form:select> 
	           	 <form:input type="hidden" path="id"/> 
	            <br>
	             
	            <c:choose>
    			<c:when test="${isEdit}"> 
    			<form:button>Update Task</form:button></c:when>    
    			<c:otherwise><form:button>Add Task</form:button></c:otherwise>
				</c:choose>
	            
	          
		    <!-- <input type="submit" value="Submit" />  -->    
		   
		 </form:form> 
		 <button type="button" onclick="taskList()">Task List</button> 
		  </table> 
		 </div>
</body>
</html>

<script language="javascript" type="text/javascript">

function taskList()
{
	window.location.href = "/taskList";
}
</script>



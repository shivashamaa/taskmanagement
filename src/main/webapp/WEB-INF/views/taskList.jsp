<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List</title>
</head>
<body>
    <div align="center" class ="padding-bottom-20">
        <table border="1" cellpadding="5">
            <caption><h2>List of users</h2></caption>
            <tr>
                <th>Task Name</th>
                <th>Task Description</th>
                <th>Status</th>
                <th>Module</th>
                <th>Priority</th>
                <th>Start date</th>
                <th>End Date</th>
                <th>Expected Date</th>
                 <th></th>
            </tr>
            <c:forEach var="user" items="${taskList}">
                <tr>
                    <td><c:out value="${user.taskName}" /></td>
                    <td><c:out value="${user.description}" /></td>
                    <td><c:out value="${user.status}" /></td>
                    <td><c:out value="${user.module}" /></td>
                    <td><c:out value="${user.preority}" /></td>
                    <td><c:out value="${user.startDate}" /></td>
                    <td><c:out value="${user.endDate}" /></td>
                    <td><c:out value="${user.expectedEndDate}"/></td>
                    <td><button type="button" onclick="editTask(${user.id})">Edit</button> </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>

<style>
.selected-item {
  font-size: 15px;
  margin: 5px;
}

input[type="checkbox"] {
  width: 20px;
  height: 20px;
}

.padding-bottom-20 {
  padding-bottom: 20px;
}
</style>

<script language="javascript" type="text/javascript">

function editTask(id)
{
	window.location.href = "editTask/"+id;
}
</script>
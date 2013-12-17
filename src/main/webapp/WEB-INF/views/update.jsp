<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mtt Task Management Tool</title>
</head>
<body>
	<div align="center">
	<form:form method="post" action="updateTask" commandName="taskForm">
		<div><h3>Update Task Details</h3></div>
		<table>
	    	<td><form:input type="hidden" path="taskId"/></td>
	    	<tr>
				<td>Description:</td><td><form:input path='description' /></td><td><font color='red'><form:errors path='description' /></font></td>
			</tr>
			<tr>
				<td>Checked:</td><td><form:checkbox path="checked"/></td>
			<tr>
				<td><input type='submit' value='Update' /></td><td><input type="button" onclick="location.href='home'" value="Cancel" /></td>
			</tr>
		</table>
	</form:form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mtt Task Management Tool</title>
</head>
<body>
	<div width="700px" align="center">
	<form:form method="post" action="createTask" commandName="taskForm">
		<div><h3>Create Task Details</h3></div>
		<table>
	    	<tr>
				<td>Description: </td><td><form:input path='description' /></td><td><font color='red'><form:errors path='description' /></font></td>
			</tr>
			<tr>
				<td>Checked:</td><td><form:checkbox path='checked' /></td></td>
			</tr>
			<tr>
				<td><input type='submit' value='Create' /></td><td><input type="button" onclick="location.href='home'" value="Cancel" /></td>
			</tr>
		</table>
	</form:form>
	</div>
</body>
</html>
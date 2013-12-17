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
	<form:form method="post" action="login" commandName="loginForm">
		<table>
	    	<tr>
				<td>Username:</td><td><form:input path='username' /></td><td><font color='red'><form:errors path='username' /></font></td>
			</tr>
			<tr>
			<tr>
				<td>Password:</td><td><form:input type="password" path='password' /></td><td><font color='red'><form:errors path='password' /></font></td>
			</tr>
			<tr>
				<td><form:input type="hidden" path='general' /></td><td><font color='red'><form:errors path='general' /></font></td>
			<tr>
				<td><input type='submit' value='Login' /></td>
			</tr>
		</table>
	</form:form>
	</div>
</body>
</html>
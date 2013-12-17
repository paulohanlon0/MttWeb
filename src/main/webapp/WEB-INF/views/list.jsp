<%@page import="ie.mtt.mtttodo.auth.api.AuthUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="ie.mtt.mtttodo.auth.api.AuthUser" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mtt Task Management Tool</title>
</head>
<body>
	<% AuthUser user = (AuthUser)request.getSession().getAttribute("authUser");
		String firstname = user.getFirstName();
		String surname = user.getSurname();%>
	<div width="700px" align="center">
		<table width="700px">
			<tr>
				<td align="left"><h4>Welcome: <%=firstname %> <%=surname %></h4></td>
				<td align="right"><input type="button" onclick="location.href='logout'" value="Logout"/></td>
			</tr>
		</table>
	</div>
	<div align="center">
	<table width="700px">
		<tr>
			<td align="left"><input type="button" onclick="location.href='createForm'" value="Create Task" /></td>
		</tr>
		<tr><td align="left"><font color='red'><form:errors path="errorText" /></font></td></tr>
	</table>
	
	<table width="700px">
		<thead width="700px">
			<tr align="left">
				<th width="280px"><b>Description</b></th>
				<th width="120px"><b>Entry Date</b></th>
				<th width="120px"><b>Checked</b></th>
				<th width="180px"><b>Actions</b></th>
			</tr>
		</thead>
		<tbody width="700px">
			<c:forEach items="${tasksForm}" var="task">
				<tr align="left">
					<td width="280px"><c:out value="${task.description}" /></td>
					<td width="120px"><c:out value="${task.entryDate}" /></td>
					<td width="120px"><input type="checkbox" disabled="disabled" <c:if test="${task.checked}">checked="checked"</c:if> /></td>
					<td width="180px">
						<input type="button" onclick="location.href='updateForm?taskId=${task.taskId}'" value="Update" />
						<input type="button" onclick="location.href='delete?taskId=${task.taskId}'" value="Delete" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>
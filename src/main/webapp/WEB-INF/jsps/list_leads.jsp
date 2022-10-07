<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leads</title>
</head>
<body>
	<a href="viewLeadPage">Create New Lead</a>
	<h2>List All Leads</h2>
	<table>
		<tr>
			<th>FirstName</th>
			<th>LastName</th>
			<th>EMAIL</th>
			<th>MOBILE</th>
			<th>SOURCE</th>
		</tr>
		<c:forEach var="lead" items="${leads}">
			<tr>
				<td><a href="findLeadById?id=${lead.id}">${lead.firstName}</a></td>
				<td>${lead.lastName}</td>
				<td>${lead.email}</td>
				<td>${lead.mobile}</td>
				<td>${lead.source}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
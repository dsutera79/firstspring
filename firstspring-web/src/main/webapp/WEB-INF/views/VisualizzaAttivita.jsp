<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Gestione Attività</title>
				
	</head>
	
<body>
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand">Activity List</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li><a href="activities/new">New Activity</a></li>
	    </ul>
	  </div>
	</nav>
	
	<div  class="container">
	
		<c:if test = "${listActivities.size() == 0}"><h3 align="center">No Activity!</h3></c:if>
		<c:if test = "${listActivities.size() != 0}">
			<table class="table table-condensed">
			<thead>
				<tr>
					<th>ID Activity</th>
					<th>Name Activity</th>
					<th>State Activity</th>
					<th></th><th></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listActivities}" var="activity">
				<tr
					<c:choose>
						<c:when test = "${activity.stateActivity=='Active'}"> class="success"</c:when>
						<c:when test = "${activity.stateActivity=='Referred'}"> class="info"</c:when>
						<c:when test = "${activity.stateActivity=='Closed'}"> class="danger"</c:when>
					</c:choose>>
					<td >${activity.idActivity}</td>
					<td>${activity.activity}</td>
					<td>${activity.stateActivity}</td>
					<td>
						<button type="submit" class="btn btn-info" 
							onclick="window.location.href='activities/${activity.idActivity}/edit'"><span class="glyphicon glyphicon-pencil"></span>
						</button>
					</td>
					<td>
						<button type="submit" class="btn btn-danger" id="del_${activity.idActivity}" 
							onclick="javascript:delActivity(${activity.idActivity})"><span class="glyphicon glyphicon-trash"></span>
						</button>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
		</c:if>
	</div>	


<script>
	function delActivity(idActivity){
		var id="#del_" + idActivity;
		var URLid = '<%=request.getContextPath()%>/activities/'+idActivity;
		$.ajax({
			url: URLid,
			type: 'DELETE',
			success: function(response){$(id).parents("tr").fadeOut(800);},
			error: function(response){alert("Activity not deleted - HTTP Response: "+ response.status);}
		});
	}
</script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Gestione Attività</title>
	</head>
	
<body>
	
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand">
	      	<c:choose>
				<c:when test = "${activity !=null}">Modify Activity</c:when>
				<c:when test = "${activity == null}">New Activity</c:when>
			</c:choose>
	      </a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li><a href="<%=request.getContextPath()%>/activities">Activity List</a></li>
	    </ul>
	  </div>
	</nav>

	<div  class="container">	
		<c:choose>
			<c:when test = "${activity !=null}"><form id="editActivityForm" action="javascript:modActivity(${activity.idActivity})"></c:when>
			<c:when test = "${activity == null}"><form action="<%= request.getContextPath() %>/activities" method="POST" name="newActivityForm"></c:when>
		</c:choose>
		
			<div class="form-group">
		    	<label for="nomeAttività">Name Activity:</label>
		    	<input type="input" class="form-control" id="nameActivity" name="nameActivity" value="${activity.activity}">
		  	</div>
		  	<div class="form-group">
		    	<label for="tipoAttivita">Activity Sate:</label>
		    	<select class="form-control" data-width="auto id="stateActivity" name="stateActivity">
	  				<option selected value="Active">Active</option>
	  				<option value="Referred">Referred</option>
	  				<option value="Closed">Closed</option>
				</select>
		  	</div>
		  
			<c:choose>
				<c:when test = "${activity !=null}"><button type="submit" class="btn btn-default">Update Activity</button></c:when>
				<c:when test = "${activity == null}"><button type="submit" class="btn btn-default">Add Activity</button></c:when>
			</c:choose>		
		</form>

		<c:if test = "${activity !=null}">
			<script>
				$("select").val("${activity.stateActivity}")
			
				function modActivity(idActivity){
					URLId = '<%=request.getContextPath()%>/activities/'+idActivity;
					$.ajax({
						type: 'PUT',
						url: URLId,
						success: function(response) {alert("Correctly Updated!");},
						error: function(response){alert("Activity not updated - HTTP Response: " + response.status);}
					});
				}
			</script>
		</c:if>
	</div>
	
</body>
</html>
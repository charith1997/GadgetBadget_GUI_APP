<%@page import="model.Project"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project Management - GadgetBadget</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="Components/jquery-3.5.0.min.js"></script>
	<script src="Components/Project.js"></script>
	
</head>
<body>
	<div class="container"> 
 		<p class="font-weight-bold">
				<center><h1><u><i><b>Project Management - GadgetBadget</b></i></u></h1></center>
		</p>
			<br><br>
			
			<fieldset>
	
				<legend><b>Add Project Details</b></legend>
					<form id="PROJECT" name="PROJECT" class="border border-light p-5">
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Project Code:</label>
						    <input type="hidden" id="projectID" name="projectID">
						    <input type="text" id="projectCode" class="form-control" name="projectCode">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Project Title:</label>
						    <input type="text" id="projectTitle" class="form-control" name="projectTitle">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Project Manager:</label>
						    <input type="text" id="projectManager" class="form-control" name="projectManager">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Project Category:</label>
						    <input type="text" id="projectCategory" class="form-control" name="projectCategory">						    
						</div>
												
						<br> 
						<div id="alertSuccess" class="alert alert-success"></div>
						<div id="alertError" class="alert alert-danger"></div>	
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg btn-block"> 
						
					</form>
				
							
			</fieldset>
			
			<br> 
			
			<div class="container" id="ProjectGrid">
				<fieldset>
					<legend><b>View Project Details</b></legend>
					<form method="post" action="project.jsp" class="table table-striped">
						<%
						Project viewProject = new Project();
						out.print(viewProject.readproject());
						%>
					</form>
				</fieldset>
			</div>
	</div>
</body>
</html>
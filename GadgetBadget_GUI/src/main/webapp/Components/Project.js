//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidProjectIDSave").val("");
	$("#PROJECT")[0].reset();
});

$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text(""); 
	$("#alertSuccess").hide(); 
	$("#alertError").text(""); 
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm(); 
	
	// If not valid
	if (status != true) 
	{ 
 		$("#alertError").text(status); 
 		$("#alertError").show(); 
		return; 
	}
	
	// If valid
	var type = ($("#projectID").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "ProjectAPI",
		type : type,
		data : $("#PROJECT").serialize(),
		dataType : "text",
		complete : function(response, status) {
			//console.log(status);
			onItemSaveComplete(response.responseText, status);
			window.location.reload(true);
		}
	});
	
});

function onItemSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#ProjectGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#projectID").val("");
	$("#PROJECT")[0].reset();
}

 $(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "ProjectAPI",
		type : "DELETE",
		data : "projectID=" + event.target.value,
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
			window.location.reload(true);
		}
	});
});

function onItemDeleteComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#ProjectGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event)
		{
			$("#projectID").val($(this).closest("tr").find('td:eq(0)').text());
			$("#projectCode").val($(this).closest("tr").find('td:eq(1)').text());
			$("#projectTitle").val($(this).closest("tr").find('td:eq(2)').text());
			$("#projectManager").val($(this).closest("tr").find('td:eq(3)').text());
			$("#projectCategory").val($(this).closest("tr").find('td:eq(4)').text());		
		});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	// Project Code
	if ($("#projectCode").val().trim() == "") {
		return "Please insert Project Code.";
	}
	
	// Project Title
	if ($("#projectTitle").val().trim() == "") {
		return "Please insert Project Title.";
	}
	
	// Project Manager
	if ($("#projectManager").val().trim() == "") {
		return "Please insert Project Manager.";
	}
	
	// Project Category
	if ($("#projectCategory").val().trim() == "") {
		return "Please insert Project Category.";
	}
	
	return true;
}
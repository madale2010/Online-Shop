<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>

	<jsp:include page="main_menu.jsp" />
	<jsp:include page="/manager/UpdateDocument" />
	<div class="container-fluid">
		<div class="table-responsive">
			<form action="UpdateDocument" id="UpdateDocument" name="UpdateDocument" enctype="multipart/form-data" method="POST">
			<table id="documentTable" class="table table-hover">
	        	   <thead>
		            	<tr>
							<th>Name</th>
							<th>Data</th>
							<th>Date</th>
							<th>URL</th>
							<th> 
		                		<c:url var="addURL" value="updateSingleDocument.jsp"/>
		                		<a href="${addURL}" data-toggle="tooltip" title="Add new Document"  class="btn-link btn-success fa fa-plus"></a>
		                	</th>						
		                </tr>
	                </thead>
	                <tbody >	            	
	           		<c:forEach items="${documentList}" var="document" >
	           		<tr id="${document.name}">       			
	               		<td id="documentName">${document.name}</td>
	               		<td id="documentData"><textarea class="form-control" id="textarea" name="textarea" readonly> ${document.data}</textarea></td>
	            		<td id="documentDate">${document.date}</td>
	            		<td id="documentURL">${document.url}</td>
	            		<td id="delete"><button name="action" value="delete"  class="btn-link fa fa-times" onclick="return confirm('Are you sure you want to delete?');" ></button>
	            		<input type="hidden" name="id" value="${document.name}" />
	            		</td>
	           			<td>
	           		</tr>
	           		</c:forEach>
	         
	        	</tbody>
	    	</table>
	    	</form>
		</div>
	</div>
<br>
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	$( "#documentTable tr td" ).on( "click", function() {
		
		var itemClicked = $(this).attr('id');

		if(itemClicked=='delete'){
			var element = $( this );
			var $row = $(this).closest("tr"),        // Finds the closest row <tr> 
		    $tds = $row.find("td:nth-child(1)");
			var input3 = $("<input>").attr("type", "hidden").attr("name", "documentName").val($tds.text());
			$('#UpdateDocument').append($(input3));
		} else {
		var element = $( this );
		var $row = $(this).closest("tr"),        // Finds the closest row <tr> 
	    $tds = $row.find("td:nth-child(1)");
		var input = $("<input>").attr("type", "hidden").attr("name", "action").val("change");
		$('#UpdateDocument').append($(input));
		var input2 = $("<input>").attr("type", "hidden").attr("name", "documentName").val($tds.text());
		$('#UpdateDocument').append($(input2));
		 var clickedButtonId = $(this).attr("id");
		 $('#UpdateDocument').submit()
		}
	});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>

	<jsp:include page="main_menu.jsp" />
	<jsp:include page="/manager/UpdateCoupon" />
	<div class="container-fluid">
		<div class="table-responsive">
			<form action="UpdateCoupon" id="UpdateCoupon" name="UpdateCoupon" enctype="multipart/form-data" method="POST">
			<table id="couponTable" class="table table-hover">
	        	   <thead>
		            	<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Description</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Parameters</th>
							<th>Active</th>
							<th>Last Update</th>
							<th> 
		                		<c:url var="addURL" value="updateSingleCoupon.jsp" />
		                		<a href="${addURL}" data-toggle="tooltip" title="Add new Coupon"  class="btn-link btn-success fa fa-plus"></a>
		                	</th>
		                </tr>
	                </thead>
	                <tbody >	            	
	           		<c:forEach items="${couponList}" var="coupon" >
	           		<tr id="${couponId}">       			
	               		<td id="couponId" >${coupon.id}</td>
	               		<td id="couponName">${coupon.name}</td>
	               		<td id="couponDesc">${coupon.couponDesc}</td>
	            		<td id="couponStartDate">${coupon.activeDate}</td>
	            		<td id="couponEndDate">${coupon.expiresDate}</td>
	            		<td id="couponParm">${coupon.parmData}</td>
	            		<td id="couponActive">${coupon.active}</td>
	            		<td id="couponLastUpdate">${coupon.lastUpdated}</td>
	            		<td><button name="action" value="delete"  class="btn-link fa fa-times" onclick="return confirm('Are you sure you want to delete?');" ></button></td>
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
	$( "#couponTable tr td" ).on( "click", function() {
		
		var itemClicked = $(this).attr('id');

		if(itemClicked=='delete'){
			var element = $( this );
			var $row = $(this).closest("tr"),        // Finds the closest row <tr> 
		    $tds = $row.find("td:nth-child(1)");
			var input3 = $("<input>").attr("type", "hidden").attr("name", "couponId").val($tds.text());
			$('#UpdateCoupon').append($(input3));
		} else {
		var element = $( this );
		var $row = $(this).closest("tr"),        // Finds the closest row <tr> 
	    $tds = $row.find("td:nth-child(1)");
		var input = $("<input>").attr("type", "hidden").attr("name", "action").val("change");
		
		$('#UpdateCoupon').append($(input));
		var input2 = $("<input>").attr("type", "hidden").attr("name", "couponId").val($tds.text());
		$('#UpdateCoupon').append($(input2));
		 $('#UpdateCoupon').submit()
		}
	});
	</script>
</body>
</html>
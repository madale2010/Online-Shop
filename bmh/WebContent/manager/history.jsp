<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>

	<jsp:include page="/manager/GenerateReports" />
	<jsp:include page="main_menu.jsp" />
		<div class="container-fluid">
			<table class="table table-hover table-fixedheader">
	        	   <thead>
		            	<tr class="col-xs-12">
							<th class="col-xs-1">Order Id</th>
							<th class="col-xs-1">Sale Date</th>
							<th class="col-xs-1">Full Name</th>
							<th class="col-xs-1">Date Shipped</th>
							<th class="col-xs-1">Order Value</th>
							<th class="col-xs-1">Shipping</th>
							<th class="col-xs-1">Sales Tax</th>
							<th class="col-xs-1" >Order Total</th>
							<th class="col-xs-1" >Card Fees</th>
							<th class="col-xs-1">Total</th>								
		                </tr>
	                </thead>
	                <tbody>	            	
	           		<c:forEach items="${orderList}" var="order" >
	           		<tr class="col-xs-12">       			
	               		<td class="col-xs-1">${order.getOrderId()}</td>
	            		<td class="col-xs-1">${order.getSaleDate()}</td>
	            		<td class="col-xs-1">${order.getFullName()}</td>
	            		<td class="col-xs-1">${order.getShipDate()}</td>
	            		<td class="col-xs-1">${order.getOrderValue()}</td>
	            		<td class="col-xs-1">${order.getShipping()}</td>
	            		<td class="col-xs-1">${order.getSalesTax()}</td>
	            		<td class="col-xs-1">${order.getOrderTotal()}</td>
	            		<td class="col-xs-1">${order.getCardFees()}</td>
	            		<td class="col-xs-1">${order.getNetAmount()}</td>
	           		</tr>
	           		</c:forEach>
	                    	
	                	
	        	</tbody>
	    	</table>
	</div>
	<!-- Footer -->
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>
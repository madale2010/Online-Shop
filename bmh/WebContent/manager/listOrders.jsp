<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>
	<jsp:include page="main_menu.jsp" />
	<jsp:include page="/OrderHandler" />
	<div class="container-fluid">
		<div class="table-responsive">
			<form action="../OrderHandler" id="orderForm" name="OrderHandler">
			<table id="orderTable" class="table table-hover">
	        	   <thead>
		            	<tr>
							<th>Id</th>
							<th>Status</th>
							<th>Order Date</th>
							<th>Buyer Name</th>
							<th>Date Shipped</th>
							<th>Payment Status</th>
							<th>Items</th>
							<th>Shipping</th>
							<th>Total</th>						
		                </tr>
	                </thead>
	                <tbody >	            	
	           		<c:forEach items="${orderList}" var="order" >
	           		<tr id="${orderId}">       			
	               		<td id="orderId" >${order.getOrderId()}</td>
	               		<td id="orderStatus">${order.getOrderStatus()}</td>
	            		<td id="orderDate">${order.getOrderDate()}</td>
	            		<td id="buyerName">${order.getBuyerName()}</td>
	            		<td id="shipDate">${order.getShipDate()}</td>
	            		<td id="paymentStatus">${order.getPaymentStatus()}</td>
	            		<td id="totalItems">${order.getTotalItems()}</td>
	            		<td id="shippingCost">$${order.getShippingCost()}</td>
	            		<td id="orderTotal">$${order.getOrderTotal()}</td>
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
</body>
</html>
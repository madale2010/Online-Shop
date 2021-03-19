<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>
	<jsp:include page="/manager/GenerateReports" />
	<jsp:include page="main_menu.jsp" />
		<div class="container-fluid">
			<table class="table table-sm table-responsive">
	        	   <thead>
		            	<tr class="col-xs-12">
							<th class="col-xs-1">Sale Id</th>
							<th class="col-xs-1">Amount</th>
							<th class="col-xs-1">State</th>
							<th class="col-xs-1">Pay Id</th>
							<th class="col-xs-1">Time</th>
							<th class="col-xs-1">Payment</th>
							<th class="col-xs-1">Protection</th>
							<th class="col-xs-1">Status</th>
							<th class="col-xs-1">Fees</th>
							<th class="col-xs-1">Amt Rec</th>
							<th class="col-xs-1">Rate</th>
							<th class="col-xs-1">Receipt</th>						
		                </tr>
	                </thead>
	                <tbody>	            	
	           		<c:forEach items="${saleList}" var="sale" >
	           		<tr class="col-xs-12">       			
	               		<td >${sale.getId()}</td>
	            		<td >${sale.getAmount().getTotal()}</td>
	            		<td >${sale.getParentPayment()}</td>
	            		<td >${sale.getCreateTime()}</td>
	            		<td >${sale.getPaymentMode()}</td>
	            		<td >${sale.getProtectionEligibility()}</td>
	            		<td >${sale.getPaymentHoldStatus()}</td>
	            		<td >${sale.getTransactionFee().getValue()}</td>
	            		<td >${sale.getReceivableAmount().getValue()}</td>
	            		<td >${sale.getExchangeRate()}</td>
	            		<td >${sale.getReceiptId()}</td>
	           		</tr>
	           		</c:forEach>
	                    	
	                	
	        	</tbody>
	    	</table>
	</div>
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="fragments/header.jsp" />
	<c:import url="/CartHolder" />
	<body>
		<c:import url="fragments/googleTags.jsp" />
		<!--  Start of menu bar -->
		<jsp:include page="fragments/main_menu.jsp" />
		<div class="container-fluid">
			<h1 style="display:none;">Blue Mountain Holly - Cart</h1>

			<h2>Shopping Cart</h2>
			<hr>
			<c:choose>
				<c:when test="${empty cartItems}">
					<p>Your cart is currently empty.</p>
				</c:when>
				<c:otherwise>
				
				<div class="col-xs-8">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th><label>Item</label></th>
									<th><label>Name</label></th>
									<th><label>Price</label></th>
									<th><label>Quantity</label></th>
									<th><label></label></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${cartItems}" var="cartItem">
									<tr>
										<td>
											<c:url var="productURL" value="product.jsp">
												<c:param name="pid" value="${cartItem.pid}" />
											</c:url>
											<a href="${productURL}" 
											   data-toggle="popover" 
											   title="${cartItem.name}" 
											   data-content="${cartItem.shortDescription}">
												<img src="${cartItem.imageURL}" 
													 alt="${cartItem.tags}"
													 class="img-responsive-checkout" /></a>
										</td>
										<td>${cartItem.name}</td>
										<td>${cartItem.price}</td>
										<td>
											<select class="form-control form-control-sm" name="selectedQuantity_${cartItem.pid}">
												<c:forEach var="i" begin="1" end="${cartItem.maxQuantity}">
													<c:choose>
														<c:when test="${i==cartItem.quantity}">
															<option value="${i}" selected="selected"><c:out value="${i}"></c:out></option>
														</c:when>
														<c:otherwise>
															<option value="${i}"><c:out value="${i}"></c:out></option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</td>
										<td>
											<form action="CartHolder" method="get">
												<button name="action" value="remove-${cartItem.pid}"
													class="btn-link fa fa-times"></button>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-xs-4 col-xs-pull">
					<ul class="list-group">
					    <li class="list-group-item list-group-item-info"><div class="row">Shipping Method</div>
					    	<sub>Enter shipping info to proceed to checkout</sub>
					    </li>
					    <li class="list-group-item">
						    <form role="form-horizontal" name="ShippingTool" action="ShippingTool" method="get">
								<input type="text" class="form-control-sm" size="2" name="inputZip"	id="inputZip"  value="${cartItem.destinationZip}" placeholder="Zip">
								<button class="btn-link fa fa-calculator" name="action" value="cartCalculate"></button>
							</form>
							<c:if test="${not empty rateList}">
							 <div class="form-group">
      							<label for="Select">Select Shipping Option</label>
      							<select id="shippingMethod" class="form-control form-control-sm">
									<c:forEach items="${rateList}" var="rate" >
										<c:choose>
											<c:when test="${shippingMethod==rate.serviceName}">
												<option selected value="${rate.serviceName}" > $${rate.amount} ${rate.serviceName}</option>
											</c:when>
											<c:otherwise>
												<option value="${rate.serviceName}" > $${rate.amount} ${rate.serviceName}</option>
											</c:otherwise>
										</c:choose>	
										  
									</c:forEach>
					      		</select>
							</div>
						</c:if>
					    </li>
					    <li class="list-group-item list-group-item-info">Totals</li>
					    <li class="list-group-item">
					     <fieldset disabled>
								<div class="row col-xs-push-1">
									<label>Subtotal</label>
									 <div class="col-xs-4">
									<input class="form-control form-control-sm" id="subTotal" name="subTotal" value="${cart.getStringSubTotal()}" />
									</div>
								</div>
								<div class="row col-xs-push-1">
									<label>Tax (5.3%)</label> 
									 <div class="col-xs-4">
									<input class="form-control form-control-sm " id="tax" name="tax" value="${cart.getStringTax()}" />
									</div>
								</div>
								<div class="row col-xs-push-1">
									<label>Handling Fee</label> 
									 <div class="col-xs-4">
									<input class="form-control form-control-sm" id="handlingFee" name="handlingFee" value="${cart.getStringHandlingFee()}" />
									 </div>
								</div>
								<div class="row col-xs-push-1">
									<label>Shipping</label>
									 <div class="col-xs-4"> 
										<input class="form-control form-control-sm" id="shippingTotal" name="shippingTotal" value="${cart.getStringShippingTotal()}" />
									</div>
								</div>
								<div class="row col-xs-push-1">
									<label>Grand Total</label>
									 <div class="col-xs-4">
										<input class="form-control form-control-sm" id="total" name="total" value="${cart.getStringTotal()}" />
									</div>
								</div>
							</fieldset>
						</li>
						 <li class="list-group-item">
						 	
								<form class="form-inline" id="t1" action="LoadCheckoutProcess">
									<c:choose>
									<c:when test="${empty cart.rateObject}">
										<input type="image" class="btn-link btn btn-sm" />
									</c:when>
									<c:otherwise>
										<script>
										    window.paypalCheckoutReady = function() {
										      paypal.checkout.setup("J82A9PHZTSDQ4", {
										        locale: 'en_US',
										        environment: 'production',
										        container: ['t1']
										      });
										    }
  										</script>
  										
									</c:otherwise>
								</c:choose>
								</form>
							
						</li>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
		<!-- Footer -->
		<jsp:include page="fragments/footer.jsp" />
		<script async src="//www.paypalobjects.com/api/checkout.js"></script>
        <script>
            $(document).on("change", "#shippingMethod", function() {// When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
            	 var shipMethod=$('#shippingMethod').val();
            	 var action='updateCartTotals';
                $.get("ShippingTool",{shippingMethod:shipMethod,action:action}, function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                    $("#total").text(responseText);
                    window.location.reload();
// Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                });
            });
        </script>
	</body>
</html>
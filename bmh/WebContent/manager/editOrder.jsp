<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>

	<c:import url="main_menu.jsp" />
	<br>
	<ul class="nav nav-tabs">
		<li class="nav-item"><a data-toggle="tab" class="nav-link active" href="#orderDetails">Order Details</a></li>
		<li class="nav-item"><a data-toggle="tab" class="nav-link" href="#customerDetails">Customer Details</a></li>
		<li class="nav-item"><a data-toggle="tab" class="nav-link" href="#shippingDetails">Shipping Details</a></li>
	</ul>
	
	<div class="container">
		<div class="tab-content">
	  		<div role="tabpanel" class="tab-pane fade in active" id="orderDetails">
				<div class="card">
					<div class="card-header">
						<h3 class="text-xs-center">Order Details</h3>
					</div>
					<div class="card-block">
						<form class="form-inline" role="form" action="OrderHandler" method="post">
					        <div class="row">
					         	<label for="inputsm" class="col-xs-2 form-control-label">Order Id: </label>
					         	<p class="form-control-static col-xs-4">${order.getOrderId()}</p>
					         	<label for="inputsm" class="col-xs-2 form-control-label">Order Status: </label> 
								<select class="form-control form-control-sm" name="order_status">
									<option value="Open">Open</option>
									<option value="Closed">Closed</option>
									<option value="Pending">Pending Refund</option>
								</select>				        
					        </div>
							<div class="row">
								<label for="inputsm" class="col-xs-2 form-control-label">Payment Date: </label>
								<p class="form-control-static col-xs-5">${order.getPaymentDate()}</p>
								<label for="inputsm" class="col-xs-2 form-control-label">Shipping Details </label>
								
							</div>
							<div class="row">
								<label for="inputsm" class="col-xs-2 form-control-label">Payment Status: </label>
								<p class="form-control-static col-xs-4">${order.getPaymentStatus()}</p>
								<label for="inputsm" class="col-xs-2 form-control-label">Rate Id: </label>
								<p class="form-control-static col-xs-3">${order.getRateDetails().getRateObjectId()}</p>
							</div>	
							<div class="row">
								<label for="inputsm" class="col-xs-2 form-control-label">Payment Method: </label>
								<p class="form-control-static col-xs-4">${order.getPaymentMethod()}</p>
								<label for="inputsm" class="col-xs-2 form-control-label">Provider: </label>
								<p class="form-control-static col-xs-3">${order.getRateDetails().provider}</p>
							</div>	
							<div class="row">
								<label for="inputsm" class="col-xs-2 form-control-label">Payment Type: </label>
								<p class="form-control-static col-xs-4">${order.getPaymentType()}</p>
								<label for="inputsm" class="col-xs-2 form-control-label">Service: </label>
								<p class="form-control-static col-xs-3">${order.getRateDetails().serviceName}</p>
							</div>	
							<div class="row">
								<label for="inputsm" class="col-xs-2 form-control-label">Protection Elg: </label>
								<p class="form-control-static col-xs-4">${order.getProtectionEligibility()}</p>
								  <button class="btn-sm btn btn-info" name="action" value="insert">Print Label</button>
							</div>	
							<div class="row">
								<label for="inputsm" class="col-xs-2 form-control-label"></label>
								<p class="form-control-static col-xs-4"></p>
							</div>																																									
						</form>
					</div>
				</div>
			</div>

			<!-- Tab page for Customer Details -->
			<div role="tabpanel" class="tab-pane fade in"  id="customerDetails">
				<div class="card">
					<div class="card-header">
						<h3 class="text-xs-center">Customer Details</h3>
					</div>
					<div class="card-block">
						<div class=" row col-xs-12">
							<label class="form-control-label form-control-sm">User Id :</label>
							${order.getBuyerUserId()}
						</div>
						<div class=" row col-xs-12">
							<label class="form-control-label form-control-sm">User Name :</label>
							${order.getBuyerName()}
						</div>
						<div class=" row col-xs-12">
							<label class="form-control-label form-control-sm">Full Name :</label>
							${order.getFullName()}
						</div>
						<div class=" row col-xs-12">
							<label class="form-control-label form-control-sm">First Name :</label>
							${order.getFirstName()}
						</div>
						<div class=" row col-xs-12">
							<label class="form-control-label form-control-sm">Last Name :</label>
							${order.getLastName()}
						</div>
						<div class=" row col-xs-12">
							<label class="form-control-label form-control-sm">Phone :</label>
							${order.getPhone()}
						</div>
						<div class=" row col-xs-12">
							<label class="form-control-label form-control-sm">Email :</label>
							${order.getEmail()}
						</div>
					</div>						
					<div class="card-footer">
					</div>
				</div>
			</div>
	  	
		  	<!-- Tab page for Shipping Details -->
			<div role="tabpanel" class="tab-pane fade in" id="shippingDetails">
				<div class="card">
					<div class="card-header">
						<h3 class="text-xs-center">Shipping Details</h3>
					</div>
					<div class="card-block">
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">Shipping Method :</label>
							${order.getShippingMethod()}
						</div>
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">Ship To Name :</label>
							${order.getShipToName()}
						</div>
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">Ship Date :</label>
							${order.getShipDate()}
						</div>
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">Street 1 :</label>
							${order.getShipStreet1()}
						</div>
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">Street 2 :</label>
							${order.getShipStreet2()}
						</div>
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">City :</label>
							${order.getShipCity()}
						</div>
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">State :</label>
							${order.getShipState()}
						</div>
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">Zip :</label>
							${order.getShipZipCode()}
						</div>
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">Country :</label>
							${order.getShipCountry()}
						</div>
					</div>
				</div>
			</div>
		</div>
  	</div>
	<!-- Footer -->
	<c:import url="footer.jsp" />
</body>
</html>
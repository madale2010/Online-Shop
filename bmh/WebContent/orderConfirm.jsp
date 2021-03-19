<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/header.jsp" />
<body>
<c:import url="fragments/googleTags.jsp" />
<jsp:include page="fragments/main_menu.jsp" />
<jsp:include page="/ProcessCheckoutRequest" />
<br>
	<div class="container-fluid">
		<div class="card card-default col-xs-push-2">
			<div class="panel-heading text-xs-center">
				<h3>Order Details</h3>
			</div>
			<div class="card-block">
				<div class="row"> 
					<div class="col-xs-push-1 col-xs-8">
						Thank you for your payment. Your transaction has been completed, and a receipt 
						for your purchase has been emailed to you. You may log into your account at 
						www.paypal.com to view details of this transaction.
					</div>
				</div>
				<h4>General Info</h4>
				<div class="row">
					<div class="col-xs-2">
						<div>Name:</div>
					</div>
					<div>${customer.firstName} ${customer.middleName} ${customer.lastName}</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<div>Email:</div>
					</div>
					<div>${customer.email}</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<div>Order Id:</div>
					</div>
					<div>${customer.saleId}</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<div>Order Date:</div>
					</div>
					<div>${customer.orderDate}</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<div>Sales Tax:</div>
					</div>
					<div>${customer.getSale().getAmount().getDetails().getTax()}</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<div>Fee :</div>
					</div>
					<div>${customer.getSale().getTransactionFee().getValue()}</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<div>Sub Total:</div>
					</div>
					<div>${customer.getSale().getAmount().getDetails().getSubtotal()}</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<div>Order Total:</div>
					</div>
					<div>${customer.getSale().getAmount().getTotal()}</div>
				</div><br>
				<h4>Shipping Info</h4>
				<div class="row">
					<div class="col-xs-2">
						<div>Shipping To:</div>
					</div>
					<div>${customer.shipToStreet}</div>
				</div>
				<div class="row">
					<div class="col-xs-2"></div>
					<div>${customer.shipToCity} ${customer.shipToState}, ${customer.shipToZip}</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<div>Shipping Option:</div>
					</div>
					<div>${customer.shippingOption}</div>
				</div><br>
				<h4>Item Details</h4>
				<c:forEach items="${customer.getCart().getCartItems()}" var="cartItem" >
				<div class="row">
					<div class="col-xs-2">
						<img style="size:75px; width:75px;" src="${cartItem.imageURL}"/>
					</div>
					<div class="col-xs-3" >${cartItem.name}</div>
					<div class="col-xs-2" >${cartItem.quantity}</div>
					<div class="col-xs-2" >${cartItem.price}</div>
				</div>
				</c:forEach>
			</div>
			<div class="card-footer text-xs-center">Thank you for shopping at Blue Mountain Holly.</div>
		</div>
	</div>
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>
	<jsp:include page="main_menu.jsp" />
	<br>
	<div class="container-fluid container-custom">
	<form class="form form-inline form-custom" role="form" action="UpdateCoupon" method="post" enctype="multipart/form-data">
	<div class="row text-xs-center">
					<c:choose>
						<c:when test="${not empty coupon.id}">
							<button class="btn-success fa fa-check-square-o" name="action" value="update"> Update</button>
						</c:when>
						<c:otherwise>
							<button class="btn-success fa fa-check-square-o" name="action" value="insert">Insert</button>
						</c:otherwise>
					</c:choose>
					<button class="btn-danger fa fa-ban" name="action" value="cancel" onclick="goBack()"> Cancel</button>
		</div>
		<div class="card">
		<div class="card-header">
			<h5 class="text-xs-center">Insert/Update New Product</h5>
		</div>
		
	        <div class="table-responsive">
  				<table class="table table-condensed table-borderless">
					<tr>
						<td><label data-toggle="tooltip" title="Coupon Id" for="inputsm">ID</label></td>
						<td><input class="form-control form-control-sm"  name="ID" class="form-control input-sm disabled" id="ID" readonly required data-validation-required-message="Please enter PID" type="text" value="${coupon.id}"></td>
						<td><label for="inputsm">Name</label></td>
						<td><input class="form-control form-control-sm" name="name" class="form-control input-sm" id="name" required data-validation-required-message="Please enter product name." type="text" value="${coupon.name}"></td>
					</tr>
					<tr>
						
						<td><label for="inputsm">Active Date</label></td>
						<td><input class="form-control form-control-sm"  name="activeDate" class="form-control input-sm" id="activeDate" type="text"  value="${coupon.activeDate}"></td>
						<td><label for="inputsm">Description</label></td>
						<td><input class="form-control form-control-sm"  name="couponDesc" class="form-control input-sm" id="couponDesc" type="text" value="${coupon.couponDesc}"></td>
					</tr>
					<tr>
						<td><label for="inputsm"> Expires Date</label></td>
						<td><input class="form-control form-control-sm" name="expiresDate" class="form-control input-sm" id="expiresDate" type="text" value="${coupon.expiresDate}"></td>
						<td><label for="inputsm">Parameters</label></td>
						<td><input class="form-control form-control-sm" name="parmData" class="form-control input-sm" id="parmData" type="text" value="${coupon.parmData}"></td>
					</tr>
					<tr>
						<td><label for="inputsm">LastUpdated</label></td>
						<td><input name="lastUpdated" class="form-control form-control-sm" id="lastUpdated" value="${coupon.lastUpdated}" readonly/></td>
						<td><label for="inputsm">Active</label></td>
						<c:choose>
							<c:when test="${coupon.active=='Y'}">
								<td><input class="form-control form-control-sm" name="active" class="form-control input-sm" id="active" type="checkbox" value="Y" checked></td>
							</c:when>
							<c:otherwise>
								<td><input class="form-control form-control-sm" name="active" class="form-control input-sm" id="active" type="checkbox" value="Y"></td>
							</c:otherwise>
						</c:choose>	
					</tr>
  				</table>
			</div>
		</div>
		</form>
	</div>
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>
<jsp:include page="/ShippingTool"></jsp:include>
<jsp:include page="main_menu.jsp" />
	<ul class="nav nav-tabs">
		<li class="nav-item"><a data-toggle="tab" class="nav-link" href="#calculator">Calculator</a></li>
		<li class="nav-item"><a data-toggle="tab" class="nav-link active" href="#productShipping">Product Shipping</a></li>
	</ul>
<div class="container-fluid">
	<div class="tab-content">
  		<div role="tabpanel" class="tab-pane fade" id="calculator">
			<div class="row">
				<div class="card card-default col-xs-6">
					<div class="card-header">
						<h4>Shipping Calculator</h4>
					</div>
					<form class="form-inline" role="form" action="../ShippingTool" method="GET">
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">Service :</label>
							<select class="form-control form-control-sm" name="service_select">
								<option value="First Class">First Class</option>
								<option value="First Class Commercial">First Class Commercial</option>
								<option value="First Class HFP Commercial">First Class HFP Commercial</option>
								<option value="Priority">Priority</option>
								<option value="Priority Commercial">Priority Commercial</option>
								<option value="Priority CPP">Priority CPP</option>
								<option value="Priority HFP Commercial">Priority HFP Commercial</option>
								<option value="Priority HFP CPP">Priority HFP CPP</option>
								<option value="Priority Mail Express">Priority Mail Express</option>
								<option value="Priority Mail Express SH">Priority Mail Express SH</option>
								<option value="Priority Mail Express HFP">Priority Mail Express HFP</option>
								<option value="Priority Mail Express Commercial">Priority Mail Express Commercial</option>
								<option value="Priority Mail Express CPP">Priority Mail Express CPP</option>
								<option value="Priority Mail Express SH Commercial">Priority Mail Express SH Commercial</option>
								<option value="Priority Mail Express HFP Commercial">Priority Mail Express HFP Commercial</option>
								<option value="Priority Mail Express HFP CPP">Priority Mail Express HFP CPP</option>
								<option value="Standard Post">Standard Post</option>
								<option value="Media Mail">Media Mail</option>
								<option value="Library Mail">Library Mail</option>
								<option value="All & Online">All &amp; Online</option>
							</select>
							</div>
							<div class="form-group row col-xs-12">	
							<label class="form-control-label form-control-sm">FirstClassMailType :</label>
							<select class="form-control form-control-sm" name="mail_type_select">
								<option value="LETTER">LETTER</option>
								<option value="FLAT">FLAT</option>
								<option value="PARCEL">PARCEL</option>
								<option value="POSTCARD">POSTCARD</option>
								<option value="PACKAGE SERVICE">PACKAGE SERVICE</option>
							</select>
							</div>
							<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">Container :</label>
							<select class="form-control form-control-sm" name="container_select">
								<option value="Lg Md Sm Regular Flat Rate Env.">Lg Md Sm Regular Flat Rate Env.</option>
								<option value="Gift Card Flat Rate Env.">Gift Card Flat Rate Env.</option>
								<option value="Window Flat Rate Env.">Window Flat Rate Env.</option>
								<option value="Sm Flat Rate Env.">Sm Flat Rate Env.</option>
								<option value="Regional Rate Box A">Regional Rate Box A</option>
								<option value="Regional Rate Box B">Regional Rate Box B</option>
								<option value="Regional Rate Box C">Regional Rate Box C</option>
								<option value="Flat Rate Env.">Flat Rate Env.</option>
								<option value="Legal Flat Rate Env.">Legal Flat Rate Env.</option>
								<option value="Padded Flat Rate Env.">Padded Flat Rate Env.</option>
								<option value="Flat Rate Box">Flat Rate Box</option>
								<option value="Variable/Null">Variable/Null</option>
								<option value="Rectangular">Rectangular</option>
								<option value="Nonrectangular">Nonrectangular</option>
							</select>
						</div>
							<div class="form-group row col-xs-12" ><br></div>
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">Size :</label>
							<select class="form-control form-control-sm" name="size_select">
								<option value="REGULAR">Regular</option>
								<option value="LARGE">Large</option>
							</select>
							<label class="form-control-label form-control-sm">Pounds :</label><input type="text" size="3" class="form-control form-control-sm" name="Pounds" value="0" />
							<label class="form-control-label form-control-sm">Ounces :</label><input type="text" size="3" class="form-control form-control-sm" name="Ounces" value="3" />
						</div>
						<div class="form-group row col-xs-12" ><br></div>
						<br>
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">Length :</label><input type="text" size="3" class="form-control form-control-sm" name="Length" value="6" />
							<label class="form-control-label form-control-sm">Height :</label><input type="text" size="3" class="form-control form-control-sm" name="Height" value="1" />
							<label class="form-control-label form-control-sm">Width :</label><input type="text" size="3" class="form-control form-control-sm" name="Width" value="9" />
							<label class="form-control-label form-control-sm">Girth :</label><input type="text" size="3" class="form-control form-control-sm" name="Girth" value="" />
						</div>
						<div class="form-group row col-xs-12" ><br></div>
						<br>
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">Machinable  :</label>
							<select class="form-control form-control-sm" name="machine_select">
								<option value="TRUE">TRUE</option>
								<option value="FALSE">FALSE</option>
							</select>
							<label class="form-control-label form-control-sm">Package Id :</label>
							<input type="text" size="1" class="form-control form-control-sm" name="PackageId"  value="0" />
						</div>	
						<div class="form-group row col-xs-12" ><br></div>
						<div class="form-group row col-xs-12">
							<label class="form-control-label form-control-sm">ZipDestination :</label><input class="form-control form-control-sm" name="ZipDestination" value="61532" />
						</div>
						<div class="form-group row col-xs-12" ><br></div>
						<div class="form-group">
							<button class="btn btn-primary" name="action" value="check">Check</button>				
						</div>
					</form>
					<br>
					<div class="card card-default">
						<div class="card-header">
							<h4>Results</h4>
						</div>
						<div class="card-block">
							<c:choose>
							<c:when test="${not empty error}">
								<label>Error Message :${errorMessage} </label>
							</c:when>
							<c:otherwise>
								<label>Shipping Cost:</label><input class="form-control form-control-sm" name="amount" value="${amount}" />
							</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane fade in active" id="productShipping">
			<jsp:include page="productShippingListing.jsp"></jsp:include>
		</div>
	</div>
</div>
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
</body>
</html>
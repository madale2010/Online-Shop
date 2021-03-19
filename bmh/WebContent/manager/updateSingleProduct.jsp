<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>
	<jsp:include page="main_menu.jsp" />
	<br>
	<div class="container-fluid container-custom">
		<form id="fileUploadForm" 
			  method="post"
			  style="display: none" >
			<input id="fileUploader" type="file" name="fileUpload" />
		</form>
		<form class="form form-inline" 
		      role="form"
			  action="UpdateProduct" 
			  method="post" 
			  enctype="multipart/form-data">
			<div class="row text-xs-center">
				<c:choose>
					<c:when test="${not empty productRecord.name}">
						<button class="btn-success fa fa-check-square-o" name="action"
							value="update">Update</button>
					</c:when>
					<c:otherwise>
						<button class="btn-success fa fa-check-square-o" name="action"
							value="insert">Insert</button>
					</c:otherwise>
				</c:choose>
				<button class="btn-danger fa fa-ban" name="action" value="cancel"
					onclick="goBack()">Cancel</button>
			</div>
			<div class="card">
				<div class="card-header">
					<h5 class="text-xs-center">Insert/Update New Product</h5>
				</div>

				<div class="table-responsive">
					<table class="table table-condensed table-borderless">
						<tr>
							<td><label data-toggle="tooltip" title="Product ID BMH_0000_PRODUCT" for="inputsm">PID</label></td>
							<td><input class="form-control form-control-sm" size="7" name="PID" class="form-control input-sm disabled" id="PID"	readonly required data-validation-required-message="Please enter PID" type="text" value="${productRecord.pid}"></td>
							<td><label for="inputsm">Name</label></td>
							<td><input class="form-control form-control-sm"
								name="PRODUCT_NAME" class="form-control input-sm"
								id="PRODUCT_NAME" required
								data-validation-required-message="Please enter product name."
								type="text" value="${productRecord.name}"></td>
						</tr>
						<tr>
							<td><label for="inputsm">Category</label></td>
							<td>
								<div class="form-group">
									<select class="form-control-sm" id="category" name="category">
										<c:forEach items="${category}" var="categoryName">
											<c:choose>
												<c:when test="${productRecord.category==categoryName}">
													<option selected value="${categoryName}">${categoryName}</option>
												</c:when>
												<c:otherwise>
													<option value="${categoryName}">${categoryName}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</td>
							<td><label for="inputsm">SubCategory</label></td>
							<td>
								<div class="form-group">
									<select class="form-control-sm" id="subCategories"
										name="subCategories">
										<c:forEach var="submenu" items="${subCategory}">
											<c:choose>
												<c:when test="${productRecord.subCategory==submenu}">
													<option selected value="${submenu}">${submenu}</option>
												</c:when>
												<c:otherwise>
													<option value="${submenu}">${submenu}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td><label for="inputsm">Price</label></td>
							<td><input class="form-control form-control-sm" size="7"
								name="PRICE" class="form-control input-sm" id="PRICE"
								type="text" required
								data-validation-required-message="Please enter price."
								value="${productRecord.price}"></td>
							<td><label for="inputsm">Quantity</label></td>
							<td><input class="form-control form-control-sm" size="3"
								name="QUANTITY" class="form-control input-sm" id="QUANTITY"
								type="text" required
								data-validation-required-message="Please enter quantity."
								value="${productRecord.quantity}"></td>
						</tr>
						<tr>
							<td><label for="inputsm"> Tags</label></td>
							<td><input class="form-control form-control-sm" name="TAGS"
								class="form-control input-sm" id="TAGS" type="text"
								value="${productRecord.tags}"></td>
							<td><label for="inputsm">Show Listing</label></td>

							<c:choose>
								<c:when test="${productRecord.showItem=='Y'}">
									<td><input class="form-control form-control-sm"
										name="SHOW" class="form-control input-sm" id="SHOW"
										type="checkbox" value="Y" checked></td>
								</c:when>
								<c:otherwise>
									<td><input class="form-control form-control-sm"
										name="SHOW" class="form-control input-sm" id="SHOW"
										type="checkbox" value="Y"></td>
								</c:otherwise>
							</c:choose>
						</tr>
						</table>
						<div class="input-group">
						<label class="input-group-addon" for="inputsm">Description</label>
						<textarea id="description" name="DESCRIPTION">${productRecord.description}</textarea>
						</div>
						<table>
						<tr>
							<td><label for="inputsm">Short Desc</label></td>
							<td><textarea name="SHORT_DESCRIPTION"
									class="form-control-sm" rows="7" cols="35" id="shortDesc">${productRecord.shortDescription}</textarea></td>
						</tr>
						<tr>
							<td><label for="inputsm">Featured</label></td>
							<c:choose>
								<c:when test="${productRecord.featured=='Y'}">
									<td><input class="form-control form-control-sm"
										name="FEATURED" class="form-control input-sm" id="FEATURED"
										type="checkbox" value="Y" checked></td>
								</c:when>
								<c:otherwise>
									<td><input class="form-control form-control-sm"
										name="FEATURED" class="form-control input-sm" id="FEATURED"
										type="checkbox" value="Y"></td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td><label for="inputsm">Upload Primary Image</label></td>
							<td><input class="form-control form-control-sm" size="15"
								name="PRIMARY_IMAGE" id="PRIMARY_IMAGE" type="file"></td>
							<td><label for="inputsm">Upload Additional (MAX 15)</label></td>
							<td><input class="form-control form-control-sm" size="15"
								type="file" id="otherImages" name="otherImages"
								multiple="multiple" /></td>
						</tr>
					</table>
					<div class="card">
						<div class="card-header">Shipping Details</div>
						<div class="card-block">
							<div class="row">
								<h5>Dimensions</h5>
							</div>
							<label for="inputsm">Length</label> <input
								class="form-control form-control-sm"
								value="${productRecord.shipDetails.length}" size="5"
								name="SHIP_LENGTH" id="length" type="text"> <label
								for="inputsm">Height</label> <input
								class="form-control form-control-sm"
								value="${productRecord.shipDetails.height}" size="5"
								name="SHIP_HEIGHT" id="height" type="text"> <label
								for="inputsm">Width</label> <input
								class="form-control form-control-sm"
								value="${productRecord.shipDetails.width}" size="5"
								name="SHIP_WIDTH" id="width" type="text">
							<div class="row">
								<h5>Weight</h5>
							</div>
							<label for="inputsm">Pounds</label> <input
								class="form-control form-control-sm"
								value="${productRecord.shipDetails.pounds}" size="5"
								name="SHIP_POUNDS" id="pounds" type="text"> <label
								for="inputsm">Ounces</label> <input
								class="form-control form-control-sm"
								value="${productRecord.shipDetails.ounces}" size="5"
								name="SHIP_OUNCES" id="ounces" type="text">
						</div>
					</div>
				</div>
				<ul class="nav nav-tabs">
					<li class="nav-item"><a data-toggle="tab"
						class="nav-link active" href="#primary">Primary Image</a></li>
					<li class="nav-item"><a data-toggle="tab" class="nav-link"
						href="#additional">Additional Images</a></li>
				</ul>
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane fade in active" id="primary">
						<div class="card">
							<div class="card-block">
								<c:choose>
									<c:when test="${not empty productRecord.imageURL}">
										<img class="img-responsive"
											style="height: 200px; width: 200px;"
											src="../${productRecord.imageURL}" alt="Thumbnail Image 1" />
									</c:when>
									<c:otherwise>
										<p>No Image</p>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane fade" id="additional">
						<div class="card">
							<div class="card-block">

								<c:choose>
									<c:when test="${not empty productRecord.imageList}">
										<c:forEach var="images" items="${productRecord.imageList}">
											<div class="list-inline-item">
												<img class="img-responsive"
													style="height: 200px; width: 200px;" src="../${images}"
													alt="Thumbnail Image 1" />
											</div>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<p>No Image</p>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/header.jsp" />
<jsp:include page="/RequestProducts">
	<jsp:param name="action" value="single" />
</jsp:include>

<body>
<c:import url="fragments/googleTags.jsp" />
	<script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.6&appId=171237326622610";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
	</script>
<c:set var="enlargeThis" value="" scope="page" />
	<jsp:include page="fragments/main_menu.jsp" />
	<div class="container-fluid">
		<hr>
		<h3 class="label-font">
			<c:out value="${product.name}" />
		</h3>
		<hr>
		<div class="col-xs-7">
			<div class="card">
				<div class="card-header">						
					<a href="" data-toggle="modal" data-target="#galleryViewer"> 
					<img src="${product.imageURL}" id="main" onclick="openOnImageClick(this.id)" alt=""	class="img-fluid" />
					</a><div class="pull-xs-right"><div class='fb-like' 
					     data-href='${currentUrl}' 
					    data-layout="button_count" data-share='true' data-action="like" data-size="small" data-show-faces="false" data-share="false"></div></div>
				</div>
				<div class="card-block">
				${product.description}
				</div>
			</div>
		</div>
		<div class="col-xs-5 pull-xs-right">
			<div class="card">
				<div class="card-header">
					<h4>Details</h4>
				</div>
				<div class="card-block">
					
					<div class="row">
						Price:${product.price}
					</div>
					<br>
					<div class="row">
						<label for="qty">Qty :</label>

						<select class="dropdown-toggle" name="selectedQuantity">
							<c:forEach var="i" begin="1" end="${product.quantity}">
								<option><c:out value="${i}" /></option>
							</c:forEach>
						</select>
					</div>
					<div class="row">
						<c:url var="cartURL" value="index.jsp">
							<c:param name="action" value="addItem" />
							<c:param name="productId" value="${product.pid}" />
						</c:url>
						<a href="${cartURL}" class="btn btn-primary btn-sm fa fa-cart-plus" role="button"> Add</a>
					</div>
					
				</div>
				<div class="card-header">
					<h4>Shipping Details</h4>
				</div>
				<div class="card-block">
					<form action="ShippingTool" role="form">
						<div class="form-group">
							<input type="text" class="form-control-sm" size="4" name="inputZip"	id="inputZip" placeholder="Enter Zip">								
							<input type="hidden" name="pid" value="${product.pid}" />
							<button type="submit" name="action" value="calculate" class="btn btn-sm fa fa-calculator" style="color:blue"></button><img src="https://shippo-static.s3.amazonaws.com/providers/75/USPS.png"/>
						</div>
						  
						
						<c:if test="${not empty rateList}">
							<c:forEach items="${rateList}" var="rate" >
								 <div class="form-group-sm">
									$${rate.amount} -  
									<label data-toggle="tooltip" 
										   title="${rate.durationTerms}" 
										   data-content="${rate.durationTerms}">${rate.serviceName}</label> 
		
								</div>
							</c:forEach>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="card col-xs-8">
			<div class="row">
				<c:forEach items="${product.imageList}" var="image">
					<div class="col-xs-3">
						<a href="" data-toggle="modal" data-target="#galleryViewer"> <img
							src="${image}" id="2" onclick="openOnImageClick(this.id)" alt=""
							class="img-fluid img-product" />
						</a>
					</div>
				</c:forEach>
			</div>
		</div>  
	</div>

	<jsp:include page="fragments/galleryViewer.jsp" />
	<!-- Footer -->
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>
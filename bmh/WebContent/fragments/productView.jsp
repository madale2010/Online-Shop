<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="container-fluid container-custom">
	<c:set var="start" value="${start}" />
	<c:set var="end" value="${end}" />
	<div class="card-deck-wrapper" style="max-width:100%;">
	<div class="card-deck" style="max-width:100%;">
		<c:forEach items="${productList}" var="product" varStatus="theCount" begin="${start}" end="${end}" >
				<div class="card text-xs-center" style="max-width:200px;">
					<c:url var="productURL" value="product.jsp">
						<c:param name="pid" value="${product.pid}" />
					</c:url>
					<a href="${productURL}"> 
						<img class="img-fluid m-x-auto d-block" style='max-width: 100%;max-height: 150px;' src="${product.imageURL}" id="thumb_${product.pid}" alt="${product.tags}" />
					</a>
					<div class="card-block">
						<p class="list-group-item list-group-item-info">${product.name}</p>
						<br>
						<p>
							<span class="col-xs-offset-1"> <b>Price:</b>
								${product.price}
							</span> <span class="col-xs-offset-1"> <b>Quantity:</b>
								${product.quantity}
							</span>
						</p>
						<c:choose>
							<c:when test="${product.quantity eq '0'}">
								<a href="" class="btn disabled sold-out center-block" role="button"> 
									<span class=""></span>Sold Out
								</a>
							</c:when>
							<c:otherwise>
								<c:url var="cartURL" value="index.jsp">
									<c:param name="action" value="addItem" />
									<c:param name="productId" value="${product.pid}" />
								</c:url>
								<a href="${cartURL}"  class="btn btn-primary btn-sm fa fa-cart-plus" role="button"> Add</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			<c:if test="${theCount.count%4==0 }">
			</div>
			<div class="card-deck" style="max-width:100%;">
			</c:if>
			
		</c:forEach>
		</div>
		</div>

</div>
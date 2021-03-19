<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="fragments/header.jsp" />
	<jsp:include page="/RequestProducts" />
	<body>
	<c:import url="fragments/googleTags.jsp" />
		<jsp:include page="fragments/main_menu.jsp" />
		<hr>
		<div class="container-fluid container-custom">
		
		<c:choose>
			<c:when test="${not empty param.subCategory}">
				<h2 class="text-xs-center">${param.category} - ${param.subCategory}</h2>
			</c:when>
			<c:when test="${not empty param.category}">
				<h2 class="text-xs-center">${param.category}</h2>
	
			</c:when>
			<c:otherwise>
				<h2 class="text-xs-center">PRODUCTS</h2>
			</c:otherwise>
		</c:choose>
		</div>
		<br> <br>
		<jsp:include page="fragments/productView.jsp" />
		<jsp:include page="fragments/pagination.jsp" />
		<!-- Footer -->
		<jsp:include page="fragments/footer.jsp" />
	</body>
</html>

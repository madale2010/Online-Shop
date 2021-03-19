<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/header.jsp" />
<body>
<c:import url="fragments/googleTags.jsp" />
<c:import url="/RequestProducts">
	<c:param name="featured" value="true" />
</c:import>
<!--  Start of menu bar -->
	<jsp:include page="fragments/main_menu.jsp" />
	<!--  End of menu bar -->
<br>

<!-- Carousel insert -->
	<jsp:include page="fragments/carousel.jsp" />
	<div  class="container-fluid container-custom-c text-xs-center">
	<h1 style="display:none;">Blue Mountain Holly - Home Decor &amp; Paper Products</h1>
		<div class="row" >
		<div class="col-xs-3"></div>
			<div class="col-xs-2">Custom Orders</div>
			<div class="col-xs-2">Handmade Items</div>
			<div class="col-xs-2">Exceptional</div>
		</div>
		<div class="row">
		<div class="col-xs-3"></div>
			<div class="col-xs-2">&amp;</div>
			<div class="col-xs-2">&amp;</div>
			<div class="col-xs-2">Customer</div>
		</div>
		<div class="row">
		<div class="col-xs-3"></div>
			<div class="col-xs-2">Personalization</div>
			<div class="col-xs-2">Crafts</div>
			<div class="col-xs-2">Service</div>
		</div>
		
	</div>
	<br>
	<div class="container-fluid container-custom">
	<div class="card">
		<div class="card-header custom">
			<h3 class="text-xs-center">Why Blue Mountain Holly?</h3>
		</div>
		<div class="card-block">
			Blue Mountain Holly was born from my love of designing and creating. 
			Every card I tape to the wooden signs I paint has thought in the colors, elements and textures. 
			I start with an idea, from a quirky question for a card to a color palette for a wooden box and let my intuition tell me where to go.&nbsp;
			<br />I am a one woman show, the Designer, Manufacturing and Shipping Departments with support from my amazing husband and family.&nbsp;
			I strive to provide outstanding customer service with a personal touch so please feel free to contact me with any questions. 
			For more information about who "I" am, head on over to 
			<a href="about.jsp">here</a>.
		</div>
		</div>
	</div>

	<div class="container-fluid">
		<hr>
		<h4 class="text-xs-center custom">Featured Products</h4>
		<hr>
	</div>
	<!-- Product insert -->
	<jsp:include page="fragments/productView.jsp" />
	<!-- Footer -->

	<jsp:include page="fragments/footer.jsp" />
</body>
</html>
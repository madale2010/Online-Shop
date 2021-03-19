<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
	<jsp:include page="main_menu.jsp" />
	<c:import url="/manager/ConfigSettings" />
<body>
<div class="container-fluid">
		<form class="form form-inline form-custom" role="form" action="ConfigSettings" id="ConfigSettings" name="ConfigSettings" method="post" enctype="multipart/form-data">
		<div class="row">
		<div class="card col-xs-4">
			<div class="card-header">
				<p>Logo Options</p>
			</div>
			<div class="card-body">
				<label class="form-control-label form-control-sm" for="inputsm">Current Logo</label>
				<img class="logo-properties" id="logoDisplay" name="logoDisplay" src="${logoImage}" alt=""/>
				<label class="form-control-label form-control-sm" for="inputsm">Change Logo</label>
				<input name="logoImage" id="logoImage" type="file">
			</div>
		</div>
		<div class="card col-xs-5">
			<div class="card-header">
				<p>Font and Category</p>
			</div>
			<div class="card-block">
				<div class="form-group-sm">
					<label class="form-control-label-sm" data-toggle="tooltip" title="Change the font on your web page" for="Carbontype">Font Type: </label> 
					<select	name="fontTypeSelect" id="fontTypeSelect" class="form-control form-control-sm">
						<option>Carbontype</option>
						<option>Underwood Champion</option>
						<option>Veteran Typewriter</option>
					</select>
				</div>
				<div role="separator" class="dropdown-divider"></div>
				<div class="form-group-md">
					<label class="form-control-label-sm form-control-label-top">Categories: </label>
						<br>
						<ul>
							<c:forEach items="${categoriesList}" var="categoryName" >
								<li class="list-group-item" id="${categoryName}" >${categoryName}<button id="remove_cat" value="${categoryName}" class="btn-link fa fa-minus pull-xs-right"></button></li>
							</c:forEach>
						</ul>
					<div class="form-group">					
						<button id="addCategoryBtn" class="btn-link fa fa-plus form-control-label-top"></button>
						<input class="form-control-label-top" name="addCategoryName"  type="text"  id="addCategoryName" /></div>
					</div>
					<div role="separator" class="dropdown-divider"></div>
				<div class="form-group-md"> 
					<label class="form-control-label-sm form-control-label-top">Sub Categories: </label>
						<br>
						<ul>
							<c:forEach var="submenu" items="${subCategoriesList}">
								<li  class="list-group-item" id="${submenu}" >${submenu}<button id="remove_sub" value="${submenu}" class="btn-link fa fa-minus pull-xs-right"></button></li>
							</c:forEach>
						</ul>
				</div>
				<div class="form-group">
					<button id="addSubCategoryBtn" class="btn-link fa fa-plus form-control-label-top"></button>
					<input class="form-control-label-top"  type="text" name="addSubCategoryName" id="addSubCategoryName" />
				</div>
			</div>
		</div>
		<div class="card col-xs-3">
			<div class="card-header">
				<p>Carousel Options</p>
			</div>
			<div class="card-body">
				<label data-toggle="tooltip" title="Select images for Carousel" for="inputsm">Carousel items</label>
				<input class="form-control form-control-sm" size="15" type="file" id="carouselImages" name="carouselImages"  multiple="multiple"/>

				 <div class="preview-area"></div>
				 <div class="">
				 	<p>Items on file</p>
				 	<c:if test="${not empty carouselList}">
					 	<c:forEach items="${carouselList}" var="image">
					 		<img alt="" class="thumb-sm" src="images/${image}"/>
					 	</c:forEach>
				 	</c:if>
				 </div>
			</div>
		</div>
		</div>
		<div class="text-xs-center">
			<button class="btn btn-sm btn-success" name="action" value="save">Save Changes</button>
			<button class="btn btn-sm btn-danger" type="button" onclick="goBack()">Cancel</button>
		</div>
		</form>
	</div>
	<br>	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	 $("#remove_cat").on( "click", function() {
		  
		  var input = $("<input>").attr("type", "hidden").attr("name", "remove_cat").val(makrup);
			$('#ConfigSettings').append($(input));
		 
	 });
	 $("#remove_sub").on( "click", function() {
		  
		  var input = $("<input>").attr("type", "hidden").attr("name", "remove_sub").val(makrup);
			$('#ConfigSettings').append($(input));
		 
	 });
	</script>
	</body>
</html>
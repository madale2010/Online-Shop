<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>
	<jsp:include page="main_menu.jsp" />
	<jsp:include page="/manager/UpdateProduct" />
	<div class="container-fluid">
	<form role="form" action="UpdateProduct" id="UpdateProduct" name="UpdateProduct" enctype="multipart/form-data" method="GET">
	<button name="action" value="add" data-toggle="tooltip" title="Add new product" class="btn-link btn-sm fa fa-plus" >Add Product</button>
	<div class="table-responsive">
			<table id="productTable" class="table table-striped" >
	        	   <thead>
		            	<tr>
		                <th>Id</th>
		                <th>Img</th>
		                <th>Feature</th>
		                <th>Listed</th>
		                <th>Name</th>
		                <th>Category</th>
		                <th>Sub Category</th>
		                <th>Price</th>
		                <th>Qty</th>
		                <th><i data-toggle="tooltip" title="Delete Product" class="pull-xs-right fa fa-trash"></i></th>

		                </tr>
	                </thead>
	                <tbody>	
	           		<c:forEach items="${productList}" var="tableData" varStatus="status">
	           		<tr>
	            		<td id="pid" class="col-xs">${tableData.pid}</td>
	            		<td id="image" class="col-xs"><img class="thumb-sm" src="${tableData.imageURL}"></td>
	            		<td id="featured" class="col-xs">
		            		<c:choose>
			            		<c:when test="${tableData.featured=='Y'}">
			            			<input type="checkbox" id="featuredVal" value="Y" checked/>
			            		</c:when>
			            		<c:otherwise>
			            			<input type="checkbox" id="featuredVal" value="N"/>
			            		</c:otherwise>
		            		</c:choose>
	            		</td>
	            		<td id="showItem" class="col-xs">
	            			
	            			<c:choose>
			            		<c:when test="${tableData.showItem=='Y'}">
			            			<input type="checkbox" id="showItemVal" value="${tableData.showItem}" checked/>
			            		</c:when>
			            		<c:otherwise>
			            			<input type="checkbox" id="showItemVal" value="${tableData.showItem}"/>
			            		</c:otherwise>
		            		</c:choose>
	            		</td>
	            		<td id="name" class="col-xs">${tableData.name}</td>
	            		<td id="category" class="col-xs">${tableData.category}</td>
	            		<td id="subCategory" class="col-xs">${tableData.subCategory}</td>
	            		
	            		<td id="price" >${tableData.price}</td>
	            		<td id="quantity">${tableData.quantity}</td>
	            		<td ><button name="delete" class="btn-link fa fa-times" onclick="return confirm('Are you sure you want to delete?');"  value="${tableData.pid}"></button></td>
	           		</tr>
	           		</c:forEach>
	        	</tbody>
	    	</table>
	    	</div>

	    	</form>
	</div>
<br>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
</body>
</html>
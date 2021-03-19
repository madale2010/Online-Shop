<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>
	<jsp:include page="main_menu.jsp" />
	<jsp:include page="/manager/UpdateBlog" />
	<br>
	<div class="container-fluid">
	
		<form role="form" action="UpdateBlog" id="UpdateBlog" name="UpdateBlog" enctype="multipart/form-data" method="POST">
		<c:url var="addURL" value="updateSingleBlog.jsp" /><a href="${addURL}" data-toggle="tooltip" title="Add new blog" class="btn-link btn-success fa fa-plus"></a>
			<div class="table-responsive">
				<table id="blogTable" class="table table-striped">
	        	   <thead>
		            	<tr>
			         	<th>Id</th>
			            <th>Category</th>
			            <th>Title</th>
			            <th>Date</th>
			            <th>Data</th>
			  			<th>Action</th>
		                <tr>
	                </thead>
	                <tbody>	
		           		<c:forEach items="${blogger}" var="blogData" varStatus="status">
			           		<tr>
			            		<td id="id">${blogData.id}</td>
			            		<td id="category">${blogData.category}</td>
			            		<td id="title">${blogData.title}</td>
			            		<td id="data">${blogData.date}</td>
			            		<td id="blog"><textarea class="form-control" id="textarea" name="textarea" readonly> ${blogData.blogHtml}</textarea></td>
			            					
								<td id="button"><button name="action" value="delete"  class="btn-link fa fa-times" onclick="return confirm('Are you sure you want to delete?');" ></button></td>
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
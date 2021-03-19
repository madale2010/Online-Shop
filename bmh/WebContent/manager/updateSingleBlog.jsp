<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>
	<jsp:include page="main_menu.jsp" />
	<div class="container-fluid">
		<h3 class="text-xs-center">Insert/Update a new blog </h3>
		<div class="card">
			<form style="display:none" id="fileUploadForm" method="post">
			    <input id="fileUploader" type="file" name="fileUpload"  />
			</form>
			<form class="form-inline" action="UpdateBlog" name="UpdateBlog" enctype="multipart/form-data" method="post">
				<input type="hidden" class="form-control" name="blogId"  value="${blog.id}" type="text" />
				<div class="row">
					<div class="col-xs-6">
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1">Blog Title</span>
							<input class="form-control" name="blogTitle"  value="${blog.title}" type="text" placeholder="Title" />
						</div>
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1">Category </span>
							<input class="form-control" name="blogCategory"  value="${blog.category}" type="text" placeholder="Category" />
						</div>
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1">Blog Date</span>
							<input class="form-control" name="blogDate"  value="${blog.date}" type="text" placeholder="Date" />
						</div>
						
					</div>
				<div class="pull-xs-right">
					<button class="btn-link fa fa-floppy-o fa-2x" data-toggle="tooltip" title="Save Blog"  type="submit" name="action" value="save" ></button>
					<button class="btn-link fa fa-ban fa-2x" type="button" onclick="goBack()"></button>
					<button class="btn-link fa fa-trash-o fa-2x" data-toggle="tooltip" title="Delete Blog" type="button" ></button>
				</div>
				</div>
				<br>			
				<textarea id="tinymce" name="tinymce">${blog.blogHtml}</textarea>
			</form>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
</body>
</html>
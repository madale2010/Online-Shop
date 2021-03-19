<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<body>
	<jsp:include page="main_menu.jsp" />
	<div class="container-fluid">
		<h3 class="text-xs-center">Insert/Update Document</h3>
		<div class="card">
			<form class="form-inline" action="UpdateDocument" name="UpdateDocument" enctype="multipart/form-data" method="post">
				<label class="control-label">Document Name</label> 
				<input class="form-control" name="docName"  type="text" placeholder="Title" value="${document.name}" />
				<label class="control-label">Document URL</label>
				<input class="form-control" name="docURL" type="text" placeholder="URL" value="${document.url}" />
				
				<div class="pull-xs-right">
					<c:choose>
						<c:when test="${not empty document.name}">
							<button class="btn-link fa fa-floppy-o fa-2x" data-toggle="tooltip" title="Save"  type="submit" name="action" value="update" ></button>
						</c:when>
						<c:otherwise>
							<button class="btn-link fa fa-floppy-o fa-2x" data-toggle="tooltip" title="Save"  type="submit" name="action" value="save" ></button>
						</c:otherwise>
					</c:choose>
					<button class="btn-link fa fa-ban fa-2x" type="button" onclick="goBack()"></button>
					<button class="btn-link fa fa-trash-o fa-2x" data-toggle="tooltip" title="Delete" type="button" ></button>
				</div>
				
				<textarea id="tinymce" name="tinymce">${document.data}</textarea>			
			</form>
		
		<form style="display:none" id="fileUploadForm" method="post">
		    <input id="fileUploader" type="file" name="fileUpload"  />
		</form>
		</div>
	</div>
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
</body>
</html>
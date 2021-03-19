<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="fragments/header.jsp" />
	<body>
	<c:import url="fragments/googleTags.jsp" />
	<!--  Start of menu bar -->
	<jsp:include page="fragments/main_menu.jsp" />
	<jsp:include page="/Blogger" />
		<br>
		<br>
		<div class="container-fluid">
		<h1 style="display:none;">Blue Mountain Holly - Blogs</h1>
		
		 	<!-- Content Row -->
	    	<div class="row">
		    	<div class="col-xs-7 push-xs-1">
		    		<!--  Here we must make this dynamic and change on the fly let java do the work -->
					<c:forEach items="${blogger}" var="blogObj">
						<!-- So we must be able to decide when we want a level in line blog and block format for pictures  -->
						<div class="row">
							
								<h3>
									<c:url var="blogURL" value="blogPost.jsp">
										<c:param name="action" value="single" />
										<c:param name="blogId" value="${blogObj.id}" />
									</c:url>
									<a class="label-font-ct blog-link" href="${blogURL}">${blogObj.title}</a>
								</h3>
								<p>by Holly Dale <small>${blogObj.date}</small></p>
								<p id="blogLimit">${blogObj.data}</p>
								<c:url var="blogURL" value="blogPost.jsp">
									<c:param name="action" value="single" />
									<c:param name="blogId" value="${blogObj.id}" />
								</c:url>
								<a class="btn btn-link pull-right" href="${blogURL}">Read More <i
									class="fa fa-angle-right"></i></a>
								
						</div>
						<hr>
					</c:forEach>
				</div>
			<!-- /.row -->
			 <!-- Blog Sidebar Widgets Column -->
	           <ul class="list-group col-xs-3 pull-xs-right">
	                <!-- Blog Search card -->
	                 <li class="list-group-item">
	                    <h4>Blog Search</h4>
	                    <div class="input-group input-group-sm">
	                        <input type="text" class="form-control" />
	                        <span class="input-group-btn">
	                            <button class="btn"><span class="fa fa-search"></span></button>
	                        </span>
	                    </div>
	                    <!-- /.input-group -->
	               </li>
	                <!-- Blog Categories card -->
	                 <li class="list-group-item">
	                    <h4 class="text-xs-center">Blog Categories</h4>
	                    <div class="row">
	                        <div class="col-xs-3 text-xs-center">
	                            <ul class="list-unstyled">
	                                <c:forEach items="${blogCategories}" var="category">
	            						<li>
	            					       <a href="#">${category}</a>
	                                	</li>
	            					</c:forEach>
	                            </ul>
	                        </div>
	                    </div>

	                    <!-- /.row -->
	                </li>

 </ul>
<!-- Blog List Page -->

			</div>
			<div class="row text-xs-center">
<nav aria-label="Page navigation">
  <ul class="pagination">
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
        <span class="sr-only">Previous</span>
      </a>
    </li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
        <span class="sr-only">Next</span>
      </a>
    </li>
  </ul>
</nav>
	    	<!-- /.row -->
			</div>
		</div>
		<!-- Footer -->
		<jsp:include page="fragments/footer.jsp" />
	</body>
</html>
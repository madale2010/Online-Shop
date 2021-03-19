<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="fragments/header.jsp" />
	<body>
	<c:import url="fragments/googleTags.jsp" />
	<!-- Facebook SDK load -->
	<div id="fb-root"></div>
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '171237326622610',
      xfbml      : true,
      version    : 'v2.6'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>
	<jsp:include page="/Blogger" />
		
		<!-- Navigation -->
	   	<jsp:include page="fragments/main_menu.jsp" />
		<br>
	    <!-- Page Content -->
	    <div class="container-fluid">
	    <h1 style="display:none;">Blog - ${blog.title}</h1>
	    
	        <!-- Content Row -->
	        <div class="row">
	            <!-- Blog Post Content Column -->
	            <div class="col-lg-8">
	                <!-- Blog Post -->
					<h4>${blog.title}</h4>
	                <hr>
	                <!-- Date/Time -->
	                <div>
						<i class="fa fa-clock-o"></i> Posted on ${blog.date}
						<div class='fb-like' 
					     data-href='${currentUrl}' 
					    data-layout="button_count" data-share='true' data-action="like" data-size="small" data-show-faces="false" data-share="false"></div></div>
	                <hr>
	                <!-- Preview Image -->
	                <c:if test="${not empty blog.image}">
	                	<img class="img-fluid"	src="${blog.image}" alt="">
	                </c:if>
	                <hr>
	                <!-- Post Content -->
	                <p class="lead"></p>
					${blog.blogHtml}
					
	                <hr>
					<div class='fb-comments' 
						 data-href='${currentUrl}' 
						 data-num-posts='5'
						 data-width='470'></div>
				</div>
	        </div>
	        <!-- /.row -->
	        <hr>
	    <!-- Footer -->
		<jsp:include page="fragments/footer.jsp" />
		<script src="https://apis.google.com/js/client:platform.js" async defer></script>
	    </div>
	    <!-- /.container -->
	</body>
</html>
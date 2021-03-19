<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:import url="/LoadMenu" />
<c:url var="indexURL" value="index.jsp" />
<c:url var="manageURL" value="manager/management.jsp" />
<c:url var="aboutURL" value="about.jsp" />
<c:url var="blogURL" value="blog.jsp" />
<c:url var="contactURL" value="contact.jsp" />
<c:url var="shopAllURL" value="shopAll.jsp" />
<c:url var="searchURL" value="search.jsp" />
<c:url var="cartURL" value="cart.jsp">
	<c:param name="cartItems" value="${cart}" />
</c:url>
<c:set value="${pageContext.request.requestURL}" var="currentPage" />
<c:set value="${fn:split(currentPage,'/')}" var="separatorPosition" />
<c:set value="${separatorPosition[fn:length(separatorPosition)-1]}" var="currentPage"/>
	<div class="container-fluid">
		<nav class="nav nav-tabs navbar-custom">	
			 <ul class="nav navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="${indexURL}">Home</a>		
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${aboutURL}">About <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${blogURL}">Blog</a>
				</li>
				<li class="nav-item dropdown-submenu">
					<a class="nav-link dropdown-toggle" 
					   href="shopAll.jsp" 
					   data-toggle="dropdown" 
					   aria-haspopup="true" 
					   aria-expanded="false">Shop All</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
						<c:forEach items="${mainMenu}" var="menuObject" >
							<c:url var="mainURL" value="shopAll.jsp" >
								<c:param name="category" value="${fn:escapeXml(menuObject.key)}"/> 
							</c:url>
							<li class="dropdown-submenu-right">
								<a class="dropdown-item dropdown-toggle"
									onClick="reply_click(this.href)" 
									tabindex="-1" 
									id="menu" 
									data-toggle="dropdown" 
									role="button" 
									href="${mainURL}" 
									aria-haspopup="true" 
									aria-expanded="false">${menuObject.key}</a>		
							 	<ul class="dropdown-menu">
									<c:forEach var="submenu" items="${menuObject.value}">
										<li class="dropdown-item">
											<c:url var="subURL" value="shopAll.jsp" >
												<c:param name="category" value="${fn:escapeXml(menuObject.key)}"/>
												<c:param name="subCategory" value="${fn:escapeXml(submenu)}"/>  
											</c:url>
											<a class="dropdown-item" 
											   onClick="reply_click(this.href)" 
											   data-toggle="dropdown" 
											   href="${subURL}">${submenu}</a>
										 </li>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</li>
				<li class="nav-item">
					<c:if test="${cartTotal>0}">
						<a class="nav-link" href="${cartURL}">
							<span class="fa fa-shopping-cart"></span> <sub>${cartTotal}</sub>
						</a>
					</c:if>
				</li>
				<li class="nav-item pull-xs-right">
					<ul class="nav navbar-nav">
						<li class="nav-item">
							<div class="input-group input-group-sm">
							<div class="row">
									<input id="search" name="search" type="text" size="7" class="form-control-sm" placeholder="Search">
									<button type="submit" onclick="search()" class="btn-link fa fa-search"></button>
							</div>
							</div>
						</li>

						<li class="nav-item">
							<c:choose>
							<c:when test="${authUser==true}">
								<a class="nav-link" href="${manageURL}">Manage</a>
							</c:when>
							<c:otherwise>
							<form name="Login">
								<button type="button" class="btn btn-custom btn-sm" data-toggle="modal"  data-target="#myModal">Login</button>				
							</form>
							</c:otherwise>
							</c:choose>

						</li>
					<li class="nav-item">			
						<a class="btn btn-custom btn-sm" href="${contactURL}"> Contact</a>
						</li>
					</ul>
				</li>
			</ul>
		</nav>
		<div align="center">
			<!-- This should really be pulled from file -->
			<img class="logo-properties" src="${logoImage}" alt=""/>
			<!-- On checkout we don't want sub menu  -->
			<c:if test="${currentPage ne 'cart.jsp' && currentPage ne 'orderReview.jsp' && currentPage ne 'orderConfirm.jsp'}">
				<nav class="nav nav-inline">
					<ul class="nav nav-tabs nav-tabs-center" >
						<c:forEach items="${mainMenu}" var="menuObject" >
								<c:url var="mainURL" value="shopAll.jsp" >
									<c:param name="category" value="${fn:escapeXml(menuObject.key)}"/> 
								</c:url>
								<li class="nav-item dropdown-submenu">
								<a class="nav-link dropdown-toggle"  
								   data-toggle="dropdown" 
								   role="button" 
								   href="${mainURL}" 
								   aria-haspopup="true" 
								   aria-expanded="false">${menuObject.key}</a>
									<ul class="dropdown-menu">
										<c:forEach var="submenu" items="${menuObject.value}">
											<li class="dropdown-item">
												<c:url var="subURL" value="shopAll.jsp" >
													<c:param name="category" value="${fn:escapeXml(menuObject.key)}"/>
													<c:param name="subCategory" value="${fn:escapeXml(submenu)}"/> 
												</c:url>
												<a class="dropdown-item" 
												   onClick="reply_click(this.href)" 
												   data-toggle="dropdown" 
												   role="button" href="${subURL}" 
												   aria-haspopup="true" 
												   aria-expanded="false">${submenu}</a>
											</li>
										</c:forEach>
									</ul>
								</li>
						</c:forEach>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>	
<!-- Login popup request here -->
<jsp:include page="login.jsp"/>
<script type="text/javascript">
function search(){
	var query = '?q='+$('#search').val();	     
	location.href="search.jsp"+query;
}
</script>
<!--  End of login -->
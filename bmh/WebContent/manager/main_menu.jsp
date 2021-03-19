<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="debugUrl" value="../fragments/debug.jsp"/>
<c:url var="updateSingleBlogUrl" value="updateSingleBlog.jsp"/>
<c:url var="updateBlogUrl" value="updateBlog.jsp"/>
<c:url var="salesUrl" value="sales.jsp"/>
<c:url var="ordersUrl" value="listOrders.jsp"/>
<c:url var="googleStatsUrl" value="googleStats.jsp"/>
<c:url var="historyUrl" value="history.jsp"/>
<c:url var="reportPageUrl" value="reportPage.jsp"/>
<c:url var="pagePropertiesUrl" value="pageProperties.jsp"/>
<c:url var="updateProductsUrl" value="updateProducts.jsp"/>
<c:url var="shippingProfilesUrl"  value="shippingProfiles.jsp"/>
<c:url var="shippingToolUrl"  value="shippingTool.jsp"/>
<c:url var="documentsEditorUrl"  value="listDocuments.jsp"/>
<c:url var="couponsUrl"  value="listCoupons.jsp"/>

	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<!-- Collect the nav links, forms, and other content for toggling -->
		<nav>	
			<ul class="nav nav-tabs navbar-custom">
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="management.jsp"/>">HOME</a>		
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="../index.jsp"/>">My Shop</a>
				</li>
				<li class="nav-item dropdown-submenu">
					<a class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" href="#" aria-haspopup="true" aria-expanded="false">Update Blog</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
						<li>
							<a class="dropdown-item" data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${updateSingleBlogUrl}" aria-haspopup="true" aria-expanded="false">Insert New</a>
						</li>
						<li>
							<a class="dropdown-item" data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${updateBlogUrl}" aria-haspopup="true" aria-expanded="false">View Blogs</a>
						</li>
					</ul>
				</li>
				<li class="nav-item dropdown-submenu">
					<a class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" href="#" aria-haspopup="true" aria-expanded="false">Reports</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">

						<li>
							<a class="dropdown-item" data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${salesUrl}" aria-haspopup="true" aria-expanded="false">Sales</a>
						</li>
						<li>
							<a class="dropdown-item" data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${ordersUrl}" aria-haspopup="true" aria-expanded="false">Orders</a>
						</li>
						<li>
							<a class="dropdown-item" data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${googleStatsUrl}" aria-haspopup="true" aria-expanded="false">Google Analytics</a>
						</li>
						<li>
							<a class="dropdown-item" data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${historyUrl}" aria-haspopup="true" aria-expanded="false">History (ETSY)</a>
						</li>
						<li>
							<a class="dropdown-item" data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${reportPageUrl}" aria-haspopup="true" aria-expanded="false">Generate Reports</a>
						</li>
					</ul>
				</li>
				<li class="nav-item dropdown-submenu">
					<a class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" href="#" aria-haspopup="true" aria-expanded="false">Manage Shop</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
						<li>
							<a class="dropdown-item"  data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="#" aria-haspopup="true" aria-expanded="false">Tax &amp; Finance</a>	
						</li>
						<li>
							<a class="dropdown-item"  data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${updateProductsUrl}" aria-haspopup="true" aria-expanded="false">Update Products</a>	
						</li>
						<li>
							<a class="dropdown-item"  data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${couponsUrl}" aria-haspopup="true" aria-expanded="false">Update Coupons</a>	
						</li>
						<li>
							<a class="dropdown-item"  data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${shippingProfilesUrl}" aria-haspopup="true" aria-expanded="false">Shipping Profiles</a>	
						</li>
						<li>
							<a class="dropdown-item"  data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${ordersUrl}" aria-haspopup="true" aria-expanded="false">Orders</a>	
						</li>
					</ul>
				</li>
				<li class="nav-item dropdown-submenu">
					<a class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" href="#" aria-haspopup="true" aria-expanded="false">Manage Website</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
						<li>
							<a class="dropdown-item"  data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${pagePropertiesUrl}" aria-haspopup="true" aria-expanded="false">Page Properties</a>
						</li>
						<li>
							<a class="dropdown-item"  data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${documentsEditorUrl}" aria-haspopup="true" aria-expanded="false">Documents Editor</a>	
						</li>
						<li>
							<a class="dropdown-item"  data-toggle="dropdown" onClick="reply_click(this.href)" role="button" href="${shippingToolUrl}" aria-haspopup="true" aria-expanded="false">Shipping Tools</a>	
						</li>
					</ul>
				</li>
				<li class="nav-item pull-xs-right">
					<form class="form-horizontal" role="form" action="LogOut" method="get" >
					 	<button class="dropdown-item" type="submit">Sign out</button>
					 </form>
				</li>
			</ul>
			</nav>
		</div>	
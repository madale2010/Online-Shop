<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="header.jsp" />
<c:import url="/manager/ManagementLoader"/>
<body>
	<jsp:include page="main_menu.jsp" />
	
	<!--  Include a summary of shops daily accounts -->
	<div class="container-fluid">
		<div class="row">
		
			<!--  Left Side for daily stats -->
			<div class="card card-default col-xs-9">
				<div class="card-header">
					<h5 class="text-xs-center" >Alerts</h5>
				</div>
				<div class="card-block">
					<form action="ManagementLoader" method="get">
						<table id="alertTable" class="table table-hover table-fixedheader" >						
				        	<thead>
					        	<tr>
						        	<th class="col-xs-2">Alert Type</th>
						        	<th class="col-xs-3">Alert Name</th>
						        	<th class="col-xs-4">Alert Message</th>
						        	<th class="col-xs-2">Alert Date</th>
						        	<th class="col-xs-1"></th>
					        	</tr>
				        	</thead>
				        	<tbody>				        	
				        		<c:forEach items="${alertsList}" var="record">
		        					<tr>	        					
		        						<td class="col-xs-2">${record.alertType}</td>
										<td class="col-xs-3">${record.alertName}</td>
										<td class="col-xs-4">${record.alertDescription}</td>
									 	<td class="col-xs-2">${record.alertDate}</td>
									 	<td class="col-xs-1">
									 		<input type="hidden" name="alertId_${record.alertId}" value="${record.alertId}" />
									 		<input type="hidden" name="alertType_${record.alertId}" value="${record.alertType}" />
									 		<input type="hidden" name="alertName_${record.alertId}" value="${record.alertName}" />
									 		<input type="hidden" name="alertDescription_${record.alertId}" value="${record.alertDescription}" />
									 		<input type="hidden" name="alertDate_${record.alertId}" value="${record.alertDate}" />
									 		<input type="hidden" name="alertLink_${record.alertId}" value="${record.alertLink}" />									 		
									 		<button class="btn-link fa fa-cogs" name="action" value="fix_${record.alertId}"></button>
									 	</td>								 
									</tr>
				        		</c:forEach>				        	
				        	</tbody>
				        </table>
				     </form>				
				</div>
				
				<div class="card-header ">
					<h4 class="text-xs-center" >Orders</h4>
				</div>
				<div class="card-block">					
					<p><label>Order Id: </label> ${orderId}</p>			
					<p><label>Order Date: </label> ${orderDate}</p>				
					<p><label>Order Ship Status: </label> ${orderShipStatus}</p>			
					<p><label>Order Ship Date: </label> ${orderShipDate}</p>					
				</div>
		
			</div>
					<!--  Right Side for stats -->
		<div class="col-xs-3 pull-xs-right">
			<div class="card card-default">
				<div class="card-header ">
					<h4 class="text-xs-center" >Daily Snapshot</h4>
				</div>
				<div class="card-block">					
					<p><label>Sales: </label> ${totalDailySales}</p>				
					<p><label>Hit Counter: </label>${hitCounter}</p>				
					<p><label>Comments: </label> ${totalDailyComments}</p>			
					<p><label>Email: </label> ${emailCount}</p>					
				</div>
			</div>
			<div class="card card-default">
				<div class="card-header ">
					<h4 class="text-xs-center" >Monthly Snapshot</h4>
				</div>
				<div class="card-block">				
					<p><label>Sales: </label> ${totalMonthlySales}</p>					
					<p><label>Hit Counter: </label> ${monthlyHitCounter}</p>					
					<p><label>Comments: </label> ${totalMonthlyComments}</p>
					<p><label>Total Blogs: </label> ${totalBlogs}</p>						
				</div>
			</div>
		</div>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<form action="../ShippingTool" method="post" >
	<table class="table-sm table-fixedheader">  <!-- class .table -->
		<thead>	
			<tr>
				<th>Id</th>
				<th class="col-xs-2">Thumbnail</th>
				<th class="col-xs-1">Pounds</th>
				<th class="col-xs-1">Ounces</th>
				<th class="col-xs-1">Length</th>
				<th class="col-xs-1">Width</th>
				<th class="col-xs-1">Height</th>
				<th class="col-xs-2">Profile Name</th>
				<th><button type="submit" name="action" value="update" class="btn btn-success btn-sm">Update All</button></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${productList}" var="product" varStatus="status">
	    	<c:set var="shipDetail" value="${product.shipDetails}" />
	    	<tr>
				<td>
					${product.pid}
					<input type="hidden" value="${product.pid}" name="pid"/>
				</td>
				<td class="col-xs-2"><img src="${product.imageURL}" title="${product.name}" data-content="${product.name}" class="img-custom-thumb" /></td>
				<td class="col-xs-1"><input class="form-control form-control-sm" size="2" name="${product.pid}_pounds" value="${shipDetail.pounds}" /></td>
				<td class="col-xs-1"><input class="form-control form-control-sm" size="2" name="${product.pid}_ounces" value="${shipDetail.ounces}" /></td>
				<td class="col-xs-1"><input class="form-control form-control-sm" size="2" name="${product.pid}_length" value="${shipDetail.length}" /></td>
				<td class="col-xs-1"><input class="form-control form-control-sm" size="2" name="${product.pid}_width" value="${shipDetail.width}" /></td>
				<td class="col-xs-1"><input class="form-control form-control-sm" size="2" name="${product.pid}_height" value="${shipDetail.height}" /></td>
				<td>
					<select class="form-control form-control-sm" name="${product.pid}_profile_select">
						<c:forEach items="${profileNameList}" var="profileName" varStatus="status">
							<c:choose>
								<c:when test="${profileName eq shipDetail.profileName}">
									<option value="${profileName}" selected>${profileName}</option>
								</c:when>
								<c:otherwise>
									<option value="${profileName}" >${profileName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
			</tr>
	    </c:forEach>
	  </tbody>
	</table>
</form>
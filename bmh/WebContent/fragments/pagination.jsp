<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${pagesNeeded>1}">
	<nav class="text-xs-center">
		<!-- Add class .pagination-lg for larger blocks or .pagination-sm for smaller blocks-->
		<ul class="pagination">
			<li class="page-item">
				<c:url var="loadP" value="">
					<c:param name="action" value="changePage" />
					<c:param name="page" value="P" />
				</c:url> 
				<a class="page-link" href="${loadP}" aria-label="Previous"> <span	aria-hidden="true">&laquo;</span></a>
			</li>
			<c:forEach var="pagesNeeded" varStatus="theCount" begin="1"	end="${pagesNeeded}">
				<c:choose>
					<c:when test="${theCount.count==1}">
						<li class="page-item active">
							<c:url var="loadMore" value="">
								<c:param name="action" value="changePage" />
								<c:param name="page" value="${theCount.count}" />
							</c:url> 
							<a class="page-link" href="${loadMore}">${theCount.count}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<c:url var="loadMore" value="">
								<c:param name="action" value="changePage" />
								<c:param name="page" value="${theCount.count}" />
							</c:url> 
							<a class="page-link" href="${loadMore}">${theCount.count}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li class="page-item">
				<c:url var="loadN" value="">
					<c:param name="action" value="changePage" />
					<c:param name="page" value="N" />
				</c:url> 
				<a class="page-link" href="${loadN}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
			</li>
		</ul>
	</nav>
</c:if>
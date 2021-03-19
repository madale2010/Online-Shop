<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div id="carousel1" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
					<c:forEach items="${carouselList}" var="item" varStatus="theCount">
						<c:choose>
							<c:when test="${theCount.count==1}">
								<li data-target="#carousel${theCount.count}" data-slide-to="${theCount.count+1}" class="active" ></li>
							</c:when>
							<c:otherwise>
								<li data-target="#carousel${theCount.count}" data-slide-to="${theCount.count+1}"></li>
							</c:otherwise>
						</c:choose>
						
						</c:forEach>
					</ol>
					<div class="carousel-inner" role="listbox">
						<c:forEach items="${carouselList}" var="item" varStatus="theCount">
							<c:choose>
								<c:when test="${theCount.count==1}">
									<div class="carousel-item active">
										<img class="img-responsive-custom center-block"	src="${item}" alt="">
									</div>
								</c:when>
								<c:otherwise>
									<div class="carousel-item">
										<img class="img-responsive-custom center-block" src="${item}" alt="">
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<a class="left carousel-control" href="#carousel1"  role="button" data-slide="prev"><span class="icon-prev"></span></a> 
					<a class="right carousel-control" href="#carousel1"  role="button" data-slide="next"><span class="icon-next"></span></a>
				</div>
			</div>
		</div>
	</div>
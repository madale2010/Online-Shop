<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:import url="fragments/header.jsp" />
<body>
<c:import url="fragments/googleTags.jsp" />
		<!--  Start of menu bar -->
		<jsp:include page="fragments/main_menu.jsp" />
		<div class="container-fluid">
		<h1 style="display:none;">Blue Mountain Holly - Policies</h1>
		
<jsp:include page="/DocumentHandler"/>
		</div>
		<jsp:include page="fragments/footer.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/header.jsp" />
<body>
<c:import url="fragments/googleTags.jsp" />
	<!--  Start of menu bar -->
	<jsp:include page="fragments/main_menu.jsp" />
	<br>
	<div class="container-fluid">
	<h1 style="display:none;">Blue Mountain Holly - About</h1>
	
		<jsp:include page="/DocumentHandler"/>
	</div>
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>
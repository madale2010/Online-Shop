<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/header.jsp" />
<body>
<c:import url="fragments/googleTags.jsp" />
<!--  Start of menu bar -->
	<jsp:include page="fragments/main_menu.jsp" />
	<!--  End of menu bar -->
<br>
<body>
	<div class="container-fluid">
	<script>
  (function() {
    var cx = '017678348563265824500:qjk7kptedg0';
    var gcse = document.createElement('script');
    gcse.type = 'text/javascript';
    gcse.async = true;
    gcse.src = 'https://cse.google.com/cse.js?cx=' + cx;
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(gcse, s);
  })();
</script>
<gcse:searchresults-only></gcse:searchresults-only>
</div>
</body>
</html>
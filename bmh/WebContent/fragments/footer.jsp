<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="eventsURL" value="events.jsp" />
<c:url var="faqURL" value="faq.jsp" />
<c:url var="policiesURL" value="policies.jsp" />
<c:url var="termsURL" value="termsOfUse.jsp" />
<c:url var="helpURL" value="help.jsp" />

<div class="container-fluid">	
	<div class="card custom-footer">
		<div class="row">
					<div class="text-xs-center col-xs-2">
						<ul class="list-unstyled">
							<li><b>Social</b></li>
							<li>
								<a href="https://www.facebook.com/bluemountainholly/"><i class="fa fa-facebook-square fa-2x"></i></a>
								<a href="https://www.instagram.com/bluemountainholly/"><i class="fa fa-linkedin-square fa-2x"></i></a>
								<a href="https://www.pinterest.com/bluemtnholly/"><i class="fa fa-pinterest-square fa-2x"></i></a>
								<a href="https://www.etsy.com/shop/BlueMountainHolly"><i class="mages/Etsy-icon.ico"></i></a>
							</li>
							<li><a href="${eventsURL}">Events</a></li>
						</ul>
					</div>
					<div class="text-xs-center col-xs-2">
						<ul class="list-unstyled">
							<li>
								<b>Information</b>
							</li>
							<li><a href="${helpURL}">Help</a></li>
							<li><a href="${faqURL}">FAQ's</a></li>
							<li><a href="${policiesURL}">Policies</a></li>
							<li><a href="${termsURL}">Terms of Use</a></li>
						</ul>
					</div>
					<div class="text-xs-center col-xs-5" style="margin-top: 100px;">
						<small><a class="madbox-link" href="http://www.madbox-solutions.com">Powered by Madbox Solutions.</a></small>
					</div>
					<div class="text-xs-center col-xs-3">
						<img class="circle" src="images/Badge Circle.png"/><br>
						<small><i class="fa fa-copyright" aria-hidden="true"></i>2014-2016 Blue Mountain Holly<br> Abingdon, VA</small>
					</div>
			
		</div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.2.0/js/tether.min.js" integrity="sha384-Plbmg8JY28KFelvJVai01l8WyZzrYWG825m+cZ0eDDS1f7d/js6ikvy1+X+guPIB" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.3/js/bootstrap.min.js" integrity="sha384-ux8v3A6CPtOTqOzMKiuo3d/DomGaaClxFYdCu2HPMBEkf6x2xiDyJ7gkXU0MWwaD" crossorigin="anonymous"></script>
<script type='text/javascript' src='utils/js/madbox.js'></script>



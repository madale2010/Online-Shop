<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<c:import url="fragments/header.jsp" />
	<body>
	<c:import url="fragments/googleTags.jsp" />
	<!--  Start of menu bar -->
	<jsp:include page="fragments/main_menu.jsp" />
	      <div class="container-fluid">
	      <h1 style="display:none;">Blue Mountain Holly - Contact</h1>
	 		<!-- Contact Form -->
	 		<br>
	        <div class="row">
		       <div class="col-xs-5 pull-xs-right">
				   <div class="card-header pull-xs-right">
	    				<h4>Contact Details</h4>
				        Abingdon, VA 24210<br>
				        <i class="fa fa-phone"></i><abbr title="Phone">P</abbr>: (276) 698-7483<br/>
	             		<i class="fa fa-envelope-o"></i><abbr title="Email">E</abbr>: holly@bluemountainholly.com<br/>			               
	              		<i class="fa fa-clock-o"></i><abbr title="Hours">H</abbr>: Mon - Fri: 9 AM to 5 PM<br/>
	                    <a href="https://www.facebook.com/adamdale123"><i class="fa fa-facebook-square"></i></a>	                  
	                    <a href="https://www.linkedin.com/in/madale2010"><i class="fa fa-linkedin-square"></i></a>	                 
	                    <a href="#"><i class="fa fa-twitter-square"></i></a>	                   
	                    <a href="#"><i class="fa fa-google-plus-square"></i></a>
	                    <br>
	                    <img src="images/adamnholly.jpg" alt="Picture"  width="50%" height="50%" />
		           </div>
		     </div>
		       	<div class="card card-block col-xs-6 col-xs-offset-1">	        
		       		<h4 class="card-title">Send us a Message</h4>
	                <form name="sentMessage"  action="EmailSend" method="post" id="contactForm" >
	                    <div class="control-group">
	                      
	                            <label class="form-control-label form-control-sm">Full Name:</label>
	                            <input type="text" class="form-control form-control-sm" name="name" id="name" required data-validation-required-message="Please enter your name.">
	                            
	                        
	                    </div>
	                    <div class="control-group">
	                      
	                            <label class="form-control-label form-control-sm">Phone Number:</label>
	                            <input type="tel" class="form-control form-control-sm" name="phone" id="phone" data-validation-required-message="Please enter your phone number.">
	             
	                    </div>
	                    <div class="control-group">
	                       
	                            <label class="form-control-label form-control-sm">Email Address:</label>
	                            <input type="email" class="form-control form-control-sm" name="email" id="email" required data-validation-required-message="Please enter your email address.">
	                       
	                    </div>
	                    <div class="control-group">
	                        
	                            <label class="form-control-label form-control-sm">Message:</label>
	                            <textarea rows="6" cols="100" class="form-control form-control-sm" name="message" id="message" required data-validation-required-message="Please enter your message"  maxlength="999" style="resize:none"></textarea>
	                      
	                    </div>
	                    <div id="success"></div>
	                    <!-- For success/fail messages -->
	                    <button type="submit" class="btn btn-primary btn-sm	">Send Message <i class="fa fa-envelope" aria-hidden="true"></i></button>
	                </form>
		            </div>
	       	 	</div>
	        <!-- /.row -->
			</div>
			<!-- end container -->
		<jsp:include page="fragments/footer.jsp" />
	</body>
</html>
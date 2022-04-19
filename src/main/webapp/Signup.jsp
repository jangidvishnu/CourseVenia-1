<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>CourseVenia</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<!-- this line will return whether user is successfully resgitered or not   -->
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">

	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>
					
						<form method="post" action="/CourseVeniaPro/Signup" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Your Name" required="required"/> <!-- it is for validation field not empty -->
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email" required="required" />
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="psw" id="pass" placeholder="Password" required="required"/>
							</div>
							<div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="password" name="psw" id="re_pass"
									placeholder="Repeat your password" required="required"/>
							</div>
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="text" name="mobile" id="contact"
									placeholder="Contact no" required="required"/>
							</div>
							<div class="form-group">
								<input type="checkbox" name="agree-term" id="agree-term"
									class="agree-term" /> <label for="agree-term"
									class="label-agree-term"><span><span></span></span>I
									agree all statements in <a href="#" class="term-service">Terms
										of service</a></label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div><p>${emailexist}</p>
							
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sing up image">
						</figure>
						<a href="login.jsp" class="signup-image-link">I am already
							member</a>
					</div>
				</div>
			</div>
		</section>


	</div>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<!--  this line for registartion done succefully we use it from sweetalert lib ! -->>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	
	<script type="text/javascript">
	
	var status = document.getElementById("status").value;
	if(status == "success"){
		<!--swal is used to pop-up the message instead of using alert  -->
		swal(" Congrats Registration done succefully","success")
	}
	if(status == "invalidName"){
		<!--swal is used to pop-up the message instead of using alert  -->
		swal(" Please Entre Name","error")
	}
	if(status == "invalidEmail"){
		<!--swal is used to pop-up the message instead of using alert  -->
		swal(" Please Entre Email","error")
	}
	if(status == "invalidUpwd"){
		<!--swal is used to pop-up the message instead of using alert  -->
		swal(" Please Entre Password","error")
	}
	if(status == "InvalidConfirmPassword"){
		<!--swal is used to pop-up the message instead of using alert  -->
		swal("Password is not Matched","error")
	}
	if(status == "invalidMobile"){
		<!--swal is used to pop-up the message instead of using alert  -->
		swal(" Please Entre Mobile Number","error")
	}
	if(status == "invalidMobileLength"){
		<!--swal is used to pop-up the message instead of using alert  -->
		swal(" Mobile Length Shoud be 10 digit","error")
	}
	</script>



</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
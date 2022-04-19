<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form  action="/CourseVeniaPro/Signup" Method="post">
<input type="text" placeholder="name" name="name"><!--  -->
<input type="text" placeholder="Email" name="email">
<input type="Password" placeholder="Password" name="psw" >
<input type="Password" placeholder="confirmPassword" name="psw" >
<input type="submit">
<p>
${emailexist}
</p>

</form>
</body>
</html>
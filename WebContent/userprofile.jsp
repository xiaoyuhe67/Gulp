
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="en_US"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Profile</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="mycss.css" />

</head>
<body style="background-color: #0ca3d2">
<nav class="navbar navbar-default" style="background-color: #01579b">
  <div class="container-fluid ">
    <div class="navbar-header" >
      <a class="navbar-brand " href="login.jsp" style="color: #fff; font-weight: bold;font-size:20px">Gulp</a>
    </div>
    <ul class="nav navbar-nav">
     <li><a href="userprofile.jsp" class="active" style="color: #fff; font-weight: bold;font-size:20px"><img src= ${ images}  width="20" height="20"></img> ${username}</a></li>
      <li ><a href="home.jsp" style="color: #fff; font-weight: bold;font-size:20px" >Home</a></li>
       <li><a href="Reviews.jsp" name="allpost" type="button" style="color: #fff; font-weight: bold;font-size:20px">Reviews</a></li>
      <li><a href="MyReviews.jsp" name="allpost" type="button" style="color: #fff; font-weight: bold;font-size:20px">My Reviews</a></li>
      <li ><a href="<%=request.getContextPath() %>/logout"  style="color: #fff; font-weight: bold;font-size:20px">Log Out</a></li>
    </ul>
  
  </div>
</nav>
<section class="container">
<div class="login">
<form action="userprofileServlet" method="get">
<h1>Update User Profile</h1>
<div>${result}</div>
<div align="center">User Name:           <input type ="text" name = "username" id="username" value ="${username}"></input>
</div>
<div align="center">User Password:       <input type="text" name ="userpassword" id="userpassword" value="${userpassword}"></input>
</div>
<div align="center">Zip Code:               <input type="text" name ="userzipcode" id="usermotto" value="${userzipcode}"></input>
</div>
<div align="center">User Email:          <input type="text" name ="useremail" id="useremail" value="${useremail}"></input>
</div>
<br>
<div align="center"><input type="submit" name="submit" id="submit" value="Update"></input></div>

</form>
</div>
</section>
</body>
</html>
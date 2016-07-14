<%@page import="DBUtil.Dataget"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%

	
%> 
<fmt:setLocale value="en_US"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="mycss.css" />
</head>
<body >

<script language="javascript" type="text/javascript">
function limitText(limitField, limitCount, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		limitCount.value = limitNum - limitField.value.length;
	}
}


</script>

<nav class="navbar navbar-default" style="background-color: #01579b">
  <div class="container-fluid ">
    <div class="navbar-header" >
      <a class="navbar-brand " href="login.jsp" style="color: #fff; font-weight: bold;font-size:20px">Gulp</a>
    </div>
    <ul class="nav navbar-nav">
     <li><a href="userprofile.jsp" style="color: #fff; font-weight: bold;font-size:20px"><img src= ${ images}  width="20" height="20"></img> ${username}</a></li>
      <li ><a href="home.jsp" style="color: #fff; font-weight: bold;font-size:20px" class="active">Home</a></li>
      <li><a href="Reviews.jsp" name="allpost" type="button" style="color: #fff; font-weight: bold;font-size:20px">Reviews</a></li>
      <li><a href="MyReviews.jsp" name="allpost" type="button" style="color: #fff; font-weight: bold;font-size:20px">My Reviews</a></li>
      <li ><a href="<%=request.getContextPath() %>/logout"  style="color: #fff; font-weight: bold;font-size:20px">Log Out</a></li>
    </ul>
  
  </div>
</nav>


<form action="homeServlet"   id="myform" name="myform" >
<div class="container">
<table align="center" border="0" class="table">
<thead>
<tr ><th>Search restaurant</th></tr>
</thead>
<tbody>

<tr style="backgroundcolor:#4db6ac ">

<td >Please enter restaurant:</td></tr>
<tr align="center"><td><input type="text" name="searchrestaurant" value=""/></td></tr>

<tr align="center"><td>
<input type="submit" name="method" value="Search" class="button"/>
</td>
</tr>

</tbody>
</table>
</div>



<div class="container">


<br /><br />

<div align="center" Style="background-color: #01579b ;color: #fff;border-bottom-width: 0;font-weight: bold;font-size:16px; height:34px">All Restaurant</div>
<table border="1" align="center" class="table responstable table-bordered table-hover">
<thead align="center">
<tr align="center">
<th align="center"><div align="center"></div></th>
<th align="center"><div align="center">Restaurant Name</div></th>
<th align="center"><div align="center">Address</div></th>
<th align="center"><div align="center">Description</div></th>
<th align="center"><div align="center">Ratings</div></th>
</tr>
</thead>
 <tbody>
 <c:forEach var="restaurant" items="${Restaurants}">
<tr>
	<td  style="width:5%" align="center" >
	<input type="checkbox" name="restaurantID" value="${restaurant.restaurantID}" 
    <c:if test="${param.restaurantID == restaurant.restaurantID and param.method != 'Save'}" > checked="checked"</c:if> 
    style="margin: 0 0 0 4px" onclick="radio(this)"></input>
    </td>
    
    <td align="center"> 
	<c:choose>
        <c:when test="${param.method == 'Edit' and param.restaurantID == restaurant.restaurantID}">
            <input type="text" name="restaurantName" style="padding: 0"
                value="<c:out value="${restaurant.restaurantName}"/>" />
        </c:when>
        <c:otherwise>
        
        <c:set var="myid" value="${restaurant.restaurantID}"/>
        <a href="homeServlet?restaurantid=<c:out value="${myid}"/>" name="linkrestaurantname" >${restaurant.restaurantName}</a></c:otherwise>
    </c:choose>
    <td align="center"> 
	<c:choose>
        <c:when test="${param.method == 'Edit' and param.restaurantID == restaurant.restaurantID}">
            <input type="text" name="restaurantAddress" style="padding: 0"
                value="<c:out value="${restaurant.restaurantAddress}"/>" />
        </c:when>
        <c:otherwise><c:out value="${restaurant.restaurantAddress}"/></c:otherwise>
    </c:choose>
 	</td> 
    <td align="center"> 
	<c:choose>
        <c:when test="${param.method == 'Edit' and param.restaurantID == restaurant.restaurantID}">
            <input type="text" name="restaurantDiscription" style="padding: 0"
                value="<c:out value="${restaurant.restaurantDiscription}"/>" />
        </c:when>
        <c:otherwise><c:out value="${restaurant.restaurantDiscription}"/></c:otherwise>
    </c:choose>
 	</td> 
	<td align="center">
   <c:set var="myname" value="${restaurant.restaurantName}"/>
   <c:set var="myavg" value="${AverageRatings[myname]}"/>
   <c:choose>
   <c:when test="${myavg==null }"> 
    <c:out value="0.0"/>  
    </c:when>
     <c:otherwise><c:out value="${myavg}"/> 
     </c:otherwise>
    </c:choose>
   </td> 
   
   
	 </tr> 
	 </c:forEach>
	  </tbody> 
 </table> 

 </div> 
 
</form>

</body>
</html>



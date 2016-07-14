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
<title>Restaurant</title>
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


<form action="restaurantServlet"   id="myform" name="myform" >
<div class="container">
<table align="center" border="0" class="table">
<thead>
<tr ><th>Search or Add review</th></tr>
</thead>
<tbody>

<tr style="backgroundcolor:#4db6ac ">

<td >Please enter Review, at most 200 characters:</td></tr>
<tr>
<td>
<textarea name="limitedtextarea" rows="5" cols="30" onKeyDown="limitText(this.form.limitedtextarea,this.form.countdown,200);" 
onKeyUp="limitText(this.form.limitedtextarea,this.form.countdown,200);">
</textarea>
</td>
</tr>
<tr>
<td><font size="2" >(Maximum characters: 200)<br>
You have <input readonly type="text" name="countdown" size="3" value="200" style="width: 50px; border:none" > characters left.</font><br>
</td>
</tr>
<tr>
<td >Please enter rate number:</td></tr>
<tr><td>
 <div class="rating" name="rate">     
        <input type="radio" name="stars" id="4_stars" value="5" >
        <label class="stars" for="4_stars">4 stars</label>
        <input type="radio" name="stars" id="3_stars" value="4" >
        <label class="stars" for="3_stars">3 stars</label>
        <input type="radio" name="stars" id="2_stars" value="3" >
        <label class="stars" for="2_stars">2 stars</label>
        <input type="radio" name="stars" id="1_stars" value="2" >
        <label class="stars" for="1_stars">1 star</label>
        <input type="radio" name="stars" id="0_stars" value="1" required>
        <label class="stars" for="0_stars">0 star</label>  
          
 </div>



</td>
</tr>
<tr align="center"><td>
<input type="submit" name="method" value="Add" class="button"/>
<input type="submit" name="method" value="Search" class="button"/>
</td>
</tr>

</tbody>
</table>
</div>



<div class="container">
<input type="submit" name="method" value="Update" class="button"/>
<input type="submit" name="method" value="Save" class="button"/>
<br /><br />
<table class="table">
<tr>
<td align="center" Style="background-color: #01579b ;color: #fff;border-bottom-width: 0;font-weight: bold;font-size:16px; height:34px">
<c:choose>
        <c:when test="${param.method == 'Update'}">
            <input type="text" name="restaurantName" style="padding: 0"
                value="<c:out value="${restaurantname}"/>" />
        </c:when>
        <c:otherwise> 
         <c:out value="${restaurantname}"/>
   </c:otherwise>
    </c:choose>

</td>
</tr>
<tr>
<td>
Restaurant Address:<c:choose>
        <c:when test="${param.method == 'Update'}">
            <input type="text" name="restaurantAddress" style="padding: 0"
                value="<c:out value="${restaurantaddress}"/>" />
        </c:when>
        <c:otherwise> 
         <c:out value="${restaurantaddress}"/>
   </c:otherwise>
    </c:choose>

</td>
</tr>
<tr>
<td>
Restaurant Description: <c:choose>
        <c:when test="${param.method == 'Update'}">
            <input type="text" name="restaurantDescription" style="padding: 0"
                value="<c:out value="${restaurantdescription}"/>" />
        </c:when>
        <c:otherwise> 
         <c:out value="${restaurantdescription}"/>
   </c:otherwise>
    </c:choose>
</td>
</tr>
<tr>
<td>
<c:set var="restname" value="${restaurantname}"/>
Average Rating Number: ${AverageRatings[restname]}
</td>
</tr>
<tr>
<td>
Number of Ratings: ${NumberofRatings[restname]}
</td>
</tr>





</table>
<div class="container">
<div align="center" Style="background-color: #01579b ;color: #fff;border-bottom-width: 0;font-weight: bold;font-size:16px; height:34px">${restaurantname}</div>
<table border="1" align="center" class="table responstable table-bordered table-hover">
<thead align="center">
<tr align="center">
<th align="center"><div align="center"></div></th>
<th align="center"><div align="center">Gravatar</div></th>
<th align="center"><div align="center">User</div></th>
<th align="center"><div align="center">Review</div></th>
<th align="center"><div align="center">Rate Number</div></th>
<th align="center"><div align="center">Rate Date</div></th>

</tr>
</thead>
 <tbody>
 <c:forEach var="review" items="${Reviewsofrestaurant}">
<tr>
	<td  style="width:5%" align="center" >
	<input type="checkbox" name="reviewID" value="${review.reviewID}" 
    <c:if test="${param.reviewID == review.reviewID and param.method != 'Save'}" > checked="checked"</c:if> 
    style="margin: 0 0 0 4px" onclick="radio(this)"></input>
    </td>
    
    <td align="center"> 
	 <c:set var="myParam" value="${review.guser.userEmail}"/>       
       <img src=${imageurlsofrestaurant[myParam] }  width="20" height="20"></img>
</td>
    
    <td align="center"> 
	<c:choose>
        <c:when test="${param.method == 'Edit' and param.reviewID == review.reviewID}">
            <input type="text" name="reviewuserName" style="padding: 0"
                value="<c:out value="${review.guser.userName}"/>" />
        </c:when>
        <c:otherwise> 
         <a href="restaurantServlet?userid=${review.guser.userID}">${review.guser.userName}</a>    
   </c:otherwise>
    </c:choose>
    <td align="center"> 
	<c:choose>
        <c:when test="${param.method == 'Edit' and param.reviewID == review.reviewID}">
            <input type="text" name="reviewtext" style="padding: 0"
                value="<c:out value="${review.reviewText}"/>" />
        </c:when>
        <c:otherwise><c:out value="${review.reviewText}"/></c:otherwise>
    </c:choose>
 	</td> 
    <td align="center"> 
	<c:choose>
        <c:when test="${param.method == 'Edit' and param.reviewID == review.reviewID}">
            <input type="text" name="ratenumber" style="padding: 0"
                value="<c:out value="${review.rateNumber}"/>" />
        </c:when>
        <c:otherwise><c:out value="${review.rateNumber}"/></c:otherwise>
    </c:choose>
 	</td> 
 	<td align="center"> 
	<c:choose>
        <c:when test="${param.method == 'Edit' and param.reviewID == review.reviewID}">
            <input type="text" name="reviewdate" style="padding: 0"
                value="<fmt:formatDate pattern="yy-MMM-dd" value="${review.reviewdate}" />" />
        </c:when>
        <c:otherwise><fmt:formatDate pattern="yy-MMM-dd" value="${review.reviewdate}" /></c:otherwise>
    </c:choose>
 	</td> 
	

	 </tr> 
	 </c:forEach>
	  </tbody> 
 </table> 

 </div> 
 </div>
</form>

</body>
</html>



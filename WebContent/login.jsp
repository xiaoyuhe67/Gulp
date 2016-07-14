<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
<jsp:include page="bootstrap.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="mycss.css" />
</head>
<body style="background-color: #0ca3d2">
<section class="container">
<div style="width: 500px; margin: 200px auto 0 auto;">
<div class="login">
<form action="loginServlet" method="get" >
<h1>Login to Web App</h1>
<div id="message"> ${loginerror} </div>

<div align="center">Email address:  <input type ="text" name = "email" id="email" value =""></input>
</div>
<div align="center">Password:       <input type="password" name ="password" id="password" value=""></input>
<input type ="hidden" name="secertvalue" id="secretvalue" value="loginForm"></input></div>
<br>
<div align="center"><input type="submit" name="login" id="submit" value="Log in" class="submit"></input>
<input type="submit" name="register" id="submit" value="Register"></input></div>
</form>
</div>
</div>
</section>

</body>
</html>
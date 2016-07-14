<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
<jsp:include page="bootstrap.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="mycss.css" />
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/simpsons.css" />
</head>
<body style="background-color: #0ca3d2">
<section class="container">
<div style="width: 500px; margin: 200px auto 0 auto;">
<div class="login">
<form action="loginServlet" method="get" >
<h1>Login to Web App</h1>
<div id="message"> ${loginerror} </div>

<div id="lisa">
        <div class="head">
	    
	    <div class="no-border body head-main"></div>
            <div class="no-border body head-main2"></div>
            <div class="no-border body head-main3"></div>
	    
	    <div class="no-border hair9"></div>
	    <div class="no-border hair10"></div>
	    
	    <div class="body hair hair1"></div>
	    <div class="body hair hair2"></div>
	    <div class="body hair hair3"></div>
	    <div class="body hair hair4"></div>
	    <div class="body hair hair5"></div>
	    <div class="body hair hair6"></div>
	    <div class="body hair hair7"></div>
	    <div class="body hair hair8"></div>
            
            <div class="body mouth-lip2"></div>
            <div class="body mouth-lip"></div>
            <div class="no-border body neck"></div>
            <div class="no-border body mouth"></div>
            <div class="no-border body neck2"></div>
            <div class="no-border body neck3"></div>
            <div class="no-border mouth-smile"></div>
	    
            <!-- The ear -->
            <div class="body ear">
                <div class="no-border inner1"></div>
                <div class="no-border inner2"></div>
                <div class="no-border body clip"></div>
            </div>
	    
            <div class="no-border eyelash1 eyelash"></div>
            <div class="no-border eyelash2 eyelash"></div>
            <div class="no-border eyelash3 eyelash"></div>
            <div class="no-border eyelash4 eyelash"></div>
            <div class="no-border eyelash5 eyelash"></div>
            <div class="no-border eyelash6 eyelash"></div>
            <div class="no-border eyelash7 eyelash"></div>
            <div class="no-border eyelash8 eyelash"></div>
            
            <!-- The right eye -->
            <div class="right-eye">
                <div class="no-border right-eye-pupil"></div>
		<div class="no-border body eyelid-top"></div>
		<div class="no-border body eyelid-bottom"></div>
            </div>
            
            <!-- The nose -->
            <div class="no-border body nose"></div>
            <div class="body nose-tip"></div>
            
            <!-- The left eye -->
            <div class="left-eye">
                <div class="no-border left-eye-pupil"></div>
		<div class="no-border body eyelid-top"></div>
		<div class="no-border body eyelid-bottom"></div>
            </div>
	    
	    <div class="necklace necklace1"></div>
	    <div class="necklace necklace2"></div>
	    <div class="necklace necklace3"></div>
	    <div class="necklace necklace5"></div>
	    <div class="necklace necklace4"></div>
        </div>
    </div>

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
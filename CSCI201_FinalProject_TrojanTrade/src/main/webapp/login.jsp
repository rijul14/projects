<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id" content="297602083103-shj3lj5hrru79sh7llvbvlrtnaskrkcm.apps.googleusercontent.com" > 
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<title>Trojan Trade</title>
	 <style>
    @font-face {
      font-family: AlfaSlabOne;
	    src: url("AlfaSlabOne-Regular.ttf");
    }

    body, html {
      text-align: center;
      font-family: "Lucida Console", Courier, monospace;
      font-size: 15px;
      color: #626262;
    }

   /* navigation */
    #nav {
      position: relative;
      width: auto;
      height: 59px;
      list-style-type: none;
      margin: 10px;
      padding: 10px;
      margin-bottom: 20px;
      font-family: AlfaSlabOne;
    }

    nav a {
      text-decoration: none;
    }

    div.saleat {
      position: absolute;
      left: 20%;
    }

    div.home{
      padding-top: 15px;
      position: absolute;
      right: 20%;
    }

    #home {
      padding-right:10px;
      color: #626262;
    }

    #login {
      color: #626262;
    }

    #banner {
      color: #B31818;
      font-size: 35px;
      position: relative;
      left: 1%;
    }
    /* navigation end */

    /* login & register */
    .container {
      width: 90%;
      margin-top: 50px;
      display: flex;
      padding-left: 16%;
      padding-right: 5%;
    }

    h4 {
      text-align: left;
      margin-left: 10%;
      font-family: AlfaSlabOne;
    }

    .item {
      width: 100%;
      padding: 10px;
    }
    
    .label {
      width: 80%;
      display: inline-block;
      box-sizing: border-box;
      text-align: left;
      padding-right: 20%;
    }
    
    .textbox {
      width: 80%;
      border: 1px solid #e2e2e2;
      border-radius: 3px;
      padding: 8px;
      margin: 10px;
      height: 50px;
    }

    #terms {
      width: 80%;
      margin-top: 10px;
      padding-left: 10%;
    }

    button {
      border: none;
      border-radius: 3px;
      padding: 10px;
      background-color: #B31818;
      color: white;
      width: 80%;
      height: 50px;
      padding: 8px;
      margin: 10px;
      font-size: 15px;
      font-family: AlfaSlabOne;
    }

    #forgot {
      background-color: #ffb22c;
      padding: 0px;
      padding: 8px;
    }
    
    .g-signin2{
	  width: 100%;
	}
	
	.g-signin2 > div{
	  margin: 0 auto;
	}

    button:hover {
      opacity: 0.8;
    }

	</style>
</head>
<body>
  <!-- navigation bar -->
<div id="nav">
    <nav>
      <div class = "saleat"> 	<a href="home.jsp" id="banner"><img src="usc_logo.png" width="50px"></a> </div>
      <div class = "home"> <a href="home.jsp" id="home">Home  </a>
      <a href="login.jsp" id="login"> Login / Register</a> </div>
    </nav>
  </div>
    <!-- login -->
    <div class="container">
      <form class="item" action="Login" method="POST">
        <h4> Login</h4>
        <label for="username" class="label"> Username </label>
        <input type="email" name="username" class="textbox" required>
        <label for="password" class="label">Password</label>
        <input type="password" name="password" class="textbox" required>
        <button type="submit" name="login"> <i class="fa fa-sign-in"></i> Sign in </button>
        <button id="forgot"> Yay, Trojans!  </button>
    	<p style="color:red" id="error"> ${error} </p>
    </form>
      <!-- register -->
      <form class="item" action="Register" method="POST">
        <h4>Register</h4>
        <label for="username" class="label">Username</label>
        <input type="email" name="username" class="textbox" required>
        <label for="name" class="label">Nickname</label>
        <input type="text" name="nickname" class="textbox" required>
        <label for="password" class="label">Password</label>
        <input type="password" name="password" class="textbox" minlength="8" id="pwd" required>
        <label for="onfirmedpassword" class="label">Confirm Password <span id="matching"></span></label>
        <input type="password" name="confirmedpassword" class="textbox" id="confirmed" required> 
        <label for="location" class="label">Location</label>
        <select name="location" id="location" class="textbox">
	        <option value="village">USC Village</option>
	        <option value="newnorth">New North</option>
	        <option value="lyoncenter">Lyon Center</option>
	        <option value="rtcc">Ronald Tutor Campus Center</option>
	        <option value="bookstore">USC Bookstore</option>
	        <option value="leavey">Leavey Library</option>
	        <option value="doheny">Doheny Library</option>
	        <option value="mccarthyquad">McCarthy Quad</option>
   	    </select>
        <div id="terms">
        <input type="checkbox" name="terms" required>
        <label for="terms">I have read and agree to all terms and conditions of Trojan Trade.</label></div>
        <p style="color:red"> ${register} </p>
        <button type="submit" id="reg-submit"> <i class="fa fa-user-plus"></i> Create Account</button>
    </form>
  </div>
  <script>
  document.getElementById("confirmed").onchange = function() {
		if(document.getElementById("pwd").value == document.getElementById("confirmed").value) {
			document.getElementById("matching").style.color = 'green';
			document.getElementById("matching").innerHTML = ' matching!';
			// document.getElementById('reg-submit').disabled = true;
			
		}
		else {
			document.getElementById("matching").style.color = 'red';
			document.getElementById("matching").innerHTML = ' not match!';
			// document.getElementById('reg-submit').disabled = false;
		}
	}
 	document.getElementById("pwd").onchange = function() {
		if(document.getElementById("pwd").value == document.getElementById("confirmed").value) {
			document.getElementById("matching").style.color = 'green';
			document.getElementById("matching").innerHTML = ' matching!';
			// document.getElementById('reg-submit').disabled = true;
			
		}
		else {
			document.getElementById("matching").style.color = 'red';
			document.getElementById("matching").innerHTML = ' not match!';
			// document.getElementById('reg-submit').disabled = false;
		}
	}
 	document.getElementById("forgot").onclick = function() {
		alert("FIGHT ON! ✌️");
 	}
 	
  </script>
</body>
</html>
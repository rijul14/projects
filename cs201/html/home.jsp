<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="CSCI201_FinalProject_TrojanTrade.Item"%>
<%@page import="CSCI201_FinalProject_TrojanTrade.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">	<title>Trojan Trade</title>
	 <style>
    @font-face {
      font-family: AlfaSlabOne;
	    src: url("AlfaSlabOne-Regular.ttf");
    }

    body, html {
      text-align: center;
      font-family: AlfaSlabOne;
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
    #home:hover {
      color: #000000;
    }

    #login {
      color: #626262;
    }

    #login:hover {
      color: #000000;
    }

    #banner {
      position: relative;
      left: 1%;
    }
    /* navigation end */

    /* banner start */
    #logo {
 	  padding: 80px;
      font-family: AlfaSlabOne;
      font-size: 80px;
      color: #B31818;
      text-shadow: 3px 3px #660000;
    }

    #image-div {
      padding: 0px;
      width: 100%;
      display: block;
      margin-left: auto;
      margin-right: auto;
      background-image: url("trojan2.jpg");
      background-size: cover;
      pardding: 50px;
    }
    /* banner end *


    /* search */
    #warning {
       font-size: 30px;
       font-style: italic;
       font-family: AlfaSlabOne;
       margin: 20px;
    }
    
    .search {
      width: 100%;
      height: 100px;
      text-align: center;
    }

    .item{
      width: 50%;
      height: 50px;
      margin: 5px;
      border: 1px solid #e2e2e2;
      border-radius: 3px;
      padding-left: 10px;
      
    }

    #submit {
      border: none;
      border-radius: 3px;
      background-color: #B31818;
      height: 60px;
      width: 100px;
      cursor: pointer;
      color: white;
    }

    .submit:hover {
      background-color: #990000;
    }

    #item-search {
      height: 60px;
    }

	#filter_btn {
		margin: 30px;
	}
	
    #filter_form {
      margin: 30px;
      margin-left: auto;
      margin-right: auto;

    }
    #filter_form .form-group {
      padding: 10px;
    }

    /* search end */
    
    /* category */
    .radio-toolbar {
      width: 80%;
	  margin: 0px;
	}
	
	.radio-toolbar input[type="radio"] {
	  opacity: 0;
	  position: fixed;
	  width: 0;
	}
	
	.radio-toolbar label {
	    display: inline-block;
	    background-color: #B31818;
	    padding: 10px;
	    color: white;
	    font-size: 16px;
	    border-radius: 4px;
	    margin: 10px;
	    width: 300px;
	    height: 80px;
	    cursor: pointer;
	}
	
	.radio-toolbar label:hover {
	  background-color: #FFC72C;
	}
	
	.radio-toolbar input[type="radio"]:checked + label {
	    background-color: #FFC72C;
	}

	</style>
</head>
<body>
  <!-- navigation bar -->
  <div id="nav">
    <nav>
      <div class="saleat"> 	<a href="home.jsp" id="banner"><img src="usc_logo.png" width="50px"></a> </div>
      <% User user = (User)session.getAttribute("loggedUser");
         String check = "";
         String name = "";
      	 if(user == null) { 
      		 check = "0";
      		 name = "";
         }
      	 else { 
      		 check = "1";
      		 name = user.getNickname();
         }
      	 %>
      <span id="logCheck" data-prodnumber="<%=check%>"></span>
      <div class="dropdown home" id="dropdown">
  		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
  		Welcome, <%=name%> <i class="fa fa-user"></i></button>
		  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		  	<a class="dropdown-item" href="home.jsp">Home</a>
		    <a class="dropdown-item" href="favorite.jsp">Favorite</a>
		    <a class="dropdown-item" href="history.jsp">History</a>
		    <a class="dropdown-item" href="setting.jsp">Setting</a>
		    <a class="dropdown-item" href="javascript: logout()">Log out</a>
		    <form method="POST" id="logout-form" action="Logout"></form>
		  </div>
	  </div>
	  <div class="home"> <a href="home.jsp" id="home"> Home  </a>
	  <a href="login.jsp" id="login"> Login / Register</a> </div>
    </nav>
  </div>
  
  <!-- search -->
  <form action="Main" method="GET">
  <div id="image-div">
  	<div id="logo"> WELCOME TO <br> TROJAN TRADE </div>
    <div class="search" id="search-form">
      <input type="text" class="item" id="item-search" name="item-search" value="" placeholder="Enter item you're looking for"> 
      <button id="submit"><i class="fa fa-search fa-2x"></i></button>
    </div>
    </div>
	
	<h3 id="warning"> ${warning} </h3>
    <button class="btn" type="button" id="filter_btn">
      <i class="fa fa-plus fa-3x"></i></button>

  <div id="filter_form" class="row justify-content-center">
    <div class="radio-toolbar">
	    <input type="radio" id="electronics" name="category" value="electronics">
	    <label for="electronics"><i class="fa fa-desktop"></i><br>Electronics</label>
	    <input type="radio" id="appliances" name="category" value="appliances">
	    <label for="appliances"><i class="fa fa-home"></i><br>Appliances</label>
	    <input type="radio" id="beautyHealth" name="category" value="beautyHealth">
	    <label for="beautyHealth"><i class="fa fa-bath"></i><br>Beauty & Health</label> 
	    <input type="radio" id="book" name="category" value="book">
	    <label for="book"><i class="fa fa-book"></i><br>Book</label> 
	    <input type="radio" id="clothing" name="category" value="clothing">
	    <label for="clothing"><i class="fa fa-female"></i><i class="fa fa-male"></i><br>Clothing</label> 
	    <input type="radio" id="furniture" name="category" value="furniture">
	    <label for="furniture"><i class="fa fa-hotel"></i><br>Furniture</label>
	    <input type="radio" id="vehicle" name="category" value="vehicle">
	    <label for="vehicle"><i class="fa fa-bicycle"></i><br>Vehicle</label>  
	    <input type="radio" id="other" name="category" value="other">
	    <label for="other"><i class="fa fa-gift"></i><br>Other</label> 
	    <input type="hidden" name="page" value="home">
	</div>
  </div>
  </form>
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
   <script type="text/javascript">
  function logout() {
	  	document.getElementById("logout-form").submit();
		alert("Successfully logged out!");
	}
  </script>
  <script>
  $(window).on('load', function() {
      $("#filter_form").css("display", "none");
      if ($("#logCheck").attr("data-prodnumber") == "0") {
    	  $("#login").css("display", "inline");
    	  $("#home").css("display", "inline");
    	  $("#dropdown").css("display", "none");
      }
      else if($("#logCheck").attr("data-prodnumber") == "1") {
    	  $("#login").css("display", "none");
    	  $("#home").css("display", "none");
    	  $("#dropdown").css("display", "inline");
      }
    });

    // slide down and up the input
    $("#filter_btn").click(function() {
      $("#filter_form").slideToggle("slow");
    });
  </script>
</body>
</html>
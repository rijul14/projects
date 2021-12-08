<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CSCI201_FinalProject_TrojanTrade.Item"%>
<%@page import="CSCI201_FinalProject_TrojanTrade.User"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
    /* banner end */
    
    /* post start*/
    #post {
    	width: 60%;
    	margin-left: auto;
    	margin-right: auto;
    }
    #description {
    	height: 100px;
    }
    input[type=text] {
		width: 100%;
		height: 50px;
		background-color: #f1f1f1;
		border: 2px blue;
		padding: 20px;
	}
	input[type=text]:focus {
	  width: 100%;
	}
	label{
	    display: block; /* add this */
	    padding-top: 5px;
	    padding-bottom: 10px;
	}
	textarea {
		width: 100%;
		background-color: #f1f1f1;
		padding: 20px;
	}
	select {
	  width: 100%;
	  height: 50px;
	  color: black;
	  border: none;
	  border-radius: 4px;
	  background-color: #f1f1f1;
	  padding-left: 30px;
	}
	input[type=submit] {
	  border: none;
      border-radius: 3px;
      background-color: #B31818;
      height: 60px;
      width: 100px;
      cursor: pointer;
      color: white;
      margin: 30px;
	}
	input[type=reset] {
	  border: none;
      border-radius: 3px;
      background-color: #B31818;
      height: 60px;
      width: 100px;
      cursor: pointer;
      color: white;
      margin: 30px;
	}
    /* post end */
    h2 {
    	color: #B31818;
    	font-size: 50px;
      	text-shadow: 1px 1px #660000;
    	padding: 30px;
    	margin-top: 30px;
    	margin-bottom: 30px;
    	background-image: url("trojan2.jpg");
      	background-size: cover;
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
         int id = 0;
      	 if(user == null) { 
      		 check = "0";
      		 name = "";
      		 id = 0;
         }
      	 else { 
      		 check = "1";
      		 name = user.getNickname();
      		 id = user.getId();
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
		    <a class="dropdown-item" href="Setting?index=<%=id%>">Setting</a>
		    <a class="dropdown-item" href="javascript: logout()">Log out</a>
		    <form method="POST" id="logout-form" action="Logout"></form>
		  </div>
	  </div>
	  <div class="home"> <a href="home.jsp" id="home"> Home  </a>
	  <a href="login.jsp" id="login"> Login / Register</a> </div>
    </nav>
  </div>
  
  <h2>New Post</h2>
  
  <form id="post" action="Post" method="POST">
  	
    <label for="title">Title your Listing.</label>
    <input type="text" name="title" id="title" required>
    
    <label for="description">Describe your Item:</label>
    <textarea rows="3" cols="30" name="description" id="description" required></textarea>
    
    <label for="price">Price of your Item:</label>
     <input type="text" name="price" id="price" required>
    
    <label for="status">Status:</label>
    <select name="status" id="status">
        <option value="selling">Selling</option>
        <option value="reserved">Reserved</option>
        <option value="sold">Sold</option>
    </select>
    
    <label for="category">Category:</label>
    <select name="category" id="category">
    	<option value="other">Others</option>
        <option value="electornics">Electronic</option>
        <option value="appliances">Appliances</option>
        <option value="beautyHealth">Beauty & Health </option>
        <option value="book">Book</option>
        <option value="clothing">Clothing</option>
        <option value="furniture">Furniture</option>
        <option value="Vehicle">Vehicle</option>
    </select>
    
    <label for="location">Locations:</label>
    <select name="location" id="location">
        <option value="village">USC Village</option>
        <option value="newnorth">New North</option>
        <option value="lyoncenter">Lyon Center</option>
        <option value="rtcc">Ronald Tutor Campus Center</option>
        <option value="bookstore">USC Bookstore</option>
        <option value="leavey">Leavey Library</option>
        <option value="doheny">Doheny Library</option>
        <option value="mccarthyquad">McCarthy Quad</option>
    </select>
    <label for="image">Upload an Image of your Item.</label>
    <input type="file" id="image" name="image" value="Upload" required> <br/>
    
    <input type="reset" value="Reset">
    <input type="submit" value="Submit">
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
</body>
</html>
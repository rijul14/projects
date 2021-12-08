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
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

	<title>Trojan Trade</title>
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
      margin-bottom: 50px;
    }

    nav a {
      text-decoration: none;
      color: #626262;
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

    #favorite:hover {
      color: #000000;
    }

    #login:hover {
      color: #000000;
    }

    #banner {
      font-family: Lobster;
      color: #B31818;
      font-size: 35px;
      position: relative;
      left: 1%;
    }
    
    .dropdown {
    	position: relative;
    }
    /* navigation end */

    #image-div {
      padding: 30px;
      width: 70%;
      display: block;
      margin-left: auto;
      margin-right: auto;
    }

    #image {
      border-radius: 20px;
      width: 100%;
      height: 350px;
      object-fit: cover;
      overflow: hidden;
    }

    .container {
      width: 80%;
      height: 200px;
      margin: auto;
      padding: 10px;
      padding-left: 10%;
      color: #707070;
    }

	/* search start */
	.search {
	  margin-top:10px;
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
	
    #filter_form {
      margin-left: auto;
      margin-right: auto;

    }
    #filter_form .form-group {
      padding: 10px;
    }
    
    #filter_btn {
      margin-bottom: 10px;
    }

    /* search end */
    
    /* category */
    .radio-toolbar {
      width: 60%;
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
	    font-size: 14px;
	    border-radius: 4px;
	    margin: 10px;
	    width: 200px;
	    height: 60px;
	    cursor: pointer;
	}
	
	.radio-toolbar label:hover {
	  background-color: #FFC72C;
	}
	
	.radio-toolbar input[type="radio"]:checked + label {
	    background-color: #FFC72C;
	}

    /* list start */
    
    #result {
      text-align: left;
      padding-left: 21%;
    }

    #list {
      width: 75%;
      margin-left: 13%;
    }

    .res{
      border-top: 1px solid #9c9c9c;
      width: 80%;
      margin-left: 10%;
      display: flex;
      padding: 30px;
      cursor: pointer;
      text-decoration: none;
    }

    .res:hover {
      background-color: black;
      opacity: 0.1;
    }

    .image img {
      border-radius: 20px;
      width: 200px;
      height: 200px;
      object-fit: fill;
    }

    .detail {
      padding-left: 15%;
      color: #858585;
      text-align: left;
    }

    .detail h3 { 
      color: #4D4D4D;
    }

    /* list end */
    
    #addPost {
      position:fixed;
      top: 60%;
      right: 0;
      margin: 140px;
      color: #FFC72C;
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
  <!-- search -->
  <form>
  <h3 id="result"> ${total} Results for ${term}</h3>
   <div class="search" id="search-form">
      <input type="text" class="item" id="item-search" name="item-search" value="" placeholder="Enter item you're looking for"> 
      <button id="submit"><i class="fa fa-search fa-2x"></i></button>
    </div>
	<h4 id="warning"> ${warning} </h4>

    <button class="btn" type="button" id="filter_btn">
      <i class="fa fa-sort-down fa-3x"></i></button>

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
	    <input type="hidden" name="page" value="main">
	</div>
  </div>
  </form>
  <!-- list of item -->
  <div id="list">
  <% ArrayList<Item> items = (ArrayList<Item>)session.getAttribute("data");
     if(items != null) {
  	  for(Item i : items) { %>
     <a class="res" href="Detail?index=<%=i.getId()%>&page=main">
    	<div class="image"><img src="<%=i.getImage()%>"></div>
    	<div class="detail">
    		<h3><%=i.getTitle()%></h3>
        	<h5>Price: <%=i.getPrice()%></h5>
        	<h5>Location: <%=i.getLocation()%></h5>
        	<h5>Seller: <%=User.getNameById(i.getUserId()) %></h5>
        	<h5>Status: <%=i.getStatus()%></h5>
        </div> 
      </a>
      <%}}%>
  </div>
  
  <a href="post.jsp"><i id="addPost" class="fa fa-plus-square" style="font-size:120px;"></i></a>
  <script type="text/javascript">
  function logout() {
	  	document.getElementById("logout-form").submit();
		alert("Successfully logged out!");
	}
  function displayDetail() {
	  document.getElementByClassName("detail").submit();
  }
  </script>
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
  <script class="cssdeck" src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.1.1/bootstrap.min.jss"></script>
  <script>
    $(window).on('load', function() {
      $("#filter_form").css("display", "none");
      if ($("#logCheck").attr("data-prodnumber") == "0") {
    	  $("#login").css("display", "inline");
    	  $("#home").css("display", "inline");
    	  $("#dropdown").css("display", "none");
    	  $("#addPost").css("display", "none");
      }
      else if($("#logCheck").attr("data-prodnumber") == "1") {
    	  $("#login").css("display", "none");
    	  $("#home").css("display", "none");
    	  $("#dropdown").css("display", "inline");
    	  $("#addPost").css("display", "inline");
      }
    });

    // slide down and up the input
    $("#filter_btn").click(function() {
      $("#filter_form").slideToggle("slow");
    });
  </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>user page</title>
<style>
	.show {display:block;}
body {font-family: Arial, Helvetica, sans-serif;}

/* Full-width input fields */
input[type=email], input[type=password] {
    width: 20%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

/* Set a style for all buttons */
button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 98%%;
}

button:hover {
    opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
    width: auto;
    padding: 10px 18px;
    background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
    position: relative;
}

img.avatar {
    width: 40%;
    border-radius: 50%;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
    border: 1px solid #888;
    width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
    position: absolute;
    right: 25px;
    top: 0;
    color: #000;
    font-size: 35px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: red;
    cursor: pointer;
}

/* Add Zoom Animation */
.animate {
    -webkit-animation: animatezoom 0.6s;
    animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
    from {-webkit-transform: scale(0)} 
    to {-webkit-transform: scale(1)}
}
    
@keyframes animatezoom {
    from {transform: scale(0)} 
    to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
    .cancelbtn {
       width: 100%;
    }
}

</style>

</head>
<body>

	<button onclick="document.getElementById('postWork').style.display='block'" style="width:auto;">Post Work</button><br>
	
	
	<div id = "postWork" class = " modal">
 		<form class="modal-content animate" action="./WorkForceController" method = "POST">
  			<h2>  Work Form</h2>
    		<div class="imgcontainer">
      			<span onclick="document.getElementById('postWork').style.display='none'" class="close" title="Close Modal">&times;</span>
      		</div>
    		<div class="container">
      			SubServiceType : <select name = "SubServiceType">
     	 			<c:forEach items="${serviceTypesList}" var="serviceType">
    					<c:forEach items = "${serviceType.subServiceType}" var="subserviceType">
    						<option value=${subserviceType.subServiceTypeId}>${serviceType.name}-${subserviceType.name}</option>
    					</c:forEach>
   					 </c:forEach>
				</select>
				<br>
 				Description :  <textarea name = "description" rows = "6" cols = "50"></textarea><br>
 				<input type ="hidden" name = "action" value = "postWork">
    		 	<input type="submit" value = "Post Work">
    		</div>
  		</form>
	</div>
	<div class = "logout"><a href = "WorkForceController?action=logout"><button>Logout</button></a></div>
    		
	
<center>
    <table border = 1>
    <tr><th>SubServiceTypeName</th><th>Description</th><th>Date</th><th>ViewBids</th></tr>
    
    <c:forEach items = "${worksList}" var= "work">
    	<tr><td>${work.subServiceType.name}</td><td>${work.description}</td><td>${work.date}</td>
    		<td>
    			<button onclick="document.getElementById('${work.workId}').style.display='block'" style="width:auto;">ViewBids</button><br>
    				<div id = "${work.workId}" class = " modal">
 						<form class="modal-content animate" action="./WorkForceController" method = "POST">
    						<div class="imgcontainer">
      							<span onclick="document.getElementById('${work.workId}').style.display='none'" class="close" title="Close Modal">&times;</span>
    						</div>
    						<center>
    							<table border = 1>
   									<tr><th>ForceName</th><th>BidAmount</th><th></tr>
									<c:forEach items="${work.bid}" var="bid">
										<tr><td>${bid.user.firstName}</td><td>${bid.bidAmount }</td> 
											<c:set var="accepted" value="${bid.accepted}"/>
											<td>
											<div class="div_tdata">
												<c:if test="${accepted eq '0'}">
													<input type = "hidden" name = "action1" value = "${work.workId}">
													<input type = "hidden" name = "action" value = "acceptBid"/>
													<input  type = "submit" value = "AcceptBid">
												</c:if>
												<c:if test="${accepted ne '0'}"></c:if>
											</div>
											</td>
											<td>
												<div class="div_tdata">
													<div class="div_tdata"><a href="ManforceController?action=reject"><button>Reject</button></a></div>
												</div>
											</td>
										</c:forEach>
								</table>
							</center>			
						</form>
    				</div>
			</td>
			</tr>
		</c:forEach>
    </table>
</center>
    
	
<script>
// Get the modal

		function showBids(workId) {
			alert(workId);
			console.log(document.getElementById(workId));
			document.getElementById(workId).style.display = 'block';
		}
		function  close() {
			document.getElementById(workId).style.display = 'close';
			
		}
	

var modal = document.getElementById('PostWork');
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}</script>
</body>
</html>
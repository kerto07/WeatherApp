<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Weather Application</title>
	<meta name="description" content="Weather Application">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
</head>
<body>
	<div>
		<h2>Weather</h2>
		<p>
			Enter a zipcode in order to have weather's informations	
		</p>
		<form id="form" method="post" action="/weather/">
			<fieldset>
		  		<legend>Weather</legend>
		  		<label>
		  			Zip Code
		 		</label>
		  		<input type="text" name="zipCode" />
		  	</fieldset>
	
			<p><button type="submit">Submit</button></p>
		</form>
		<c:if test="${not empty errMsg}">
			<h4 class="error">${errMsg}</h4>
		</c:if>
	</div>
</body>
</html>

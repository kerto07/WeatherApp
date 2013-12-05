<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Weather</title>
	<script type="text/javascript" src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
</head>
<body>
	<div id="formsContent">
		<h2>Weather</h2>
		<p>
			Enter a zipcode in order to have weather's informations	
		</p>
		<form id="form" method="post" action="/weather/">
			<fieldset>
		  		<legend>Weather</legend>
		  		<label>
		  			Zip Code
		 		<label>
		  		<input type="text" name="zipCode" />
		  	</fieldset>
	
			<p><button type="submit">Submit</button></p>
		</form>
		<c:if test="${not empty errMsg}">
			<h4>${errMsg}</h4>
		</c:if>
	</div>
</body>
</html>

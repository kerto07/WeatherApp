<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Weather</title>
</head>
<body>
	<div id="formsContent">
		<h2>Weather informations</h2>
		Informations to display
		
		<br>
		City: ${weather.city}
		<br>
		State: ${weather.state}
		<br>
		Temperature F: ${weather.tempF} F
	</div>
</body>
</html>

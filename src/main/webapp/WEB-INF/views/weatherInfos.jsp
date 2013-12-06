<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Weather informations</title>
	<meta name="description" content="Weather Informations">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
</head>
<body>
	<div id="formsContent">
		<h2>Weather informations</h2>
		<br>
		City: <b>${weather.city}</b>
		<br>
		State: <b>${weather.state}</b>
		<br>
		Temperature: <b>${weather.tempF} F</b>
	</div>
</body>
</html>

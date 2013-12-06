WeatherApp
============

Sample Spring 3 MVC application displaying the weather of a city based on the zip code.

Get started
===========

1) Retrieve the sources  
```
$ git clone https://github.com/kerto07/WeatherApp.git
```
2) Build the application
```
$ mvn clean install
```
3) Run the application  
```
$ mvn clean tomcat7:run
```

Eclipse IDE integration
=======================

1) Generate the Eclipse project thanks to the Maven Eclipse plugin  
```
$ mvn eclipse:eclipse -Dwtpversion=2.0 -DdownloadSources=true -DdownloadJavadocs=true
```
2) Import the project into your Eclipse workspace  
3) Declare a new Tomcat web server instance and add the application artifact  
4) Launch the Tomcat instance
  
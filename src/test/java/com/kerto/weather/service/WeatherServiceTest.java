package com.kerto.weather.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kerto.weather.exceptions.ZipCodeFormatException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
public class WeatherServiceTest {

	@Autowired
	private WeatherService weatherService;

	@Test(expected = ZipCodeFormatException.class)
	public void testGetWeatherFromZipCodeWithZipCodeNull()
			throws ZipCodeFormatException {
		weatherService.getWeatherFromZipCode(null);
	}

	@Test(expected = ZipCodeFormatException.class)
	public void testGetWeatherFromZipCodeWithZipCodeEmpty()
			throws ZipCodeFormatException {
		weatherService.getWeatherFromZipCode("");
	}

	@Test(expected = ZipCodeFormatException.class)
	public void testGetWeatherFromZipCodeWithZipCodeContainsLetter()
			throws ZipCodeFormatException {
		weatherService.getWeatherFromZipCode("7895A");
	}

	@Test(expected = ZipCodeFormatException.class)
	public void testGetWeatherFromZipCodeWithZipCodeContainsLessThan5Number()
			throws ZipCodeFormatException {
		weatherService.getWeatherFromZipCode("7895");
	}

	@Test(expected = ZipCodeFormatException.class)
	public void testGetWeatherFromZipCodeWithZipCodeContainsMoreThan5Number()
			throws ZipCodeFormatException {
		weatherService.getWeatherFromZipCode("789566");
	}
}

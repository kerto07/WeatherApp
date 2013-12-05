package com.kerto.weather.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kerto.weather.config.TestBase;
import com.kerto.weather.exceptions.ZipCodeFormatException;

public class WeatherServiceTest extends TestBase {

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

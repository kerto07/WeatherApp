package com.kerto.weather.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kerto.weather.exceptions.JsonFormatException;
import com.kerto.weather.exceptions.ZipCodeFormatException;
import com.kerto.weather.exceptions.ZipCodeNotFoundException;
import com.kerto.weather.model.Weather;

public interface WeatherService {
	Weather getWeatherFromZipCode(String zipCode)
			throws ZipCodeFormatException, JsonProcessingException,
			ZipCodeNotFoundException, IOException, JsonFormatException;
}

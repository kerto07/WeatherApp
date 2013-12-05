package com.kerto.weather.repository;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kerto.weather.exceptions.ZipCodeNotFoundException;
import com.kerto.weather.model.Weather;

public interface WeatherRepository {
	Weather getWeatherFromZipCode(String zipCode)
			throws ZipCodeNotFoundException, IOException,
			JsonProcessingException;

	URL getURLFromZipCode(String zipCode) throws IOException;
}

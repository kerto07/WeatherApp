package com.kerto.weather.service;

import java.io.IOException;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kerto.weather.exceptions.ZipCodeFormatException;
import com.kerto.weather.exceptions.ZipCodeNotFoundException;
import com.kerto.weather.model.Weather;
import com.kerto.weather.repository.WeatherRepository;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private WeatherRepository weatherRepository;

	/**
	 * Get weather informations of a city based on the zipCode.
	 * 
	 * @param zipCode
	 * @return Weather informations
	 * 
	 * @throws ZipCodeFormatException
	 *             if the zipCode is not 5 digits
	 * @throws JsonProcessingException
	 *             if there is technical error
	 * @throws ZipCodeNotFoundException
	 *             if the zipCode don't exists
	 * @throws IOException
	 *             if there is technical error
	 */
	@Override
	public Weather getWeatherFromZipCode(String zipCode)
			throws ZipCodeFormatException, JsonProcessingException,
			ZipCodeNotFoundException, IOException {
		if (zipCode == null || zipCode.isEmpty() || zipCode.length() != 5
				|| !NumberUtils.isDigits(zipCode)) {
			throw new ZipCodeFormatException();
		}
		return weatherRepository.getWeatherFromZipCode(zipCode);
	}

}

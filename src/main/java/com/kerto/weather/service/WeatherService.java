package com.kerto.weather.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kerto.weather.exceptions.ZipCodeFormatException;
import com.kerto.weather.model.Weather;
import com.kerto.weather.repository.WeatherRepository;

@Service
public class WeatherService {

	@Autowired
	private WeatherRepository weatherRepository;

	public Weather getWeatherFromZipCode(String zipCode)
			throws ZipCodeFormatException {
		if (zipCode == null || zipCode.isEmpty() || zipCode.length() != 5
				|| !NumberUtils.isDigits(zipCode)) {
			throw new ZipCodeFormatException();
		}
		return weatherRepository.getWeatherFromZipCode(zipCode);
	}

}
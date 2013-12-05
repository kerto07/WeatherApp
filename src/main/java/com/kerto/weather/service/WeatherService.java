package com.kerto.weather.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.kerto.weather.exceptions.ZipCodeFormatException;
import com.kerto.weather.model.Weather;

@Service
public class WeatherService {

	public Weather getWeatherFromZipCode(String zipCode)
			throws ZipCodeFormatException {
		Weather result = new Weather();
		if (zipCode == null || zipCode.isEmpty() || zipCode.length() != 5
				|| !NumberUtils.isDigits(zipCode)) {
			throw new ZipCodeFormatException();
		}
		return result;
	}

}

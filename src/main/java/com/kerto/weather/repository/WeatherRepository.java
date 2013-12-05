package com.kerto.weather.repository;

import com.kerto.weather.model.Weather;

public interface WeatherRepository {
	Weather getWeatherFromZipCode(String zipCode);
}

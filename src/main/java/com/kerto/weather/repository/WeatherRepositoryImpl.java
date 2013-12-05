package com.kerto.weather.repository;

import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kerto.weather.HomeController;
import com.kerto.weather.model.Weather;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

	private static final Logger log = LoggerFactory
			.getLogger(HomeController.class);

	@Override
	public Weather getWeatherFromZipCode(String zipCode) {
		Weather weather = new Weather();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = mapper
					.readTree(new URL(
							"http://api.wunderground.com/api/ed044d75b91fb500/conditions/q/94117.json"));
			JsonNode currentObservationNode = rootNode
					.path("current_observation");
			JsonNode displayLocation = currentObservationNode
					.path("display_location");
			weather.setCity(displayLocation.path("city").asText());
			weather.setState(displayLocation.path("state_name").asText());
			weather.setTempF(currentObservationNode.path("temp_f").asDouble());
		} catch (JsonProcessingException e) {
			log.error("Error while processing json file", e);
		} catch (IOException e) {
			log.error("Error while getting json file", e);
		}

		return weather;
	}

}

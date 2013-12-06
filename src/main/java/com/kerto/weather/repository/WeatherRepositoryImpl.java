package com.kerto.weather.repository;

import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kerto.weather.HomeController;
import com.kerto.weather.exceptions.JsonFormatException;
import com.kerto.weather.exceptions.ZipCodeNotFoundException;
import com.kerto.weather.model.Weather;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

	private static final Logger log = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private Environment env;

	@Override
	public URL getURLFromZipCode(String zipCode) throws IOException {
		return new URL(env.getProperty("url.weather") + zipCode + ".json");
	}

	@Override
	public Weather getWeatherFromZipCode(String zipCode)
			throws ZipCodeNotFoundException, IOException,
			JsonProcessingException, JsonFormatException {
		Weather weather = new Weather();
		ObjectMapper mapper = new ObjectMapper();

		try {
			URL urlZipCode = getURLFromZipCode(zipCode);
			JsonNode rootNode = mapper.readTree(urlZipCode);
			JsonNode errorNode = rootNode.path("response").path("error");
			if (!errorNode.isMissingNode()) {
				throw new ZipCodeNotFoundException();
			}

			JsonNode currentObservationNode = rootNode
					.path("current_observation");
			JsonNode displayLocation = currentObservationNode
					.path("display_location");
			JsonNode cityNode = displayLocation.path("city");
			JsonNode stateNode = displayLocation.path("state_name");
			JsonNode tempNode = currentObservationNode.path("temp_f");

			if (cityNode.isMissingNode() || stateNode.isMissingNode()
					|| tempNode.isMissingNode()) {
				throw new JsonFormatException();
			}
			weather.setCity(cityNode.asText());
			weather.setState(stateNode.asText());
			weather.setTempF(tempNode.asDouble());
		} catch (JsonProcessingException e) {
			log.error("Error while processing json file", e);
			throw e;
		} catch (IOException e) {
			log.error("Error while getting json file", e);
			throw e;
		}

		return weather;
	}

}

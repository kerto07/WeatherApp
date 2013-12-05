package com.kerto.weather.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kerto.weather.exceptions.ZipCodeNotFoundException;
import com.kerto.weather.model.Weather;

@RunWith(MockitoJUnitRunner.class)
public class WeatherRepositoryTest {

	private static final String VALID_ZIP_CODE_FILE_NUMBER = "55555";
	private static final String ZIP_CODE_NOT_FOUND_FILE_NUMBER = "77777";

	private static final String CITY_SAN_FRANCISCO = "San Francisco";
	private static final String STATE_CALIFORNIA = "California";
	private static final Double TEMP_VALUE = new Double(43.7);

	@Spy
	@InjectMocks
	private WeatherRepositoryImpl weatherRepository;

	@Before
	public void setUp() throws JsonProcessingException,
			ZipCodeNotFoundException, IOException {
		URL validZipCodeUrl = getClass().getResource(
				"/weatherJsonFiles/validZipCode.json");
		doReturn(validZipCodeUrl).when(weatherRepository).getURLFromZipCode(
				VALID_ZIP_CODE_FILE_NUMBER);
		URL zipCodeNotFoundUrl = getClass().getResource(
				"/weatherJsonFiles/zipCodeNotFound.json");
		doReturn(zipCodeNotFoundUrl).when(weatherRepository).getURLFromZipCode(
				"77777");
	}

	@Test
	public void testGetWeatherFromZipCodeWithZipCodeExists()
			throws JsonProcessingException, ZipCodeNotFoundException,
			IOException {
		Weather result = weatherRepository
				.getWeatherFromZipCode(VALID_ZIP_CODE_FILE_NUMBER);
		assertThat(result.getCity()).isEqualTo(CITY_SAN_FRANCISCO);
		assertThat(result.getState()).isEqualTo(STATE_CALIFORNIA);
		assertThat(result.getTempF()).isEqualTo(TEMP_VALUE);
	}

	@Test(expected = ZipCodeNotFoundException.class)
	public void testGetWeatherFromZipCodeWithZipCodeNotExists()
			throws JsonProcessingException, ZipCodeNotFoundException,
			IOException {
		weatherRepository.getWeatherFromZipCode(ZIP_CODE_NOT_FOUND_FILE_NUMBER);
	}

}

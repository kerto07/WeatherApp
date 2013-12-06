package com.kerto.weather.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kerto.weather.exceptions.ZipCodeFormatException;
import com.kerto.weather.exceptions.ZipCodeNotFoundException;
import com.kerto.weather.model.Weather;
import com.kerto.weather.repository.WeatherRepository;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

	private static final String CITY_SAN_FRANCISCO = "San Francisco";
	private static final String STATE_CALIFORNIA = "California";
	private static final Double TEMP_VALUE = new Double(43.7);

	private static final String ZIP_CODE_CORRECT = "55555";
	private static final String ZIP_CODE_NOT_FOUND = "66666";

	@Mock
	private WeatherRepository weatherRepository;

	@InjectMocks
	private WeatherServiceImpl weatherService;

	@Before
	public void setUp() throws JsonProcessingException,
			ZipCodeNotFoundException, IOException {
		Weather mockedWeather = mock(Weather.class);
		when(mockedWeather.getCity()).thenReturn(CITY_SAN_FRANCISCO);
		when(mockedWeather.getState()).thenReturn(STATE_CALIFORNIA);
		when(mockedWeather.getTempF()).thenReturn(TEMP_VALUE);

		when(weatherRepository.getWeatherFromZipCode(ZIP_CODE_CORRECT))
				.thenReturn(mockedWeather);
		when(weatherRepository.getWeatherFromZipCode(ZIP_CODE_NOT_FOUND))
				.thenThrow(new ZipCodeNotFoundException());
	}

	@Test(expected = ZipCodeFormatException.class)
	public void testGetWeatherFromZipCodeWithZipCodeNull()
			throws ZipCodeFormatException, JsonProcessingException,
			ZipCodeNotFoundException, IOException {
		weatherService.getWeatherFromZipCode(null);
	}

	@Test(expected = ZipCodeFormatException.class)
	public void testGetWeatherFromZipCodeWithZipCodeEmpty()
			throws ZipCodeFormatException, JsonProcessingException,
			ZipCodeNotFoundException, IOException {
		weatherService.getWeatherFromZipCode("");
	}

	@Test(expected = ZipCodeFormatException.class)
	public void testGetWeatherFromZipCodeWithZipCodeContainsLetter()
			throws ZipCodeFormatException, JsonProcessingException,
			ZipCodeNotFoundException, IOException {
		weatherService.getWeatherFromZipCode("7895A");
	}

	@Test(expected = ZipCodeFormatException.class)
	public void testGetWeatherFromZipCodeWithZipCodeContainsLessThan5Number()
			throws ZipCodeFormatException, JsonProcessingException,
			ZipCodeNotFoundException, IOException {
		weatherService.getWeatherFromZipCode("7895");
	}

	@Test(expected = ZipCodeFormatException.class)
	public void testGetWeatherFromZipCodeWithZipCodeContainsMoreThan5Number()
			throws ZipCodeFormatException, JsonProcessingException,
			ZipCodeNotFoundException, IOException {
		weatherService.getWeatherFromZipCode("789566");
	}

	@Test
	public void testGetWeatherFromZipCodeWithValidZipCode()
			throws ZipCodeFormatException, JsonProcessingException,
			ZipCodeNotFoundException, IOException {
		Weather result = weatherService.getWeatherFromZipCode(ZIP_CODE_CORRECT);
		assertThat(result.getCity()).isEqualTo(CITY_SAN_FRANCISCO);
		assertThat(result.getState()).isEqualTo(STATE_CALIFORNIA);
		assertThat(result.getTempF()).isEqualTo(TEMP_VALUE);
	}

	@Test(expected = ZipCodeNotFoundException.class)
	public void testGetWeatherFromZipCodeWithZipCodeNotFound()
			throws ZipCodeFormatException, JsonProcessingException,
			ZipCodeNotFoundException, IOException {
		weatherService.getWeatherFromZipCode(ZIP_CODE_NOT_FOUND);
	}
}

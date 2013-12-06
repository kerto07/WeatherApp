package com.kerto.weather;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kerto.weather.exceptions.ZipCodeFormatException;
import com.kerto.weather.exceptions.ZipCodeNotFoundException;
import com.kerto.weather.model.Weather;
import com.kerto.weather.service.WeatherServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private WeatherServiceImpl weatherService;

	private static final String TECHNICAL_ERROR = "technical error";

	private static final Logger log = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String findWeatherInfosByZipCode(@RequestParam String zipCode,
			Model model) throws ZipCodeFormatException,
			JsonProcessingException, ZipCodeNotFoundException, IOException {

		Weather weather = weatherService.getWeatherFromZipCode(zipCode);
		model.addAttribute("weather", weather);
		return "weatherInfos";
	}

	@ExceptionHandler(ZipCodeFormatException.class)
	public ModelAndView handleZipCodeFormatException(ZipCodeFormatException ex) {
		ModelAndView model = new ModelAndView("home");
		model.addObject("errMsg", ZipCodeFormatException.ERROR_MESSAGE);

		return model;
	}

	@ExceptionHandler(ZipCodeNotFoundException.class)
	public ModelAndView handleZipCodeNotFoundException(
			ZipCodeNotFoundException ex) {
		ModelAndView model = new ModelAndView("home");
		model.addObject("errMsg", ZipCodeNotFoundException.ERROR_MESSAGE);

		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		log.error("An exception occurs when getting weather informations", ex);
		ModelAndView model = new ModelAndView("home");
		model.addObject("errMsg", TECHNICAL_ERROR);

		return model;
	}

}

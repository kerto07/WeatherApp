package com.kerto.weather;

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

import com.kerto.weather.exceptions.ZipCodeFormatException;
import com.kerto.weather.service.WeatherService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private WeatherService weatherService;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String findWeatherInfosByZipCode(@RequestParam String zipCode,
			Model model) throws ZipCodeFormatException {

		weatherService.getWeatherFromZipCode(zipCode);

		return "weatherInfos";
	}

	@ExceptionHandler(ZipCodeFormatException.class)
	public ModelAndView handleCustomException(ZipCodeFormatException ex) {

		ModelAndView model = new ModelAndView("home");
		model.addObject("errMsg", ZipCodeFormatException.ERROR_MESSAGE);

		return model;
	}

}

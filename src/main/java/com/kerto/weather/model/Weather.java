package com.kerto.weather.model;

public class Weather {

	private String city;

	private String state;

	private Double tempF;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getTempF() {
		return tempF;
	}

	public void setTempF(Double tempF) {
		this.tempF = tempF;
	}

}

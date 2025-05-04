package io.github.aguglia.service;

import io.github.aguglia.model.WeatherResponse;

public interface WeatherResponseService {
	public WeatherResponse getWeatherInfo(String adress);
}

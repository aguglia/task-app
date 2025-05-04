package io.github.aguglia.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import io.github.aguglia.model.WeatherResponse;
import io.github.aguglia.service.WeatherResponseService;

@Service
public class WeatherResponseServiceImpl implements WeatherResponseService {
	@Value("${weather.api.key}")
	private String weatherApiKey;

	private final RestClient restClient = RestClient.create();

	public WeatherResponse getWeatherInfo(String adress) {
		String url = String.format(
				"https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=ja",
	            adress, weatherApiKey);
		WeatherResponse response = restClient.get()
				.uri(url)
				.retrieve()
				.body(WeatherResponse.class);

		return response;
	}
}

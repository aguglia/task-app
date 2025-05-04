package io.github.aguglia.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
	  private List<Weather> weather;
    private Main main;
    private String name;


    @Getter
    @Setter
    public static class Weather {
        private String description;
    }

    @Getter
    @Setter
    public static class Main {
        private double temp;
    }
}

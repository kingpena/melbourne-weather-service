package com.zai.weather;

import com.zai.weather.model.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherApplicationIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getWeatherShouldReturnWeatherResponse() {
        String url = "http://localhost:" + port + "/v1/weather?city=melbourne";
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        assertThat(response).isNotNull();
    }
}
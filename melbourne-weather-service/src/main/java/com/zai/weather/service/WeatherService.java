package com.zai.weather.service;

import com.zai.weather.model.WeatherResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();
// just for the sake of test and can easily setup for both end i just hard coded the access_key

//    but the main plan is
//    In production:
//
//    Store keys in environment variables (e.g., export WEATHERSTACK_ACCESS_KEY=your_key)
//
//    Use a secrets manager like AWS Secrets Manager or HashiCorp Vault for extra security.

//Move access keys to application.yml or application.properties
//    # application.yml
//    weather:
//        weatherstack:
//            url: http://api.weatherstack.com/current
//            access-key: ${WEATHERSTACK_ACCESS_KEY}
//        openweathermap:
//            url: http://api.openweathermap.org/data/2.5/weather
//            access-key: ${OPENWEATHERMAP_ACCESS_KEY}

//    Use environment variables (like WEATHERSTACK_ACCESS_KEY) for the actual key in production.
//    This keeps secrets out of version control.

//    Create a config class
//    package com.zai.weather.config;

//import lombok.Getter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
//    @Getter
//    @Configuration
//    @ConfigurationProperties(prefix = "weather")
//    public class WeatherApiProperties {
//        private Provider weatherstack;
//        private Provider openweathermap;
//
//        @Getter
//        public static class Provider {
//            private String url;
//            private String accessKey;
//        }
//    }


//    @Service
//public class WeatherService {
//
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final WeatherApiProperties props;
//
//    public WeatherService(WeatherApiProperties props) {
//        this.props = props;
//    }
//
//    @Cacheable("weather")
//    public WeatherResponse getWeather(String city) {
//        try {
//            String url = props.getWeatherstack().getUrl() + "?access_key=" + props.getWeatherstack().getAccessKey() + "&query=" + city;
//            Map response = restTemplate.getForObject(url, Map.class);
//            Map current = (Map<?, ?>) response.get("current");
//            return new WeatherResponse(
//                ((Number) current.get("wind_speed")).doubleValue(),
//                ((Number) current.get("temperature")).doubleValue()
//            );
//        } catch (Exception ex) {
//            try {
//                String url = props.getOpenweathermap().getUrl() + "?q=" + city + ",AU&appid=" + props.getOpenweathermap().getAccessKey() + "&units=metric";
//                Map response = restTemplate.getForObject(url, Map.class);
//                Map wind = (Map<?, ?>) response.get("wind");
//                Map main = (Map<?, ?>) response.get("main");
//                return new WeatherResponse(
//                    ((Number) wind.get("speed")).doubleValue(),
//                    ((Number) main.get("temp")).doubleValue()
//                );
//            } catch (Exception fallbackEx) {
//                return new WeatherResponse(-1, -273); // fallback stale/invalid
//            }
//        }
//    }
//}


    @Cacheable("weather")
    public WeatherResponse getWeather(String city) {
        try {
//            -use this wrong access key to test the fail over API
//            String url = "http://api.weatherstack.com/current?access_key=YOUR_ACCESS_KEY&query=" + city;
            String url = "http://api.weatherstack.com/current?access_key=b577335c64909cbaf73d0514228ebd45&query=" + city;
            Map response = restTemplate.getForObject(url, java.util.Map.class);
            Map current = (java.util.Map<?, ?>) response.get("current");
            return new WeatherResponse(((Number) current.get("wind_speed")).doubleValue(),
                                       ((Number) current.get("temperature")).doubleValue());
        } catch (Exception ex) {
            try {
                String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + ",AU&appid=2326504fb9b100bee21400190e4dbe6d&units=metric";
                Map response = restTemplate.getForObject(url, java.util.Map.class);
                Map wind = (java.util.Map<?, ?>) response.get("wind");
                Map main = (java.util.Map<?, ?>) response.get("main");
                return new WeatherResponse(((Number) wind.get("speed")).doubleValue(),
                                           ((Number) main.get("temp")).doubleValue());
            } catch (Exception fallbackEx) {
                return new WeatherResponse(-1, -273); // fallback stale/invalid
            }
        }
    }
}
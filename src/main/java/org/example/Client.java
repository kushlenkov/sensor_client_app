package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.MeasurementDTO;
import org.example.parse.Measurement;
import org.example.parse.ServerResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Client {
    public static void main(String[] args) throws JsonProcessingException {
        ServerResponse response = getMeasurements();

        List<Measurement> measurements = response.getMeasurements();
        for (Measurement measurement : measurements) {
            System.out.println(measurement.getSensor().getName());
            System.out.println(measurement.getValue());
            System.out.println(measurement.getRaining());
            System.out.println(measurement.getCreatedAt());
            System.out.println("---------------------------");
        }


//        registerSensorAndSendMeasurements("Sensor2", -5, 5);
    }

    private static void registerSensorAndSendMeasurements(String sensorName,
                                                          double minTemperature,
                                                          double maxTemperature) {
        registerSensor(sensorName);

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);

            double value = random.nextDouble(minTemperature, maxTemperature);
            boolean raining = random.nextBoolean();

            sendMeasurement(value, raining, sensorName);
        }
    }

    private static ServerResponse getMeasurements() throws JsonProcessingException {
        final String url = "http://localhost:8080/measurements";

        return makeGetRequest(url);
    }

    private static void registerSensor(String sensorName) {
        final String url = "http://localhost:8080/sensors/registration";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", sensorName);

        makePostRequestWithJsonData(url, jsonData);
    }

    private static void sendMeasurement(double value, boolean raining, String sensorName) {
        final String url = "http://localhost:8080/measurements/add";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", sensorName));

        makePostRequestWithJsonData(url, jsonData);
    }

    private static void makePostRequestWithJsonData(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);

            System.out.println("Measurement successfully sent to server!");
        } catch (HttpClientErrorException e) {
            System.out.println("Error!");
            System.out.println(e.getMessage());
        }
    }

    private static ServerResponse makeGetRequest(String url) {
        final RestTemplate restTemplate = new RestTemplate();
        ServerResponse response = null;

        try {
            response = restTemplate.getForObject(url, ServerResponse.class);
        } catch (HttpClientErrorException e) {
            System.out.println("Error!");
            System.out.println(e.getMessage());
        }

        return response;
    }
}
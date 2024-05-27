package org.example;

import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DrawChart {
    public static void main(String[] args) {

    }

    private static List<Double> getTemperatureFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements";

        return null;
    }
}

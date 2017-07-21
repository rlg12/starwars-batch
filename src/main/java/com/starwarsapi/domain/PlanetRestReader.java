package com.starwarsapi.domain;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by on 21/07/17.
 */
public class PlanetRestReader implements ItemReader<Planet> {
    private static final String apiUrl="http://swapi.co/api/planets/?format=json";
    private final RestTemplate restTemplate;


    @Override
    public String read() throws Exception {
        if (planetDataIsNotInitialized()) {
            movida = fetchStudentDataFromAPI();
        }
        /*Planet nextPlanetItem = null;
        if (nextPlanet < planets.size()) {
            nextPlanetItem = planets.get(nextPlanet);
            nextPlanet++;
        }*/

        return movida;
    }



    private int nextPlanet;
    private List<Planet> planets;
    private String movida;

    PlanetRestReader( RestTemplate restTemplate) {
//        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        nextPlanet = 0;
    }

    private boolean planetDataIsNotInitialized() {
        return this.planets == null;
    }

    private String fetchStudentDataFromAPI() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                apiUrl,
                String.class
        );
        String planetData = response.getBody();
        return planetData;
    }
}

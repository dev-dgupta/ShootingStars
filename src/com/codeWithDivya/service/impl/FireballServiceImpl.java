package com.codeWithDivya.service.impl;

import com.codeWithDivya.dao.Fireball;
import com.codeWithDivya.dao.FireballParser;
import com.codeWithDivya.dao.Location;
import com.codeWithDivya.service.FireballService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FireballServiceImpl implements FireballService {


    final static String API_URL = "https://ssd-api.jpl.nasa.gov/fireball.api";
    final static int THRESHOLD_CAPACITY = 10;

    @Override
    public List<Fireball> getDataPoints(String minDate) {
        return getDataFromAPICall(minDate);
    }

    private List<Fireball> getDataFromAPICall(String minDate) {
        try {
            StringBuilder url = new StringBuilder(API_URL.concat("?date-min=").concat(minDate).concat("&req-loc=true"));
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(url.toString()))
                    .build();
            return handleResponse(HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()));
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {

        }
    }

    private List<Fireball> handleResponse(HttpResponse response) throws Exception {
        int code = response.statusCode();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(String.valueOf(response.body()));
        int count = Integer.valueOf(json.get("count").toString());

        if (THRESHOLD_CAPACITY > count)
            return Collections.emptyList();

        JSONArray array = new JSONArray();
        array.add(json.get("data"));

        FireballParser fireballParser = new FireballParser(Arrays.asList(array.get(0)), count);

        List<Fireball> fireballList = new ArrayList<>(count);
Object[] arr= (Object[]) array.get(0);
        for (int i=0;i<count;i++){
            Location location=new Location();
            location.setShootingStarBrightness(array.get(0).g);


        }
            return fireballList;
    }
}

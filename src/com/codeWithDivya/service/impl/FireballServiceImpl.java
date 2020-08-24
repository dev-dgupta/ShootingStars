package com.codeWithDivya.service.impl;

import com.codeWithDivya.service.FireballService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.codeWithDivya.utils.HttpResponseCodes;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FireballServiceImpl implements FireballService {


    final static String API_URL = "https://ssd-api.jpl.nasa.gov/fireball.api";

    @Override
    public void getDataPoints(String minDate) {


        try {
            getDataFromAPICall(minDate);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }

    private void getDataFromAPICall(String minDate) {
        try {
            StringBuilder url = new StringBuilder(API_URL.concat("?date-min=").concat(minDate));
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(url.toString()))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

            handleResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(HttpResponse response) throws Exception {
        int code = response.statusCode();
        HttpResponseCodes codes = HttpResponseCodes.valueOf(code);
        switch (codes) {
            case HttpResponseCodes.OK:

                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(String.valueOf(response.body()));
                System.out.println(json);
                break;
            case HttpResponseCodes.BAD_REQUEST:
                break;
            case HttpResponseCodes.SERVICE_UNAVAILABLE:
                break;
            case HttpResponseCodes.INTERNAL_SERVER_ERROR:
                break;
            case HttpResponseCodes.METHOD_NOT_ALLOWED:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + codes);
        }
    }
}

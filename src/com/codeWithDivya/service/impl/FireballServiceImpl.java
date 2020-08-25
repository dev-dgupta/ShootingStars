package com.codeWithDivya.service.impl;

import com.codeWithDivya.dao.Fireball;
import com.codeWithDivya.dao.Location;
import com.codeWithDivya.exceptions.FireBallException;
import com.codeWithDivya.service.FireballService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.codeWithDivya.utils.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.codeWithDivya.utils.Constants.*;
import static com.codeWithDivya.utils.Constants.Fields.*;
import static com.codeWithDivya.utils.Constants.ErrorCodes.*;

public class FireballServiceImpl implements FireballService {

    @Override
    public String getBrightestShootingStar(List<Location> locationInputs, String minDate)
            throws FireBallException {

        List<Fireball> fireballDataPoints = getDataFromAPICall(minDate);

        if (fireballDataPoints.isEmpty()) {
            return "Sufficient Data Points are not available. Please check in some time!!";
        }

        return "Brightest Star among Delphix Locations is " +
                getBrightestStarLoc(locationInputs, fireballDataPoints);
    }

    private String getBrightestStarLoc(List<Location> locationInputs, List<Fireball> fireballDataPoints) {
        double maxBrightness = Integer.MIN_VALUE;
        String brightestStar = "";
        double latMin, latMax, longMin, longMax;
        for (Location locationInput : locationInputs) {
            latMin = locationInput.getLatitude() - LAT_LONG_BUFFER;
            latMax = locationInput.getLatitude() + LAT_LONG_BUFFER;
            longMin = locationInput.getLongitude() - LAT_LONG_BUFFER;
            longMax = locationInput.getLongitude() + LAT_LONG_BUFFER;
            for (Fireball fireball : fireballDataPoints) {
                if (fireball.getLatDir().equalsIgnoreCase(locationInput.getLatDirection())
                        && fireball.getLongDir().equalsIgnoreCase(locationInput.getLongDirection())) {
                    if (Util.isInclusive(fireball.getLatitude(), latMin, latMax)
                            && Util.isInclusive(fireball.getLongitude(), longMin, longMax)) {
                        if (maxBrightness < fireball.getTotalImpactEnergy()) {
                            maxBrightness = fireball.getTotalImpactEnergy();
                            brightestStar = locationInput.getName();
                        }
                    }
                }
            }
        }
        return brightestStar;
    }

    private List<Fireball> getDataFromAPICall(String minDate) throws FireBallException {
        try {
            StringBuilder url = new StringBuilder(API_URL.concat("?date-min=").
                    concat(minDate).concat("&req-loc=true"));
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(url.toString()))
                    .build();
            return handleResponse(HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString()));
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {

        }
    }

    private List<Fireball> handleResponse(HttpResponse response) throws FireBallException, ParseException {
        ErrorCodes codes = valueOf(response.statusCode());

        switch (codes) {
            case OK:
                return handleBody(String.valueOf(response.body()));
            case BAD_REQUEST:
                throw new FireBallException("The request contained invalid keywords and/or content",
                        BAD_REQUEST);
            case METHOD_NOT_ALLOWED:
                throw new FireBallException("The request used a method other than GET or POST",
                        METHOD_NOT_ALLOWED);
            case INTERNAL_SERVER_ERROR:
                throw new FireBallException("The database is not available at the time of the request",
                        INTERNAL_SERVER_ERROR);
            case SERVICE_UNAVAILABLE:
                throw new FireBallException("The server is currently unable to handle the request due " +
                        "to a temporary overloading or maintenance of the server, " +
                        "which will likely be alleviated after some delay",
                        SERVICE_UNAVAILABLE);
            default:
                return Collections.emptyList();
        }
    }

    private List<Fireball> handleBody(String response) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response);
        int count = Integer.valueOf(json.get("count").toString());

        if (THRESHOLD_CAPACITY > count)
            return Collections.emptyList();

        JSONArray array = new JSONArray();
        array.add(json.get("data"));

        return getLocationDataPoints(count, array);
    }

    private List<Fireball> getLocationDataPoints(int count, JSONArray array) {
        List<Fireball> fireballs = new ArrayList<>(count);
        Fireball fireball;
        for (int i = 0; i < count; i++) {
            JSONArray jsonArray = (JSONArray) ((JSONArray) array.get(0)).get(i);
            if (Util.isNull(jsonArray.get(TOTAL_IMPACTED_ENERGY.getValue()),
                    jsonArray.get(LATITUDE.getValue()),
                    jsonArray.get(LATITUDE_DIRECTION.getValue()),
                    jsonArray.get(LONGITUDE.getValue()),
                    jsonArray.get(LONGITUDE_DIRECTION.getValue())))
                continue;
            fireball = new Fireball();
            fireball.setTotalImpactEnergy(Double.parseDouble(
                    jsonArray.get(TOTAL_IMPACTED_ENERGY.getValue()).toString()));
            fireball.setLatitude(Double.parseDouble(jsonArray.get(LATITUDE.getValue()).toString()));
            fireball.setLatDir(jsonArray.get(LATITUDE_DIRECTION.getValue()).toString());
            fireball.setLongitude(Double.parseDouble(jsonArray.get(LONGITUDE.getValue()).toString()));
            fireball.setLongDir(jsonArray.get(LONGITUDE_DIRECTION.getValue()).toString());
            fireballs.add(fireball);
        }
        return fireballs;
    }
}

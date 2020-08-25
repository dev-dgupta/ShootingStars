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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.codeWithDivya.utils.Constants;
import com.codeWithDivya.utils.Direction;
import com.codeWithDivya.utils.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FireballServiceImpl implements FireballService {

    @Override
    public String getBrightestShootingStar(List<Location> locationInputs, String minDate) throws FireBallException {

        List<Fireball> fireballDataPoints = getDataFromAPICall(minDate);

        if (fireballDataPoints.isEmpty()) {
            return "Sufficient Data Points are not available. Please check in some time!!";
        }

        return "Brightest Star among Delphix Locations is " + getBrightestStarLoc(locationInputs, fireballDataPoints);
    }

    private String getBrightestStarLoc(List<Location> locationInputs, List<Fireball> fireballDataPoints) {
        double maxBrightness = Integer.MIN_VALUE;
        String brightestStar = "";
        double latMin, latMax, longMin, longMax;
        for (Location locationInput : locationInputs) {
            latMin = locationInput.getLatitude() - Constants.LAT_LONG_BUFFER;
            latMax = locationInput.getLatitude() + Constants.LAT_LONG_BUFFER;
            longMin = locationInput.getLongitude() - Constants.LAT_LONG_BUFFER;
            longMax = locationInput.getLongitude() + Constants.LAT_LONG_BUFFER;
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

//    public static double fireball(double latitude, double longitude) {
//
//
//    }

    private List<Fireball> getDataFromAPICall(String minDate)throws FireBallException {
        try {
            StringBuilder url = new StringBuilder(Constants.API_URL.concat("?date-min=").concat(minDate).concat("&req-loc=true"));
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(url.toString()))
                    .build();
            return handleResponse(HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()));
        } catch (Exception e) {
            return Collections.emptyList();
        } finally {

        }
    }

    private List<Fireball> handleResponse(HttpResponse response) throws FireBallException, ParseException {
        int code = response.statusCode();

        // todo: handle these exceptions accordingly
        switch (code) {
            case 200:
                return handleBody(String.valueOf(response.body()));
            case 400:throw new FireBallException("Bad Request","400");
            case 405:throw new FireBallException("Method Not Allowed","405");
            case 500:throw new FireBallException("Internal Server Error server error","500");
            case 503:throw new FireBallException("Service Unavailable","503");
            default:
                return Collections.emptyList();
        }
    }

    private List<Fireball> handleBody(String response) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response);
        int count = Integer.valueOf(json.get("count").toString());

        if (Constants.THRESHOLD_CAPACITY > count)
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
            //todo: instead of 1,2,3,4 these must also be constants/enums
            if (Util.isNull(jsonArray.get(2), jsonArray.get(3), jsonArray.get(4), jsonArray.get(5), jsonArray.get(6)))
                continue;
            fireball = new Fireball();
            fireball.setTotalImpactEnergy(Double.parseDouble(jsonArray.get(2).toString()));
            fireball.setLatitude(Double.parseDouble(jsonArray.get(3).toString()));
            fireball.setLatDir(jsonArray.get(4).toString());
            fireball.setLongitude(Double.parseDouble(jsonArray.get(5).toString()));
            fireball.setLongDir(jsonArray.get(6).toString());
            fireballs.add(fireball);
        }
        return fireballs;
    }
}

package com.shootingStar.service.impl;

import com.shootingStar.dao.Fireball;
import com.shootingStar.dao.Location;

import com.shootingStar.enums.HttpErrorCodes;
import com.shootingStar.exceptions.FireBallException;
import com.shootingStar.service.FireballService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.shootingStar.utils.Messages;
import com.shootingStar.utils.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.shootingStar.utils.Constants.*;
import static com.shootingStar.enums.ApiResponseFields.*;
import static com.shootingStar.enums.HttpErrorCodes.*;

public class FireballServiceImpl implements FireballService {

    @Override
    public String getBrightestShootingStar(List<Location> locationInputs, String minDate)
            throws FireBallException {

        List<Fireball> fireballDataPoints = getDataFromAPICall(minDate);

        if (fireballDataPoints.isEmpty()) {
            return Messages.API_NO_DATA;
        }
        List<Object> getBrightestStar= getBrightestStarDetails(locationInputs, fireballDataPoints);
        return Messages.BRIGHTEST_LOCATION + getBrightestStar.get(0)
                +Messages.BRIGHTEST_STAR_ENERGY+ getBrightestStar.get(1);
    }

    private List<Object> getBrightestStarDetails(List<Location> locationInputs, List<Fireball> fireballDataPoints) {
        double maxBrightness = Integer.MIN_VALUE;
        List<Object> getBrightestStar =new ArrayList<>();
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
        getBrightestStar.add(brightestStar);
        getBrightestStar.add(maxBrightness);
        return getBrightestStar;
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
        HttpErrorCodes codes = valueOf(response.statusCode());

        switch (codes) {
            case OK:
                return handleBody(String.valueOf(response.body()));
            case BAD_REQUEST:
                throw new FireBallException(Messages.BAD_REQUEST, BAD_REQUEST);
            case METHOD_NOT_ALLOWED:
                throw new FireBallException(Messages.METHOD_NOT_ALLOWED, METHOD_NOT_ALLOWED);
            case INTERNAL_SERVER_ERROR:
                throw new FireBallException(Messages.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
            case SERVICE_UNAVAILABLE:
                throw new FireBallException(Messages.SERVICE_UNAVAILABLE, SERVICE_UNAVAILABLE);
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

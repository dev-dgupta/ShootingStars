package com.codeWithDivya;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here

        Location boston = new Location("Boston", 42.354558, 71.054254, Direction.N, Direction.W);
        Location ncr = new Location("NCR", 28.574389, 77.312638, Direction.N, Direction.E);
        Location sanFran = new Location("San Francisco", 37.793700, 122.403906, Direction.N, Direction.W);
        var locationInputs = new ArrayList<Location>();
        locationInputs.add(boston);
        locationInputs.add(ncr);
        locationInputs.add(sanFran);

        //call API and put all data in fireball
        // we also check for any exception in API occurring


        for(Location location:locationInputs){
//+- of location inputs
            fireball(location.getLatitude(),location.getLongitude());
        }

        //after receiving shooting stars for every location, we print the brightest shooting star

    }

    /**
     * returns the brightest shooting star energy
     * @param latitude
     * @param longitude
     * @return
     */
    public static double fireball(double latitude, double longitude) throws IOException {
        URLConnection urlConnection = new URL("https://ssd-api.jpl.nasa.gov/fireball.api?date-min=2017-01-01&").openConnection();
        String readLine = null;
      /*  urlConnection.setRequestProperty("header1", header1);
        urlConnection.setRequestProperty("header2", header2);*/
        //Get Response
        InputStream is = urlConnection.getInputStream();
        System.out.println(urlConnection.getContentType());
        double brightestStarEnergy=0.00;
        return brightestStarEnergy;

    }


}

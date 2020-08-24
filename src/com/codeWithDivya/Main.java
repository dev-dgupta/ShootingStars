package com.codeWithDivya;

import com.codeWithDivya.dao.Fireball;
import com.codeWithDivya.dao.Location;
import com.codeWithDivya.service.FireballService;
import com.codeWithDivya.service.impl.FireballServiceImpl;
import com.codeWithDivya.utils.Direction;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        var fireballService=new FireballServiceImpl();
        fireballService.getDataPoints("2017-01-01");
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

        double brightestStarEnergy=0.00;
        return brightestStarEnergy;

    }


}

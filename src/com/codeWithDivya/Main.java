package com.codeWithDivya;

import com.codeWithDivya.dao.Fireball;
import com.codeWithDivya.dao.Location;
import com.codeWithDivya.service.FireballService;
import com.codeWithDivya.service.impl.FireballServiceImpl;
import com.codeWithDivya.utils.Direction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        if (!locationInputs.isEmpty()) {
            var fireballService = new FireballServiceImpl();
            List<Fireball> fireballList = fireballService.getDataPoints("2017-01-01");
            if (fireballList.isEmpty()) {
                System.out.println("Sufficient Data Points are not available. Please check in some time!!");
            }
            for (Fireball fireball : fireballList) {
                //range
                if (fireball.getLatitude() == boston.getLatitude() && fireball.getLongitude() == boston.getLongitude()
                        && fireball.getLatitutdeDirection() == boston.getLatDirection() && fireball.getLongitudeDirection() == boston.getLongDirection()) {
                    boston.setShootingStarBrightness(fireball.getTotalImpactEnergy());
                }
                if (fireball.getLatitude() == ncr.getLatitude() && fireball.getLongitude() == ncr.getLongitude()
                        && fireball.getLatitutdeDirection() == boston.getLatDirection() &&
                        fireball.getLongitudeDirection() == ncr.getLongDirection()) {
                    ncr.setShootingStarBrightness(fireball.getTotalImpactEnergy());
                }
                if (fireball.getLatitude() == boston.getLatitude() && fireball.getLongitude() == sanFran.getLongitude()
                        && fireball.getLatitutdeDirection() == sanFran.getLatDirection() &&
                        fireball.getLongitudeDirection() == sanFran.getLongDirection()) {
                    sanFran.setShootingStarBrightness(fireball.getTotalImpactEnergy());
                }
            }

            String brightestStar = "";

            if (boston.getShootingStarBrightness() > ncr.getShootingStarBrightness()) {
                if (boston.getShootingStarBrightness() > sanFran.getShootingStarBrightness()) {
                    brightestStar = boston.getName();
                } else {
                    if (ncr.getShootingStarBrightness() > sanFran.getShootingStarBrightness()) {
                        brightestStar = ncr.getName();
                    } else brightestStar = sanFran.getName();
                }
            }


            //after receiving shooting stars for every location, we print the brightest shooting star
            System.out.printf("Brightest Star among Delphix Locations is " + brightestStar);
        }

//        /**
//         * returns the brightest shooting star energy
//         *
//         * @param latitude
//         * @param longitude
//         * @return
//         */
//        public static double fireball ( double latitude, double longitude) throws IOException {
//
//            double brightestStarEnergy = 0.00;
//            return brightestStarEnergy;
//
//        }
    }

}

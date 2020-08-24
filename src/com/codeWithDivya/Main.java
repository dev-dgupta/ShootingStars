package com.codeWithDivya;

import com.codeWithDivya.dao.Location;
import com.codeWithDivya.service.impl.FireballServiceImpl;
import com.codeWithDivya.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        //only user inputs taken
        Location boston = new Location("Boston", 42.354558, 71.054254, "N", "W");
        Location ncr = new Location("NCR", 28.574389, 77.312638, "N", "E");
        Location sanFran = new Location("San Francisco", 37.793700, 122.403906, "N", "W");
        var locationInputs = new ArrayList<Location>();
        locationInputs.add(boston);
        locationInputs.add(ncr);
        locationInputs.add(sanFran);

        if (!locationInputs.isEmpty()) {
            var fireballService = new FireballServiceImpl();
            //after receiving shooting stars for every location, we print the brightest shooting star
            System.out.println(fireballService.getBrightestShootingStar(locationInputs, Constants.SHOOTING_STAR_SINCE_DATE));
        }

    }

}

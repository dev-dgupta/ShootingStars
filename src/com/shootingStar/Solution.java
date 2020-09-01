package com.codeWithDivya;

import com.codeWithDivya.dao.Location;
import com.codeWithDivya.exceptions.FireBallException;
import com.codeWithDivya.service.FireballService;
import com.codeWithDivya.service.impl.FireballServiceImpl;
import com.codeWithDivya.utils.Constants;
import com.codeWithDivya.utils.Messages;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws FireBallException {
        //only user inputs taken
        Location boston = new Location("Boston", 42.354558, 71.054254, "N", "W");
        Location ncr = new Location("NCR", 28.574389, 77.312638, "N", "E");
        Location sanFran = new Location("San Francisco", 37.793700, 122.403906, "N", "W");
        List<Location> locationInputs = new ArrayList<>();

        //Test case 1 : add all locations shared in assignment, original case
        locationInputs.add(boston);
        locationInputs.add(ncr);
        locationInputs.add(sanFran);

        //Test case 2 : add only 2 locations , comment other test cases
        //locationInputs.add(ncr);
        //locationInputs.add(sanFran);

        //Test case 3 : send empty location list, comment other test cases

        if (!locationInputs.isEmpty()) {
            FireballService fireballService = new FireballServiceImpl();
            //after receiving shooting stars for every location, we print the brightest shooting star
            System.out.println(fireballService.getBrightestShootingStar(locationInputs, Constants.SHOOTING_STAR_SINCE_DATE));
        }
        else
            System.out.println(Messages.INSUFFICIENT_DATA);

    }

}

package com.codeWithDivya.service;

import com.codeWithDivya.dao.Location;
import java.util.List;

public interface FireballService {

    String getBrightestShootingStar(List<Location> locations, String minDate);

}

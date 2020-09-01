package com.shootingStar.service;

import com.shootingStar.dao.Location;
import com.shootingStar.exceptions.FireBallException;

import java.util.List;

public interface FireballService {

    String getBrightestShootingStar(List<Location> locations, String minDate) throws FireBallException;

}

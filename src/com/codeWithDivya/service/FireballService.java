package com.codeWithDivya.service;

import com.codeWithDivya.dao.Fireball;

import java.net.MalformedURLException;
import java.util.List;

public interface FireballService {

    public List<Fireball> getDataPoints(String minDate) throws MalformedURLException;

}

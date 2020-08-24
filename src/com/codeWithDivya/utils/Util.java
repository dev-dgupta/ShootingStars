package com.codeWithDivya.utils;

public class Util {

    public static boolean isInclusive(double x, double min, double max) {
        return x > min && x < max;
    }

    public static boolean isNull(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInclusive(int x, int min, int max) {
        return x > min && x < max;
    }

    public static boolean isInclusive(float x, float min, float max) {
        return x > min && x < max;
    }
}

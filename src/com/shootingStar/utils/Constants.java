package com.codeWithDivya.utils;

import java.util.HashMap;
import java.util.Map;

public final class Constants {

    private Constants() {
    }

    public final static String API_URL = "https://ssd-api.jpl.nasa.gov/fireball.api";
    public final static int THRESHOLD_CAPACITY = 10;
    public final static int LAT_LONG_BUFFER = 15;
    public final static String SHOOTING_STAR_SINCE_DATE = "2017-01-01";

    public enum Fields {
        DATE(0),
        ENERGY(1),
        TOTAL_IMPACTED_ENERGY(2),
        LATITUDE(3),
        LATITUDE_DIRECTION(4),
        LONGITUDE(5),
        LONGITUDE_DIRECTION(6),
        ALTITUDE(7),
        VELOCITY(8);

        private int value;

        // enum constructor - cannot be public or protected
        private Fields(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum ErrorCodes {

        OK(200),
        BAD_REQUEST(400),
        METHOD_NOT_ALLOWED(405),
        INTERNAL_SERVER_ERROR(500),
        SERVICE_UNAVAILABLE(503);

        private int value;

        private ErrorCodes(int value) {
            this.value = value;
        }

        private static Map errorCodeValueMap = new HashMap<>();

        static {
            for (ErrorCodes httpResponseCodes : ErrorCodes.values()) {
                errorCodeValueMap.put(httpResponseCodes.getValue(), httpResponseCodes);
            }
        }

        public static ErrorCodes valueOf(int httpResponseCodes) {
            return (ErrorCodes) errorCodeValueMap.get(httpResponseCodes);
        }

        public int getValue() {
            return value;
        }
    }
}

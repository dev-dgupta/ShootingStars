package com.codeWithDivya.utils;

import java.util.HashMap;
import java.util.Map;

public enum HttpResponseCodes {

    OK(200),
    BAD_REQUEST(400),
    METHOD_NOT_ALLOWED(405),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503);

    // declaring private variable for getting values
    private int value;

    // enum constructor - cannot be public or protected
    private HttpResponseCodes(int value) {
        this.value = value;
    }

    private static Map map = new HashMap<>();


    static {
        for (HttpResponseCodes httpResponseCodes : HttpResponseCodes.values()) {
            map.put(httpResponseCodes.value, httpResponseCodes);
        }
    }

    public static HttpResponseCodes valueOf(int httpResponseCodes) {
        return (HttpResponseCodes) map.get(httpResponseCodes);
    }

    public int getValue() {
        return value;
    }
}

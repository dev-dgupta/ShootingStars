package com.shootingStar.enums;

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

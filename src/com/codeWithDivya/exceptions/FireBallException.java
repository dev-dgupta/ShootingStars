package com.codeWithDivya.exceptions;

public class FireBallException extends Exception {

    /**Fireball exception handling
     *
     */
    private static final long serialVersionUID = 1L;

    private final int errorCode;

    public FireBallException(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

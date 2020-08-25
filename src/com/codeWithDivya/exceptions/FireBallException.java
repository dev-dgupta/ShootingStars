package com.codeWithDivya.exceptions;

public class FireBallException extends Exception {

    /**Fireball exception handling
     *
     */
    private static final long serialVersionUID = 1L;

    private String errorCode="Unknown_Exception";

    public FireBallException(String message,String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

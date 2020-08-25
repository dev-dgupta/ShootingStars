package com.codeWithDivya.exceptions;

import com.codeWithDivya.utils.Constants.ErrorCodes;

public class FireBallException extends Exception {

    private static final long serialVersionUID = 1L;

    private ErrorCodes errorCode;

    public FireBallException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }
}

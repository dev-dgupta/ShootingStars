package com.codeWithDivya.exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CustomExceptionHandling {


    private static void processErrorCodes(FireBallException e) throws FireBallException {
        switch(e.getErrorCode()){
            case "BAD_FILE_TYPE":
                System.out.println("Bad File Type, notify user");
                throw e;
            case "FILE_NOT_FOUND_EXCEPTION":
                System.out.println("File Not Found, notify user");
                throw e;
            case "FILE_CLOSE_EXCEPTION":
                System.out.println("File Close failed, just log it.");
                break;
            default:
                System.out.println("Unknown exception occured, lets log it for further debugging."+e.getMessage());
                e.printStackTrace();
        }
    }

    private static void processFile(String file) throws FireBallException {
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new FireBallException(e.getMessage(),"FILE_NOT_FOUND_EXCEPTION");
        }finally{
            try {
                if(fis !=null)fis.close();
            } catch (IOException e) {
                throw new FireBallException(e.getMessage(),"FILE_CLOSE_EXCEPTION");
            }
        }
    }

}

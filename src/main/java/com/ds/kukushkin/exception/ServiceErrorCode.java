package com.ds.kukushkin.exception;

public enum ServiceErrorCode {

    ERROR_CODE_EMAIL_ALREADY_USE("Email is used already"),
    ERROR_CODE_NOT_FOUND("Not found"),
    ERROR_CODE_INCORRECT_ID("Incorrect id");

    final private String errorString;

    ServiceErrorCode (String errorString){
        this.errorString = errorString;
    }

    public String getErrorString(){
        return errorString;
    }
}

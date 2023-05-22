package com.ds.kukushkin.exception;

public class ServiceException extends Exception{

    final ServiceErrorCode SERVICE_ERROR_CODE;

    public ServiceException(ServiceErrorCode serviceErrorCode){
        this.SERVICE_ERROR_CODE = serviceErrorCode;
    }

}

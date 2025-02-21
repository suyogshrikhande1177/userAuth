package com.practice.userAuth.exceptions;

public class RecordNotFoundException extends RuntimeException{

    private static final long serialVersionUID =1L;

    public RecordNotFoundException(String exceptionMessage){
        super(exceptionMessage);
    }

}

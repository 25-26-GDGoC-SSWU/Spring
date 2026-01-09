package com.study.firstpractice.exception;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException{
    private final ErrorCode errorCode;

    public GeneralException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
}

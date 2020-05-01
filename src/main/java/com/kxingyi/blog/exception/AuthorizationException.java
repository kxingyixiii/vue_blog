package com.kxingyi.blog.exception;

import com.kxingyi.blog.enums.ResultEnum;

public class AuthorizationException extends BlogException {
    public AuthorizationException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public AuthorizationException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum, throwable);
    }

    public AuthorizationException(Integer errorCode, String msg) {
        super(errorCode, msg);
    }

    public AuthorizationException(String msg) {
        super(msg);
    }

    public AuthorizationException(Throwable throwable) {
        super(throwable);
    }

    public AuthorizationException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}

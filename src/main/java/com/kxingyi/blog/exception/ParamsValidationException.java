package com.kxingyi.blog.exception;

import com.kxingyi.blog.enums.ResultEnum;
/**
 *@QualifiedClassName:com.kxingyi.blog.exception.ParamsValidationException
 *@ClassName:参数校验异常
 *@Description:TODO
 *@Author:byliu
 *@Date:2020/4/21 16:02
 *@Paramter:
 *@Return:
*/
public class ParamsValidationException extends BlogException{
    public ParamsValidationException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public ParamsValidationException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum, throwable);
    }

    public ParamsValidationException(Integer errorCode, String msg) {
        super(errorCode, msg);
    }

    public ParamsValidationException(String msg) {
        super(msg);
    }

    public ParamsValidationException(Throwable throwable) {
        super(throwable);
    }

    public ParamsValidationException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}

package com.kxingyi.blog.exception;

import com.kxingyi.blog.enums.ResultEnum;
/**
 *@QualifiedClassName:com.kxingyi.blog.exception.DataAcessException
 *@ClassName:数据异常
 *@Description:TODO
 *@Author:byliu
 *@Date:2020/4/21 16:01
 *@Paramter:
 *@Return:
*/
public class DataAcessException extends BlogException {
    public DataAcessException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public DataAcessException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum, throwable);
    }

    public DataAcessException(Integer errorCode, String msg) {
        super(errorCode, msg);
    }

    public DataAcessException(String msg) {
        super(msg);
    }

    public DataAcessException(Throwable throwable) {
        super(throwable);
    }

    public DataAcessException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}

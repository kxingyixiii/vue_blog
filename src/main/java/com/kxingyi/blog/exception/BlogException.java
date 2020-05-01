package com.kxingyi.blog.exception;

import com.kxingyi.blog.enums.ResultEnum;

/**
 *@QualifiedClassName:
 *@ClassName:自定义异常
 *@Description:TODO
 *@Author:byliu
 *@Date:2020/4/20 22:10
 *@Paramter:
 *@Return:
*/
public class BlogException extends RuntimeException{
    private static final long serialVersionUID = 2450214686001409867L;

    private Integer errorCode = ResultEnum.ERROR.getCode();

    public BlogException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
    }

    public BlogException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum.getMsg(), throwable);
        this.errorCode = resultEnum.getCode();
    }

    public BlogException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BlogException(String msg) {
        super(msg);
    }

    public BlogException(Throwable throwable) {
        super(throwable);
    }

    public BlogException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public BlogException() {
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}

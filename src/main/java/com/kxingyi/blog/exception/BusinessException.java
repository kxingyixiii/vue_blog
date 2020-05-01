package com.kxingyi.blog.exception;

import com.kxingyi.blog.enums.ResultEnum;
/**
 *@QualifiedClassName:com.kxingyi.blog.exception.BusinessException
 *@ClassName:业务逻辑异常
 *@Description:TODO
 *@Author:byliu
 *@Date:2020/4/21 16:01
 *@Paramter:
 *@Return:
*/
public class BusinessException extends  BlogException {
    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public BusinessException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum, throwable);
    }

    public BusinessException(Integer errorCode, String msg) {
        super(errorCode, msg);
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}

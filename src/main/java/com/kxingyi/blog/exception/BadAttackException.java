package com.kxingyi.blog.exception;

import com.kxingyi.blog.enums.ResultEnum;
/**
 *@QualifiedClassName:com.kxingyi.blog.exception.BadAttackException
 *@ClassName:恶意攻击异常
 *@Description:TODO
 *@Author:byliu
 *@Date:2020/4/21 16:01
 *@Paramter:
 *@Return:
*/
public class BadAttackException extends  BlogException {
    public BadAttackException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public BadAttackException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum, throwable);
    }

    public BadAttackException(Integer errorCode, String msg) {
        super(errorCode, msg);
    }

    public BadAttackException(String msg) {
        super(msg);
    }

    public BadAttackException(Throwable throwable) {
        super(throwable);
    }

    public BadAttackException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}

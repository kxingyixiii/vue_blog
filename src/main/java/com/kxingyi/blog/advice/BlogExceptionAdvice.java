package com.kxingyi.blog.advice;

import com.kxingyi.blog.exception.BlogException;
import com.kxingyi.blog.utils.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@QualifiedClassName:com.kxingyi.blog.advice.BlogExceptionAdvice
 *@ClassName:定义全局异常处理
 *@Description:TODO
 *@Author:byliu
 *@Date:2020/4/20 22:15
 *@Paramter:
 *@Return:
*/
@ControllerAdvice
@Slf4j
public class BlogExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> exceptionHandler(Exception exception){
        log.error(exception.getMessage(), exception);
        if(exception instanceof BlogException) {
            return new Result<Object>(((BlogException) exception).getErrorCode(), exception.getMessage());
        }else{
            return new Result<Object>(500, exception.getMessage());
        }
    }
}
